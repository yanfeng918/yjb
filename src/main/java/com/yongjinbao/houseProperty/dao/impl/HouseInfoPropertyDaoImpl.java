package com.yongjinbao.houseProperty.dao.impl;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.finance.entity.ExtraAward;
import com.yongjinbao.houseProperty.dao.IHouseInfoPropertyDao;
import com.yongjinbao.houseProperty.dto.BonusProcessDto;
import com.yongjinbao.houseProperty.dto.GetCommunityV2Dto;
import com.yongjinbao.houseProperty.dto.GetHouseInfoDto;
import com.yongjinbao.houseProperty.dto.HouseInfoExistDto;
import com.yongjinbao.houseProperty.entity.HouseInfoProperty;
import com.yongjinbao.houseProperty.entity.State;
import com.yongjinbao.houseProperty.vo.GetAreaHouseCountByCityVO;
import com.yongjinbao.houseProperty.vo.HouseInfoPropertyAndFavouriteStatusVO;
import com.yongjinbao.houseProperty.vo.LevelDetailVO;
import com.yongjinbao.houseinfo.entity.Community;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fddxiaohui on 2015/8/11.
 */
@Repository
public class HouseInfoPropertyDaoImpl extends BaseDaoImpl<HouseInfoProperty,Integer> implements IHouseInfoPropertyDao {


	
	@Override
	public List<HouseInfoPropertyAndFavouriteStatusVO> getHouseInfoCdt(GetHouseInfoDto getHouseInfoDto) {
		List<HouseInfoPropertyAndFavouriteStatusVO> list=getSqlSession().selectList(HouseInfoProperty.class.getName()+".getHouseInfoCdt",getHouseInfoDto);
		return list;
	}
	
	@Override
	public int getHouseInfoCdtCount(GetHouseInfoDto getHouseInfoDto) {
		 int count=getSqlSession().selectOne(HouseInfoProperty.class.getName()+".getHouseInfoCdtCount",getHouseInfoDto);
	     return count;
	}

	@Override
	public List<HouseInfoPropertyAndFavouriteStatusVO> getHouseInfoLike(GetHouseInfoDto getHouseInfoDto) {
		List<HouseInfoPropertyAndFavouriteStatusVO> list=getSqlSession().selectList(HouseInfoProperty.class.getName()+".getHouseInfoLike",getHouseInfoDto);

		return list;
	}
	
	@Override
	public int getHouseInfoLikeCount(GetHouseInfoDto getHouseInfoDto) {
		int count=getSqlSession().selectOne(HouseInfoProperty.class.getName()+".getHouseInfoLikeCount",getHouseInfoDto);
	    return count;
	}

	@Override
	public int getHouseInfoCountByAreaLike(String area_id) {
		int count = getSqlSession().selectOne(HouseInfoProperty.class.getName()+".getHouseInfoCountByArea", area_id);
		return count;
	}
	
	@Override
	public int getHouseInfoCountByAreaCdt(String area_id) {
		int count = getSqlSession().selectOne(HouseInfoProperty.class.getName()+".getHouseInfoCountByAreaCdt", area_id);
		return count;
	}

	@Override
	public String getMobileByHouseId(long houseInfo_id) {
		String mobile = getSqlSession().selectOne(HouseInfoProperty.class.getName()+".getMobileByHouseId", houseInfo_id);
		return mobile;
	}
	
    @Override
    public int getCountByMobile(String mobile) {
        int count = getSqlSession().selectOne(HouseInfoProperty.class.getName()+".selectHouseInfoByMobile", mobile);
        return count;
    }
    
    @Override
	public float getHouseInfoPrice(long houseInfo_id) {
		float infoPrice = getSqlSession().selectOne(HouseInfoProperty.class.getName()+".getHouseInfoPriceByHouseId", houseInfo_id);
		return infoPrice;
	}

	@Override
	public HouseInfoProperty browseHouseInfoByHouseId(long houseInfo_id) {
		HouseInfoProperty houseInfoProperty = new HouseInfoProperty();
		List<HouseInfoProperty> hList = getSqlSession().selectList(HouseInfoProperty.class.getName()+".browseHouseInfoByHouseId", houseInfo_id);
		if (hList.size()>0) {
			houseInfoProperty = hList.get(0);
		}
		houseInfoProperty.setArea(getHouseInfoArea(houseInfo_id));
		return houseInfoProperty;
	}

