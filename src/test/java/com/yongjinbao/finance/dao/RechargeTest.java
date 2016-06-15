package com.yongjinbao.finance.dao;

import com.yongjinbao.finance.dto.ApplyRechargeDto;
import com.yongjinbao.finance.dto.GetRechargeDto;
import com.yongjinbao.finance.entity.Recharge;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by fddxiaohui on 2015/8/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class RechargeTest {
    @Inject
    private IRechargeDao rechargeDao;
    @Test
    public void getRechargeTest(){
        GetRechargeDto getRechargeDto=new GetRechargeDto();
        getRechargeDto.setMember_id(1);
        getRechargeDto.setBeginDate("14-08-18 15:59:57");
        getRechargeDto.setEndDate("16-08-18 15:59:57");
        List<Recharge> list= rechargeDao.getRechargeList(getRechargeDto);
        for(Recharge recharge:list)
            System.out.println(recharge.getId());
    }
}
