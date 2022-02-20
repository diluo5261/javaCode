package com.dilo.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dilo.baseservice.handler.DiloException;
import com.dilo.commonutils.ResultCode;
import com.dilo.commonutils.utils.JwtUtils;
import com.dilo.ucenterservice.entity.UcenterMember;
import com.dilo.ucenterservice.entity.vo.LoginVo;
import com.dilo.ucenterservice.entity.vo.RegisterVo;
import com.dilo.ucenterservice.mapper.UcenterMemberMapper;
import com.dilo.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dilo.ucenterservice.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author dilo
 * @since 2022-02-20
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void register(RegisterVo registerVo) {
        //1.获取参数,验空
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        if (StringUtils.isEmpty(nickname) ||
                StringUtils.isEmpty(mobile)||
                StringUtils.isEmpty(password)||
                StringUtils.isEmpty(code)
        ){
            throw new DiloException(ResultCode.ERROR,"注册信心缺失");
        }

        //2.验证手机号是否重复
        QueryWrapper<UcenterMember> memberWrapper = new QueryWrapper<>();
        memberWrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(memberWrapper);
        if (count >0){
            throw  new DiloException(ResultCode.ERROR,"手机号重复");
        }

        //3.验证短信验证码
        String resultCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(resultCode)){
            throw  new DiloException(ResultCode.ERROR,"验证码错误");
        }

        //4.使用MD5加密密码
        String md5Password = MD5.encrypt(password);

        //5.补充信息后插入数据库
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setNickname(nickname);
        ucenterMember.setMobile(mobile);
        ucenterMember.setPassword(md5Password);
        ucenterMember.setAvatar("https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
        ucenterMember.setIsDisabled(false);

        baseMapper.insert(ucenterMember);
    }

    //用户登录
    @Override
    public String login(LoginVo loginVo) {

        //1.判断参数,验空
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        if (mobile == null || password == null){
            throw new DiloException(ResultCode.ERROR,"手机或密码有误");
        }

        //2.根据手机号获取信息
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(queryWrapper);

        //3.密码加密验证密码

        String md5Password = MD5.encrypt(password);
        if (!md5Password.equals(ucenterMember.getPassword())){
            throw new DiloException(ResultCode.ERROR,"手机或密码有误");
        }

        //4.生成token字符串
        String token = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());


        return token;
    }
}
