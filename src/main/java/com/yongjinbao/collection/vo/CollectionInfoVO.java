package com.yongjinbao.collection.vo;

import com.yongjinbao.collection.entity.Collection;

public class CollectionInfoVO {
	
	/** 征集房源信息 **/
	private Collection collection;
	
	/** 征集房源信息对应的回答数**/
	private int responseCount;

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public int getResponseCount() {
		return responseCount;
	}

	public void setResponseCount(int responseCount) {
		this.responseCount = responseCount;
	}
}
