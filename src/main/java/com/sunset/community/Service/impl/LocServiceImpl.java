/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/2/5
 * description:
 */
package com.sunset.community.Service.impl;

import com.sunset.community.Mapper.LocMapper;
import com.sunset.community.Service.LocService;
import com.sunset.community.Utils.LocUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.HashMap;

@Service
public class LocServiceImpl implements LocService {

    @Autowired
    LocMapper locMapper;


    @Override
    public String setLocation(String lat, String ing, long uid) throws IOException, XPathExpressionException, ParserConfigurationException {
        //调用工具类
        HashMap<String, Object> hs = new HashMap<>();
        hs = LocUtil.getLoc(lat,ing);
        if(lat==null){
            if(locMapper.getLoc(uid)==null){
                return null;
            }else{
                return locMapper.getLoc(uid);
            }
        }
        else{
            hs.put("lat",lat);
            hs.put("ing",ing);
            hs.put("uid",uid);
            locMapper.updateLoc(hs);
            return (String)hs.get("loc");
        }

//        //如果不存在则调用插入函数
//        if(locMapper.getLoc(uid) == null){
//            locMapper.setLoc(hs);
//            return (String)hs.get("loc");
//        }
//        else if(locMapper.getLoc(uid)!= hs.get("loc")){
//            //更新地理位置信息
//            locMapper.updateLoc(hs);
//            return (String)hs.get("loc");
//        }
//        return locMapper.getLoc(uid);
    }

    @Override
    public String getLocation(long uid) {
        return locMapper.getLoc(uid);
    }


}
