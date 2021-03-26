/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/3/1
 * description:
 */
package com.sunset.community.Service.impl;

import com.sunset.community.Mapper.UserMapper;
import com.sunset.community.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public HashMap<String, Object> allJoined(long uid) {
        HashMap<String,Object> joinedList = new HashMap<>();
        joinedList.put("help",userMapper.getJoinedHelp(uid));
        joinedList.put("acti",userMapper.getJoinedActi(uid));
        return joinedList;
    }

    @Override
    public HashMap<String, Object> allPosted(long uid) {
        HashMap<String,Object> joinedList = new HashMap<>();
        joinedList.put("help",userMapper.getPostedHelp(uid));
        joinedList.put("acti",userMapper.getPostedActi(uid));
        return joinedList;
    }
}
