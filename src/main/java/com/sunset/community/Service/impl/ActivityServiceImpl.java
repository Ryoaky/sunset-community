package com.sunset.community.Service.impl;

import com.sunset.community.Mapper.ActivityMapper;
import com.sunset.community.Pojo.Activity;
import com.sunset.community.Service.ActivityService;
import com.sunset.community.Utils.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    /*
    * 发布活动功能
    *
    * 作用：活动表增加活动，并且关系库新增
    *
    * 返回值：true/false
    *
    * */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean postActi(long uid,Activity activity,MultipartFile picture,MultipartFile audio,HttpServletRequest req){
        try{
            activityMapper.addActi(activity);
            long aid = activityMapper.lastId();
            activityMapper.addPoster(uid,aid);
            //上传图片
            activityMapper.addImage(Upload.pic(picture),aid);
            //上传音频
            activityMapper.addAudio(Upload.audio(audio),aid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    //判断是否人数满
    public boolean isFull(long aid) {
        return activityMapper.getJoinerNums(aid) == activityMapper.getNeedNums(aid);
    }


    Integer joiner_num;
    @Override
    public List<HashMap<String,Object>> getNearActi(long uid,String location) {

        //创建活动列表
        List<HashMap<String,Object>>  actiMapList =  new ArrayList<HashMap<String,Object>>();;
        List<Activity> actiList = activityMapper.getActiListByLoc(location);

        //迭代每一个帮助对象，添加信息构造成哈希表并添加至活动列表中
        for (Activity acti : actiList) {
            String startTime = acti.getPostTime().toString().replace("T,"," ").substring(0,16);
            String endTime = acti.getEndTime().toString().replace("T,"," ").substring(0,16);
            HashMap<String,Object> actiMap = new HashMap<>();
            joiner_num = activityMapper.getJoinerNums(acti.getAid());
            actiMap.put("peopleNum",joiner_num);  //帮助id和对应参加人数
            actiMap.put("needNum",acti.getNeedNum());  //帮助id和对应参加人数
            actiMap.put("actiObject",acti);
            actiMap.put("isPoster",activityMapper.isPoster(uid,acti.getAid()));
            actiMap.put("hasJoined",activityMapper.hasJoined(uid,acti.getAid()));
            actiMap.put("pic",activityMapper.getActiPic(acti.getAid()));
            actiMap.put("audio",activityMapper.getActiAudio(acti.getAid()));
            actiMap.put("startTimeFormed",startTime);
            actiMap.put("endTimeFormed",endTime);
//            actiMap.put("picUrl",activityMapper.getPic(acti.getAid()));
            if(!actiMap.isEmpty())
                actiMapList.add(actiMap);
        }
        return actiMapList;
    }

//    //根据活动名搜索活动
//    public List<Activity> getActiByName(String name) {
//        return activityMapper.getActiListByName(name);
//    }
//
//    public String getAudioUrlByAid(int aid) {
//        return activityMapper.getAudioUrlByAid(aid);
//    }


    @Override
    public boolean operActi(long uid, long aid) {
        if(activityMapper.isPoster(uid,aid)!=0){
            activityMapper.deleteActi(aid);
            activityMapper.deleteActiPoster(aid);
        }
        else{
            if(activityMapper.hasJoined(uid,aid)==0){
                activityMapper.joinActi(uid,aid);
            }
            else {
                activityMapper.quitActi(uid,aid);
            }
        }
        return true;
    }

    @Override
    public List<HashMap<String,Object>> getActiByName(long uid,String name) {
        List<HashMap<String,Object>>  actiMapList =  new ArrayList<HashMap<String,Object>>();;
        List<Activity> actiList = activityMapper.getActiByName(name);
        for (Activity acti : actiList) {
            System.out.print(acti.getName());
            String startTime = acti.getPostTime().toString().replace("T,"," ").substring(0,16);
            String endTime = acti.getEndTime().toString().replace("T,"," ").substring(0,16);
            HashMap<String,Object> actiMap = new HashMap<>();
            joiner_num = activityMapper.getJoinerNums(acti.getAid());
            actiMap.put("peopleNum",joiner_num);  //帮助id和对应参加人数
            actiMap.put("needNum",acti.getNeedNum());  //帮助id和对应参加人数
            actiMap.put("actiObject",acti);
            actiMap.put("isPoster",activityMapper.isPoster(uid,acti.getAid()));
            actiMap.put("hasJoined",activityMapper.hasJoined(uid,acti.getAid()));
            actiMap.put("pic",activityMapper.getActiPic(acti.getAid()));
            actiMap.put("audio",activityMapper.getActiAudio(acti.getAid()));
            actiMap.put("startTimeFormed",startTime);
            actiMap.put("endTimeFormed",endTime);
//            actiMap.put("picUrl",activityMapper.getPic(acti.getAid()));
            if(!actiMap.isEmpty())
                actiMapList.add(actiMap);
        }
        return actiMapList;
    }
}
