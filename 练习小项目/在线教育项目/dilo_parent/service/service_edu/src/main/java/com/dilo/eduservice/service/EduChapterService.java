package com.dilo.eduservice.service;

import com.dilo.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dilo.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author dilo
 * @since 2022-02-16
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoById(String courseId);
}
