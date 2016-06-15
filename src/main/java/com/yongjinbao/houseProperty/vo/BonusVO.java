package com.yongjinbao.houseProperty.vo;

import com.yongjinbao.finance.entity.ExtraAward;

public class BonusVO {

	/** 当月份自发数 **/
	private int currentSelfCount;
	
	/** 上月份自发数 **/
	private int lastSelfCount;
	
	/** level1Count**/
	private int level1Count;
	
	/** level2Count**/
	private int level2Count;
	
	/** 奖励获取情况 **/
	private ExtraAward award;

	public int getCurrentSelfCount() {
		return currentSelfCount;
	}

	public void setCurrentSelfCount(int currentSelfCount) {
		this.currentSelfCount = currentSelfCount;
	}

	public int getLastSelfCount() {
		return lastSelfCount;
	}

	public void setLastSelfCount(int lastSelfCount) {
		this.lastSelfCount = lastSelfCount;
	}

	public int getLevel1Count() {
		return level1Count;
	}

	public void setLevel1Count(int level1Count) {
		this.level1Count = level1Count;
	}

	public int getLevel2Count() {
		return level2Count;
	}

	public void setLevel2Count(int level2Count) {
		this.level2Count = level2Count;
	}

	public ExtraAward getAward() {
		return award;
	}

	public void setAward(ExtraAward award) {
		this.award = award;
	}
	
}
