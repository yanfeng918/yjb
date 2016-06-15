package com.yongjinbao.collection.dto;

import com.yongjinbao.collection.entity.Collection.Collection_STATUS;
import com.yongjinbao.commons.dto.BaseDto;

public class GetCollectionDto extends BaseDto{
	
	/**区域id**/
    private int area_id;
    
    /**小区名**/
    private String community;
    
    private Collection_STATUS status;

	public int getArea_id() {
		return area_id;
	}

	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}

	public Collection_STATUS getStatus() {
		return status;
	}

	public void setStatus(Collection_STATUS status) {
		this.status = status;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}
	
}
