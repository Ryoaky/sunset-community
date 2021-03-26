/**
 * description: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/28
 * description:
 */
package com.sunset.community.Mapper;

import com.sunset.community.Pojo.Activity;
import com.sunset.community.Pojo.Help;
import com.sunset.community.Pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {


    //    @Select("SELECT password FROM user where name=#{name}")
    User getUserByPhone(String phone);
    User getUserByUid(long uid);
    User getUserByName(String name);

    //    插入用户数据
    boolean addUser(User user);


    User getMsgForAcc(long uid);

    String getLocation(long uid);

    void setLocation(Long uid, String lat, String ing);

    boolean updateUsrMsg(User user);

    Integer getActiHistory(long uid,String year);

    Integer getActiHistory2(long uid,String year);

    Integer getHelpHistory(long uid,String year);

    Integer getHelpHistory2(long uid,String year);

    Date getRegiYear(long uid);

    List<Help> getJoinedHelp(long uid);

    List<Activity> getJoinedActi(long uid);

    List<Help> getPostedHelp(long uid);

    List<Activity> getPostedActi(long uid);

    String getUserName(long uid);
}