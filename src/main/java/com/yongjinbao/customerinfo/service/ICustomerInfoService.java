package com.yongjinbao.customerinfo.service;

import com.yongjinbao.customerinfo.dto.GetCustomerInfoDto;
import com.yongjinbao.customerinfo.entity.CustomerInfo;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.IBaseService;

/**
 * Created by fddxiaohui on 2015/8/20.
 */
public interface ICustomerInfoService extends IBaseService<CustomerInfo,Integer> {
    public <T>Pager<T> getCustomerInfo(GetCustomerInfoDto getCustomerInfoDto);
}
