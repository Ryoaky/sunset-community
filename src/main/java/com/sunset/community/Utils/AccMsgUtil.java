/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/28
 * description: 预警短信发送工具类
 */
package com.sunset.community.Utils;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.sunset.community.Pojo.AccParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccMsgUtil {

    // 短信应用 SDK AppID
    @Value("${tx2.acc.appId}")
    int appId; // 1400开头

    // 短信应用SDK AppKey
    @Value("${tx2.acc.appKey}")
    String appKey;

    // 短信模板ID，需要在短信应用中申请
    @Value("${tx2.acc.templateId}")
    int templateId ; // NOTE: 真实的模板ID需要在短信控制台中申请
    //我这里 templateId 对应的内容是"您的验证码是: {1}"
    // 签名
    @Value("${tx2.acc.smsSign}")
    String accSign ; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台申请

    @Value("${tx2.acc.smsEffectiveTime}")
    String accEffectiveTime ;

    /**
     * 指定模板 ID 单发短信
     * @param accParams
     */
    public String sendSms(AccParams accParams) {
        String rep = "error";
        System.out.println(appId);
        System.out.println(appKey);
        System.out.println(templateId);
        System.out.println(accSign);
        System.out.println(accEffectiveTime);
        try {
            String name = accParams.getName();
            String phone = accParams.getPhone();
            // 数组具体的元素个数和模板中变量个数必须一致，例如示例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            String[] params = {name,phone};
            SmsSingleSender accSingleSender = new SmsSingleSender(appId, appKey);
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsSingleSenderResult accSingleSenderResult = accSingleSender.sendWithParam("86", accParams.getPhone(),
                    templateId, params, accSign, "", "");
            System.out.println(accSingleSenderResult);
            // 如果返回码不是0，说明配置有错，返回错误信息
            if (accSingleSenderResult.result == 0) {
                rep = "success";
            } else {
                rep = accSingleSenderResult.errMsg;
            }
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return rep;
    }

}
