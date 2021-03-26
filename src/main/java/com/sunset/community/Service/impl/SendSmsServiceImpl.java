/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/29
 * description:
 */
package com.sunset.community.Service.impl;

import com.sunset.community.Mapper.ActivityMapper;
import com.sunset.community.Mapper.UserMapper;
import com.sunset.community.Pojo.*;
import com.sunset.community.Service.SendSmsService;
import com.sunset.community.Utils.AccMsgUtil;
import com.sunset.community.Utils.ActMsgUtil;
import com.sunset.community.Utils.CodeMsgUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Random;


@Service
public class SendSmsServiceImpl implements SendSmsService {

    @Resource
    CodeMsgUtil codeMsgUtil;

    @Resource
    AccMsgUtil accMsgUtil;

    @Resource
    ActMsgUtil actMsgUtil;

    @Autowired
    UserMapper userMapper ;

    @Autowired
    ActivityMapper activityMapper;

//    HashMap<String,String> accMsg = new HashMap<>();
    User user;
    Activity activity;
    @Test
    @Override
    public void accSendSms(long uid) {  //void String phone
        try{
            user = userMapper.getMsgForAcc(uid);
            AccParams accParams = new AccParams((String)user.getName(),(String)user.getGuardianPhone());
            String str = accMsgUtil.sendSms(accParams);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Override
    public void actSendSms(long uid,long aid) {  //void String phone
        try{
            user = userMapper.getUserByUid(uid);
            activity = activityMapper.getActiByAid(aid);
            ActParams actParams = new ActParams((String)user.getName(),(String)activity.getName(),(String)user.getGuardianPhone());
            String str = actMsgUtil.sendSms(actParams);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Integer verifyCode;
    @Test
    @Override
    public Integer addSendSms(String phone) {  //void String phone
        // 生成随机的验证码
        try{
//            Random random = new Random(4);
//            verifyCode = random.nextInt(1000000);
            verifyCode = (int)((Math.random()*9+1)*1000);
//        SmsParams smsParams = new SmsParams("15602238362",verifyCode.toString());
            SmsParams smsParams = new SmsParams(phone,verifyCode.toString());
//            System.out.println("生成的验证码为：" + verifyCode);
            // smsParams.setPhone("17098705205").setVerifyCode();
            String str = codeMsgUtil.sendSms(smsParams);
            System.out.println(str);
            return verifyCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean isCode(Integer codeCin,Integer code){
        if(code.equals(codeCin))
            return true;
        else
            return false;
    }
}