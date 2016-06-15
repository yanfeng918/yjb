package com.yongjinbao.customerinfo.dao;

import com.yongjinbao.customerinfo.dto.GetCustomerInfoDto;
import com.yongjinbao.customerinfo.entity.CustomerInfo;
import com.yongjinbao.mybatis.dao.IBaseDao;

import java.util.List;

/**
 * Created by fddxiaohui on 2015/8/20.
 */
public interface ICustomerInfoDao   extends IBaseDao<CustomerInfo,Integer> {
    /**
     * 查询 地区 售价 面积 小区名称等等
     */
    public List<CustomerInfo> getCustomerInfo(GetCustomerInfoDto getCustomerInfoDto);
    /**
     * 查询数量总和
     */
    public int getCustomerInfoCount(GetCustomerInfoDto getCustomerInfoDto);
}
