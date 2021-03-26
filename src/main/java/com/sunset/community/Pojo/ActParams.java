/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/1/28
 * description:
 */
package com.sunset.community.Pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ActParams {

    /**
     * 用户名
     */
    private String name;

    /**
     * 活动名称
     */
    private String aname;

    private String phone;


    public ActParams(String name, String aname,String phone) {
        this.name = name;
        this.aname = aname;
        this.phone = phone;
    }
}

