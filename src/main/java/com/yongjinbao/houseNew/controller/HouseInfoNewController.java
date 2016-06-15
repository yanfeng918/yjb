package com.yongjinbao.houseNew.controller;

import com.yongjinbao.commons.service.IAreaService;
import com.yongjinbao.houseNew.dto.GetCommunityV2Dto;
import com.yongjinbao.houseNew.dto.GetHouseInfoDto;
import com.yongjinbao.houseNew.dto.StateStatusDto;
import com.yongjinbao.houseNew.entity.HouseInfoNew;
import com.yongjinbao.houseNew.service.IHouseInfoNewService;
import com.yongjinbao.houseNew.vo.BrowseHouseInfoVO;
import com.yongjinbao.houseNew.vo.GetAreaHouseCountByCityVO;
import com.yongjinbao.houseNew.vo.HouseInfoNewAndFavouriteStatusVO;
import com.yongjinbao.houseNew.vo.StateStatusVO;
import com.yongjinbao.houseinfo.entity.Community;
import com.yongjinbao.member.dto.UpdateBalanceDto;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fddxiaohui on 2015/8/24.
 */
@Controller
@RequestMapping("/houseInfoNew")
public class HouseInfoNewController {

    @Inject
    private IHouseInfoNewService houseInfoService;
    
    @Inject
    private IMemberService memberService;
    
    @Inject
    private IAreaService areaService;



    /**
     * 条件查询房屋信息列表
     * @param getHouseInfoDto
     * @return
     */
    @RequestMapping(value = "auth/getHouseInfoList")
    @ResponseBody
    public Pager<HouseInfoNewAndFavouriteStatusVO> getHouseInfoList(@ModelAttribute("getHouseInfoDto") GetHouseInfoDto getHouseInfoDto, HttpServletRequest request){
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
        Pager<HouseInfoNewAndFavouriteStatusVO> pager=houseInfoService.getHouseInfo(getHouseInfoDto);
        return pager;
     }
    /**
     * 条件查询房屋信息列表
     * @param getHouseInfoDto
     * @return
     */
    @RequestMapping(value = "/auth/getReleaseHouseInfo")
    @ResponseBody
    public Pager<HouseInfoNew> getReleaseHouseInfo(@ModelAttribute("getHouseInfoDto") GetHouseInfoDto getHouseInfoDto, HttpServletRequest request){
        getHouseInfoDto.setMember_id(houseInfoService.getMemberId(request));
        Pager<HouseInfoNew> pager=houseInfoService.getReleaseHouseInfo(getHouseInfoDto);
        return pager;
    }
    
    /**
	 * 获取区域及房源
	 */
	@RequestMapping(value = "/getAreaHouseCountByCityOld")
	public @ResponseBody
	List<GetAreaHouseCountByCityVO> getAreaHouseCountByCityOld(long area_id) {
		List<GetAreaHouseCountByCityVO> list = new ArrayList<GetAreaHouseCountByCityVO>();
		list = houseInfoService.getAreaHouseCountByCity(area_id);
		return list;
	}
	
	 /**
	 * 获取区域及房源
	 */
	@RequestMapping(value = "/getAreaHouseCountByCity", method = RequestMethod.GET)
	public @ResponseBody
	List<GetAreaHouseCountByCityVO> getAreaHouseCountByCity(long area_id) {
		List<GetAreaHouseCountByCityVO> list = new ArrayList<GetAreaHouseCountByCityVO>();
		list = houseInfoService.getAreaHouseCountByCityV2(area_id);
		return list;
	}

	/**
	 * 确认购买信息时，返回房源信息
	 * @param houseInfo_id
	 * @return
	 */
	@RequestMapping(value = "auth/browseHouseInfo", method = RequestMethod.GET)
	public @ResponseBody
	HouseInfoNew browseHouseInfo(long houseInfo_id, HttpServletRequest request) {
		BrowseHouseInfoVO vo = houseInfoService.getBrowseHouseInfoVO(houseInfo_id, request);
		if(vo.isBought()||!vo.isBalanceEnough()){
			return vo.getHouseInfoNew();
		}
		//获取房源信息
		HouseInfoNew houseInfoNew = new HouseInfoNew();
		houseInfoNew = houseInfoService.browseHouseInfoByHouseId(houseInfo_id);
		Member loginMember = memberService.getCurrent(request);
		//自定义房源，如果查看3次后自动将所有收转入系统账号中【该逻辑包含在以下】
		Member houseInfoMember = houseInfoService.getHouseInfoMember(houseInfoNew, false);
		houseInfoNew.setMember(houseInfoMember);
		//更新账户余额状态
		//收入者增加余额【即为房源信息提供会员】 
		//modify here yanfeng 系统扣除30%的手续费
		Member systemMember = memberService.getSystemMember();
		if(houseInfoMember.getId()==systemMember.getId()){
			houseInfoService.updateBalanceInfo(houseInfoMember, 
					Float.parseFloat(String.valueOf(houseInfoNew.getInfoPrice())), UpdateBalanceDto.INCREASE);
		}else{
			houseInfoService.updateBalanceInfo(houseInfoMember, 
					Float.parseFloat(String.valueOf(houseInfoNew.getInfoPrice()*0.7)), UpdateBalanceDto.INCREASE);
			houseInfoService.updateBalanceInfo(systemMember, 
					Float.parseFloat(String.valueOf(houseInfoNew.getInfoPrice()*0.3)), UpdateBalanceDto.INCREASE);
		}
		//支出者减少余额【即为查看数据会员/登录会员】
		if (loginMember.getId()==systemMember.getId()) {
			//备用，系统登录时，更新一次系统账户信息
			loginMember = memberService.getSystemMember();
		}
		houseInfoService.updateBalanceInfo(loginMember, 
				Float.parseFloat(String.valueOf(houseInfoNew.getInfoPrice())), UpdateBalanceDto.REDUCE);
		//更改收支明细、并写入我的查看房源信息
		houseInfoService.addIncomeExpenseAndBrowseInfo(houseInfoMember, loginMember, houseInfoNew);
		return houseInfoNew;
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
     * 获取今日房源数
     */
	@RequestMapping(value = "/getTodayHouseInfoCount", method = RequestMethod.GET)
	@ResponseBody
    public int getTodayHouseInfoCount(int area_id){
		return houseInfoService.getTodayHouseInfoCount(area_id);
    }

	
	/**
	 * 检查是否可以对当前房源进行申诉操作
	 * @since 2015年11月6日16:03
	 * @author masw
	 * @return
	 */
	@RequestMapping(value = "auth/checkStateStatus", method = RequestMethod.GET)
	@ResponseBody
	public StateStatusVO checkStateStatus(long houseInfo_id, HttpServletRequest request){
		StateStatusDto dto = new StateStatusDto();
		dto.setHouseInfo_id(houseInfo_id);
		dto.setMember_id(memberService.getMemberId(request));
		StateStatusVO vo= houseInfoService.checkStateStatus(dto);
		vo.setHouseInfo_id(houseInfo_id);
		return vo;
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
	

	
    
}
