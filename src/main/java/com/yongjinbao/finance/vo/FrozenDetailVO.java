package com.yongjinbao.finance.vo;


import com.yongjinbao.commons.vo.BaseVO;
import com.yongjinbao.finance.entity.WithDraw;

/**
 * 冻结明细表，包括提现冻结和发布征集冻结
 * Created by msw on 2015/10/07.
 */
public class FrozenDetailVO extends BaseVO {
  
	/**冻结类型**/
    private String frozenType;
    
    /**冻结金额**/
    private float frozenCount;

	public String getFrozenType() {
		return frozenType;
	}

	public void setFrozenType(String frozenType) {
		this.frozenType = frozenType;
	}

	public float getFrozenCount() {
		return frozenCount;
	}

	public void setFrozenCount(float frozenCount) {
		this.frozenCount = frozenCount;
	}
}
