package com.dilo.orderservice.service.impl;

import com.dilo.baseservice.handler.DiloException;
import com.dilo.commonutils.ResultCode;
import com.dilo.commonutils.vo.CourseWebVoForOrder;
import com.dilo.commonutils.vo.UcenterMemberForOrder;
import com.dilo.orderservice.client.EduClient;
import com.dilo.orderservice.client.UcenterClient;
import com.dilo.orderservice.entity.TOrder;
import com.dilo.orderservice.mapper.TOrderMapper;
import com.dilo.orderservice.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dilo.orderservice.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author dilo
 * @since 2022-02-23
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        //1.跨模块获取课程信息
        CourseWebVoForOrder courseInfoForOrder = eduClient.getCourseInfoForOrder(courseId);
        //1.1 进行校验,是否成功取得数据
        if (courseInfoForOrder == null){
            throw new DiloException(ResultCode.ERROR,"获取课程信息失败!");
        }

        //2.跨模块获取用户信息
        UcenterMemberForOrder uncenterForOrder = ucenterClient.getUncenterForOrder(memberId);

        //2.1进行校验
        if (uncenterForOrder == null){
            throw new DiloException(ResultCode.ERROR,"获取用户信息失败!");
        }

        //3.生成订单编号
        String orderNo = OrderNoUtil.getOrderNo();

        //4.封存数据,存入数据库
        TOrder order = new TOrder();

        order.setOrderNo(orderNo);
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoForOrder.getTitle());
        order.setCourseCover(courseInfoForOrder.getCover());
        order.setTeacherName(courseInfoForOrder.getTeacherName());
        order.setTotalFee(courseInfoForOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(uncenterForOrder.getMobile());
        order.setNickname(uncenterForOrder.getNickname());
        order.setStatus(0);//未支付
        order.setPayType(1);//1：微信

        baseMapper.insert(order);

        return orderNo;
    }
}
