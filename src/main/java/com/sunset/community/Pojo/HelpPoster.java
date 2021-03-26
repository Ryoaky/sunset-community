package com.sunset.community.Pojo;


public class HelpPoster {

  private long hid;
  private long uid;

  public HelpPoster(long uid, long hid) {
    this.hid = hid;
    this.uid = uid;
  }


  public long getHid() {
    return hid;
  }

  public void setHid(long hid) {
    this.hid = hid;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

}
