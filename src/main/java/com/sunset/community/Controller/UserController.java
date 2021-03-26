/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/23
 * description: 用户数据控制器
 */
package com.sunset.community.Controller;

import com.sunset.community.Pojo.User;
import com.sunset.community.Service.HelpService;
import com.sunset.community.Service.Login_Regi_Service;
import com.sunset.community.Service.SendSmsService;
import com.sunset.community.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class UserController {


    @Resource
    private UserService userService;

    @Resource
    Login_Regi_Service loginRegisterService;

    @RequestMapping("/joined")
    @ResponseBody
    public HashMap<String,Object> alljoined(HttpServletRequest request){
        long uid = (long)request.getSession().getAttribute("uid");
        return userService.allJoined(uid);
    }

    @RequestMapping("/posted")
    @ResponseBody
    public HashMap<String,Object> allposted(HttpServletRequest request){
        long uid = (long)request.getSession().getAttribute("uid");
        return userService.allPosted(uid);
    }

    @RequestMapping(value="/usrMsg.do", method= RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Object> getUsrMsg(HttpServletRequest request) {
        long uid = (long)request.getSession().getAttribute("uid");
//        long uid = (long)httpSession.getAttribute("uid");
        return loginRegisterService.getPersonalMsg(uid);
    }

    @RequestMapping(value="/updateUsrMsg.do", method= RequestMethod.POST)
    @ResponseBody
    public boolean updateUsrMsg(User user,HttpServletRequest request) {
        long uid = (long)request.getSession().getAttribute("uid");
        user.setUid(uid);
        return loginRegisterService.updatePersonalMsg(user);
    }

    @RequestMapping(value="/usrhistory.do", method= RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> updateUsrMsg(HttpServletRequest request) {
        long uid = (long)request.getSession().getAttribute("uid");
        return loginRegisterService.getUsrHistory(uid);
    }

    @RequestMapping(value="/setSession.do", method= RequestMethod.GET)
    @ResponseBody
    public boolean handSetCode(HttpServletRequest request, HttpSession session, long uid) {
        request.getSession().setAttribute("uid",uid);
//        request.getSession().setAttribute("location",location);
        return true;
    }

}

