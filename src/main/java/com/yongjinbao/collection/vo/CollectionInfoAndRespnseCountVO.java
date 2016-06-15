package com.yongjinbao.collection.vo;

import com.yongjinbao.collection.entity.Collection;

public class CollectionInfoAndRespnseCountVO {
	
	private int responseCount;
	private Collection collection;
	public int getResponseCount() {
		return responseCount;
	}
	public void setResponseCount(int responseCount) {
		this.responseCount = responseCount;
	}
	public Collection getCollection() {
		return collection;
	}
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
}
