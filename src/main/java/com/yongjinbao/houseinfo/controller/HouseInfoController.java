package com.yongjinbao.houseinfo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.beans.Message;
import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.commons.service.IAreaService;
import com.yongjinbao.houseinfo.dto.GetCommunityV2Dto;
import com.yongjinbao.houseinfo.dto.GetHouseInfoDto;
import com.yongjinbao.houseinfo.dto.HouseInfoExistDto;
import com.yongjinbao.houseinfo.dto.StateStatusDto;
import com.yongjinbao.houseinfo.entity.Community;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.houseinfo.entity.State;
import com.yongjinbao.houseinfo.entity.State.stateStatus;
import com.yongjinbao.houseinfo.service.IHouseInfoService;
import com.yongjinbao.houseinfo.vo.AgentDynamicVO;
import com.yongjinbao.houseinfo.vo.BatchCheckMobileVO;
import com.yongjinbao.houseinfo.vo.BrowseHouseInfoVO;
import com.yongjinbao.houseinfo.vo.GetAreaHouseCountByCityVO;
import com.yongjinbao.houseinfo.vo.HouseInfoVandFavouriteStatusVO;
import com.yongjinbao.houseinfo.vo.StateStatusVO;
import com.yongjinbao.houseinfo.vo.TopTenRankingVO;
import com.yongjinbao.houseinfo.vo.UpdateHouseInfoVO;
import com.yongjinbao.member.dto.UpdateBalanceDto;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;

/**
 * Created by fddxiaohui on 2015/8/24.
 */
@Controller
@RequestMapping("/houseInfo")
public class HouseInfoController {

    @Inject
    private IHouseInfoService houseInfoService;
    
    @Inject
    private IMemberService memberService;
    
    @Inject
    private IAreaService areaService;

    /**
     * 条件查询房屋信息列表
     * @param getHouseInfoDto
     * @return
     */
    @RequestMapping(value = "auth/getHouseInfoList",method = RequestMethod.POST)
    @ResponseBody
    public Pager<HouseInfoVandFavouriteStatusVO> getHouseInfoList(@ModelAttribute("getHouseInfoDto") GetHouseInfoDto getHouseInfoDto, HttpServletRequest request){
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
        getHouseInfoDto.setMember_id(memberService.getMemberId(request));
        Pager<HouseInfoVandFavouriteStatusVO> pager=houseInfoService.getHouseInfo(getHouseInfoDto);
        return pager;
     }
    /**
     * 条件查询房屋信息列表
     * @param getHouseInfoDto
     * @return
     */
    @RequestMapping(value = "/auth/getReleaseHouseInfo",method = RequestMethod.POST)
    @ResponseBody
    public Pager<HouseInfo> getReleaseHouseInfo(@ModelAttribute("getHouseInfoDto") GetHouseInfoDto getHouseInfoDto,HttpServletRequest request){
        getHouseInfoDto.setMember_id(houseInfoService.getMemberId(request));
        Pager<HouseInfo> pager=houseInfoService.getReleaseHouseInfo(getHouseInfoDto);
        return pager;
    }
    
