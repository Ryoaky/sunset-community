/**
 * description: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/2/5
 * description:
 */
package com.sunset.community.Mapper;

import com.sunset.community.Pojo.Location;
import com.sunset.community.Pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface LocMapper {

    boolean updateLoc(HashMap<String,Object> hs);

    boolean setLoc(HashMap<String, Object> hs);

    String getLoc(long uid);
}
