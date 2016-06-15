package com.yongjinbao.finance.service;

import com.yongjinbao.finance.dto.GetRechargeDto;
import com.yongjinbao.finance.entity.Recharge;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.IBaseService;

/**
 * Created by yanfeng on 2015/8/17.
 */
public interface IRechargeService extends IBaseService<Recharge,Integer>{
	
	/**
	 * 充值记录查询
	 */
	public <T>Pager<T> getRechargeList(GetRechargeDto getRechargeDto);
	
	/**
	 * 添加充值信息
	 */
	public boolean addRecharge(Recharge recharge);
	
}
