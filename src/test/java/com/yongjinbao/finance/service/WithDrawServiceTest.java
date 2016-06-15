package com.yongjinbao.finance.service;

import com.yongjinbao.finance.dao.IWithDrawDao;
import com.yongjinbao.finance.dto.ApplyWithDrawDto;
import com.yongjinbao.finance.dto.GetFrozenWithDrawListDto;
import com.yongjinbao.finance.dto.GetWithDrawDto;
import com.yongjinbao.finance.entity.WithDraw;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.entity.SystemContext;
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
public class WithDrawServiceTest {
    @Inject
    private IWithDrawService withDrawService;

    @Test
    public void getHouseInfoPage(){
        GetWithDrawDto getWithDrawDto=new GetWithDrawDto();
        SystemContext.setPageSize(10);
        SystemContext.setPageOffset(1);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
        Pager<WithDraw> pager= withDrawService.getWithDrawList(getWithDrawDto);
        /*for(WithDraw withDraw:pager.getList())
            System.out.println(withDraw.getId());*/
    }
    @Test
    public void getFrozenWithDrawList(){
        GetFrozenWithDrawListDto getFrozenWithDrawListDto=new GetFrozenWithDrawListDto();
        SystemContext.setPageSize(10);
        SystemContext.setPageOffset(1);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
        Pager<WithDraw> pager= withDrawService.getFrozenWithDrawList(getFrozenWithDrawListDto);
        for(WithDraw withDraw:pager.getList())
            System.out.println(withDraw.getId());
    }
}
