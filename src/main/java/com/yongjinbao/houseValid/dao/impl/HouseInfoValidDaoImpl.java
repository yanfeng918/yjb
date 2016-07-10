package com.yongjinbao.houseValid.dao.impl;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.finance.entity.ExtraAward;
import com.yongjinbao.houseValid.dao.IHouseInfoValidDao;
import com.yongjinbao.houseValid.dto.BonusProcessDto;
import com.yongjinbao.houseValid.dto.GetCommunityV2Dto;
import com.yongjinbao.houseValid.dto.GetHouseInfoDto;
import com.yongjinbao.houseValid.dto.HouseInfoExistDto;
import com.yongjinbao.houseValid.entity.HouseInfoValid;
import com.yongjinbao.houseValid.entity.State;
import com.yongjinbao.houseValid.vo.GetAreaHouseCountByCityVO;
import com.yongjinbao.houseValid.vo.HouseInfoValidAndFavouriteStatusVO;
import com.yongjinbao.houseValid.vo.LevelDetailVO;
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
public class HouseInfoValidDaoImpl extends BaseDaoImpl<HouseInfoValid,Integer> implements IHouseInfoValidDao {


	
	@Override
	public List<HouseInfoValidAndFavouriteStatusVO> getHouseInfoCdt(GetHouseInfoDto getHouseInfoDto) {
		List<HouseInfoValidAndFavouriteStatusVO> list=getSqlSession().selectList(HouseInfoValid.class.getName()+".getHouseInfoCdt",getHouseInfoDto);
		return list;
	}
	
	@Override
	public int getHouseInfoCdtCount(GetHouseInfoDto getHouseInfoDto) {
		 int count=getSqlSession().selectOne(HouseInfoValid.class.getName()+".getHouseInfoCdtCount",getHouseInfoDto);
	     return count;
	}

	@Override
	public List<HouseInfoValidAndFavouriteStatusVO> getHouseInfoLike(GetHouseInfoDto getHouseInfoDto) {
		List<HouseInfoValidAndFavouriteStatusVO> list=getSqlSession().selectList(HouseInfoValid.class.getName()+".getHouseInfoLike",getHouseInfoDto);

		return list;
	}
	
	@Override
	public int getHouseInfoLikeCount(GetHouseInfoDto getHouseInfoDto) {
		int count=getSqlSession().selectOne(HouseInfoValid.class.getName()+".getHouseInfoLikeCount",getHouseInfoDto);
	    return count;
	}

	@Override
	public int getHouseInfoCountByAreaLike(String area_id) {
		int count = getSqlSession().selectOne(HouseInfoValid.class.getName()+".getHouseInfoCountByArea", area_id);
		return count;
	}
	
	@Override
	public int getHouseInfoCountByAreaCdt(String area_id) {
		int count = getSqlSession().selectOne(HouseInfoValid.class.getName()+".getHouseInfoCountByAreaCdt", area_id);
		return count;
	}

	@Override
	public String getMobileByHouseId(long houseInfo_id) {
		String mobile = getSqlSession().selectOne(HouseInfoValid.class.getName()+".getMobileByHouseId", houseInfo_id);
		return mobile;
	}
	
    @Override
    public int getCountByMobile(String mobile) {
        int count = getSqlSession().selectOne(HouseInfoValid.class.getName()+".selectHouseInfoByMobile", mobile);
        return count;
    }
    
    @Override
	public float getHouseInfoPrice(long houseInfo_id) {
		float infoPrice = getSqlSession().selectOne(HouseInfoValid.class.getName()+".getHouseInfoPriceByHouseId", houseInfo_id);
		return infoPrice;
	}

	@Override
	public HouseInfoValid browseHouseInfoByHouseId(long houseInfo_id) {
		HouseInfoValid houseInfoValid = new HouseInfoValid();
		List<HouseInfoValid> hList = getSqlSession().selectList(HouseInfoValid.class.getName()+".browseHouseInfoByHouseId", houseInfo_id);
		if (hList.size()>0) {
			houseInfoValid = hList.get(0);
		}
//		houseInfoValid.setArea(getHouseInfoArea(houseInfo_id));
		return houseInfoValid;
	}

