/**
 * description: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/28
 * description:
 */
package com.sunset.community.Mapper;

import com.sunset.community.Pojo.ActiJoiner;
import com.sunset.community.Pojo.ActiPoster;
import com.sunset.community.Pojo.Activity;
import com.sunset.community.Pojo.User;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.List;

@Mapper
@Repository
public interface ActivityMapper {

    List<Activity> getPostActi(Long uid);

    List<Activity> getJoinActi(Long uid);

    //添加用户和发布活动的数据
    boolean addPostActi(ActiPoster actiPoster);

    boolean addJoinActi(ActiJoiner actiJoiner);

    //获得活动数据
    List<Activity> getActiListByLoc(String loc);
    List<Activity> getActiListByType(String type);
    List<Activity> getActiByName(String name);  //模糊查询

    //添加活动数据
    boolean addActi(Activity acti);

    Activity getActiByAid(long aid);

    Integer getJoinerNums(long aid);

    Integer isPoster(long uid, long aid);

    Integer hasJoined(long uid, long aid);

    String getPic(long aid);

    Integer getNeedNums(long aid);

    boolean deleteActi(long aid);

    boolean joinActi(long uid, long aid);

    boolean quitActi(long uid, long aid);

    long lastId();

    boolean addPoster(long uid, long aid);

//    int saveFile(long aid, Blob blob);

    boolean addImage(String pic, long aid);

    boolean addAudio(String audio, long aid);

    List<Activity> selectNowAct(String year,String month,String day);

    List<User> getJoiners(long aid);

    User getPoster(long aid);

    boolean updateState(long aid);

    String getActiPic(long aid);

    String getActiAudio(long aid);

    boolean deleteActiPoster(long aid);
}
