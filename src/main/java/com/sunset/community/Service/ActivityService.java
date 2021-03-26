/**
 * description: 夕阳红小舍
 * author: SiHao Liu
 * date:2021/3/1
 * description:
 */
package com.sunset.community.Service;

import com.sunset.community.Pojo.Activity;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface ActivityService {

    /*
     * 发布活动功能
     *
     * 作用：活动表增加活动，并且关系库新增
     *
     * 返回值：true/false
     *
     * */
    boolean postActi(long uid,Activity activity,MultipartFile picture,MultipartFile audio,HttpServletRequest req);


    List<HashMap<String, Object>> getNearActi(long uid, String location);

    boolean operActi(long uid, long aid);

    List<HashMap<String,Object>> getActiByName(long uid,String name);

//    //搜索活动
//    public List<Activity> getActiByName(String name);

//
//    //判断是否人数满
//    public boolean isFull(long aid);

}
