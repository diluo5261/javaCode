package com.dilo.cmsservice.service.impl;

import com.dilo.cmsservice.entity.CrmBanner;
import com.dilo.cmsservice.mapper.CrmBannerMapper;
import com.dilo.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author dilo
 * @since 2022-02-19
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Autowired
    private CrmBannerMapper bannerMapper;

    @Cacheable(value = "banner",key = "selectIndexList")
    @Override
    public List<CrmBanner> getAllBanner() {
        List<CrmBanner> bannerList = bannerMapper.selectList(null);
        return bannerList;
    }
}
