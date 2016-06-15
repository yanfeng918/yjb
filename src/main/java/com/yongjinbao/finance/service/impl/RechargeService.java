package com.yongjinbao.finance.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yongjinbao.finance.dao.IRechargeDao;
import com.yongjinbao.finance.dto.GetRechargeDto;
import com.yongjinbao.finance.entity.Recharge;
import com.yongjinbao.finance.service.IRechargeService;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
@Service
public class RechargeService extends BaseServiceImpl<Recharge,Integer> implements IRechargeService{
	
    @Inject
    private IRechargeDao rechargeDao;
    
    @Inject
    public void setBaseDao(IRechargeDao rechargeDao) {
        super.setBaseDao(rechargeDao);
    }

	@Override
	public <T>Pager<T> getRechargeList(GetRechargeDto getRechargeDto) {
		// TODO Auto-generated method stub
        getRechargeDto.setPageOffset((getRechargeDto.getPageNumber()-1)*getRechargeDto.getPageSize());
        Pager<Recharge> pages=new Pager<Recharge>();
        List<Recharge> list=rechargeDao.getRechargeList(getRechargeDto);
        pages.setList(list);
        pages.setPageOffset(getRechargeDto.getPageOffset());
        pages.setPageSize(getRechargeDto.getPageSize());
        int total=rechargeDao.getRechargeListCount(getRechargeDto);
        pages.setTotalCount(total);
        return (Pager<T>) pages;
	}

	@Override
	public boolean addRecharge(Recharge recharge) {
		// TODO Auto-generated method stub
		return rechargeDao.addRecharge(recharge);
	}
}
