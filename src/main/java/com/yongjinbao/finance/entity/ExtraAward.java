package com.yongjinbao.finance.entity;


import com.yongjinbao.commons.entity.BaseEntity;
import com.yongjinbao.member.entity.Member;

/**
 * Created by fddxiaohui on 2015/11/30.
 * 额外奖励历史纪录
 */
public class ExtraAward extends BaseEntity {
    //所属会员
    private Member member;
    //上个月成功数量
    private int lastMonNum;
    //本月成功数量
    private int thisMonNum;
    //额外奖励
    private int extraAward;
    //一级成功数量
    private int firLevelNum;
    //二级成功数量
    private int secLevelNum;
    //所属月份
    private String month;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getLastMonNum() {
        return lastMonNum;
    }

    public void setLastMonNum(int lastMonNum) {
        this.lastMonNum = lastMonNum;
    }

    public int getThisMonNum() {
        return thisMonNum;
    }

    public void setThisMonNum(int thisMonNum) {
        this.thisMonNum = thisMonNum;
    }

    public int getExtraAward() {
        return extraAward;
    }

    public void setExtraAward(int extraAward) {
        this.extraAward = extraAward;
    }

    public int getFirLevelNum() {
        return firLevelNum;
    }

    public void setFirLevelNum(int firLevelNum) {
        this.firLevelNum = firLevelNum;
    }

    public int getSecLevelNum() {
        return secLevelNum;
    }

    public void setSecLevelNum(int secLevelNum) {
        this.secLevelNum = secLevelNum;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
