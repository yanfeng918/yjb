package com.yongjinbao.houseinfo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.finance.entity.ExtraAward;
import com.yongjinbao.houseinfo.dao.IHouseInfoDao;
import com.yongjinbao.houseinfo.dto.BonusProcessDto;
import com.yongjinbao.houseinfo.dto.GetCommunityV2Dto;
import com.yongjinbao.houseinfo.dto.GetHouseInfoDto;
import com.yongjinbao.houseinfo.dto.HouseInfoExistDto;
import com.yongjinbao.houseinfo.entity.Community;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.houseinfo.entity.State;
import com.yongjinbao.houseinfo.vo.GetAreaHouseCountByCityVO;
import com.yongjinbao.houseinfo.vo.HouseInfoVandFavouriteStatusVO;
import com.yongjinbao.houseinfo.vo.LevelDetailVO;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
@Repository
public class HouseInfoDaoImpl extends BaseDaoImpl<HouseInfo,Integer> implements IHouseInfoDao {

	/*@Override
	public List<HouseInfoNew> getHouseInfoNew(GetHouseInfoDto getHouseInfoDto) {
        List<HouseInfoNew> list=getSqlSession().selectList(HouseInfoNew.class.getName()+".getHouseInfoNew",getHouseInfoDto);
        for (HouseInfoNew houseInfo : list) {
			houseInfo.setArea(getHouseArea(getHouseInfoDto.getArea_id()));
		}
        return list;
	}
	
    @Override
    public int getHouseInfoCount(GetHouseInfoDto getHouseInfoDto) {
        int count=getSqlSession().selectOne(HouseInfoNew.class.getName()+".getHouseInfoCount",getHouseInfoDto);
        return count;
    }*/
	
	@Override
	public List<HouseInfoVandFavouriteStatusVO> getHouseInfoCdt(GetHouseInfoDto getHouseInfoDto) {
		List<HouseInfoVandFavouriteStatusVO> list=getSqlSession().selectList(HouseInfo.class.getName()+".getHouseInfoCdt",getHouseInfoDto);
//		for (HouseInfoNewAndFavouriteStatusVO houseInfo : list) {
//			houseInfo.setArea(getHouseArea(getHouseInfoDto.getArea_id()));
//		}
		return list;
	}
	
	@Override
	public int getHouseInfoCdtCount(GetHouseInfoDto getHouseInfoDto) {
		 int count=getSqlSession().selectOne(HouseInfo.class.getName()+".getHouseInfoCdtCount",getHouseInfoDto);
	     return count;
	}

	@Override
	public List<HouseInfoVandFavouriteStatusVO> getHouseInfoLike(GetHouseInfoDto getHouseInfoDto) {
		List<HouseInfoVandFavouriteStatusVO> list=getSqlSession().selectList(HouseInfo.class.getName()+".getHouseInfoLike",getHouseInfoDto);
//		for (HouseInfoNewAndFavouriteStatusVO houseInfo : list) {
//			houseInfo.setArea(getHouseArea(getHouseInfoDto.getArea_id()));
//		}
		return list;
	}
	
	@Override
	public int getHouseInfoLikeCount(GetHouseInfoDto getHouseInfoDto) {
		int count=getSqlSession().selectOne(HouseInfo.class.getName()+".getHouseInfoLikeCount",getHouseInfoDto);
	    return count;
	}

	@Override
	public int getHouseInfoCountByAreaLike(String area_id) {
		int count = getSqlSession().selectOne(HouseInfo.class.getName()+".getHouseInfoCountByArea", area_id);
		return count;
	}
	
	@Override
	public int getHouseInfoCountByAreaCdt(String area_id) {
		int count = getSqlSession().selectOne(HouseInfo.class.getName()+".getHouseInfoCountByAreaCdt", area_id);
		return count;
	}

	@Override
	public String getMobileByHouseId(long houseInfo_id) {
		String mobile = getSqlSession().selectOne(HouseInfo.class.getName()+".getMobileByHouseId", houseInfo_id);
		return mobile;
	}
	
    @Override
    public int getCountByMobile(String mobile) {
        int count = getSqlSession().selectOne(HouseInfo.class.getName()+".selectHouseInfoByMobile", mobile);
        return count;
    }
    
    @Override
	public float getHouseInfoPrice(long houseInfo_id) {
		float infoPrice = getSqlSession().selectOne(HouseInfo.class.getName()+".getHouseInfoPriceByHouseId", houseInfo_id);
		return infoPrice;
	}

