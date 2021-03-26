/**
 * description: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/2/5
 * description:
 */
package com.sunset.community.Service;

import com.sunset.community.Pojo.Help;
import com.sunset.community.Pojo.HelpJoiner;
import com.sunset.community.Pojo.HelpPoster;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.HashMap;

public interface LocService {

//    HashMap<String,Object> getNsetLoc(String lat, String ing,long uid) throws IOException, XPathExpressionException, ParserConfigurationException;

    String setLocation(String lat, String ing, long uid) throws IOException, XPathExpressionException, ParserConfigurationException;

    String getLocation(long uid);

//    String getLocation(long uid);
}
