package com.dilo.controller;

import com.dilo.pojo.ProductInfo;
import com.dilo.service.ProductInfoService;
import com.dilo.utils.FileNameUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //每页显示的记录数
    public static final int PAGE_SIZE = 5;

    //异步上传的图片名称
    String saveFileName= "";


    //切记:在界面层中,一定会有业务逻辑层的对象
    @Autowired
    private ProductInfoService productInfoService;

    //显示全部商品不分页
    @RequestMapping("/getAll")
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<ProductInfo> list = productInfoService.getAll();

        modelAndView.addObject("list",list);
        modelAndView.setViewName("product");

        return modelAndView;
    }

    //获取第一页的5条数据

    @RequestMapping("/split")
    public ModelAndView split(){
        ModelAndView modelAndView = new ModelAndView();

        //得到第一页的数据
        PageInfo info = productInfoService.splitPage(1,PAGE_SIZE);
        modelAndView.addObject("info",info);
        modelAndView.setViewName("product");
        return modelAndView;
    }

    //ajax分页翻页处理
    @RequestMapping("/ajaxSplit")
    @ResponseBody
    public void ajaxSplite(int page, HttpSession session){
        //取得当前page 参数页面的数据
        PageInfo info = productInfoService.splitPage(page,PAGE_SIZE);
        session.setAttribute("info",info);
    }

    //异步ajax文件上传处理
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request){
        //1.提起生成文件名UUID + 上传图片的后缀 .jpg .png
        saveFileName = FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        //2.得到项目中图片存储的路径
        String path = request.getServletContext().getRealPath("/image_big");
        //3.转存
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);
        return object.toString();

    }

    @RequestMapping("/save")
    public ModelAndView save(ProductInfo info,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        info.setpImage(saveFileName);
        info.setpDate(new Date());

        //info对象中有表单提交上来的5个数据,有异步ajax上来的图片名称数据,有上架时间的数据
        int num = -1;
        try{
            num = productInfoService.save(info);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        if (num > 0){
            modelAndView.addObject("msg","增加成功!");
        }else{
            modelAndView.addObject("msg","增加失败!");
        }

        //增加成功后,应该重新访问数据库,所以跳帧分页显示的action
        modelAndView.setViewName("forward:/prod/split.action");

        //清空 saveFileName变量中的内容,wile下次增加或修改的异步ajax上床处理
        saveFileName = "";

        return modelAndView;
    }

    @RequestMapping("/one")
    public ModelAndView one(int pid){
        ModelAndView modelAndView = new ModelAndView();
        ProductInfo info = productInfoService.getById(pid);
        modelAndView.addObject("prod",info);
        modelAndView.setViewName("update");

        return modelAndView;
    }

    @RequestMapping("/update")
    public ModelAndView update(ProductInfo info){
        ModelAndView modelAndView = new ModelAndView();
        //因为ajax的异步上传,如果上传过,则saveFileName里有上传来的图片名称
        //如果没有使用异步ajax上传过图片,则saveFileName = "";
        //实体类info使用隐藏表单域提供上来的原始图片的名称;

        if (!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }

        //完成更新操作
        int num = -1;
        num = productInfoService.update(info);

        if (num > 0){
            //说明更新成功
            modelAndView.addObject("msg","更新成功!");
        }else{
            modelAndView.addObject("msg","更新失败!");
        }

        //处理完更新后,saveFileNAme里可能有数据,而下一次更新时要使用这个变量作为判断的依据,就会出错,所以必须清空 saveFileName
        saveFileName = "";

        modelAndView.setViewName("forward:/prod/split.action");
        return modelAndView;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(int pid){
        ModelAndView modelAndView = new ModelAndView();

        int num = -1;
        try{
            num = productInfoService.delete(pid);
        }catch(Exception e){
            e.printStackTrace();
        }

        if (num >0){
            modelAndView.addObject("msg","删除成功!");
        }else{
            modelAndView.addObject("msg","地喊出失败");
        }

        //删除结束后跳到分页显示
        modelAndView.setViewName("forward:/prod/deleteAjaxSplit.action");

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit",produces = "text/html;charset=UTF-8")
    public String deleteAjaxSplit(HttpServletRequest request){
        //取得第一页的数据
        PageInfo info = productInfoService.splitPage(1,PAGE_SIZE);
        request.getSession().setAttribute("info",info);

        return request.getAttribute("msg").toString();

    }





}
