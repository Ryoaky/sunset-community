/**
 * title: 夕阳红小舍交流社区
 * author: Jacob Chen
 * date:2021/2/3
 * description: 帮助广场功能的实现（获得附近帮助，发布帮助，对帮助的操作如撤销参与退出帮助）
 */
package com.sunset.community.Service.impl;

import com.sunset.community.Mapper.HelpMapper;
import com.sunset.community.Mapper.UserMapper;
import com.sunset.community.Pojo.Help;
import com.sunset.community.Pojo.HelpJoiner;
import com.sunset.community.Pojo.HelpPoster;
import com.sunset.community.Service.HelpService;
import com.sunset.community.Utils.Upload;
import com.sunset.community.Utils.LocUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HelpServiceImpl implements HelpService {

    @Autowired
    HelpMapper helpMapper ;

    @Autowired
    UserMapper userMapper;

    Integer joiner_num;
    String loc_url;
    @Override
    public List<HashMap<String,Object>> getNearHelp(long uid,String location) {

        //创建帮助列表
        List<HashMap<String,Object>>  helpMapList =  new ArrayList<HashMap<String,Object>>();;

        List<Help> helpList = helpMapper.getHelpListByLoc(location);

        //迭代每一个帮助对象，添加信息构造成哈希表并添加至帮助列表中
        for (Help help : helpList) {
            String time = help.getTime().toString().replace("T,"," ").substring(0,16);
            HashMap<String,Object> helpMap = new HashMap<>();
            HashMap<String,String> LatIngMap = new HashMap<>();
            joiner_num = helpMapper.getJoinerNums(help.getHid());
            helpMap.put("peopleNum",joiner_num);  //帮助id和对应参加人数
            helpMap.put("helpObject",help);
            helpMap.put("isPoster",helpMapper.isPoster(uid,help.getHid()));
            helpMap.put("hasJoined",helpMapper.hasJoined(uid,help.getHid()));
            helpMap.put("voice",helpMapper.getVoice(help.getHid()));
            helpMap.put("timeFormed",time);
            LatIngMap = LocUtil.locToLatIng(help.getLocation());

            //通过百度接口获得获得求助者附近的地图，协助帮助者的活动
            loc_url = "http://api.map.baidu.com/staticimage/v2?ak=AZdnE33jfd9R1LSztoeF0qw91iA9Am7e&mcode=666666&center=" +
                    LatIngMap.get("ing")+
                    "," +
                    LatIngMap.get("lat")+
                    "&width=300&height=200&zoom=18";
//            loc_url = "http://api.map.baidu.com/staticimage?width=330&height=300&center=" +
//                    LatIngMap.get("ing") +
//                    "," +
//                    LatIngMap.get("lat") +
//                    "&zoom=18";
            helpMap.put("picUrl",loc_url);
            if(!helpMap.isEmpty())
                helpMapList.add(helpMap);
        }
        return helpMapList;
    }

    @Override
    public boolean addHelp(Help help, HelpPoster hp) {
        helpMapper.addHelp(help);
        helpMapper.addPostHelp(hp);
        return true;
    }

    @Override
    public boolean joinHelp(Help help, HelpJoiner hj) {
        helpMapper.addJoinHelp(hj);
        return true;
    }

    @Override
    public long getPoster(long hid) {
        return helpMapper.getPoster(hid);
    }

    @Override
    public boolean deleteHelp(long hid) {
        return helpMapper.deleteHelp(hid);
    }

    @Override
    public long getJoiner(long hid) {
        return helpMapper.getJoiner(hid);
    }

    @Override
    public boolean deleteJoin(long hid) {
        return helpMapper.deleteJoin(hid);
    }

    @Override
    public boolean operHelp(long uid, long hid) {
        if(helpMapper.isPoster(uid,hid)!=0){
            helpMapper.deleteHelp(hid);
            helpMapper.deleteHelpPoster(hid);
        }
        else{
            if(helpMapper.hasJoined(uid,hid)!=0){
                helpMapper.joinHelp(uid,hid);
            }
            else {
                helpMapper.quitHelp(uid,hid);
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean postHelp(long uid, Help help, MultipartFile audio, HttpServletRequest req) {
        try{
            helpMapper.addHelp(help);
            long hid = helpMapper.lastId();
            helpMapper.addPoster(uid,hid);
            //上传音频
            helpMapper.addVoice(Upload.audio(audio),hid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<HashMap<String,Object>> getHelpByName(long uid,String name) {
        //创建帮助列表
        List<HashMap<String,Object>>  helpMapList =  new ArrayList<HashMap<String,Object>>();;

        List<Help> helpList = helpMapper.getHelpByName(name);

        //迭代每一个帮助对象，添加信息构造成哈希表并添加至帮助列表中
        for (Help help : helpList) {
            String time = help.getTime().toString().replace("T,"," ").substring(0,16);
            HashMap<String,Object> helpMap = new HashMap<>();
            HashMap<String,String> LatIngMap = new HashMap<>();
            joiner_num = helpMapper.getJoinerNums(help.getHid());
            helpMap.put("peopleNum",joiner_num);  //帮助id和对应参加人数
            helpMap.put("helpObject",help);
            helpMap.put("isPoster",helpMapper.isPoster(uid,help.getHid()));
            helpMap.put("hasJoined",helpMapper.hasJoined(uid,help.getHid()));
            helpMap.put("voice",helpMapper.getVoice(help.getHid()));
            helpMap.put("timeFormed",time);
            LatIngMap = LocUtil.locToLatIng(help.getLocation());

            //通过百度接口获得获得求助者附近的地图，协助帮助者的活动
            loc_url = "http://api.map.baidu.com/staticimage/v2?ak=AZdnE33jfd9R1LSztoeF0qw91iA9Am7e&mcode=666666&center=" +
                    LatIngMap.get("ing")+
                    "," +
                    LatIngMap.get("lat")+
                    "&width=300&height=200&zoom=18";
//            loc_url = "http://api.map.baidu.com/staticimage?width=330&height=300&center=" +
//                    LatIngMap.get("ing") +
//                    "," +
//                    LatIngMap.get("lat") +
//                    "&zoom=18";
            helpMap.put("picUrl",loc_url);
            if(!helpMap.isEmpty())
                helpMapList.add(helpMap);
        }
        return helpMapList;
    }

}
