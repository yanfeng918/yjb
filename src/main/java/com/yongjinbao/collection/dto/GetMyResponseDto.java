package com.yongjinbao.collection.dto;

import com.yongjinbao.commons.dto.BaseDto;

public class GetMyResponseDto extends BaseDto {
	
	
	/**【根据collected_id获取回答房源】 **/
	private long collection_id;
	
	/**【回答者ID】 **/
	private long response_member_id;
	
	public long getCollection_id() {
		return collection_id;
	}

	public void setCollection_id(long collection_id) {
		this.collection_id = collection_id;
	}

	public long getResponse_member_id() {
		return response_member_id;
	}

	public void setResponse_member_id(long response_member_id) {
		this.response_member_id = response_member_id;
	}

	
	
}
