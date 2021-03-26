package com.sunset.community.Pojo;


public class Help {

  private long hid;
  private String name;
  private String introduce;
  private java.sql.Timestamp time;
  private String location;

  public Help(String name, String intro, String location) {
    this.name = name;
    this.introduce = intro;
    this.location = location;
  }

  public Help(String name, String intro,java.sql.Timestamp time, String location,long hid) {
    this.hid=hid;
    this.name = name;
    this.introduce = intro;
    this.time=time;
    this.location = location;
  }


  public long getHid() {
    return hid;
  }

  public void setHid(long hid) {
    this.hid = hid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getIntroduce() {
    return introduce;
  }

  public void setIntroduce(String introduce) {
    this.introduce = introduce;
  }


  public java.sql.Timestamp getTime() {
    return time;
  }

  public void setTime(java.sql.Timestamp time) {
    this.time = time;
  }


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

}
