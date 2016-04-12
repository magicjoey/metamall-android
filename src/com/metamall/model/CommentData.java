package com.metamall.model;

/**
 * Created by Administrator on 2016/4/11.
 */
public class CommentData {
    /**
     * 评论昵称、星数、规格、内容、日期
     */
    private String nickname;
    private Float starsNum;
    private String specification;
    private String substance;
    private String utc;


    public String getnickname(){
        return nickname;
    }
    public void setnickname(String nickname){
        this.nickname=nickname;
    }
    public Float getstarsNum(){
        return starsNum;
    }
    public void setstarsNum(Float starsNum){
        this.starsNum=starsNum;
    }
    public String getspecification(){
        return specification;
    }
    public void setspecification(String specification){
        this.specification=specification;
    }
    public String getsubstance(){
        return substance;
    }
    public void setsubstance(String substance){
        this.substance=substance;
    }
    public String getutc(){
        return utc;
    }
    public void setutc(String utc){
        this.utc=utc;
    }
}
