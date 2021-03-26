/**
 * description: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/3/1
 * description:
 */
package com.sunset.community.Service;

import java.util.HashMap;

public interface UserService {

    HashMap<String,Object> allJoined(long uid);
    HashMap<String,Object> allPosted(long uid);
}
