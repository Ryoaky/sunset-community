package com.sunset.community.Pojo;


import java.sql.Timestamp;

public class Activity {

  private long aid;
  private String name;
  private int needNum;
  private java.sql.Timestamp startTime;
  private java.sql.Timestamp endTime;
  private java.sql.Timestamp postTime;
  private String intro;
  private boolean state;
  private String location;
  private String type;
  private String detailLoc;

  public Activity(String name, int needNum,
                  Timestamp startTime, Timestamp endTime,
                  String intro, boolean state, String location, String type,String detailLoc) {
    this.name=name;
    this.needNum=needNum;
    this.startTime=startTime;
    this.endTime=endTime;
    this.intro=intro;
    this.state=state;
    this.location=location;
    this.type=type;
    this.detailLoc=detailLoc;
  }

  public Activity(long aid,String name, int needNum,
                  Timestamp startTime, Timestamp endTime, Timestamp postTime,
                  String intro, boolean state, String location, String type,String detailLoc) {
    this.aid=aid;
    this.name=name;
    this.needNum=needNum;
    this.startTime=startTime;
    this.endTime=endTime;
    this.postTime=postTime;
    this.intro=intro;
    this.state=state;
    this.location=location;
    this.type=type;
    this.detailLoc=detailLoc;
  }


  public long getAid() {
    return aid;
  }

  public void setAid(long aid) {
    this.aid = aid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getNeedNum() {
    return needNum;
  }

  public void setNeedNum(int needNum) {
    this.needNum = needNum;
  }


  public java.sql.Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(java.sql.Timestamp startTime) {
    this.startTime = startTime;
  }


  public java.sql.Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(java.sql.Timestamp endTime) {
    this.endTime = endTime;
  }


  public java.sql.Timestamp getPostTime() {
    return postTime;
  }

  public void setPostTime(java.sql.Timestamp postTime) {
    this.postTime = postTime;
  }


  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }


  public boolean getState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
