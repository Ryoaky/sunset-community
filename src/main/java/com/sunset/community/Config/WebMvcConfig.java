/**
 * title: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/3/8
 * description: 配置静态资源映射
 */
package com.sunset.community.Config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加静态资源文件，外部可以直接访问地址
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/SunsetMulti/sunsetRedPic/**").addResourceLocations("file:D:/SunsetMulti/sunsetRedPic/");
        registry.addResourceHandler("/audio/SunsetMulti/sunsetRedAudio/**").addResourceLocations("file:D:/SunsetMulti/sunsetRedAudio/");
//        registry.addResourceHandler("/img/SunsetMulti/sunsetRedPic/**").addResourceLocations("/SunsetMulti/sunsetRedPic/");
//        registry.addResourceHandler("/audio/SunsetMulti/sunsetRedAudio/**").addResourceLocations("/SunsetMulti/sunsetRedAudio/");
    }
}