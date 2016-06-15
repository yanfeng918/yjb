package com.yongjinbao.houseinfo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.houseinfo.dto.BonusProcessDto;
import com.yongjinbao.houseinfo.service.IHouseInfoService;
import com.yongjinbao.houseinfo.vo.BonusVO;
import com.yongjinbao.houseinfo.vo.LevelDetailVO;
import com.yongjinbao.member.service.IMemberService;

/**
 * 额外奖励Controller层
 * @since 2015年11月12日16:11
 */
@Controller
@RequestMapping("/extraBonus")
public class ExtraBonusController {

    @Inject
    private IHouseInfoService houseInfoService;
    
    @Inject
    private IMemberService memberService;

    /**
     * 获取奖励信息
     */
    @RequestMapping(value = "auth/getBonusInfo",method = RequestMethod.GET)
    @ResponseBody
    public BonusVO getBonusInfo(int yearMonth,HttpServletRequest request){
    	BonusProcessDto dto = new BonusProcessDto();
    	dto.setMember_id(memberService.getMemberId(request));
    	dto.setYearMonth(yearMonth);
        return houseInfoService.getBonusInfo(dto);
     }
    
    /**
     * 1级会员及各自发布成功数量
     */
    @RequestMapping(value = "auth/getLevel1Detail",method = RequestMethod.GET)
    @ResponseBody
    public List<LevelDetailVO> getLevel1Detail(int yearMonth,HttpServletRequest request){
    	BonusProcessDto dto = new BonusProcessDto();
    	dto.setMember_id(memberService.getMemberId(request));
    	dto.setYearMonth(yearMonth);
        return houseInfoService.getLevel1Detail(dto);
     }
    
    /**
     * 2级会员及各自发布成功数量
     */
    @RequestMapping(value = "auth/getLevel2Detail",method = RequestMethod.GET)
    @ResponseBody
    public List<LevelDetailVO> getLevel2Detail(int yearMonth,HttpServletRequest request){
    	BonusProcessDto dto = new BonusProcessDto();
    	dto.setMember_id(memberService.getMemberId(request));
    	dto.setYearMonth(yearMonth);
        return houseInfoService.getLevel2Detail(dto);
     }
}