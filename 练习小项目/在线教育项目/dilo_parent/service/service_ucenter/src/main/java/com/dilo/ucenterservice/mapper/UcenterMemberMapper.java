package com.dilo.ucenterservice.mapper;

import com.dilo.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author dilo
 * @since 2022-02-20
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    int countRegister(String day);
}
