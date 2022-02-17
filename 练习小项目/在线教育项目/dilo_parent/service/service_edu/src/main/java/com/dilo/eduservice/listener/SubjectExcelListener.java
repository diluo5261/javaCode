package com.dilo.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dilo.baseservice.handler.DiloException;
import com.dilo.eduservice.entity.EduSubject;
import com.dilo.eduservice.entity.vo.ExcelSubjectData;
import com.dilo.eduservice.service.EduSubjectService;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {
    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    //创建有参数构造，传递subjectService用于操作数据库
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        //1.读取数据验空
        if (excelSubjectData == null) {
            throw new DiloException(20001, "导入课程失败!");
        }

        //2.判断1级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectService, excelSubjectData.getOneSubjectName());

        //3.一级不重复插入数据库
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(excelSubjectData.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }

        String pid = existOneSubject.getId();
        //4.判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, excelSubjectData.getTwoSubjectName(), pid);

        //5.二级分类不重复插入数据库
        if (existTwoSubject == null) {

            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(excelSubjectData.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            subjectService.save(existTwoSubject);
        }
    }



    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0).eq("title", name);
        EduSubject eduSubject = subjectService.getOne(queryWrapper);
        return eduSubject;
    }

    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", pid).eq("title", name);
        EduSubject eduSubject = subjectService.getOne(queryWrapper);
        return eduSubject;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
