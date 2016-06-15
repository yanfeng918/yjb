package com.yongjinbao.collection.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.beans.Message;
import com.yongjinbao.collection.dto.AddCollectionHouseInfoDto;
import com.yongjinbao.collection.dto.GetCollectionDto;
import com.yongjinbao.collection.dto.GetMyResponseDto;
import com.yongjinbao.collection.dto.ResponseCollectionGto;
import com.yongjinbao.collection.entity.Collection;
import com.yongjinbao.collection.entity.Collection.Collection_STATUS;
import com.yongjinbao.collection.service.ICollectionService;
import com.yongjinbao.collection.vo.CollectionCountByAreaVO;
import com.yongjinbao.collection.vo.CollectionInfoAndRespnseCountVO;
import com.yongjinbao.collection.vo.MyResponseHouseInfoVO;
import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.houseinfo.dto.HouseInfoExistDto;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.dto.UpdateBalanceDto;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;
import com.yongjinbao.mybatis.entity.Pager;

/**
 * @desc 征集房源
 * @author mashenwei
 * @since 2015/09/14
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {

	@Inject
	private ICollectionService collectionService;

	@Inject
	private IMemberService memberService;

	/**
	 * 发布征集房源信息
	 * @return
	 */
	@RequestMapping(value = "/auth/addCollection",method = RequestMethod.POST)
	@ResponseBody
	public Message addCollection(Collection collection,HttpServletRequest request){
		Member loginMember = memberService.getCurrent(request);
		if (loginMember==null) {
			return new Message(Message.Type.error, "获取登录用户失败，请尝试重新登录！");
		}
		long memberId = loginMember.getId();
		if(collectionService.getMyReleaseCount(memberId)>5){
			return new Message(Message.Type.error, "一天最多发布五条数据");
		}
		if (collection.getCollectPrice()>loginMember.getBalance()) {
			return new Message(Message.Type.error, "发布征集失败，您的账户余额不足"+
					collection.getCollectPrice()+"元！");
		}
		try {
			collection.setMember_id(memberId);
			collection.setAreaSize(request.getParameter("areaSize1").equals("")?0f:Float.parseFloat(request.getParameter("areaSize1")));
			collection.setSalePrice(request.getParameter("salePrice1").equals("")?0f:Float.parseFloat(request.getParameter("salePrice1")));
			if (request.getParameter("room")!=null&&request.getParameter("office")!=null) {
				if (request.getParameter("room").equals("零")) {
					collection.setHouseShape("不限");
				}else {
					collection.setHouseShape(request.getParameter("room")+"室"+request.getParameter("office")+"厅");
				}
			}
			//2015年10月7日 修改，新增征集时，冻结该征集价格的资金
			collection.setStatus(Collection.Collection_STATUS.COLLECTING);
			collectionService.addCollection(collection);
			//冻结资金,即减少账户余额
			UpdateBalanceDto balanceDto = new UpdateBalanceDto();
			balanceDto.setMember(loginMember);
			balanceDto.setUpdateAmount(collection.getCollectPrice(), UpdateBalanceDto.REDUCE);
			memberService.updateBalance(balanceDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(Message.Type.error, "保存失败！");
		}
		return new Message(Message.Type.success, "保存成功！");
	}

	/**
	 * 获取我的征集数据
	 */
	@RequestMapping(value = "/auth/getMyCollectionList",method = RequestMethod.POST)
	@ResponseBody
	public Pager<CollectionInfoAndRespnseCountVO> getMyCollectionList( GetCollectionDto getCollectionDto,HttpServletRequest request){
		getCollectionDto.setMember_id(memberService.getMemberId(request));
		Pager<Collection> pager=collectionService.getMyCollectionList(getCollectionDto);
		Pager<CollectionInfoAndRespnseCountVO> pager1 =new Pager<CollectionInfoAndRespnseCountVO>();
		List<CollectionInfoAndRespnseCountVO> list = new ArrayList<CollectionInfoAndRespnseCountVO>();
		pager1.setList(list);
		pager1.setPageSize(pager.getPageSize());
		pager1.setPageOffset(pager.getPageOffset());
		pager1.setPageCount(pager.getPageCount());
		pager1.setTotalCount(pager.getTotalCount());
		CollectionInfoAndRespnseCountVO andRespnseCountVO = null;
		for (Collection collection : pager.getList()) {
			andRespnseCountVO = new CollectionInfoAndRespnseCountVO();
			int responseCountByCollectId = getResponseCountByCollectId(collection.getId());
			andRespnseCountVO.setCollection(collection);
			andRespnseCountVO.setResponseCount(responseCountByCollectId);
			list.add(andRespnseCountVO);
		}
		return pager1;
	}

	/**
	 * 获取所有征集数据列表
	 */
	@RequestMapping(value = "getAllCollectionList",method = RequestMethod.POST)
	@ResponseBody
	public Pager<CollectionInfoAndRespnseCountVO> getAllCollectionList(GetCollectionDto getCollectionDto,HttpServletRequest request){
		//修改：新增回答供求的数量
		getCollectionDto.setStatus(Collection_STATUS.COLLECTING);
		Pager<Collection> pager=collectionService.getAllCollectionList(getCollectionDto);
		Pager<CollectionInfoAndRespnseCountVO> pager1 =new Pager<CollectionInfoAndRespnseCountVO>();
		List<CollectionInfoAndRespnseCountVO> list = new ArrayList<CollectionInfoAndRespnseCountVO>();
		pager1.setList(list);
		pager1.setPageSize(pager.getPageSize());
		pager1.setPageOffset(pager.getPageOffset());
		pager1.setPageCount(pager.getPageCount());
		pager1.setTotalCount(pager.getTotalCount());
		CollectionInfoAndRespnseCountVO andRespnseCountVO = null;
		for (Collection collection : pager.getList()) {
			andRespnseCountVO = new CollectionInfoAndRespnseCountVO();
			int responseCountByCollectId = getResponseCountByCollectId(collection.getId());
			andRespnseCountVO.setCollection(collection);
			andRespnseCountVO.setResponseCount(responseCountByCollectId);
			list.add(andRespnseCountVO);
		}

		return pager1;
	}

	/**
	 * 向征集信息提交我的房源
	 */
	@RequestMapping(value = "/auth/addResponseToCollection",method = RequestMethod.POST)
	@ResponseBody
	public Message addResponseToCollection(HouseInfo houseInfo, long collection_id, HttpServletRequest request){
		try {
			AddCollectionHouseInfoDto collectionHouseInfoGto = new AddCollectionHouseInfoDto();
			collectionHouseInfoGto.setCollection_id(collection_id);
			houseInfo.setHouseShape(request.getParameter("room")+"室"+request.getParameter("office")+"厅");
			houseInfo.setMember(memberService.getCurrent(request));
			houseInfo.init();
			houseInfo.setAreaSize(request.getParameter("areaSize1").equals("")?0f:Float.parseFloat(request.getParameter("areaSize1")));
            houseInfo.setSalePrice(request.getParameter("salePrice1").equals("")?0f:Float.parseFloat(request.getParameter("salePrice1")));
			Area area=new Area();
			area.setId(Long.parseLong(request.getParameter("area_id")));
			houseInfo.setArea(area);
			HouseInfoExistDto houseInfoExistDto = new HouseInfoExistDto();
			if(houseInfo.getBan().isEmpty()||"不限".equals(houseInfo.getBan())){
				return new Message(Message.Type.error,"请添加正确的楼栋号！");
			}
			if(houseInfo.getRoomNumber().isEmpty()||"不限".equals(houseInfo.getRoomNumber())){
				return new Message(Message.Type.error,"请添加正确的房间号！");
			}
			houseInfoExistDto.setBan(houseInfo.getBan());
			houseInfoExistDto.setMobile(houseInfo.getMobile());
			houseInfoExistDto.setRoomNumber(houseInfo.getRoomNumber());
			boolean isExisted = collectionService.isHouseInfoExist(houseInfoExistDto);
			if (isExisted) {
				return new Message(Message.Type.error,"您添加的房源信息已经存在！");
			}
			houseInfo.setPriority(1);
			collectionHouseInfoGto.setHouseInfo(houseInfo);
			collectionService.addResponseToCollection(collectionHouseInfoGto);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Message(Message.Type.error, "提交房源失败！");
		}
		return new Message(Message.Type.success, "提交房源成功！");
	}

	/**
	 * 根据collection_id获取回答数
	 */
	@RequestMapping(value = "/auth/getResponseCountByCollectId",method = RequestMethod.GET)
	@ResponseBody
	public int getResponseCountByCollectId(long collection_id){
		GetMyResponseDto dto = new GetMyResponseDto();
		dto.setCollection_id(collection_id);
		return collectionService.getResponseCountByCollectId(dto);
	}

	/**
	 * 根据collection_id获取回答房源信息
	 */
	@RequestMapping(value = "/auth/getResponseHouseInfoByCollectionId",method = RequestMethod.POST)
	@ResponseBody
	public Pager<HouseInfo> getResponseHouseInfoByCollectionId(GetMyResponseDto dto){

		Pager<HouseInfo> pager=collectionService.getResponseHouseInfoByCollectId(dto);
		return pager;
	}

	/** 我的提交房源数据列表[针对征集]**/
	@RequestMapping(value = "/auth/getMyResponsedHouseInfoList",method = RequestMethod.POST)
	@ResponseBody
	public Pager<MyResponseHouseInfoVO> getMyResponsedHouseInfoList(GetMyResponseDto dto,HttpServletRequest request){
		dto.setResponse_member_id(memberService.getMemberId(request));
		Pager<MyResponseHouseInfoVO> pager=collectionService.getMyResponsedHouseInfoList(dto);
		return pager;
	}

	/**
	 * 获取区域及相应的征集数
	 * @param area_id
	 * @return
	 */
	@RequestMapping(value = "/getAreaCollectionCountByCityOld", method = RequestMethod.GET)
	public @ResponseBody
	List<CollectionCountByAreaVO> getAreaCollectionCountByCityOld(long area_id) {
		List<CollectionCountByAreaVO> list = new ArrayList<CollectionCountByAreaVO>();
		list = collectionService.getAreaCollectionByAreaId(area_id);
		return list;
	}

	/**
	 * 获取区域及相应的征集数
	 * @return
	 */
	@RequestMapping(value = "/getAreaCollectionCountByCity", method = RequestMethod.GET)
	public @ResponseBody
	List<CollectionCountByAreaVO> getAreaCollectionCountByCity(long area_id) {
		List<CollectionCountByAreaVO> list = new ArrayList<CollectionCountByAreaVO>();
		list = collectionService.getCollectionCountByAreaIdV2(area_id);
		return list;
	}

	/**
	 * 获取今日征集数
	 */
	@RequestMapping(value = "/getTodayCollectionCount", method = RequestMethod.GET)
	@ResponseBody
	public int getTodayCollectionCount(int area_id){
		int m= collectionService.getTodayCollectionCount(area_id);
		return m;
	}

	/**
	 * 获取最新10条征集
	 * @return
	 */
	@RequestMapping(value = "/getLatestCollectionList", method = RequestMethod.POST)
	@ResponseBody
	public List<Collection> getLatestCollectionList(GetCollectionDto getCollectionDto){
		getCollectionDto.setPageSize(4);
		getCollectionDto.setPageNumber(1);
		getCollectionDto.setStatus(Collection_STATUS.COLLECTING);
		Pager<Collection> pager=collectionService.getAllCollectionList(getCollectionDto);
		List<Collection> list = pager.getList();
		return list;
	}

	/**
	 * 根据collectionId获取征集信息
	 * @return
	 */
	@RequestMapping(value = "/auth/getCollectionByCollectionId", method = RequestMethod.GET)
	@ResponseBody
	public CollectionInfoAndRespnseCountVO getCollectionByCollectionId(long collectionId){
		CollectionInfoAndRespnseCountVO vo = new CollectionInfoAndRespnseCountVO();
		vo.setCollection(collectionService.load(collectionId));
		vo.setResponseCount(getResponseCountByCollectId(collectionId));
		return vo;
	}

	/**
	 * 更新回答者的房源查看次数【查看次数+1】
	 * @return
	 */
	@RequestMapping(value = "/auth/increaseReadTime", method = RequestMethod.POST)
	@ResponseBody
	public boolean increaseReadTime(long collection_id, long houseInfo_id){
		ResponseCollectionGto responseCollectionGto = new ResponseCollectionGto();
		responseCollectionGto.setCollection_id(collection_id);
		responseCollectionGto.setHouseInfo_id(houseInfo_id);
		return collectionService.updateReadTime(responseCollectionGto);
	}

	/**
	 * 结束我发布的征集
	 * @return
	 */
	@RequestMapping(value = "/auth/closeMyCollectionById", method = RequestMethod.POST)
	@ResponseBody
	public boolean closeMyCollectionById(long collection_id,HttpServletRequest request){
		try {
			if (collectionService.closeMyCollectionById(collection_id)) {
				UpdateBalanceDto dto = new UpdateBalanceDto();
				dto.setMember(memberService.getCurrent(request));
				dto.setUpdateAmount(collectionService.load(collection_id).getCollectPrice(), UpdateBalanceDto.INCREASE);
				memberService.updateBalance(dto);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
