/**
 * description: 夕阳红小舍
 * author: Jacob Chen
 * date:2021/2/3
 * description:
 */
package com.sunset.community.Service;

import com.sunset.community.Pojo.Help;
import com.sunset.community.Pojo.HelpJoiner;
import com.sunset.community.Pojo.HelpPoster;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface HelpService {

    List<HashMap<String,Object>>getNearHelp(long uid, String location);

    boolean addHelp(Help help, HelpPoster hp);

    boolean joinHelp(Help help, HelpJoiner hj);

    long getPoster(long hid);

    boolean deleteHelp(long hid);


    long getJoiner(long hid);

    boolean deleteJoin(long hid);

    boolean operHelp(long uid, long hid);

    boolean postHelp(long uid, Help help, MultipartFile audio, HttpServletRequest req);

    List<HashMap<String,Object>> getHelpByName(long uid,String name);
}
