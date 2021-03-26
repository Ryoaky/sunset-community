package com.sunset.community.Pojo;


public class HelpJoiner {

  private long hid;
  private long uid;

    public HelpJoiner(Long uid, long hid) {
      this.hid = hid;
      this.uid  = uid;
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
