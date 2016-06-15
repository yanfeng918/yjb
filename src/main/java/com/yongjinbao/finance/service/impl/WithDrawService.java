package com.yongjinbao.finance.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yongjinbao.finance.dao.IWithDrawDao;
import com.yongjinbao.finance.dto.GetFrozenDetailListDto;
import com.yongjinbao.finance.dto.GetFrozenWithDrawListDto;
import com.yongjinbao.finance.dto.GetWithDrawDto;
import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.finance.service.IWithDrawService;
import com.yongjinbao.finance.vo.FrozenDetailVO;
import com.yongjinbao.finance.vo.WithDrawVO;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
@Service
public class WithDrawService extends BaseServiceImpl<WithDraw,Integer> implements IWithDrawService{
	
    @Inject
    private IWithDrawDao withDrawDao;
    
    @Inject
    public void setBaseDao(IWithDrawDao withDrawDao) {
        super.setBaseDao(withDrawDao);
    }
	@Override
	public <T>Pager<T> getWithDrawList(GetWithDrawDto getWithDrawDto) {
        getWithDrawDto.setPageOffset((getWithDrawDto.getPageNumber()-1)*getWithDrawDto.getPageSize());
        Pager<WithDrawVO> pages=new Pager<WithDrawVO>();
        List<WithDrawVO> list=withDrawDao.getWithDrawList(getWithDrawDto);
        pages.setList(list);
        pages.setPageOffset(getWithDrawDto.getPageOffset());
        pages.setPageSize(getWithDrawDto.getPageSize());
        int total=withDrawDao.getWithDrawListCount(getWithDrawDto);
        pages.setTotalCount(total);
        return (Pager<T>) pages;
	}

	@Override
	public <T>Pager<T> getFrozenWithDrawList(GetFrozenWithDrawListDto getFrozenWithDrawListDto) {
        getFrozenWithDrawListDto.setPageOffset((getFrozenWithDrawListDto.getPageNumber()-1)*getFrozenWithDrawListDto.getPageSize());
        Pager<WithDraw> pages=new Pager<WithDraw>();
        List<WithDraw> list=withDrawDao.getFrozenWithDrawList(getFrozenWithDrawListDto);
        pages.setList(list);
        pages.setPageOffset(getFrozenWithDrawListDto.getPageOffset());
        pages.setPageSize(getFrozenWithDrawListDto.getPageSize());
        int total=withDrawDao.getFrozenWithDrawListCount(getFrozenWithDrawListDto);
        pages.setTotalCount(total);
        return (Pager<T>) pages;
	}
	
	@Override
	public <T> Pager<T> getFrozenDetailList(GetFrozenDetailListDto dto) {
		dto.setPageOffset((dto.getPageNumber()-1)*dto.getPageSize());
        Pager<FrozenDetailVO> pages=new Pager<FrozenDetailVO>();
        List<FrozenDetailVO> list=withDrawDao.getFrozenDetailList(dto);
        pages.setList(list);
        pages.setPageOffset(dto.getPageOffset());
        pages.setPageSize(dto.getPageSize());
        int total=withDrawDao.getFrozenDetailListCount(dto);
        pages.setTotalCount(total);
        return (Pager<T>) pages;
	}
	
	/** 2015年10月17日 最新提现列表**/
    public List<WithDraw> getLatesWithDraw(){
    	return withDrawDao.getLatesWithDraw();
    }
    
    @Override
    public float getTotalWithdrawAmount(long member_id) {
    	return withDrawDao.getTotalWithdrawAmount(member_id);
    }
}
