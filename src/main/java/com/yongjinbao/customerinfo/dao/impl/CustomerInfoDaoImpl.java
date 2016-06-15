package com.yongjinbao.customerinfo.dao.impl;

import com.yongjinbao.customerinfo.dao.ICustomerInfoDao;
import com.yongjinbao.customerinfo.dto.GetCustomerInfoDto;
import com.yongjinbao.customerinfo.entity.CustomerInfo;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fddxiaohui on 2015/8/20.
 */
@Repository
public class CustomerInfoDaoImpl extends BaseDaoImpl<CustomerInfo,Integer> implements ICustomerInfoDao{
    /**
     * 查询 地区 售价 面积 小区名称等等
     */
    @Override
    public List<CustomerInfo> getCustomerInfo(GetCustomerInfoDto getCustomerInfoDto) {
        // TODO Auto-generated method stub
        List<CustomerInfo> list=getSqlSession().selectList(CustomerInfo.class.getName()+".getCustomerInfo",getCustomerInfoDto);
        return list;
    }

    @Override
    public int getCustomerInfoCount(GetCustomerInfoDto getCustomerInfoDto) {
        int count=getSqlSession().selectOne(CustomerInfo.class.getName()+".getCustomerInfoCount",getCustomerInfoDto);
        return count;
    }
}
