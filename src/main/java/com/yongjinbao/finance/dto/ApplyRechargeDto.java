package com.yongjinbao.finance.dto;

import com.yongjinbao.commons.dto.BaseDto;

/**
 * Created by fddxiaohui on 2015/8/19.
 */
public class ApplyRechargeDto extends BaseDto {
    /**账号id**/
    private int bankAccount_id;
    /**业务状态**/
    private int status;
    /**充值数量**/
    private float amount;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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
}
