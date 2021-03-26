/**
 * description: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/28
 * description:
 */
package com.sunset.community.Service;

import com.sunset.community.Pojo.User;

import java.util.HashMap;

public interface Login_Regi_Service {


    boolean addUser(User user);

    boolean isUser(String phone);

    User getUser(String phone);

    HashMap<String, Object> getUserMsg(long uid);

    HashMap<String, Object> getPersonalMsg(long uid);

    void setLocation(Long uid,String lat, String ing);

    boolean updatePersonalMsg(User user);

    HashMap<String, Object> getUsrHistory(long uid);
}

