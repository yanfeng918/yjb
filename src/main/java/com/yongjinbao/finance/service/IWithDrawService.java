package com.yongjinbao.finance.service;

import java.util.List;

import com.yongjinbao.finance.dto.ApplyWithDrawDto;
import com.yongjinbao.finance.dto.GetFrozenDetailListDto;
import com.yongjinbao.finance.dto.GetFrozenWithDrawListDto;
import com.yongjinbao.finance.dto.GetWithDrawDto;
import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.finance.vo.FrozenDetailVO;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.IBaseService;

/**
 * Created by yanfeng on 2015/8/17.
 */
public interface IWithDrawService extends IBaseService<WithDraw,Integer>{
	
	/**
	 * 提现记录查询
	 */
	public <T>Pager<T> getWithDrawList(GetWithDrawDto getWithDrawDto);
	
	/**
	 * 冻结明细记录查询
	 */
	public <T>Pager<T> getFrozenWithDrawList(GetFrozenWithDrawListDto getFrozenWithDrawListDto);
	
	/**【冻结列表】2015年10月7日 新增征集房源冻结记录**/
    public <T>Pager<T> getFrozenDetailList(GetFrozenDetailListDto dto);
    
    /** 2015年10月17日 最新提现列表**/
    public List<WithDraw> getLatesWithDraw();
    
    /** 2015年10月26日15:01:26 手机端获取累积提现总额**/
    public float getTotalWithdrawAmount(long member_id);
	
}
