package com.yongjinbao.enums.expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yanfeng on 2016/7/18.
 */
@AllArgsConstructor
public enum EXPENSES_TYPE {

    dealExpense(1,"会员-交易支出"),promoteWelfare(2,"系统-推广福利"),
    extraAward(3,"系统-额外奖励"),activityReward(4,"系统-活动奖励");
    @Getter
    @Setter
    private Integer code;
    @Getter
    @Setter
    private String desc;

    public static EXPENSES_TYPE getValue(Integer code) {
        if (code == null) {
            return null;
        }
        final EXPENSES_TYPE[] values = EXPENSES_TYPE.values();
        for (EXPENSES_TYPE value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
