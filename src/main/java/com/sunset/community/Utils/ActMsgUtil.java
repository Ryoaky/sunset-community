/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/28
 * description: 活动结束提醒短信
 */
package com.sunset.community.Utils;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.sunset.community.Pojo.AccParams;
import com.sunset.community.Pojo.ActParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ActMsgUtil {

    // 短信应用 SDK AppID
    @Value("${tx3.act.appId}")
    int appId; // 1400开头

    // 短信应用SDK AppKey
    @Value("${tx3.act.appKey}")
    String appKey;

    // 短信模板ID，需要在短信应用中申请
    @Value("${tx3.act.templateId}")
    int templateId ; // NOTE: 真实的模板ID需要在短信控制台中申请
    //我这里 templateId 对应的内容是"您的验证码是: {1}"
    // 签名
    @Value("${tx3.act.smsSign}")
    String accSign ; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台申请

    @Value("${tx3.act.smsEffectiveTime}")
    String accEffectiveTime ;

    /**
     * 指定模板 ID 单发短信
     * @param actParams
     */
    public String sendSms(ActParams actParams) {
        String rep = "error";
        System.out.println(appId);
        System.out.println(appKey);
        System.out.println(templateId);
        System.out.println(accSign);
        System.out.println(accEffectiveTime);
        try {
            String name = actParams.getName();
            String aname = actParams.getAname();
            // 数组具体的元素个数和模板中变量个数必须一致，例如示例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            String[] params = {name,aname};
            SmsSingleSender actSingleSender = new SmsSingleSender(appId, appKey);
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsSingleSenderResult actSingleSenderResult = actSingleSender.sendWithParam("86", actParams.getPhone(),
                    templateId, params, accSign, "", "");
            System.out.println(actSingleSenderResult);
            // 如果返回码不是0，说明配置有错，返回错误信息
            if (actSingleSenderResult.result == 0) {
                rep = "success";
            } else {
                rep = actSingleSenderResult.errMsg;
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