    /**
     * 判断房源是否重复
     * @param houseInfo
     * @return
     */
    @RequestMapping(value = "/auth/addHouseValidate",method = RequestMethod.POST)
    @ResponseBody
    public Boolean validateHouseInfo(@ModelAttribute("addHouseInfo") HouseInfo houseInfo){
    	return houseInfoService.validateHouseInfo(houseInfo);
    }
    /**
     * 添加房屋信息
     * @param houseInfo
     * @return
     */
    @RequestMapping(value = "/auth/addHouseInfoV2",method = RequestMethod.POST)
    @ResponseBody
    public Message addHouseInfoV2(@ModelAttribute("addHouseInfo") HouseInfo houseInfo,HttpServletRequest request){
//    	if (validateHouseInfo(houseInfo)) {
//    		return new Message(Message.Type.error,"房源重复！");
//		}
        try {
            Member member=new Member();
            member.setId(houseInfoService.getMemberId(request));
            houseInfo.setAreaSize(request.getParameter("areaSize1").equals("")?0f:Float.parseFloat(request.getParameter("areaSize1")));
            houseInfo.setSalePrice(request.getParameter("salePrice1").equals("")?0f:Float.parseFloat(request.getParameter("salePrice1")));
            houseInfo.setMember(member);
            houseInfo.setHouseShape(request.getParameter("room")+"室"+request.getParameter("office")+"厅");
            Area area=new Area();
            area.setId(Long.parseLong(request.getParameter("area_id")));
            houseInfo.setArea(area);
            //2015年12月8日14:44，添加发房人与发布房源是否同一区域
            Member loginM = new Member();
            loginM = memberService.getCurrent(request);
//            if (!houseInfoService.isHouseMemberFromSameCity(request,area.getId())) {
//            	return new Message(Message.Type.error,"您注册所在地与发房地不一致！");
//			}
            String type = request.getParameter("type");
            if("zdy".equals(type)){
            }else{
            	//修正：如果是苏州房源，标价30元。2016年1月7日
            	houseInfo.setInfoPrice(10f);
            	Area houseArea = new Area();
            	houseArea = areaService.load(area.getId());
            	if(houseArea.getId()==5307l
            			||houseArea.getTreePath().indexOf("5307")!=-1) {
					houseInfo.setInfoPrice(30f);
				}	
            }
            houseInfo.setStatus(HouseInfo.HouseInfo_STATUS.APPLY);
            houseInfo.setAvailable(true);
            houseInfo.setCreateDate(new Date());
            houseInfo.setPriority(1);
            houseInfoService.add(houseInfo);
        }catch (Exception e){
        	e.printStackTrace();
            return new Message(Message.Type.error,"添加房屋信息错误");
        }
        return new Message(Message.Type.success,"添加房屋信息成功");
    }
    
    /**
     * 修改房屋信息
     * @param houseInfo
     * @return
     */
    @RequestMapping(value = "/auth/updateHouseInfo",method = RequestMethod.POST)
    @ResponseBody
    public Message updateHouseInfo(@ModelAttribute("updateHouseInfo") HouseInfo houseInfo,HttpServletRequest request){
        try {
        	if (request.getParameter("areaSizes")!=null) {
        		houseInfo.setAreaSize(request.getParameter("areaSizes").equals("")?0f:Float.parseFloat(request.getParameter("areaSizes")));
			}else {
				houseInfo.setAreaSize(0f);
			}
        	if (request.getParameter("salePrices")!=null) {
        		houseInfo.setSalePrice(request.getParameter("salePrices").equals("")?0f:Float.parseFloat(request.getParameter("salePrices")));
			}else {
				houseInfo.setSalePrice(0f);
			}
        	Area area=new Area();
            area.setId(Long.parseLong(request.getParameter("area_ids")));
            houseInfo.setArea(area);
            houseInfo.setHouseShape(request.getParameter("room")+"室"+request.getParameter("office")+"厅");
            //2015年10月13日 修改，前端不能提交修改价格
            HouseInfo tempHouseInfo = houseInfoService.loadHouseInfo(houseInfo.getId());
            houseInfo.setInfoPrice(tempHouseInfo.getInfoPrice());
            houseInfoService.update(houseInfo);
        }catch (Exception e){
        	e.printStackTrace();
            return new Message(Message.Type.error,"修改房屋信息错误");
        }
        return new Message(Message.Type.success,"修改房屋信息成功");
    }
    
    /**
     * 修改房屋信息价格
     * @param houseInfo
     * @return
     */
    @RequestMapping(value = "/auth/updateHouseInfoPrice",method = RequestMethod.POST)
    @ResponseBody
    public Message updateHouseInfoPrice(HouseInfo houseInfo,HttpServletRequest request){
        try {
            houseInfoService.update(houseInfo);
        }catch (Exception e){
            return new Message(Message.Type.error,"修改房屋信息错误");
        }
        return new Message(Message.Type.success,"修改房屋信息成功");
    }
    
