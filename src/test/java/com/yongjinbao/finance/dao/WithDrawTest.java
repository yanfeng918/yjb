package com.yongjinbao.finance.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yongjinbao.finance.dto.GetFrozenWithDrawListDto;
import com.yongjinbao.finance.dto.GetWithDrawDto;
import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.finance.vo.WithDrawVO;

/**
 * Created by fddxiaohui on 2015/8/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class WithDrawTest {
    @Inject
    private IWithDrawDao withDrawDao;


    //提现记录查询
    @Test
    public void getWithDrawList(){
        GetWithDrawDto getWithDrawDto=new GetWithDrawDto();
        getWithDrawDto.setMember_id(22);
        getWithDrawDto.setEndDate("16-08-18 15:59:57");
        getWithDrawDto.setBeginDate("14-08-18 15:59:57");
        List<WithDrawVO> list= withDrawDao.getWithDrawList(getWithDrawDto);
        for(WithDrawVO withDraw:list)
            System.out.println(withDraw.getStatus().getOutername());
    }
    //冻结记录查询
    @Test
    public void getFrozenWithDrawList(){
        GetFrozenWithDrawListDto getFrozenWithDrawListDto=new GetFrozenWithDrawListDto();
        getFrozenWithDrawListDto.setMember_id(22);
        getFrozenWithDrawListDto.setStatus(WithDraw.WithDraw_STATUS.APPLY);

        List<WithDraw> list= withDrawDao.getFrozenWithDrawList(getFrozenWithDrawListDto);
        for(WithDraw withDraw:list)
            System.out.println(withDraw.getId());


    }
}
