/*
 * 
 * 
 * 
 */
package com.yongjinbao.commons.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yongjinbao.commons.dao.IAreaDao;
import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.commons.service.IAreaService;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;
import com.yongjinbao.utils.RedisUtils;
import com.yongjinbao.utils.SerializeUtil;
import com.yongjinbao.utils.SettingUtils;
import com.yongjinbao.utils.WebUtils;

/**
 * Service - 地区
 * 
 * 
 * 
 */
@Service("areaServiceImpl")
public class AreaServiceImpl extends BaseServiceImpl<Area, Long> 
implements IAreaService {

	@Resource(name = "areaDaoImpl")
	private IAreaDao areaDao;

	@Resource(name = "areaDaoImpl")
	public void setBaseDao(IAreaDao areaDao) {
		super.setBaseDao(areaDao);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Area> findRoots() {
		int expired_Time = SettingUtils.get().getRedis_expire_12Hour();
		String key = "areaService_findRoots_expire";
		byte[] bs = RedisUtils.get(SerializeUtil.serialize(key));
		List<Area> areaList = new ArrayList<>();
		if (bs==null) {
			areaList =areaDao.findRoots();
			RedisUtils.set(SerializeUtil.serialize(key), SerializeUtil.serialize(areaList),expired_Time);
		}else {
			areaList = (List<Area>) SerializeUtil.unserialize(bs);
		}
		return areaList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Area> findChildren(long id) {
		int expired_Time = SettingUtils.get().getRedis_expire_3Hour();
		String key = "areaService_findChildren_expire"+id;
		byte[] bs = RedisUtils.get(SerializeUtil.serialize(key));
		List<Area> areaList = new ArrayList<>();
		if (bs==null) {
			areaList =areaDao.findChildren(id);
			RedisUtils.set(SerializeUtil.serialize(key), SerializeUtil.serialize(areaList),expired_Time);
		}else {
			areaList = (List<Area>) SerializeUtil.unserialize(bs);
		}
		return areaList;
	}

	@Override
	public Long getAreaIdByName(String fullName) {

		if (fullName.equals("")) {

		}else if (fullName.contains("浦东")&&!fullName.contains("东外滩")) {
			if (!fullName.contains("新区")) {
				fullName = fullName.substring(0, 2)+"新区"+fullName.substring(2, fullName.length());
			}

		}else if (fullName.contains("杨浦东外滩")) {
			fullName = "杨浦区东外滩";

		}else if (fullName.contains("大宁绿地")) {
			fullName = "闸北区大宁";
		}else if (fullName.contains("崇明")) {
			fullName = fullName.substring(0, 2)+"县"+fullName.substring(2, fullName.length());
		}else{
			fullName = fullName.substring(0, 2)+"区"+fullName.substring(2, fullName.length());
		}
		return areaDao.getAreaIdByName(fullName);

	}

	@Override
	public void addAreaByName(String areaName) {

		Area area = new Area();
		String areName = areaName.substring(0, 2);
		String districtName = areaName.substring(2, areaName.length());
		Area parent=null;
		try {
			parent = areaDao.findByAreaName(areName);

			if (parent!=null) {
				area.setName(districtName);
				area.setFullName(parent.getFullName() + districtName);
				area.setTreePath(parent.getTreePath() + parent.getId() + ",");
				area.setParent(parent.getId());
				areaDao.add(area);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(areName+"<---------这里报错（模糊查询出错）-------->");
			e.printStackTrace();
		}

	}

	@Override
	public Long getCityByArea(Long areaId) {
		Long cityId=null;
		Area area = load(areaId);
		if(area==null){
//			throw new Exception("不存在此区域Id");
			return 0L;
		}
		String[] areas = area.getTreePath().split(",");
		if(areas.length==0){
			cityId=area.getId();
		}else{
			cityId=Long.parseLong(areas[1]);
		}
		return cityId;
	}
	
	@Override
	public boolean isAreaFromSame(HttpServletRequest request, Long areaId2) {
		Area houseArea = areaDao.load(areaId2);
		String treePath = houseArea.getTreePath();
		String cityId = WebUtils.getCookie(request, "cityId");
		return treePath.indexOf(cityId)!=-1;
	}

}