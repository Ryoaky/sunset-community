/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/2/6
 * description: 预警短信发送控制器
 */
package com.sunset.community.Controller;

import com.sunset.community.Service.SendSmsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AccidentMsgController {

    @Resource
    private SendSmsService smsService;


    //用于向监护人发送老人跌倒预警
    @RequestMapping(value="/alarm.do", method= RequestMethod.POST)
    @ResponseBody
    public boolean accidentMsg(HttpServletRequest request){
        long uid = (long)request.getSession().getAttribute("uid");
        smsService.accSendSms(uid);
        return true;
    }

}
