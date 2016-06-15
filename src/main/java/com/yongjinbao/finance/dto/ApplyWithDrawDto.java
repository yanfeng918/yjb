package com.yongjinbao.finance.dto;

import com.yongjinbao.commons.dto.BaseDto;
import com.yongjinbao.finance.entity.WithDraw;

/**
 * Created by fddxiaohui on 2015/8/19.
 */
public class ApplyWithDrawDto extends BaseDto {
    /**账号id**/
    private int bankAccount_id;
    /**业务状态**/
    private WithDraw.WithDraw_STATUS status;
    /**提现数量**/
    private float amount;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getBankAccount_id() {
        return bankAccount_id;
    }

    public void setBankAccount_id(int bankAccount_id) {
        this.bankAccount_id = bankAccount_id;
    }

    public WithDraw.WithDraw_STATUS getStatus() {
        return status;
    }

    public void setStatus(WithDraw.WithDraw_STATUS status) {
        this.status = status;
    }
}
