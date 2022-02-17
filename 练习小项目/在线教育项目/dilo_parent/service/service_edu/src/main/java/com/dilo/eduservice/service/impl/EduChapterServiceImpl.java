package com.dilo.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dilo.eduservice.entity.EduChapter;
import com.dilo.eduservice.entity.EduVideo;
import com.dilo.eduservice.entity.vo.ChapterVo;
import com.dilo.eduservice.entity.vo.VideoVo;
import com.dilo.eduservice.mapper.EduChapterMapper;
import com.dilo.eduservice.mapper.EduVideoMapper;
import com.dilo.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author dilo
 * @since 2022-02-16
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduChapterMapper chapterMapper;

    @Autowired
    private EduVideoMapper videoMapper;

    //根据课程id查询章节，小节信息
    @Override
    public List<ChapterVo> getChapterVideoById(String courseId) {

        //1.根据courseId查询章节集合信息
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<EduChapter> chapterList = chapterMapper.selectList(chapterQueryWrapper);

        //2.根据courseId查询小节集合信息
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        List<EduVideo> videoList = videoMapper.selectList(videoQueryWrapper);

        //3.遍历章节信息进行封装
        List<ChapterVo> chapterVideoList = new ArrayList<>();

        for (EduChapter eduChapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            chapterVideoList.add(chapterVo);

            //4.遍历小结信息进行封装
            List<VideoVo> videoVos = new ArrayList<>();
            for (EduVideo eduVideo : videoList) {
                if (eduChapter.getId().equals(eduVideo.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVos);
        }
        return chapterVideoList;
    }
}
