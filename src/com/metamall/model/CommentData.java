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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Float getStarsNum() {
        return starsNum;
    }

    public void setStarsNum(Float starsNum) {
        this.starsNum = starsNum;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }
}
