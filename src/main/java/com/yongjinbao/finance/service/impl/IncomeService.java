package com.yongjinbao.finance.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yongjinbao.finance.dao.IIncomeDao;
import com.yongjinbao.finance.dto.GetIncomeDto;
import com.yongjinbao.finance.dto.GetIncomeExpenseDto;
import com.yongjinbao.finance.entity.Income;
import com.yongjinbao.finance.service.IIncomeService;
import com.yongjinbao.finance.vo.IncomeAmountVO;
import com.yongjinbao.finance.vo.IncomeExpenseVO;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.houseinfo.service.IHouseInfoService;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
@Service
public class IncomeService extends BaseServiceImpl<Income,Integer> implements IIncomeService{
	
    @Inject
    private IIncomeDao incomeDao;
    
    @Inject
    private IHouseInfoService houseInfoService;
    
    @Inject
    public void setBaseDao(IIncomeDao incomeDao) {
        super.setBaseDao(incomeDao);
    }

	@Override
	public <T>Pager<T> getIncomeList(GetIncomeDto getIncomeDto) {
        getIncomeDto.setPageOffset((getIncomeDto.getPageNumber()-1)*getIncomeDto.getPageSize());
        Pager<Income> pages=new Pager<Income>();
        List<Income> list=incomeDao.getIncomeList(getIncomeDto);
        pages.setList(list);
        pages.setPageOffset(getIncomeDto.getPageOffset());
        pages.setPageSize(getIncomeDto.getPageSize());
        int total=incomeDao.getIncomeListCount(getIncomeDto);
        pages.setTotalCount(total);
        return (Pager<T>) pages;
	}

	@Override
	public void addIncomeInfo(Income income) {
		incomeDao.add(income);
	}
	
	@Override
	public <T>Pager<T> getMyIncomeExpenseList(GetIncomeExpenseDto getIncomeExpenseDto) {
		getIncomeExpenseDto.setPageOffset((getIncomeExpenseDto.getPageNumber()-1)*getIncomeExpenseDto.getPageSize());
		Pager<IncomeExpenseVO> pages=new Pager<IncomeExpenseVO>();
		List<IncomeExpenseVO> list = incomeDao.getMyIncomeExpenseList(getIncomeExpenseDto);
		pages.setList(list);
		for (IncomeExpenseVO incomeExpenseVO : list) {
			Long houseInfo_id = incomeExpenseVO.getHouseInfo_id();
			if (houseInfo_id!=null&&houseInfo_id!=0) {
				HouseInfo houseInfo = new HouseInfo();
				if (!incomeExpenseVO.getDetail().equals("inviteReleaseIncome")) {
					houseInfo = houseInfoService.loadHouseInfo(incomeExpenseVO.getHouseInfo_id());
					incomeExpenseVO.setHouseInfo(houseInfo);
				}else {
					//推广福利
					houseInfo=houseInfoService.loadHouseInfoWithMember(incomeExpenseVO.getHouseInfo_id());
//					houseInfo.set
					HouseInfo temp = new HouseInfo();
					temp.setId(houseInfo.getId());
					temp.setCommunity(houseInfo.getCommunity());
					temp.setMember(houseInfo.getMember());
					incomeExpenseVO.setHouseInfo(temp);
				}
			}else {
				//没有房源ID的则为充值或提现记录
			}
		}
		pages.setPageOffset(getIncomeExpenseDto.getPageOffset());
        pages.setPageSize(getIncomeExpenseDto.getPageSize());
        int total = incomeDao.getIncomeExpenseCount(getIncomeExpenseDto);
        pages.setTotalCount(total);
		return (Pager<T>) pages;
	}
	
	@Override
	public IncomeAmountVO getIncomeAmountByDate(long member_id) {
		return incomeDao.getIncomeAmountByDate(member_id);
	}
}
