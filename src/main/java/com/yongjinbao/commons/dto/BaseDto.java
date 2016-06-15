package com.yongjinbao.commons.dto;

/**
 * Created by fddxiaohui on 2015/8/18.
 */
public class BaseDto {
    /**开始时间**/
    private String beginDate;
    /**结束时间**/
    private String endDate;
    /**会员id**/
    private long member_id;
    /**分页的页码大小**/
    private int pageSize;
    /**分页的偏移量 pageSize*(pageNun-1) **/
    private int pageOffset;
    /**排序字段**/
    private String sort;
    /**排序升降序**/
    private String order;
    /**页码**/
    private int pageNumber;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getMember_id() {
        return member_id;
    }

    public void setMember_id(long member_id) {
        this.member_id = member_id;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
    
}
