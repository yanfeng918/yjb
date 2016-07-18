package com.yongjinbao.enums.house;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yanfeng on 2016/7/18.
 */
@AllArgsConstructor
public enum HouseTypeEnum {
    DEFLAUT(0, "默认"),
    VALID(1, "有效房源"),
    NEW(2, "最新房源"),
    PROPERTY(3,"物业房源");
    @Getter
    @Setter
    private Integer code;
    @Getter
    @Setter
    private String desc;

    public static HouseTypeEnum getValue(Integer code) {
        if (code == null) {
            return null;
        }
        final HouseTypeEnum[] values = HouseTypeEnum.values();
        for (HouseTypeEnum value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
