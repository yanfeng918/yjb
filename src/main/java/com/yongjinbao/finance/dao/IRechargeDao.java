package com.yongjinbao.finance.dao;

import java.util.List;

import com.yongjinbao.finance.dto.ApplyRechargeDto;
import com.yongjinbao.finance.dto.GetRechargeDto;
import com.yongjinbao.finance.entity.Recharge;
import com.yongjinbao.mybatis.dao.IBaseDao;

/**
 * Created by yanfeng on 2015/8/17
 * alter by xiaohui on 2015/8/18
 */
public interface IRechargeDao extends IBaseDao<Recharge,Integer> {
	/**
	 * 充值记录查询
	 * @return 
	 */
	public List<Recharge> getRechargeList(GetRechargeDto getRechargeDto);
    public int getRechargeListCount(GetRechargeDto getRechargeDto);
    
    /**
     * 添加充值记录
     */
    public boolean addRecharge(Recharge recharge);
}