	@Override
	public HouseInfo browseHouseInfoByHouseId(long houseInfo_id) {
		HouseInfo houseInfo = new HouseInfo();
		List<HouseInfo> hList = getSqlSession().selectList(HouseInfo.class.getName()+".browseHouseInfoByHouseId", houseInfo_id);
		if (hList.size()>0) {
			houseInfo = hList.get(0);
		}
		houseInfo.setArea(getHouseInfoArea(houseInfo_id));
		return houseInfo;
	}

	@Override
	public Member getHouseMember(long houseInfo_id) {
		return getSqlSession().selectOne(HouseInfo.class.getName()+".getHouseInfoMember", houseInfo_id);
	}
	
	@Override
	public boolean updateHouseInfoWhenBrowse(HouseInfo houseInfo) {
		int update = getSqlSession().update(HouseInfo.class.getName()+".update", houseInfo);
		return update>0;
	}
	

    @Override
    public List<HouseInfo> getReleaseHouseInfo(GetHouseInfoDto getHouseInfoDto) {
        List<HouseInfo> list=getSqlSession().selectList(HouseInfo.class.getName()+".selectReleaseHouseInfo",getHouseInfoDto);
        return list;
    }

    @Override
    public int getReleaseHouseInfoCount(GetHouseInfoDto getHouseInfoDto) {
        int count = getSqlSession().selectOne(HouseInfo.class.getName()+".selectReleaseHouseInfoCount", getHouseInfoDto);
        return count;
    }
    
    public Area getHouseArea(int area_id){
    	Area area = getSqlSession().selectOne(HouseInfo.class.getName()+".getHouseInfoAreaByHouseId", area_id);
    	return area;
    }
    
    public int getHouseAreaId(long houseInfo_id){
    	return getSqlSession().selectOne(HouseInfo.class.getName()+".getAreaIdByHouseInfoId", houseInfo_id);
    }
    
    @Override
    public Area getHouseInfoArea(long houseInfo_id) {
    	return getHouseArea(getHouseAreaId(houseInfo_id));
    }
    
    public int getTodayHouseInfoCount(int area_id){
    	return getSqlSession().selectOne(HouseInfo.class.getName()+".getTodayHouseInfoCount", area_id);
    }
    
//    @Override
//    public boolean updateMyHouseInfo(HouseInfoNew houseInfo) {
//		return updateHouseInfoWhenBrowse(houseInfo);
//    }
    
    @Override
    public List<Map<String, Object>> getTop10Ranking() {
    	List<Map<String, Object>> rankList = new ArrayList<Map<String, Object>>();
    	rankList = getSqlSession().selectList(HouseInfo.class.getName()+".getTop10Ranking");
    	return rankList;
    }
    
    @Override
    public List<HouseInfo> getLatestHouseInfo() {
    	List<HouseInfo> list = new ArrayList<HouseInfo>();
    	list = getSqlSession().selectList(HouseInfo.class.getName()+".getLatestHouseInfo");
    	return list;
    }
    
    @Override
    public HouseInfo loadHouseInfo(long id) {
    	return getSqlSession().selectOne(HouseInfo.class.getName()+".loadHouseInfo", id);
    }
    
    @Override
    public HouseInfo loadHouseInfoWithMember(long id) {
    	return getSqlSession().selectOne(HouseInfo.class.getName()+".loadHouseInfoWithMember", id);
    }
    
    @Override
    public boolean isHouseInfoExist(HouseInfoExistDto houseInfoExistDto) {
    	return (getSqlSession().selectList(HouseInfo.class.getName()+".isHouseInfoExist", houseInfoExistDto)).size()>0;
    }
    
    @Override
    public String selectAreaByName(String name) {
    	List<String> areaList = getSqlSession().selectList(HouseInfo.class.getName()+".selectAreaByName", name);
    	if (areaList.size()>0) {
			return areaList.get(0);
		}else {
			return "";
		}
    }
    
    @Override
	public List<GetAreaHouseCountByCityVO> getAreaHouseCountByCityV2(long area_id) {
		List<GetAreaHouseCountByCityVO> list = new ArrayList<GetAreaHouseCountByCityVO>();
		list = getSqlSession().selectList(HouseInfo.class.getName()+".getAreaHouseCountByCityV2", area_id);
		return list;
	}