    /**
	 * 获取区域及房源
	 */
	@RequestMapping(value = "/getAreaHouseCountByCityOld", method = RequestMethod.GET)
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
	HouseInfo browseHouseInfo(long houseInfo_id,HttpServletRequest request) {
		BrowseHouseInfoVO vo = houseInfoService.getBrowseHouseInfoVO(houseInfo_id, request);
		if(vo.isBought()||!vo.isBalanceEnough()){
			return vo.getHouseInfo();
		}
		//获取房源信息
		HouseInfo houseInfo = new HouseInfo();
		houseInfo = houseInfoService.browseHouseInfoByHouseId(houseInfo_id);
		Member loginMember = memberService.getCurrent(request);
		//自定义房源，如果查看3次后自动将所有收转入系统账号中【该逻辑包含在以下】
		Member houseInfoMember = houseInfoService.getHouseInfoMember(houseInfo, false);
		houseInfo.setMember(houseInfoMember);
		//更新账户余额状态
		//收入者增加余额【即为房源信息提供会员】 
		//modify here yanfeng 系统扣除30%的手续费
		Member systemMember = memberService.getSystemMember();
		if(houseInfoMember.getId()==systemMember.getId()){
			houseInfoService.updateBalanceInfo(houseInfoMember, 
					Float.parseFloat(String.valueOf(houseInfo.getInfoPrice())), UpdateBalanceDto.INCREASE);
		}else{
			houseInfoService.updateBalanceInfo(houseInfoMember, 
					Float.parseFloat(String.valueOf(houseInfo.getInfoPrice()*0.7)), UpdateBalanceDto.INCREASE);
			houseInfoService.updateBalanceInfo(systemMember, 
					Float.parseFloat(String.valueOf(houseInfo.getInfoPrice()*0.3)), UpdateBalanceDto.INCREASE);
		}
		//支出者减少余额【即为查看数据会员/登录会员】
		if (loginMember.getId()==systemMember.getId()) {
			//备用，系统登录时，更新一次系统账户信息
			loginMember = memberService.getSystemMember();
		}
		houseInfoService.updateBalanceInfo(loginMember, 
				Float.parseFloat(String.valueOf(houseInfo.getInfoPrice())), UpdateBalanceDto.REDUCE);
		//更改收支明细、并写入我的查看房源信息
		houseInfoService.addIncomeExpenseAndBrowseInfo(houseInfoMember, loginMember, houseInfo);
		return houseInfo;
	}
	
	/**
	 * 获取房源查看操作VO
	 */
	@RequestMapping(value = "/auth/getBrowseHouseInfoVO", method = RequestMethod.GET)
	public @ResponseBody
	BrowseHouseInfoVO getBrowseHouseInfoVO(long houseInfo_id,HttpServletRequest request) {
		return houseInfoService.getBrowseHouseInfoVO(houseInfo_id, request);
	}
	
