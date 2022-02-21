import request from '@/utils/request'

export default{
    //用户注册
    register(registerVo){
        return request({
            url: `/ucenterservice/member/register`,
            method: 'post',
            data:registerVo
          })
    },
    //短信发送
    sendSmsPhone(phone){
        return request({
            url: `/edusms/sms/sendSmsPhone/${phone}`,
            method: 'get'
          })

    }
}