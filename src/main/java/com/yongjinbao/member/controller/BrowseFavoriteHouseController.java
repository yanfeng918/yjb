package com.yongjinbao.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.beans.Message;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.dto.GetBrowseFavoriteInfoListDto;
import com.yongjinbao.member.dto.MyBrowseInfoDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo;
import com.yongjinbao.member.entity.BrowseFavoriteInfo.BrowseFavoriteStyle;
import com.yongjinbao.member.entity.BrowseFavoriteInfo.CustomerHouseStyle;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.member.service.impl.BrowseFavoriteHouseInfoService;
import com.yongjinbao.member.vo.GetMyBrowseVO;
import com.yongjinbao.mybatis.entity.Pager;

/**
 * Created by fddxiaohui on 2015/9/7.
 */
@Controller
@RequestMapping("/bfhouse/auth")
public class BrowseFavoriteHouseController {
    @Inject
    private BrowseFavoriteHouseInfoService browseFavoriteHouseInfoService;
    
    @Inject
    private IMemberService memberService;
    /**
     * 查询收藏的房屋信息
     * @param getBrowseFavoriteInfoListDto
     * @return
     */
    @RequestMapping(value = "/getFavoriteHouse",method = RequestMethod.POST)
    @ResponseBody
    public Pager<GetMyBrowseVO> getFavoriteHouse(@ModelAttribute("getBrowseFavoriteInfoListDto") GetBrowseFavoriteInfoListDto getBrowseFavoriteInfoListDto,
    		HttpServletRequest request){
        getBrowseFavoriteInfoListDto.setBrowseFavoriteStyle(BrowseFavoriteInfo.BrowseFavoriteStyle.Favorites);
        getBrowseFavoriteInfoListDto.setMember_id(memberService.getMemberId(request));
        Pager<GetMyBrowseVO> pager=browseFavoriteHouseInfoService.getFavoriteHouseInfoList(getBrowseFavoriteInfoListDto);
        return pager;
    }
    /**
     * 查询查看过的房屋信息
     * @param getBrowseFavoriteInfoListDto
     * @return
     */
    @RequestMapping(value = "/getBrowseHouse",method = RequestMethod.POST)
    @ResponseBody
    public Pager<GetMyBrowseVO> getBrowseHouse(@ModelAttribute("getBrowseFavoriteInfoListDto") GetBrowseFavoriteInfoListDto getBrowseFavoriteInfoListDto,
    		HttpServletRequest request){
    	getBrowseFavoriteInfoListDto.setMember_id(memberService.getMemberId(request));
        getBrowseFavoriteInfoListDto.setBrowseFavoriteStyle(BrowseFavoriteInfo.BrowseFavoriteStyle.Browse);
        Pager<GetMyBrowseVO> pager=browseFavoriteHouseInfoService.getFavoriteHouseInfoList(getBrowseFavoriteInfoListDto);
        return pager;
    }
    
    /**
     * 收藏房源信息
     * @param request
     * @param houseInfo_id
     * @return
     */
    @RequestMapping(value = "/addFavoriteHouseInfo", method = RequestMethod.GET)
	@ResponseBody
    public Message addFavoriteHouseInfo(HttpServletRequest request, long houseInfo_id) {
		Long id=memberService.getMemberId(request);
		Member member=memberService.load(id);
		if (member == null) {
			return new Message(Message.Type.error, "获取登录用户失败！");
		}
		MyBrowseInfoDto myBrowseInfoDto = new MyBrowseInfoDto();
		myBrowseInfoDto.setBrowseFavoriteStyle(MyBrowseInfoDto.Favorites);
		myBrowseInfoDto.setHouseInfo_id(houseInfo_id);
		myBrowseInfoDto.setMember_id(id);
		if (browseFavoriteHouseInfoService.isHouseInfoInMyFavorite(myBrowseInfoDto)) {
			return new Message(Message.Type.error, "该数据已经收藏！");
		}
		BrowseFavoriteInfo browseFavoriteInfo = new BrowseFavoriteInfo();
		browseFavoriteInfo.setBrowseFavoriteStyle(BrowseFavoriteStyle.Favorites);
		browseFavoriteInfo.setCustomerHouseStyle(CustomerHouseStyle.HouseInfo);
		HouseInfo houseInfo = new HouseInfo();
		houseInfo.setId(houseInfo_id);
		browseFavoriteInfo.setHouseInfo(houseInfo);
		browseFavoriteInfo.setMember(member);
		try {
			browseFavoriteHouseInfoService.addFavoriteHouseInfo(browseFavoriteInfo);
		} catch (Exception e) {
			// TODO: handle exception
			return new Message(Message.Type.error, "收藏失败!");
		}
    	return new Message(Message.Type.success, "收藏成功!");
    }
    /**
     * 取消已经收藏的房源信息
     */
    @RequestMapping(value = "/cancleFavoriteHouseInfo", method = RequestMethod.GET)
	@ResponseBody
    public Message cancleFavoriteHouseInfo(HttpServletRequest request, long houseInfo_id) {
    	Long id=memberService.getMemberId(request);
		Member member=memberService.load(id);
		if (member == null) {
			return new Message(Message.Type.error, "取消失败。原因：获取登录用户失败！");
		}
		MyBrowseInfoDto myBrowseInfoDto = new MyBrowseInfoDto();
		myBrowseInfoDto.setBrowseFavoriteStyle(MyBrowseInfoDto.Favorites);
		myBrowseInfoDto.setHouseInfo_id(houseInfo_id);
		myBrowseInfoDto.setMember_id(id);
		BrowseFavoriteInfo browseFavoriteInfo = browseFavoriteHouseInfoService.getBrowseFavoriteInfo(myBrowseInfoDto); 
		Long favoriteId = browseFavoriteInfo.getId();
		if (favoriteId == null) {
			return new Message(Message.Type.error, "取消失败。原因：未能获取收藏信息！");
		}
		String fId = String.valueOf(favoriteId);
		try {
			browseFavoriteHouseInfoService.cancelBrowseFavoriteInfo(Integer.parseInt(fId));
		} catch (Exception e) {
			// TODO: handle exception
			return new Message(Message.Type.error, "取消失败。原因：未能删除数据！"); 
		}
		return new Message(Message.Type.success, "取消成功！");
    }
}
