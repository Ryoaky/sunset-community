/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/2/28
 * description: 活动结束扫描，提醒监护人短信发送
 */
package com.sunset.community.Schedule;

import com.sunset.community.Mapper.ActivityMapper;
import com.sunset.community.Mapper.HelpMapper;
import com.sunset.community.Pojo.Activity;
import com.sunset.community.Pojo.User;
import com.sunset.community.Service.SendSmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class SchedulerTask {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerTask.class);


    @Autowired
    ActivityMapper activityMapper;

    @Resource
    private SendSmsService smsService;


    @Scheduled(cron = "0 0 0/1 * * ?")
    public void actTimer() throws ParseException {
        logger.info("=====>>>>>对活动时间进行扫描");

        //活动当前系统时间并格式化
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        //当前时间的年月日
        SimpleDateFormat df = new SimpleDateFormat("yyy--MM-dd HH:mm:ss");
        String year = df.format(nowTime).substring(0,4);
        logger.info(year);
        String month = df.format(nowTime).substring(6,8);
        logger.info(month);
        String day = df.format(nowTime).substring(9,11);
        logger.info(day);

        //首先找出系统 当天 且 活动状态为已开始的活动
        List<Activity> actiList = activityMapper.selectNowAct(year,month,day);
        //对比系统时间，若超过则修改活动状态 并 发送活动结束提醒短信
        for(Activity acti : actiList){
            logger.info(acti.getName());
            if(acti.getEndTime().compareTo(nowTime)<0){
                //要给每一个参与者（包括发布者）的监护人发送信息

                //首先获取用户的列表
                List<User> userList = activityMapper.getJoiners(acti.getAid());
                userList.add(activityMapper.getPoster(acti.getAid()));
                //迭代待发送用户的列表
                for(User user: userList){
                    smsService.actSendSms(user.getUid(),acti.getAid());
                }
                activityMapper.updateState(acti.getAid());
            };
        }
    }

}