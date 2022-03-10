package com.dilo.gmall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dilo.gmall.model.product.BaseTrademark;
import com.dilo.gmall.product.mapper.BaseTrademarkMapper;
import com.dilo.gmall.product.service.BaseTrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseTrademarkMapperImpl extends ServiceImpl<BaseTrademarkMapper, BaseTrademark> implements BaseTrademarkService {

    @Autowired
    private BaseTrademarkMapper trademarkMapper;

    @Override
    public IPage<BaseTrademark> getBaseTrademarkController(Page<BaseTrademark> spuInfoPage) {
        IPage<BaseTrademark> iPage = trademarkMapper.selectPage(spuInfoPage, null);
        return iPage;
    }
}
