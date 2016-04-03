package com.metamall.Clutter;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/1.
 */
public class UserEntity implements Serializable
{
    private static final long serialVersionUID = -5683263669918171030L;

    private String userName;
    // 原始密码

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    private String password;

    public String getProvince()
    {
        return province;
    }
    public void setProvince(String province){
        this.province=province;
    }
    private String province;

    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city=city;
    }
    private String city;

    public String getdistrict(){
        return district;
    }
    public void setdistrict(String district){
        this.district=district;
    }

    private String district;

    public String getaddressDetails(){
        return addressDetails;
    }
    public void setaddressDetails(String addressDetails){
        this.addressDetails=addressDetails;
    }
    private String addressDetails;

    public String getnumber(){
        return number;
    }
    public void setnumber(String number){
        this.number=number;
    }
    private String number;


}