	@Override
	public Member getHouseMember(long houseInfo_id) {
		return getSqlSession().selectOne(HouseInfoProperty.class.getName()+".getHouseInfoMember", houseInfo_id);
	}
	
	@Override
	public boolean updateHouseInfoWhenBrowse(HouseInfoProperty houseInfoProperty) {
		int update = getSqlSession().update(HouseInfoProperty.class.getName()+".update", houseInfoProperty);
		return update>0;
	}
	

    @Override
    public List<HouseInfoProperty> getReleaseHouseInfo(GetHouseInfoDto getHouseInfoDto) {
        List<HouseInfoProperty> list=getSqlSession().selectList(HouseInfoProperty.class.getName()+".selectReleaseHouseInfo",getHouseInfoDto);
        return list;
    }

    @Override
    public int getReleaseHouseInfoCount(GetHouseInfoDto getHouseInfoDto) {
        int count = getSqlSession().selectOne(HouseInfoProperty.class.getName()+".selectReleaseHouseInfoCount", getHouseInfoDto);
        return count;
    }
    
    public Area getHouseArea(int area_id){
    	Area area = getSqlSession().selectOne(HouseInfoProperty.class.getName()+".getHouseInfoAreaByHouseId", area_id);
    	return area;
    }
    
    public int getHouseAreaId(long houseInfo_id){
    	return getSqlSession().selectOne(HouseInfoProperty.class.getName()+".getAreaIdByHouseInfoId", houseInfo_id);
    }
    
    @Override
    public Area getHouseInfoArea(long houseInfo_id) {
    	return getHouseArea(getHouseAreaId(houseInfo_id));
    }
    
    public int getTodayHouseInfoCount(int area_id){
    	return getSqlSession().selectOne(HouseInfoProperty.class.getName()+".getTodayHouseInfoCount", area_id);
    }
    

    
    @Override
    public List<Map<String, Object>> getTop10Ranking() {
    	List<Map<String, Object>> rankList = new ArrayList<Map<String, Object>>();
    	rankList = getSqlSession().selectList(HouseInfoProperty.class.getName()+".getTop10Ranking");
    	return rankList;
    }
    
    @Override
    public List<HouseInfoProperty> getLatestHouseInfo() {
    	List<HouseInfoProperty> list = new ArrayList<HouseInfoProperty>();
    	list = getSqlSession().selectList(HouseInfoProperty.class.getName()+".getLatestHouseInfo");
    	return list;
    }
    
    @Override
    public HouseInfoProperty loadHouseInfo(long id) {
    	return getSqlSession().selectOne(HouseInfoProperty.class.getName()+".loadHouseInfo", id);
    }
    
    @Override
    public HouseInfoProperty loadHouseInfoWithMember(long id) {
    	return getSqlSession().selectOne(HouseInfoProperty.class.getName()+".loadHouseInfoWithMember", id);
    }
    
    @Override
    public boolean isHouseInfoExist(HouseInfoExistDto houseInfoExistDto) {
    	return (getSqlSession().selectList(HouseInfoProperty.class.getName()+".isHouseInfoExist", houseInfoExistDto)).size()>0;
    }
    
    @Override
    public String selectAreaByName(String name) {
    	List<String> areaList = getSqlSession().selectList(HouseInfoProperty.class.getName()+".selectAreaByName", name);
    	if (areaList.size()>0) {
			return areaList.get(0);
		}else {
			return "";
		}
    }
    
    @Override
	public List<GetAreaHouseCountByCityVO> getAreaHouseCountByCityV2(long area_id) {
		List<GetAreaHouseCountByCityVO> list = new ArrayList<GetAreaHouseCountByCityVO>();
		list = getSqlSession().selectList(HouseInfoProperty.class.getName()+".getAreaHouseCountByCityV2", area_id);
		return list;
	}

    @Override
    public boolean isMobileOk(String mobile){
    	List<String> list=getSqlSession().selectList(HouseInfoProperty.class.getName()+".isAgentMobile", mobile);
    	if (list.size()>0) {
			return false;
		}
    	List<HouseInfoProperty> list1 = getSqlSession().selectList(HouseInfoProperty.class.getName()+".isMobileExisted", mobile);
    	if (list1.size()>0) {
			return false;
		}
    	return true;
    }

