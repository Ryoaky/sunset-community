/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/23
 * description: 广场控制器
 */
package com.sunset.community.Controller;

import com.sunset.community.Pojo.User;
import com.sunset.community.Service.*;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class SquareController {

    @Resource
    Login_Regi_Service loginRegisterService;

    @Resource
    HelpService helpService;

    @Resource
    ActivityService activityService;

    @RequestMapping(value="/square.do", method= RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Object> squareMsg(long uid,HttpServletRequest request){
        request.getSession().setAttribute("uid",uid);
        HashMap<String,Object> userMap = loginRegisterService.getUserMsg(uid);
        return userMap;
    }

    @RequestMapping(value="/search.do", method= RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Object> search(String name,HttpServletRequest request){
        HashMap<String,Object> searchMap = new HashMap<>();
        long uid = (long)request.getSession().getAttribute("uid");
        searchMap.put("acti",activityService.getActiByName(uid,name));
        searchMap.put("help",helpService.getHelpByName(uid,name));
        return searchMap;
    }

}

