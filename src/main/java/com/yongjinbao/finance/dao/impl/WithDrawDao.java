package com.yongjinbao.finance.dao.impl;

import java.util.List;

import com.yongjinbao.finance.dto.ApplyWithDrawDto;
import com.yongjinbao.finance.dto.GetFrozenDetailListDto;
import com.yongjinbao.finance.dto.GetFrozenWithDrawListDto;
import com.yongjinbao.finance.dto.GetWithDrawDto;
import com.yongjinbao.finance.entity.Recharge;
import com.yongjinbao.finance.vo.FrozenDetailVO;
import com.yongjinbao.finance.vo.WithDrawVO;
import org.springframework.stereotype.Repository;

import com.yongjinbao.finance.dao.IWithDrawDao;
import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

/**
 * Created by yanfeng on 2015/8/17
 * alter by xiaohui on 2015/8/18
 */
@Repository
public class WithDrawDao extends BaseDaoImpl<WithDraw,Integer> implements IWithDrawDao {
	@Override
	public List<WithDrawVO> getWithDrawList(GetWithDrawDto getWithDrawDto) {
		// TODO Auto-generated method stub
        List<WithDrawVO> list=getSqlSession().selectList(WithDraw.class.getName()+".getWithDrawList",getWithDrawDto);
        return list;
	}

	@Override
	public List<WithDraw> getFrozenWithDrawList(GetFrozenWithDrawListDto getFrozenWithDrawListDto) {
		// TODO Auto-generated method stub
		List<WithDraw> list=getSqlSession().selectList(WithDraw.class.getName()+".getFrozenWithDrawList",getFrozenWithDrawListDto);
        return list;
	}

    @Override
    public int getWithDrawListCount(GetWithDrawDto getWithDrawDto) {
        int count=getSqlSession().selectOne(WithDraw.class.getName()+".getWithDrawListCount",getWithDrawDto);
        return count;
    }

    @Override
    public int getFrozenWithDrawListCount(GetFrozenWithDrawListDto getFrozenWithDrawListDto) {
        int count=getSqlSession().selectOne(WithDraw.class.getName()+".getFrozenWithDrawListCount",getFrozenWithDrawListDto);
        return count;
    }
    
    @Override
    public List<FrozenDetailVO> getFrozenDetailList(GetFrozenDetailListDto dto) {
    	List<FrozenDetailVO> list=getSqlSession().selectList(WithDraw.class.getName()+".getFrozenDetailList",dto);
        return list;
    }
    
    @Override
    public int getFrozenDetailListCount(GetFrozenDetailListDto dto) {
    	int count=getSqlSession().selectOne(WithDraw.class.getName()+".getFrozenDetailListCount",dto);
        return count;
    }
    
    @Override
    public List<WithDraw> getLatesWithDraw() {
    	List<WithDraw> list= getSqlSession().selectList(WithDraw.class.getName()+".getLatestWithDrawInfo");
    	return list;
    }
    
    @Override
    public float getTotalWithdrawAmount(long member_id) {
    	Float sum=getSqlSession().selectOne(WithDraw.class.getName()+".getTotalWithdrawAmount", member_id);
    	return (sum==null)?0:sum;
    }
}