	@Override
	public Member getHouseMember(long houseInfo_id) {
		return getSqlSession().selectOne(HouseInfoValid.class.getName()+".getHouseInfoMember", houseInfo_id);
	}
	
	@Override
	public boolean updateHouseInfoWhenBrowse(HouseInfoValid houseInfoValid) {
		int update = getSqlSession().update(HouseInfoValid.class.getName()+".update", houseInfoValid);
		return update>0;
	}
	

    @Override
    public List<HouseInfoValid> getReleaseHouseInfo(GetHouseInfoDto getHouseInfoDto) {
        List<HouseInfoValid> list=getSqlSession().selectList(HouseInfoValid.class.getName()+".selectReleaseHouseInfo",getHouseInfoDto);
        return list;
    }

    @Override
    public int getReleaseHouseInfoCount(GetHouseInfoDto getHouseInfoDto) {
        int count = getSqlSession().selectOne(HouseInfoValid.class.getName()+".selectReleaseHouseInfoCount", getHouseInfoDto);
        return count;
    }
    
    public Area getHouseArea(int area_id){
    	Area area = getSqlSession().selectOne(HouseInfoValid.class.getName()+".getHouseInfoAreaByHouseId", area_id);
    	return area;
    }
    
    public int getHouseAreaId(long houseInfo_id){
    	return getSqlSession().selectOne(HouseInfoValid.class.getName()+".getAreaIdByHouseInfoId", houseInfo_id);
    }
    
    @Override
    public Area getHouseInfoArea(long houseInfo_id) {
    	return getHouseArea(getHouseAreaId(houseInfo_id));
    }
    
    public int getTodayHouseInfoCount(int area_id){
    	return getSqlSession().selectOne(HouseInfoValid.class.getName()+".getTodayHouseInfoCount", area_id);
    }
    

    
    @Override
    public List<Map<String, Object>> getTop10Ranking() {
    	List<Map<String, Object>> rankList = new ArrayList<Map<String, Object>>();
    	rankList = getSqlSession().selectList(HouseInfoValid.class.getName()+".getTop10Ranking");
    	return rankList;
    }
    
    @Override
    public List<HouseInfoValid> getLatestHouseInfo() {
    	List<HouseInfoValid> list = new ArrayList<HouseInfoValid>();
    	list = getSqlSession().selectList(HouseInfoValid.class.getName()+".getLatestHouseInfo");
    	return list;
    }
    
    @Override
    public HouseInfoValid loadHouseInfo(long id) {
    	return getSqlSession().selectOne(HouseInfoValid.class.getName()+".loadHouseInfo", id);
    }
    
    @Override
    public HouseInfoValid loadHouseInfoWithMember(long id) {
    	return getSqlSession().selectOne(HouseInfoValid.class.getName()+".loadHouseInfoWithMember", id);
    }
    
    @Override
    public boolean isHouseInfoExist(HouseInfoExistDto houseInfoExistDto) {
    	return (getSqlSession().selectList(HouseInfoValid.class.getName()+".isHouseInfoExist", houseInfoExistDto)).size()>0;
    }
    
    @Override
    public String selectAreaByName(String name) {
    	List<String> areaList = getSqlSession().selectList(HouseInfoValid.class.getName()+".selectAreaByName", name);
    	if (areaList.size()>0) {
			return areaList.get(0);
		}else {
			return "";
		}
    }
    
    @Override
	public List<GetAreaHouseCountByCityVO> getAreaHouseCountByCityV2(long area_id) {
		List<GetAreaHouseCountByCityVO> list = new ArrayList<GetAreaHouseCountByCityVO>();
		list = getSqlSession().selectList(HouseInfoValid.class.getName()+".getAreaHouseCountByCityV2", area_id);
		return list;
	}

    @Override
    public boolean isMobileOk(String mobile){
    	List<String> list=getSqlSession().selectList(HouseInfoValid.class.getName()+".isAgentMobile", mobile);
    	if (list.size()>0) {
			return false;
		}
    	List<HouseInfoValid> list1 = getSqlSession().selectList(HouseInfoValid.class.getName()+".isMobileExisted", mobile);
    	if (list1.size()>0) {
			return false;
		}
    	return true;
    }

