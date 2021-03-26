/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2020/12/3
 * description: 登录注册控制器
 */
package com.sunset.community.Controller;


import com.sunset.community.Pojo.User;
import com.sunset.community.Service.Login_Regi_Service;
import com.sunset.community.Service.SendSmsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Controller
@ControllerAdvice  //exception注解
public class Login_Regi_Controller extends HttpServlet{

    @Resource
    private Login_Regi_Service loginRegisterService;


    @Resource
    private SendSmsService smsService;

    private Integer code;


    //发送短信验证码的控制器
    @RequestMapping(value="/getCode.do", method= RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Object> loginGetCode(String phone,HttpServletRequest request) {
        //首先查看用户是否存在
        if(loginRegisterService.isUser(phone)){
            //存在则发送手机验证短信
            code = smsService.addSendSms(phone);
            HashMap<String,Object> codeMap = new HashMap<>();
            codeMap.put(phone,code);
            codeMap.put("uid",loginRegisterService.getUser(phone).getUid());
//            HttpSession session = request.getSession();
//            session.setAttribute("code", code);
            return codeMap; //发送成功 (提示用户输入验证码）
        }
        else
            return null; //发送失败 （提示用户注册）
    }


//    //校验短信验证码的路径，成功则返回获取用户个人信息
//    @RequestMapping(value="/login.do", method= RequestMethod.POST)
//    @ResponseBody
//    public HashMap<String,Object> loginCodeCheacked(String phone, Integer codeCin,HttpSession httpSession, HttpServletRequest request)
//            throws UnsupportedEncodingException {
//            request.setCharacterEncoding("utf-8");
//
//            if(smsService.isCode(codeCin, (Integer)httpSession.getAttribute("code"))){
//                //获得用户信息(要展示的）
//                User user = loginRegisterService.getUser(phone);
//                HashMap<String,Object> rsMap = loginRegisterService.getUserMsg(user.getUid());
////              System.out.print(rsMap);
//                //会话用的属性
////                session.setAttribute("activeUser", user.getName());
//                HttpSession session2 = request.getSession();
//                session2.setAttribute("uid", user.getUid());
//                System.out.print("ok match"+(Integer) httpSession.getAttribute("code"));
//                httpSession.setAttribute("uid",user.getUid());
//                return rsMap;
////                return new ModelAndView(basepath+"../user/user_home","rsMap",rsMap);
//            }
//            else{
//                System.out.print(codeCin+"wrong match"+(Integer) httpSession.getAttribute("code"));
//                return null;
//            }
////                return new ModelAndView(basepath+"../login.jsp","alert","验证码输入错误");
//        }

//name=Jacob&phone=...&guardianName=Jane&guardianPhone=123456
    @RequestMapping(value = "/register.do", method= RequestMethod.POST)
    @ResponseBody
    public String addUser(User user) {

        try{
            if(loginRegisterService.addUser(user)){
                String alert1 ="注册成功，请登录！";
                System.out.println(alert1);
                return alert1;
            }
            else{
                String alert2 = "该号码已被注册";
                return alert2;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            String alert3 = "注册失败，出现错误";
            return alert3;
        }
    }
}
