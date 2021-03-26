/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/2/16
 * description:页面控制器
 */
package com.sunset.community.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class pageController {

    @RequestMapping({"audio"})
    public String audio(){
        return "audio";
    }

    @RequestMapping({"canjia"})
    public String canjia(){
        return "canjia";
    }

    @RequestMapping({"fabu"})
    public String fabu(){
        return "fabu";
    }

    @RequestMapping({"square"})
    public String square(){
        return "guangchang";
    }

    @RequestMapping({"","index"})
    public String index(){
        return "index";
    }

    @RequestMapping({"login"})
    public String login(){
        return "login";
    }

    @RequestMapping({"personinfo"})
    public String personinfo(){
        return "personinfo";
    }

    @RequestMapping({"shake"})
    public String shake(){
        return "shake";
    }


//    @RequestMapping({"shake"})
//    public String shake(){
//        return "alarm";
//    }



//    @RequestMapping({"shake2"})
//    public String shake2(){
//        return "shake2";
//    }
//
//    @RequestMapping({"shake3"})
//    public String shake3(){
//        return "shake3";
//    }

    @RequestMapping(value="/request", method= RequestMethod.POST)
    @ResponseBody
//    @CrossOrigin("http://localhost:63342")
    public String request(){
        return "request";
    }


}
