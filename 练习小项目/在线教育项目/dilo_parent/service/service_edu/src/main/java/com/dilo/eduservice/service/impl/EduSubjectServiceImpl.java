package com.dilo.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dilo.baseservice.handler.DiloException;
import com.dilo.eduservice.entity.EduSubject;
import com.dilo.eduservice.entity.vo.ExcelSubjectData;
import com.dilo.eduservice.entity.vo.OneSubjectVo;
import com.dilo.eduservice.entity.vo.TwoSubjectVo;
import com.dilo.eduservice.listener.SubjectExcelListener;
import com.dilo.eduservice.mapper.EduSubjectMapper;
import com.dilo.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author dilo
 * @since 2022-02-15
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    //导入课程方法
    @Override
    public void addSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream,ExcelSubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DiloException(20001,"导入课程失败!");
        }
    }

    //查询所有课程分类方法
    @Override
    public List<OneSubjectVo> getAllSubject() {
        //1.查询所有的一级方法
        QueryWrapper<EduSubject> oneWrapper = new QueryWrapper<>();
        oneWrapper.eq("parent_id",0);
        List<EduSubject> oneSubjectList = baseMapper.selectList(oneWrapper);

        //2.查询所有的二级方法
        QueryWrapper<EduSubject> twoWrapper = new QueryWrapper<>();
        twoWrapper.ne("parent_id",0);
        List<EduSubject> twoSubjectList = baseMapper.selectList(twoWrapper);

        //3.封装一类方法
        ArrayList<OneSubjectVo> allSubjectList = new ArrayList<>();

        for (int i = 0; i < oneSubjectList.size(); i++) {
            //3.1.取出每个一级分类
            EduSubject oneSubject = oneSubjectList.get(i);

            //3.2.EduSubject转化OneSubjectVo
            OneSubjectVo oneSubjectVo = new OneSubjectVo();
            BeanUtils.copyProperties(oneSubject,oneSubjectVo);
            //

            //4.找到和一类有关的二类进行封装
            ArrayList<TwoSubjectVo> twoSubjectVos = new ArrayList<>();
            for (int m = 0; m < twoSubjectList.size(); m++) {
                //4.1取出每一个二级份分类
                EduSubject twoSubject = twoSubjectList.get(m);

                //4.2判断是否归属此一级
                if (twoSubject.getParentId().equals(oneSubject.getId())){

                    TwoSubjectVo twoSubjectVo = new TwoSubjectVo();
                    //4.3eduSubject转化twoSubject
                    BeanUtils.copyProperties(twoSubject,twoSubjectVo);
                    twoSubjectVos.add(twoSubjectVo);
                }
            }
            oneSubjectVo.setChildren(twoSubjectVos);
            allSubjectList.add(oneSubjectVo);
        }
        return allSubjectList;
    }
}
