/**
 * title: 夕阳红小舍
 * author: SiHao Liu
 * date:2021/3/1
 * description: 用于保存和生成多媒体的数据和访问路径
 */
package com.sunset.community.Utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.UUID;

public class Upload {
    //上传音频
    public static String audio(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getBytes());
        String filePath = "D://SunsetMulti//sunsetRedAudio//"; // 上传后的路径
        String fileName = UUID.randomUUID() + ".mp3"; // 新文件名
        System.out.println(fileName);
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String audioUrl =
//                req.getScheme() + "://" + req.getServerName() + ":" +
//                req.getServerPort() +
                "/audio/SunsetMulti/sunsetRedAudio/" + fileName;
        return audioUrl;
    }


    //上传图片
    public static String pic(MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "D://SunsetMulti//sunsetRedPic//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename =
//                req.getScheme() + "://" + req.getServerName() + ":" +
//                req.getServerPort() +
                        "/img/SunsetMulti/sunsetRedPic/" + fileName;
        return filename;
    }
}
