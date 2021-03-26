/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/2/16
 * description: 定位服务控制器（经纬度转定位，定位转经纬度）
 */
package com.sunset.community.Controller;

import com.sunset.community.Service.LocService;
import com.sunset.community.Service.Login_Regi_Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class LocController {

    @Resource
    private LocService locService;

    //设置定位的控制器
    @RequestMapping(value="/setLoc.do", method= RequestMethod.GET)
    @ResponseBody
    public String setLoc(String lat,String ing,HttpServletRequest request) throws IOException, XPathExpressionException, ParserConfigurationException {
        long uid = (long)request.getSession().getAttribute("uid");
        return locService.setLocation(lat,ing,uid);
    }

    //浏览器获取经纬度失败调用历史数据库的控制器
    //设置定位的控制器
    @RequestMapping(value="/historyLoc.do", method= RequestMethod.GET)
    @ResponseBody
    public String historyLoc(HttpServletRequest request) throws IOException, XPathExpressionException, ParserConfigurationException {
        long uid = (long)request.getSession().getAttribute("uid");
        return locService.getLocation(uid);
    }


}
