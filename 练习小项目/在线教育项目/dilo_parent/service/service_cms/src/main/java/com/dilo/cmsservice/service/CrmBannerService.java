package com.dilo.cmsservice.service;

import com.dilo.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author dilo
 * @since 2022-02-19
 */
public interface CrmBannerService extends IService<CrmBanner> {
    List<CrmBanner> getAllBanner();

}
