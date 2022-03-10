package com.dilo.gmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dilo.gmall.model.product.BaseTrademark;
import com.dilo.gmall.model.product.SpuInfo;
import org.apache.ibatis.annotations.Mapper;

public interface BaseTrademarkService extends IService<BaseTrademark> {

    IPage<BaseTrademark> getBaseTrademarkController(Page<BaseTrademark> spuInfoPage);
}