	@Override
	public List<HouseInfoProperty> isLocalMobile(String mobile) {
		List<HouseInfoProperty> list = getSqlSession().selectList(HouseInfoProperty.class.getName() + ".isMobileExisted", mobile);
		return list;
	}

	@Override
	public boolean isAgentMobile(String mobile) {
		List<String> list = getSqlSession().selectList(HouseInfoProperty.class.getName() + ".isAgentMobile", mobile);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<State> isStated(long houseInfo_id) {
		List<State> list = new ArrayList<State>();
		list = getSqlSession().selectList(HouseInfoProperty.class.getName() + ".isStated", houseInfo_id);
		return list;
	}

	@Override
	public boolean isStatedExpired(long houseInfo_id) {
		Long id = getSqlSession().selectOne(HouseInfoProperty.class.getName() + ".isStatedExpired", houseInfo_id);
		return id==null;
	}

	@Override
	public boolean isStatedCountEnough(long member_id) {
		int count = getSqlSession().selectOne(HouseInfoProperty.class.getName() + ".getTodayStatedCount", member_id);
		return count>=5;
	}
	
	@Override
	public boolean addState(State state) {
		int insert = getSqlSession().insert(HouseInfoProperty.class.getName()+".addState", state);
		return insert>0;
	}
	
	@Override
	public List<Community> getTopTenCommunity(String keyWords) {
		List<Community> list = new ArrayList<Community>();
		list = getSqlSession().selectList(HouseInfoProperty.class.getName()+".getTopTenCommunity", keyWords);
		return list;
	}

	@Override
	public int getSelfCountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfoProperty.class.getName() + ".getselfHouseCount", dto);
		return count;
	}

	@Override
	public int getlevel1CountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfoProperty.class.getName() + ".getLevelOneHouseCount", dto);
		return count;
	}

	@Override
	public int getlevel2CountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfoProperty.class.getName() + ".getLevelTwoHouseCount", dto);
		return count;
	}

	@Override
	public List<Community> getTopTenCommunityV2(GetCommunityV2Dto getCommunityV2Dto) {
		List<Community> list = new ArrayList<Community>();
		list = getSqlSession().selectList(HouseInfoProperty.class.getName()+".getTopTenCommunityV2", getCommunityV2Dto);
		return list;
	}


	@Override
	public List<LevelDetailVO> getLevel1Detail(BonusProcessDto dto) {
		List<LevelDetailVO> list = new ArrayList<LevelDetailVO>();
		if(String.valueOf(dto.getYearMonth()).length()>6){
			list = getSqlSession().selectList(HouseInfoProperty.class.getName() + ".getLevelOneDetailCondition", dto);
		}else{
			list = getSqlSession().selectList(HouseInfoProperty.class.getName() + ".getLevelOneDetail", dto);
		}
		return list;
	}

	@Override
	public List<LevelDetailVO> getLevel2Detail(BonusProcessDto dto) {
		List<LevelDetailVO> list = new ArrayList<LevelDetailVO>();
		if(String.valueOf(dto.getYearMonth()).length()>6){
			list = getSqlSession().selectList(HouseInfoProperty.class.getName() + ".getLevelTwoDetailCondition", dto);
		}else{
			list = getSqlSession().selectList(HouseInfoProperty.class.getName() + ".getLevelTwoDetail", dto);
		}
		return list;
	}
	
	@Override
	public ExtraAward getCurrentBonusStatus(BonusProcessDto dto) {
		ExtraAward award = new ExtraAward();
		List<ExtraAward> eList = new ArrayList<ExtraAward>();
		eList = getSqlSession().selectList(HouseInfoProperty.class.getName() + ".getBonusAchieveState", dto);
		return eList.size()>0?eList.get(0):award;
	}
	
	@Override
	public boolean updateHouseToApplyStatus(HouseInfoProperty houseInfoProperty) {
		int count = getSqlSession().update(HouseInfoProperty.class.getName() + ".updateHouseToApplyStatus", houseInfoProperty);
		return count>0;
	}
	
	@Override
	public boolean addActiveRecord(long houseInfo_id) {
		int insert = getSqlSession().insert(HouseInfoProperty.class.getName()+".addActiveRecord", houseInfo_id);
		return insert>0;
	}

}
