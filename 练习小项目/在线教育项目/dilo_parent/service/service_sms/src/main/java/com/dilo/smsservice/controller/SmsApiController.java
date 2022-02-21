package com.dilo.smsservice.controller;

import com.dilo.commonutils.R;
import com.dilo.smsservice.service.SmsService;
import com.dilo.smsservice.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Api(description="短信发送")
@RestController
@RequestMapping("/edusms/sms")
@CrossOrigin
public class SmsApiController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @ApiOperation("短信发送")
    @GetMapping("sendSmsPhone/{phone}")
    public R sendSmsPhone(@PathVariable String phone){
        //1.拿手机号到redis中查询以前是否发送过验证码
        String rPhone = redisTemplate.opsForValue().get(phone);

        //2.验证验证码是否存在,存在直接返回
        if (rPhone != null){
            return R.ok().message("验证码已存在");
        }

        //3.验证码不存在,生成验证码
        String code = RandomUtil.getFourBitRandom();

        //4.调用接口服务,发送短信,
        HashMap<String, String> map = new HashMap<>();
        map.put("code",code);
        System.out.println("验证码为:"+code);
        boolean isSend = smsService.sendSmsPhone(phone,map);

        //5.发送成功,验证码存入redis,时效5分钟
        if (isSend){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }else{
            return R.error();
        }


    }

}
