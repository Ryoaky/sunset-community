/**
 * description: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/28
 * description:
 */
package com.sunset.community.Mapper;

import com.sunset.community.Pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HelpMapper {
    List<Help> getPostHelp(Long uid);

    List<Help> getJoinHelp(Long uid);

    //添加帮助和发布帮助的数据
    boolean addPostHelp(HelpPoster helpPoster);

    boolean addJoinHelp(HelpJoiner helpJoiner);

    //获得帮助数据
    List<Help> getHelpListByLoc(String loc);

    //添加帮助数据
    boolean addHelp(Help help);

    long getPoster(long hid);

    boolean deleteHelp(long hid);

    long getJoiner(long hid);

    boolean deleteJoin(long hid);


    Integer getJoinerNums(long hid);

    Integer isPoster(long uid, long hid);

    Integer hasJoined(long uid, long hid);

    boolean joinHelp(long uid, long hid);

    boolean quitHelp(long uid, long hid);

    long lastId();

    boolean addPoster(long uid, long hid);

    boolean addVoice(String audio, long hid);

    String getVoice(long hid);

    boolean deleteHelpPoster(long hid);

    List<Help> getHelpByName(String name);
}
