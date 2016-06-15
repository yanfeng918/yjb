package com.yongjinbao.collection.vo;

import com.yongjinbao.collection.entity.Collection;

public class CollectionListAndCountVO extends Collection {

	private static final long serialVersionUID = 8420601451975257324L;

	/** 【查询征集数据列表】  模糊或精确查询时相应的数目**/
	private int totalCount;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
