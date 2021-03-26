/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/24
 * description:
 */
package com.sunset.community.Service.impl;

import com.sunset.community.Mapper.ActivityMapper;
import com.sunset.community.Mapper.HelpMapper;
import com.sunset.community.Mapper.UserMapper;
import com.sunset.community.Pojo.User;
import com.sunset.community.Service.Login_Regi_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


//@ServiceExcepCatch
@Service
public class Login_Regi_ServiceImpl implements Login_Regi_Service {

    @Autowired
    UserMapper userMapper ;

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    HelpMapper helpMapper;


    @Override
    public boolean addUser(User user) {
        if(userMapper.addUser(user)){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean isUser(String phone) {
        if(userMapper.getUserByPhone(phone) != null ){
            return true;
        }
        else
            return false;
    }

    @Override
    public User getUser(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = -1,readOnly = false,rollbackFor ={Exception.class})
    public HashMap<String, Object> getUserMsg(long uid) {

        HashMap<String,Object> rsMap = new HashMap<>();

        User user = userMapper.getUserByUid(uid);
        rsMap.put("user",user); //用户的信息

//        List<Activity> ActiList1 = activityMapper.getPostActi(uid);
//        List<Activity> ActiList2 = activityMapper.getJoinActi(uid);
//        List<Help> HelpList1 = helpMapper.getPostHelp(uid);
//        List<Help> HelpList2 = helpMapper.getJoinHelp(uid);
//        System.out.print(HelpList2.get(0).getHid());
//
//        rsMap.put("ActiPoster",ActiList1);   //发布的活动
//        rsMap.put("ActiJoiner",ActiList2);  //参加的活动
//        rsMap.put("HelpPoster",HelpList1);
//        rsMap.put("HelpJoiner",HelpList2);

        return rsMap;
    }

    @Override
    public void setLocation(Long uid,String lat, String ing) {
        userMapper.setLocation(uid,lat,ing);
    }

    @Override
    public HashMap<String, Object> getPersonalMsg(long uid) {
        HashMap<String,Object> rsMap = new HashMap<>();

        User user = userMapper.getUserByUid(uid);
        rsMap.put("user",user); //用户的信息

        return rsMap;
    }

    //更新用户信息
    @Override
    public boolean updatePersonalMsg(User user) {
        return userMapper.updateUsrMsg(user);
    }

    @Override
    public HashMap<String, Object> getUsrHistory(long uid) {
        SimpleDateFormat df = new SimpleDateFormat("yyy--MM-dd HH:mm:ss");
        Date regiDate = userMapper.getRegiYear(uid);
        Integer regiYear = Integer.valueOf(df.format(regiDate).substring(0,4));

        Calendar date = Calendar.getInstance();
        Integer year = date.get(Calendar.YEAR);
        HashMap<String,Object> histMap = new HashMap<>();
        histMap.put("uid",uid);
        histMap.put("userName",userMapper.getUserName(uid));
        histMap.put("regiYear",regiYear);
        histMap.put("year",year);
        histMap.put("activityJoined",userMapper.getActiHistory(uid,String.valueOf(year)));
        histMap.put("activityPosted",userMapper.getActiHistory2(uid,String.valueOf(year)));
        histMap.put("helpJoined",userMapper.getHelpHistory(uid,String.valueOf(year)));
        histMap.put("helpPosted",userMapper.getHelpHistory2(uid,String.valueOf(year)));
        return histMap;
    }
}
