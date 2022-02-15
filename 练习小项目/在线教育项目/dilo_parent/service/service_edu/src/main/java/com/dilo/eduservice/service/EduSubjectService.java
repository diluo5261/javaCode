package com.dilo.eduservice.service;

import com.dilo.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dilo.eduservice.entity.vo.OneSubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author dilo
 * @since 2022-02-15
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubjectVo> getAllSubject();
}