	@Override
	public List<HouseInfoValid> isLocalMobile(String mobile) {
		List<HouseInfoValid> list = getSqlSession().selectList(HouseInfoValid.class.getName() + ".isMobileExisted", mobile);
		return list;
	}

	@Override
	public boolean isAgentMobile(String mobile) {
		List<String> list = getSqlSession().selectList(HouseInfoValid.class.getName() + ".isAgentMobile", mobile);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<State> isStated(long houseInfo_id) {
		List<State> list = new ArrayList<State>();
		list = getSqlSession().selectList(HouseInfoValid.class.getName() + ".isStated", houseInfo_id);
		return list;
	}

	@Override
	public boolean isStatedExpired(long houseInfo_id) {
		Long id = getSqlSession().selectOne(HouseInfoValid.class.getName() + ".isStatedExpired", houseInfo_id);
		return id==null;
	}

	@Override
	public boolean isStatedCountEnough(long member_id) {
		int count = getSqlSession().selectOne(HouseInfoValid.class.getName() + ".getTodayStatedCount", member_id);
		return count>=5;
	}
	
	@Override
	public boolean addState(State state) {
		int insert = getSqlSession().insert(HouseInfoValid.class.getName()+".addState", state);
		return insert>0;
	}
	
	@Override
	public List<Community> getTopTenCommunity(String keyWords) {
		List<Community> list = new ArrayList<Community>();
		list = getSqlSession().selectList(HouseInfoValid.class.getName()+".getTopTenCommunity", keyWords);
		return list;
	}

	@Override
	public int getSelfCountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfoValid.class.getName() + ".getselfHouseCount", dto);
		return count;
	}

	@Override
	public int getlevel1CountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfoValid.class.getName() + ".getLevelOneHouseCount", dto);
		return count;
	}

	@Override
	public int getlevel2CountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfoValid.class.getName() + ".getLevelTwoHouseCount", dto);
		return count;
	}

	@Override
	public List<Community> getTopTenCommunityV2(GetCommunityV2Dto getCommunityV2Dto) {
		List<Community> list = new ArrayList<Community>();
		list = getSqlSession().selectList(HouseInfoValid.class.getName()+".getTopTenCommunityV2", getCommunityV2Dto);
		return list;
	}


	@Override
	public List<LevelDetailVO> getLevel1Detail(BonusProcessDto dto) {
		List<LevelDetailVO> list = new ArrayList<LevelDetailVO>();
		if(String.valueOf(dto.getYearMonth()).length()>6){
			list = getSqlSession().selectList(HouseInfoValid.class.getName() + ".getLevelOneDetailCondition", dto);
		}else{
			list = getSqlSession().selectList(HouseInfoValid.class.getName() + ".getLevelOneDetail", dto);
		}
		return list;
	}

	@Override
	public List<LevelDetailVO> getLevel2Detail(BonusProcessDto dto) {
		List<LevelDetailVO> list = new ArrayList<LevelDetailVO>();
		if(String.valueOf(dto.getYearMonth()).length()>6){
			list = getSqlSession().selectList(HouseInfoValid.class.getName() + ".getLevelTwoDetailCondition", dto);
		}else{
			list = getSqlSession().selectList(HouseInfoValid.class.getName() + ".getLevelTwoDetail", dto);
		}
		return list;
	}
	
	@Override
	public ExtraAward getCurrentBonusStatus(BonusProcessDto dto) {
		ExtraAward award = new ExtraAward();
		List<ExtraAward> eList = new ArrayList<ExtraAward>();
		eList = getSqlSession().selectList(HouseInfoValid.class.getName() + ".getBonusAchieveState", dto);
		return eList.size()>0?eList.get(0):award;
	}
	
	@Override
	public boolean updateHouseToApplyStatus(HouseInfoValid houseInfoValid) {
		int count = getSqlSession().update(HouseInfoValid.class.getName() + ".updateHouseToApplyStatus", houseInfoValid);
		return count>0;
	}
	
	@Override
	public boolean addActiveRecord(long houseInfo_id) {
		int insert = getSqlSession().insert(HouseInfoValid.class.getName()+".addActiveRecord", houseInfo_id);
		return insert>0;
	}

}
