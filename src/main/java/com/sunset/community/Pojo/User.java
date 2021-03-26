package com.sunset.community.Pojo;


public class User {

  private long uid;
  private String name;
  private String phone;
  private String guardianName;
  private String guardianPhone;
  private java.sql.Timestamp regiTime;


  public User(){}
  public User(String name, String phone, String guardianName,String guardianPhone) {
    this.name = name;
    this.phone = phone;
    this.guardianName = guardianName;
    this.guardianPhone = guardianPhone;
  }

  public User(long uid,String name, String phone, String guardianName,String guardianPhone,java.sql.Timestamp regiTime) {
    this.uid = uid;
    this.name = name;
    this.phone = phone;
    this.guardianName = guardianName;
    this.guardianPhone = guardianPhone;
    this.regiTime = regiTime;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getGuardianName() {
    return guardianName;
  }

  public void setGuardianName(String guardianName) {
    this.guardianName = guardianName;
  }


  public String getGuardianPhone() {
    return guardianPhone;
  }

  public void setGuardianPhone(String guardianPhone) {
    this.guardianPhone = guardianPhone;
  }


  public java.sql.Timestamp getRegiTime() {
    return regiTime;
  }

  public void setRegiTime(java.sql.Timestamp regiTime) {
    this.regiTime = regiTime;
  }

}
