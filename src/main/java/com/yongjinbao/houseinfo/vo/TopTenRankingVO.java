package com.yongjinbao.houseinfo.vo;

/**
 * 本月牛人榜【前十名】
 * @author msw
 * @since 2015/09/10
 */
public class TopTenRankingVO {
	
	/** 【本月牛人榜】会员名称  **/
	private String memberName;
	
	/** 【本月牛人榜】发布房源数  **/
	private int releaseCount;

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getReleaseCount() {
		return releaseCount;
	}

	public void setReleaseCount(int releaseCount) {
		this.releaseCount = releaseCount;
	}

	@Override
	public String toString() {
		return "[会员名=" + memberName + ", 发布房源数=" + releaseCount + "]";
	}
	
	
}