    @Override
    public boolean isMobileOk(String mobile){
    	List<String> list=getSqlSession().selectList(HouseInfo.class.getName()+".isAgentMobile", mobile);
    	if (list.size()>0) {
			return false;
		}
    	List<HouseInfo> list1 = getSqlSession().selectList(HouseInfo.class.getName()+".isMobileExisted", mobile);
    	if (list1.size()>0) {
			return false;
		}
    	return true;
    }

	@Override
	public List<HouseInfo> isLocalMobile(String mobile) {
		List<HouseInfo> list = getSqlSession().selectList(HouseInfo.class.getName() + ".isMobileExisted", mobile);
		return list;
	}

	@Override
	public boolean isAgentMobile(String mobile) {
		List<String> list = getSqlSession().selectList(HouseInfo.class.getName() + ".isAgentMobile", mobile);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<State> isStated(long houseInfo_id) {
		List<State> list = new ArrayList<State>();
		list = getSqlSession().selectList(HouseInfo.class.getName() + ".isStated", houseInfo_id);
		return list;
	}

	@Override
	public boolean isStatedExpired(long houseInfo_id) {
		Long id = getSqlSession().selectOne(HouseInfo.class.getName() + ".isStatedExpired", houseInfo_id);
		return id==null;
	}

	@Override
	public boolean isStatedCountEnough(long member_id) {
		int count = getSqlSession().selectOne(HouseInfo.class.getName() + ".getTodayStatedCount", member_id);
		return count>=5;
	}
	
	@Override
	public boolean addState(State state) {
		int insert = getSqlSession().insert(HouseInfo.class.getName()+".addState", state);
		return insert>0;
	}
	
	@Override
	public List<Community> getTopTenCommunity(String keyWords) {
		List<Community> list = new ArrayList<Community>();
		list = getSqlSession().selectList(HouseInfo.class.getName()+".getTopTenCommunity", keyWords);
		return list;
	}

	@Override
	public int getSelfCountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfo.class.getName() + ".getselfHouseCount", dto);
		return count;
	}

	@Override
	public int getlevel1CountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfo.class.getName() + ".getLevelOneHouseCount", dto);
		return count;
	}

	@Override
	public int getlevel2CountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfo.class.getName() + ".getLevelTwoHouseCount", dto);
		return count;
	}

	@Override
	public List<Community> getTopTenCommunityV2(GetCommunityV2Dto getCommunityV2Dto) {
		List<Community> list = new ArrayList<Community>();
		list = getSqlSession().selectList(HouseInfo.class.getName()+".getTopTenCommunityV2", getCommunityV2Dto);
		return list;
	}


	@Override
	public List<LevelDetailVO> getLevel1Detail(BonusProcessDto dto) {
		List<LevelDetailVO> list = new ArrayList<LevelDetailVO>();
		if(String.valueOf(dto.getYearMonth()).length()>6){
			list = getSqlSession().selectList(HouseInfo.class.getName() + ".getLevelOneDetailCondition", dto);
		}else{
			list = getSqlSession().selectList(HouseInfo.class.getName() + ".getLevelOneDetail", dto);
		}
		return list;
	}

	@Override
	public List<LevelDetailVO> getLevel2Detail(BonusProcessDto dto) {
		List<LevelDetailVO> list = new ArrayList<LevelDetailVO>();
		if(String.valueOf(dto.getYearMonth()).length()>6){
			list = getSqlSession().selectList(HouseInfo.class.getName() + ".getLevelTwoDetailCondition", dto);
		}else{
			list = getSqlSession().selectList(HouseInfo.class.getName() + ".getLevelTwoDetail", dto);
		}
		return list;
	}
	
	@Override
	public ExtraAward getCurrentBonusStatus(BonusProcessDto dto) {
		ExtraAward award = new ExtraAward();
		List<ExtraAward> eList = new ArrayList<ExtraAward>();
		eList = getSqlSession().selectList(HouseInfo.class.getName() + ".getBonusAchieveState", dto);
		return eList.size()>0?eList.get(0):award;
	}
	
	@Override
	public boolean updateHouseToApplyStatus(HouseInfo houseInfo) {
		int count = getSqlSession().update(HouseInfo.class.getName() + ".updateHouseToApplyStatus", houseInfo);
		return count>0;
	}
	
	@Override
	public boolean addActiveRecord(long houseInfo_id) {
		int insert = getSqlSession().insert(HouseInfo.class.getName()+".addActiveRecord", houseInfo_id);
		return insert>0;
	}

}
