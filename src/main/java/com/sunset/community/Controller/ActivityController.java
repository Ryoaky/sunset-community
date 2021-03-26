/**
 * title: 夕阳红小舍
 * author: SiHao Liu
 * date:2021/3/4
 * description: 活动广场控制器
 */

package com.sunset.community.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunset.community.Pojo.*;
import com.sunset.community.Service.ActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/square")
public class ActivityController {

    @Resource
    ActivityService activityService;


    @RequestMapping(value="/postActi.do", method= RequestMethod.POST)
    @ResponseBody
    public boolean postActi(MultipartFile picture, MultipartFile audio, HttpServletRequest req, String location, HttpSession httpSession) {

        long uid = (long)req.getSession().getAttribute("uid");
        try {
            //表单数据
            String name = req.getParameter("name");
            String detailLoc = req.getParameter("detailLoc");
            String needNum = req.getParameter("needNum");
            String startTime = req.getParameter("startTime");
            startTime = startTime.replace("T"," ").concat(":00");
            String endTime = req.getParameter("endTime");
            endTime = endTime.replace("T"," ").concat(":00");
            String intro = req.getParameter("intro");
            String type = req.getParameter("type");

            //构建活动对象
            Activity activity = new Activity( name,Integer.parseInt(needNum),
                    Timestamp.valueOf(startTime), Timestamp.valueOf(endTime),
                    intro,false,location,type,detailLoc);

            //上传活动对象和图片
            boolean b = activityService.postActi(uid,activity,picture,audio,req);
            if (b) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    //对活动的操作
    @RequestMapping(value="/operActi.do", method= RequestMethod.POST)
    @ResponseBody
    public boolean operActi(long aid,HttpServletRequest request) {
        long uid = (long)request.getSession().getAttribute("uid");
//        long uid = (long)httpSession.getAttribute("uid");
        return activityService.operActi(uid,aid);
    }


    //查找附近的活动
    @RequestMapping(value="/nearActi.do", method= RequestMethod.POST)
    @ResponseBody
    public List<HashMap<String,Object>> getNearActi(String location,HttpServletRequest request) {
        long uid = (long)request.getSession().getAttribute("uid");
//        long uid = (long)httpSession.getAttribute("uid");
        return activityService.getNearActi(uid,location);
    }

}