	/**
	 * 获取房源是否能够修改的状态【包括是否能修改和是否只能修改价格】
	 * @param houseInfo_id
	 * @return
	 */
	@RequestMapping(value = "auth/checkHouseInfoStatus", method = RequestMethod.GET)
	@ResponseBody
	public UpdateHouseInfoVO checkHouseInfoStatus(long houseInfo_id){
		return houseInfoService.getUpdateStatus(houseInfo_id);
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
	 * 牛人榜数据【前10】
	 * @return
	 */
	@RequestMapping(value = "/getTopTenRankingList", method = RequestMethod.GET)
	@ResponseBody
	public List<TopTenRankingVO> getTopTenRankingList(){
		return houseInfoService.getTop10Ranking();
	}
	
	/**
	 * 获取经纪人动态列表
	 * @return
	 */
	@RequestMapping(value = "/getAgentDynamicsList", method = RequestMethod.GET)
	@ResponseBody
	public List<AgentDynamicVO> getAgentDynamicsList(){
		List<AgentDynamicVO> list = houseInfoService.getAgentDynamicList();
		return list;
	}
	
	/**
	 * 检查是否可以对当前房源进行申诉操作
	 * @since 2015年11月6日16:03
	 * @author masw
	 * @return
	 */
	@RequestMapping(value = "auth/checkStateStatus", method = RequestMethod.GET)
	@ResponseBody
	public StateStatusVO checkStateStatus(long houseInfo_id,HttpServletRequest request){
		StateStatusDto dto = new StateStatusDto();
		dto.setHouseInfo_id(houseInfo_id);
		dto.setMember_id(memberService.getMemberId(request));
		StateStatusVO vo= houseInfoService.checkStateStatus(dto);
		vo.setHouseInfo_id(houseInfo_id);
		return vo;
	}

	/**
	 * 批量查询电话号码是否存在
	 */
	@RequestMapping(value = "/auth/batchCheckMobile",method = RequestMethod.POST)
	@ResponseBody
	public List<BatchCheckMobileVO> batchCheckMobile(@RequestParam(value = "dataList[]")String[] dataList){
		List<BatchCheckMobileVO> list=new ArrayList<BatchCheckMobileVO>();
		if(dataList.length>30){
			return list;
		}
		for(int i=0;i< dataList.length;i++){
			BatchCheckMobileVO vo=new BatchCheckMobileVO();
			vo.setMobile(dataList[i]);
			if (houseInfoService.isAgentMobile(dataList[i])){
				vo.setReason("经纪人号码");
				vo.setIsOperate(false);
				list.add(vo);continue;
			}
			HouseInfoExistDto dto = new HouseInfoExistDto();
			dto.setMobile(dataList[i]);
			if (houseInfoService.isLocalMobile(dataList[i])) {
			//if (houseInfoService.isLocalMobile(dataList[i])|| houseInfoService.isHouseInfoExistRemoteInvacationV2(dto)) {
				vo.setReason("系统已存在此电话号码");
				vo.setIsOperate(false);
				list.add(vo);
				continue;
			}
			vo.setReason("号码平台不存在");
			vo.setIsOperate(true);
			list.add(vo);
		}
		return list;
	}
	
	/**
	 * 检查远程是否存在房源
	 * @return
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isHouseInfoExistRemoteInvacation(HouseInfoExistDto houseInfoExistDto){
		Boolean houseInfoExistRemoteInvacation = houseInfoService.isHouseInfoExistRemoteInvacationV2(houseInfoExistDto);
		return houseInfoExistRemoteInvacation;
	}
	
	/**
     * 添加申诉信息
     * @return
     */
    @RequestMapping(value = "/auth/addStateInfo",method = RequestMethod.POST)
    @ResponseBody
    public Message addStateInfo(State state,long houseInfo_id, HttpServletRequest request){
    	try { 
    		state.setMember_id(memberService.getMemberId(request));
    		state.setStateStatus(stateStatus.APPLY);
    		state.setHouseInfo_id(houseInfo_id);
			houseInfoService.addState(state);
		} catch (Exception e) {
			e.printStackTrace();
            return new Message(Message.Type.error,"提交申诉失败！");
		}
        return new Message(Message.Type.success,"提交申诉成功！");
    }
	
    /**
	 * 获取关键字楼盘字典
	 * @since 2015年11月11日
	 * @author masw
	 * @return
	 */
	@RequestMapping(value = "/getCommunity", method = RequestMethod.GET)
	@ResponseBody
	public List<Community>  getCommunity(String communityKeyWords,HttpServletRequest request){
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
	public List<Community>  getCommunityV2(GetCommunityV2Dto getCommunityV2Dto,HttpServletRequest request){
		return houseInfoService.getTopTenCommunityV2(getCommunityV2Dto);
	}
	
//	public static void main(String[] args) {
//		String splitStr = "：";
//		String a = "23：00";
//		String b[] = a.split(splitStr);
//		int hour = Integer.parseInt(b[0]);
//		int minute = Integer.parseInt(b[1]);
//		if (minute%10<=5&&minute%10!=0) {
//			minute = minute/10*10+5;
//		}else if(minute%10==0){
//			minute = 0;
//		}else{
//			minute = (minute/10+1)*10;
//		}
//		if (minute==60) {
//			hour++;
//		}
//		String newHourAndMinute = ((hour==24)?"00":hour)+splitStr+((minute==60)?"00":(minute==0?"00":minute));
//		System.out.println(newHourAndMinute);
//	}
	
    
}
