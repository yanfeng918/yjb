package com.yongjinbao.houseValid.controller;

import com.yongjinbao.commons.Constants;
import com.yongjinbao.commons.service.IAreaService;
import com.yongjinbao.houseValid.dto.GetCommunityV2Dto;
import com.yongjinbao.houseValid.dto.GetHouseInfoDto;
import com.yongjinbao.houseValid.entity.HouseInfoValid;
import com.yongjinbao.houseValid.service.IHouseInfoValidService;
import com.yongjinbao.houseValid.vo.*;
import com.yongjinbao.houseinfo.entity.Community;
import com.yongjinbao.houseinfo.vo.HouseInfoValiddFavouriteStatusVO;
import com.yongjinbao.member.dto.UpdateBalanceDto;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fddxiaohui on 2015/8/24.
 */
@Controller
@RequestMapping("/houseInfoValid")
public class HouseInfoValidController {

    @Inject
    private IHouseInfoValidService houseInfoService;
    
    @Inject
    private IMemberService memberService;
    
    @Inject
    private IAreaService areaService;


    /**
     * 条件查询房屋信息列表
     * @param getHouseInfoDto
     * @return
     */
    @RequestMapping(value = "getHouseInfoList")
    @ResponseBody
    public Pager<HouseInfoValiddFavouriteStatusVO> getHouseInfoList(GetHouseInfoDto getHouseInfoDto, HttpServletRequest request){
        if(getHouseInfoDto.getAreaSize()!=null&&getHouseInfoDto.getAreaSize().split(",").length==2){
            getHouseInfoDto.setMinAreaSize(Integer.parseInt(getHouseInfoDto.getAreaSize().split(",")[0]));
            getHouseInfoDto.setMaxAreaSize(Integer.parseInt(getHouseInfoDto.getAreaSize().split(",")[1]));
        }
        if(getHouseInfoDto.getSalePrice()!=null&&getHouseInfoDto.getSalePrice().split(",").length==2){
            getHouseInfoDto.setMinSalePrice(Integer.parseInt(getHouseInfoDto.getSalePrice().split(",")[0]));
            getHouseInfoDto.setMaxSalePrice(Integer.parseInt(getHouseInfoDto.getSalePrice().split(",")[1]));
        }
//        getHouseInfoDto.setStatus(HouseInfo_STATUS.SUCCESS.name());
        getHouseInfoDto.setAvailable(true);
        //【修改 2015年9月18】此member_id用于判断是否属于自己的收藏
//        getHouseInfoDto.setMember_id(memberService.getMemberId(request));
        Pager<HouseInfoValiddFavouriteStatusVO> pager=houseInfoService.getHouseInfo(getHouseInfoDto);
		List<HouseInfoValiddFavouriteStatusVO> list = pager.getList();
		HouseInfoValiddFavouriteStatusVO[] data = list.toArray(new HouseInfoValiddFavouriteStatusVO[list.size()]);
		pager.setList(null);
		pager.setData(data);
        return pager;
     }

    
	/**
	 * 确认购买信息时，返回房源信息
	 * @param houseInfo_id
	 * @return
	 */
	@RequestMapping(value = "auth/browseHouseInfo", method = RequestMethod.GET)
	public @ResponseBody
	HouseInfoValid browseHouseInfo(long houseInfo_id, HttpServletRequest request) {
		BrowseHouseInfoVO vo = houseInfoService.getBrowseHouseInfoVO(houseInfo_id, request);
		if(vo.isBought()||!vo.isBalanceEnough()){
			return vo.getHouseInfoValid();
		}
		//获取房源信息
		HouseInfoValid houseInfoValid = new HouseInfoValid();
		houseInfoValid = houseInfoService.browseHouseInfoByHouseId(houseInfo_id);
		Member loginMember = memberService.getCurrent(request);
		//自定义房源，如果查看3次后自动将所有收转入系统账号中【该逻辑包含在以下】
		Member houseInfoMember = houseInfoService.getHouseInfoMember(houseInfoValid, false);
		houseInfoValid.setMember(houseInfoMember);
		//更新账户余额状态
		//收入者增加余额【即为房源信息提供会员】

		//modify here yanfeng 系统扣除30%的手续费
		Member systemMember = memberService.getSystemMember();

		houseInfoService.updateBalanceInfo(systemMember,
					Float.parseFloat(String.valueOf(Constants.infoPrice)), UpdateBalanceDto.INCREASE);

		//支出者减少余额【即为查看数据会员/登录会员】
		if (loginMember.getId()==systemMember.getId()) {
			//备用，系统登录时，更新一次系统账户信息
			//loginMember = memberService.getSystemMember();
		}
		houseInfoService.updateBalanceInfo(loginMember, 
				Float.parseFloat(String.valueOf(Constants.infoPrice)), UpdateBalanceDto.REDUCE);
		//更改收支明细、并写入我的查看房源信息
		houseInfoService.addIncomeExpenseAndBrowseInfo(houseInfoMember, loginMember, houseInfoValid);
		return houseInfoValid;
	}
	
	/**
	 * 获取房源查看操作VO
	 */
	@RequestMapping(value = "/auth/getBrowseHouseInfoVO", method = RequestMethod.GET)
	public @ResponseBody
	BrowseHouseInfoVO getBrowseHouseInfoVO(long houseInfo_id, HttpServletRequest request) {
		return houseInfoService.getBrowseHouseInfoVO(houseInfo_id, request);
	}
	



    /**
	 * 获取关键字楼盘字典
	 * @since 2015年11月11日
	 * @author masw
	 * @return
	 */
	@RequestMapping(value = "/getCommunity", method = RequestMethod.GET)
	@ResponseBody
	public List<Community>  getCommunity(String communityKeyWords, HttpServletRequest request){
		return houseInfoService.getTopTenCommunity(communityKeyWords);
	}
	
    /**
	 * 获取关键字楼盘字典
	 * @since 2015年11月11日
	 * @author masw
	 * @return
	 */
	@RequestMapping(value = "/getCommunityV2", method = RequestMethod.GET)
	@ResponseBody
	public List<Community>  getCommunityV2(GetCommunityV2Dto getCommunityV2Dto, HttpServletRequest request){
		return houseInfoService.getTopTenCommunityV2(getCommunityV2Dto);
	}


	/**
	 * 获取已经购买的房源
	 */
	@RequestMapping(value = "/auth/getBoughtHouseInfo", method = RequestMethod.GET)
	public @ResponseBody
	HouseInfoValid getBoughtHouseInfo(long houseInfo_id, HttpServletRequest request) {
		return houseInfoService.getBoughtHouseInfo(houseInfo_id, request);
	}

	
    
}
