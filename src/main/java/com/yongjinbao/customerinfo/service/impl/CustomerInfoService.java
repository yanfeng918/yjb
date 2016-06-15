package com.yongjinbao.customerinfo.service.impl;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.yongjinbao.customerinfo.dao.ICustomerInfoDao;
import com.yongjinbao.customerinfo.dto.GetCustomerInfoDto;
import com.yongjinbao.customerinfo.entity.CustomerInfo;
import com.yongjinbao.customerinfo.service.ICustomerInfoService;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.entity.SystemContext;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;

/**
 * Created by fddxiaohui on 2015/8/20.
 */
@Service
public class CustomerInfoService extends BaseServiceImpl<CustomerInfo,Integer> implements ICustomerInfoService{
    @Inject
    private ICustomerInfoDao customerInfoDao;

    @Inject
    public void setBaseDao(ICustomerInfoDao customerInfoDao) {
        super.setBaseDao(customerInfoDao);
    }

    @Override
    public <T> Pager<T> getCustomerInfo(GetCustomerInfoDto getCustomerInfoDto) {
        getCustomerInfoDto.setPageSize(SystemContext.getPageSize());
        getCustomerInfoDto.setPageOffset((SystemContext.getPageOffset()-1)*SystemContext.getPageSize());
        getCustomerInfoDto.setOrder(SystemContext.getOrder());
        getCustomerInfoDto.setSort(SystemContext.getSort());
        Pager<CustomerInfo> pages=new Pager<CustomerInfo>();
        List<CustomerInfo> list=customerInfoDao.getCustomerInfo(getCustomerInfoDto);
        pages.setList(list);
        pages.setPageOffset(SystemContext.getPageOffset());
        pages.setPageSize(SystemContext.getPageSize());
        int total=customerInfoDao.getCustomerInfoCount(getCustomerInfoDto);
        pages.setTotalCount(total);
        return (Pager<T>) pages;
    }
}
