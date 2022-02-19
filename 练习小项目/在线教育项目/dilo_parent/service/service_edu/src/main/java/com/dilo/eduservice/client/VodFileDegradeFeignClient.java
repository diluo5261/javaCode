package com.dilo.eduservice.client;

import com.dilo.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R delVideo(String videoId) {
        return R.error().message("单个删除失败");
    }

    @Override
    public R delVideoList(List<String> videoList) {
        return R.error().message("多个删除失败");
    }
}
