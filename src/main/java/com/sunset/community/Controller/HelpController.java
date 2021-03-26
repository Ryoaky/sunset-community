/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/29
 * description: 帮助广场控制器
 */
package com.sunset.community.Controller;

import com.sunset.community.Pojo.Activity;
import com.sunset.community.Pojo.Help;
import com.sunset.community.Pojo.HelpJoiner;
import com.sunset.community.Pojo.HelpPoster;
import com.sunset.community.Service.HelpService;
import com.sunset.community.Service.Login_Regi_Service;
import com.sunset.community.Service.SendSmsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class HelpController {

    @Resource
    private HelpService helpService;


//    @RequestMapping(value="/addHelp.do", method= RequestMethod.GET)
//    @ResponseBody
//    public boolean addHelp(Help help,HttpSession httpSession) {
//        HelpPoster hp = new HelpPoster((Long) httpSession.getAttribute("uid"),help.getHid());
//        helpService.addHelp(help,hp);
//        return true;
//    }
//
//    @RequestMapping(value="/deleteHelp.do", method= RequestMethod.GET)
//    @ResponseBody
//    public boolean deleteHelp(HttpSession httpSession,long hid) {
//        //先判断这个活动是不是属于这个操作者？不对，应该在展示活动的时候判断然后给删除键？
//
//
//        //如果帮助被删除，要向参与者发送信息（既参与者查找活动为空时会有提醒，需要在用户界面实现)!!!!!!!!保留了帮助参与者元组
//        if((long)httpSession.getAttribute("uid") == helpService.getPoster(hid)){
//            helpService.deleteHelp(hid);
//            return true;
//        }
//        else return false;
//    }
//
//    @RequestMapping(value="/joinHelp.do", method= RequestMethod.GET)
//    @ResponseBody
//    public boolean joinHelp(Help help,HttpSession httpSession) {
//        HelpJoiner hj = new HelpJoiner((Long) httpSession.getAttribute("uid"),help.getHid());
//        helpService.joinHelp(help,hj);
//        return true;
//    }
//
//    @RequestMapping(value="/deleteJoin.do", method= RequestMethod.GET)
//    @ResponseBody
//    public boolean deleteJoin(HttpSession httpSession,long hid) {
//        //先判断这个活动是不是属于这个操作者？不对，应该在展示活动的时候判断然后给删除键？
//
//        if((long)httpSession.getAttribute("uid") == helpService.getJoiner(hid)){
//            helpService.deleteJoin(hid);
//            return true;
//        }
//        else return false;
//    }


    @RequestMapping(value="/nearHelp.do", method= RequestMethod.POST)
    @ResponseBody
    public List<HashMap<String,Object>> getNearHelp(String location,HttpServletRequest request) {
        long uid = (long)request.getSession().getAttribute("uid");
        return helpService.getNearHelp(uid,location);
    }

    @RequestMapping(value="/operHelp.do", method= RequestMethod.POST)
    @ResponseBody
    public boolean operHelp(long hid,HttpServletRequest request) {
        long uid = (long)request.getSession().getAttribute("uid");
        return helpService.operHelp(uid,hid);
    }

    @RequestMapping(value="/postHelp.do", method= RequestMethod.POST)
    @ResponseBody
    public boolean postHelp(MultipartFile audio, HttpServletRequest req, String location,HttpSession httpSession) {

        long uid = (long)req.getSession().getAttribute("uid");
        try {
            String name = req.getParameter("name");
            String intro = req.getParameter("introduce");

            //构建帮助对象
            Help help = new Help(name,intro,location);
            System.out.println(help);

            //上传帮助对象和音频
            boolean b = helpService.postHelp(uid,help,audio,req);
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
}
