package com.yongjinbao.finance.dto;

import com.yongjinbao.commons.dto.BaseDto;
import com.yongjinbao.finance.entity.WithDraw;

/**
 * Created by fddxiaohui on 2015/8/19.
 * 冻结明细DTO
 */
public class GetFrozenWithDrawListDto extends BaseDto {
    /**业务状态**/
    private WithDraw.WithDraw_STATUS status;

    public WithDraw.WithDraw_STATUS getStatus() {
        return status;
    }

    public void setStatus(WithDraw.WithDraw_STATUS status) {
        this.status = status;
    }
}
