package com.yongjinbao.finance.dao;

import java.util.List;

import com.yongjinbao.finance.dto.ApplyRechargeDto;
import com.yongjinbao.finance.dto.ApplyWithDrawDto;
import com.yongjinbao.finance.dto.GetFrozenDetailListDto;
import com.yongjinbao.finance.dto.GetFrozenWithDrawListDto;
import com.yongjinbao.finance.dto.GetWithDrawDto;
import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.finance.vo.FrozenDetailVO;
import com.yongjinbao.finance.vo.WithDrawVO;
import com.yongjinbao.mybatis.dao.IBaseDao;

/**
 * Created by yanfeng on 2015/8/17
 * alter by xiaohui on 2015/8/18
 */
public interface IWithDrawDao extends IBaseDao<WithDraw,Integer> {
	/**
	 * 提现记录查询
	 */
	public List<WithDrawVO> getWithDrawList(GetWithDrawDto getWithDrawDto);

    /**
     * 查询提现记录总数
     * @param getWithDrawDto
     * @return
     */
    public int getWithDrawListCount(GetWithDrawDto getWithDrawDto);
	
	/**
	 * 冻结明细记录查询
	 */
	public List<WithDraw> getFrozenWithDrawList(GetFrozenWithDrawListDto getFrozenWithDrawListDto);

    /**
     * 查询冻结明细总数
     * @param getFrozenWithDrawListDto
     * @return
     */
    public int getFrozenWithDrawListCount(GetFrozenWithDrawListDto getFrozenWithDrawListDto);
    
    /**【冻结列表】2015年10月7日 新增征集房源冻结记录**/
    public List<FrozenDetailVO> getFrozenDetailList(GetFrozenDetailListDto dto);
    
    /**【冻结数】2015年10月7日 新增征集房源冻结记录**/
    public int getFrozenDetailListCount(GetFrozenDetailListDto dto);
    
    /** 2015年10月17日 最新提现列表**/
    public List<WithDraw> getLatesWithDraw();
    
    /** 2015年10月26日15:01:26 手机端获取累积提现总额**/
    public float getTotalWithdrawAmount(long member_id);
    
}
