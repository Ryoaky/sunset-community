/**
 * description: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/25
 * description:
 */
package com.sunset.community.Service;

import java.util.Map;

//接口 传入参数为电话号码，模板编号，验证码
public interface SendSmsService {

    //发送短信并返回验证码等待校验
    Integer addSendSms(String phone);

    //获得输入的验证码并校验
    boolean isCode(Integer codeCin,Integer code);

    //发生预警短信
    void accSendSms(long uid);

    //发生活动结束提醒短信
    void actSendSms(long uid,long aid);
}
