/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/2/4
 * description: 调用百度接口的经纬度转换工具
 */
package com.sunset.community.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.util.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Controller
@ControllerAdvice
@RequestMapping("/loc")
public class LocUtil {

///html/body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div/div[1]/div[1]/p/span[2]
    @RequestMapping(value="/takeBaidu.do", method= RequestMethod.GET)
    @ResponseBody
    public static HashMap<String,Object> getLoc(String lat, String ing) throws XPathExpressionException, ParserConfigurationException, IOException {
//        String url = "http://api.map.baidu.com/geocoder?"+
//                "location=" +
//                lat +
//                "," +
//                ing +
//                "&coord_type=gcj02&output=json&src=webapp.baidu.openAPIdemo";
        System.out.println(lat);
        System.out.println(ing);
//        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=AZdnE33jfd9R1LSztoeF0qw91iA9Am7e&" +
//                "location=" +
//                lat +
//                "," +
//                ing+
//                "&coord_type=gcj02&output=json&src=webapp.baidu.openAPIdemo";
//        lat="31.225";
//        ing="116.3972282409668";
        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=AZdnE33jfd9R1LSztoeF0qw91iA9Am7e&" +
                "location=" +
                lat +
                "," +
                ing +
                "&coord_type=gcj02&output=json&src=webapp.baidu.openAPIdemo";
//        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=AZdnE33jfd9R1LSztoeF0qw91iA9Am7e&output=json&coordtype=gcj02" +
//                "&location=" +
//                lat +
//                "," +
//                ing;
        StringBuffer json = new StringBuffer();

        try {
            //通过url获得连接
            URL u = new URL(url);
            URLConnection yc = u.openConnection();
            yc.setRequestProperty("User-Agent", "Mozilla/31.0 (compatible; MSIE 10.0; Windows NT; DigExt)");

            //读取返回的数据
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), StandardCharsets.UTF_8));
            String inputline = null;
            while ((inputline = in.readLine()) != null) {
                json.append(inputline);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获得jsonobject
        String loc = null;
        String cityCode = null;
        String city = null;
        String province = null;
        String district = null;
        String street = null;

        HashMap<String,Object> hs = new HashMap<>();
        JSONObject jo = JSON.parseObject(json.toString());
        JSONObject addressComponent = null;
        if(jo.getInteger("status")==0){
           loc = jo.getJSONObject("result").getString("formatted_address");
           cityCode = jo.getJSONObject("result").getString("cityCode");

           addressComponent = jo.getJSONObject("result").getJSONObject("addressComponent");
           city = addressComponent.getString("city");
           district = addressComponent.getString("district");
           province = addressComponent.getString("province");
           street = addressComponent.getString("street");

           hs.put("loc", loc);
           System.out.print(loc);
           hs.put("cityCode",cityCode);
           hs.put("city",city);
           hs.put("district",district);
           hs.put("province",province);
           hs.put("street",street);
           return hs;
        }
        else
            return null;
    }

    @RequestMapping(value="/LatIng.do", method= RequestMethod.GET)
    @ResponseBody
    public static HashMap<String,String> locToLatIng(String location){
        HashMap<String,String> LatIng = new HashMap<>();
        String url = "http://api.map.baidu.com/geocoding/v3/?address=" +
                location +
                "&output=json&ak=AZdnE33jfd9R1LSztoeF0qw91iA9Am7e";

        StringBuffer json = new StringBuffer();
        try {
            //通过url获得连接
            URL u = new URL(url);
            URLConnection yc = u.openConnection();
            yc.setRequestProperty("User-Agent", "Mozilla/31.0 (compatible; MSIE 10.0; Windows NT; DigExt)");

            //读取返回的数据
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), StandardCharsets.UTF_8));
            String inputline = null;
            while ((inputline = in.readLine()) != null) {
                json.append(inputline);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获得jsonobject
        String lat = null;
        String ing = null;
        JSONObject jo = JSON.parseObject(json.toString());
        if(jo.getInteger("status")== 0){
            lat = jo.getJSONObject("result").getJSONObject("location").getString("lat");
            ing = jo.getJSONObject("result").getJSONObject("location").getString("lng");

            LatIng.put("lat", lat);
            LatIng.put("ing",ing);
            return LatIng;
        }
        else
            return null;
    }
}

