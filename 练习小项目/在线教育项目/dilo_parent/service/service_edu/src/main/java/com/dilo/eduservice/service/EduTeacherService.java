package com.dilo.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dilo.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author dilo
 * @since 2022-02-11
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherApiPage(Page<EduTeacher> page);
}
