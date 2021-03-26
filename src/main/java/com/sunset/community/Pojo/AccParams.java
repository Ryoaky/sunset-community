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
public class AccParams {

    /**
     * 用户名
     */
    private String name;

    /**
     * 监护人手机号码
     */
    private String phone;



    public AccParams(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

