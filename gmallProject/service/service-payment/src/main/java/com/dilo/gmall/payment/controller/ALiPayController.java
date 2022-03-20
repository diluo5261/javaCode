package com.dilo.gmall.payment.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.model.enums.PaymentType;
import com.dilo.gmall.model.payment.PaymentInfo;
import com.dilo.gmall.payment.config.AlipayConfig;
import com.dilo.gmall.payment.service.AlipayService;
import com.dilo.gmall.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.alipay.api.AlipayConstants.SIGN_TYPE;

@Controller
@RequestMapping("/api/payment/alipay")
public class ALiPayController {

    @Autowired
    private AlipayService alipayService;
    @Autowired
    private PaymentService paymentService;


    @RequestMapping("submit/{orderId}")
    @ResponseBody
    /*
        1.两个作用:返回数据是json,
        2.所用二:直接将数据输出到页面

     */
    public String submitOrder(@PathVariable Long orderId){
        String aliPay = alipayService.createAliPay(orderId);
        return aliPay;
    }

//    同步回调:http://api.gmall.com/api/payment/alipay/callback/return
    @GetMapping("callback/return")
    public String callbackReturn(){
        //重定向到:return_order_url,/pay/success.html
        return  "redirect"+ AlipayConfig.return_order_url;
    }

    //http://cw78a2.natappfree.cc/api/payment/alipay/callback/notify
    //启动内网穿透工具,每打开一次,地址就改变一次
    /**
     * 异步回调方法,必须使用内网穿透
     */
    @PostMapping("callback/notify")
    @ResponseBody
    public String callbackNotify(@RequestParam Map<String,String> paramsMap) throws AlipayApiException {


        boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.alipay_public_key,AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            //通过key获取对应的数据,校验参数是否正确
            String outTradeNo = paramsMap.get("out_trade_no");
            PaymentInfo paymentInfo = paymentService.getPaymentInfo(outTradeNo, PaymentType.ALIPAY.name());
            if (paymentInfo == null){
                return  "failure";
            }

            //验证总金额
            String totalAmount = paramsMap.get("total_amount");
            if (paymentInfo.getTotalAmount().equals(totalAmount)){
                return  "failure";
            }

            String status = paramsMap.get("trade_status");

            if ("TRADE_SUCCESS".equals(status) || "TRADE_FINISHED".equals(status) ){
                //细节:防止万一,当交易状态是交易完成,或者交易结束时,支付的状态是CLOSED或者是PAID,则返回failure
                if ("PAID".equals(paymentInfo.getPaymentStatus()) || "CLOSED".equals(paymentInfo.getPaymentStatus())){
                    return  "failure";
                }
                //更新交易记录
                paymentService.paySuccess(outTradeNo,PaymentType.ALIPAY.name(),paramsMap);

                return "success";
            }
        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
            return  "failure";
        }
        return  "failure";
    }

    @GetMapping("refund/{orderId}")
    @ResponseBody
    public Result refund(@PathVariable Long orderId){
        //调用退款接口
        boolean flag = alipayService.refund(orderId);
        return Result.ok(flag);

    }

    // 根据订单Id关闭订单
    @GetMapping("closePay/{orderId}")
    @ResponseBody
    public boolean closePay(@PathVariable Long orderId){
        boolean falg = alipayService.closePay(orderId);
        return falg;
    }

    // 查看是否有交易记录
    @RequestMapping("checkPayment/{orderId}")
    @ResponseBody
    public Boolean checkPayment(@PathVariable Long orderId){
        // 调用退款接口
        boolean flag = alipayService.checkPayment(orderId);
        return flag;
    }

    //查询本地是否有交易记录
    @GetMapping("getPaymentInfo/{outTradeNo}")
    @ResponseBody
    public PaymentInfo getPaymentInfo(@PathVariable String outTradeNo){
        PaymentInfo paymentInfo = paymentService.getPaymentInfo(outTradeNo, PaymentType.ALIPAY.name());
        if (null!=paymentInfo){
            return paymentInfo;
        }
        return null;
    }


}
