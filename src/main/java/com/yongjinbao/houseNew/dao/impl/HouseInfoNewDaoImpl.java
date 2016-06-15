package com.yongjinbao.houseNew.dao.impl;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.finance.entity.ExtraAward;
import com.yongjinbao.houseNew.dao.IHouseInfoNewDao;
import com.yongjinbao.houseNew.dto.BonusProcessDto;
import com.yongjinbao.houseNew.dto.GetCommunityV2Dto;
import com.yongjinbao.houseNew.dto.GetHouseInfoDto;
import com.yongjinbao.houseNew.dto.HouseInfoExistDto;
import com.yongjinbao.houseNew.entity.HouseInfoNew;
import com.yongjinbao.houseNew.entity.State;
import com.yongjinbao.houseNew.vo.GetAreaHouseCountByCityVO;
import com.yongjinbao.houseNew.vo.HouseInfoNewAndFavouriteStatusVO;
import com.yongjinbao.houseNew.vo.LevelDetailVO;
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
public class HouseInfoNewDaoImpl extends BaseDaoImpl<HouseInfoNew,Integer> implements IHouseInfoNewDao {


	
	@Override
	public List<HouseInfoNewAndFavouriteStatusVO> getHouseInfoCdt(GetHouseInfoDto getHouseInfoDto) {
		List<HouseInfoNewAndFavouriteStatusVO> list=getSqlSession().selectList(HouseInfoNew.class.getName()+".getHouseInfoCdt",getHouseInfoDto);
		return list;
	}
	
	@Override
	public int getHouseInfoCdtCount(GetHouseInfoDto getHouseInfoDto) {
		 int count=getSqlSession().selectOne(HouseInfoNew.class.getName()+".getHouseInfoCdtCount",getHouseInfoDto);
	     return count;
	}

	@Override
	public List<HouseInfoNewAndFavouriteStatusVO> getHouseInfoLike(GetHouseInfoDto getHouseInfoDto) {
		List<HouseInfoNewAndFavouriteStatusVO> list=getSqlSession().selectList(HouseInfoNew.class.getName()+".getHouseInfoLike",getHouseInfoDto);

		return list;
	}
	
	@Override
	public int getHouseInfoLikeCount(GetHouseInfoDto getHouseInfoDto) {
		int count=getSqlSession().selectOne(HouseInfoNew.class.getName()+".getHouseInfoLikeCount",getHouseInfoDto);
	    return count;
	}

	@Override
	public int getHouseInfoCountByAreaLike(String area_id) {
		int count = getSqlSession().selectOne(HouseInfoNew.class.getName()+".getHouseInfoCountByArea", area_id);
		return count;
	}
	
	@Override
	public int getHouseInfoCountByAreaCdt(String area_id) {
		int count = getSqlSession().selectOne(HouseInfoNew.class.getName()+".getHouseInfoCountByAreaCdt", area_id);
		return count;
	}

	@Override
	public String getMobileByHouseId(long houseInfo_id) {
		String mobile = getSqlSession().selectOne(HouseInfoNew.class.getName()+".getMobileByHouseId", houseInfo_id);
		return mobile;
	}
	
    @Override
    public int getCountByMobile(String mobile) {
        int count = getSqlSession().selectOne(HouseInfoNew.class.getName()+".selectHouseInfoByMobile", mobile);
        return count;
    }
    
    @Override
	public float getHouseInfoPrice(long houseInfo_id) {
		float infoPrice = getSqlSession().selectOne(HouseInfoNew.class.getName()+".getHouseInfoPriceByHouseId", houseInfo_id);
		return infoPrice;
	}

	@Override
	public HouseInfoNew browseHouseInfoByHouseId(long houseInfo_id) {
		HouseInfoNew houseInfoNew = new HouseInfoNew();
		List<HouseInfoNew> hList = getSqlSession().selectList(HouseInfoNew.class.getName()+".browseHouseInfoByHouseId", houseInfo_id);
		if (hList.size()>0) {
			houseInfoNew = hList.get(0);
		}
		houseInfoNew.setArea(getHouseInfoArea(houseInfo_id));
		return houseInfoNew;
	}

	@Override
	public Member getHouseMember(long houseInfo_id) {
		return getSqlSession().selectOne(HouseInfoNew.class.getName()+".getHouseInfoMember", houseInfo_id);
	}
	
	@Override
	public boolean updateHouseInfoWhenBrowse(HouseInfoNew houseInfoNew) {
		int update = getSqlSession().update(HouseInfoNew.class.getName()+".update", houseInfoNew);
		return update>0;
	}
	

    @Override
    public List<HouseInfoNew> getReleaseHouseInfo(GetHouseInfoDto getHouseInfoDto) {
        List<HouseInfoNew> list=getSqlSession().selectList(HouseInfoNew.class.getName()+".selectReleaseHouseInfo",getHouseInfoDto);
        return list;
    }

    @Override
    public int getReleaseHouseInfoCount(GetHouseInfoDto getHouseInfoDto) {
        int count = getSqlSession().selectOne(HouseInfoNew.class.getName()+".selectReleaseHouseInfoCount", getHouseInfoDto);
        return count;
    }
    
    public Area getHouseArea(int area_id){
    	Area area = getSqlSession().selectOne(HouseInfoNew.class.getName()+".getHouseInfoAreaByHouseId", area_id);
    	return area;
    }
    
    public int getHouseAreaId(long houseInfo_id){
    	return getSqlSession().selectOne(HouseInfoNew.class.getName()+".getAreaIdByHouseInfoId", houseInfo_id);
    }
    
    @Override
    public Area getHouseInfoArea(long houseInfo_id) {
    	return getHouseArea(getHouseAreaId(houseInfo_id));
    }
    
    public int getTodayHouseInfoCount(int area_id){
    	return getSqlSession().selectOne(HouseInfoNew.class.getName()+".getTodayHouseInfoCount", area_id);
    }
    

    
    @Override
    public List<Map<String, Object>> getTop10Ranking() {
    	List<Map<String, Object>> rankList = new ArrayList<Map<String, Object>>();
    	rankList = getSqlSession().selectList(HouseInfoNew.class.getName()+".getTop10Ranking");
    	return rankList;
    }
    
    @Override
    public List<HouseInfoNew> getLatestHouseInfo() {
    	List<HouseInfoNew> list = new ArrayList<HouseInfoNew>();
    	list = getSqlSession().selectList(HouseInfoNew.class.getName()+".getLatestHouseInfo");
    	return list;
    }
    
    @Override
    public HouseInfoNew loadHouseInfo(long id) {
    	return getSqlSession().selectOne(HouseInfoNew.class.getName()+".loadHouseInfo", id);
    }
    
    @Override
    public HouseInfoNew loadHouseInfoWithMember(long id) {
    	return getSqlSession().selectOne(HouseInfoNew.class.getName()+".loadHouseInfoWithMember", id);
    }
    
    @Override
    public boolean isHouseInfoExist(HouseInfoExistDto houseInfoExistDto) {
    	return (getSqlSession().selectList(HouseInfoNew.class.getName()+".isHouseInfoExist", houseInfoExistDto)).size()>0;
    }
    
    @Override
    public String selectAreaByName(String name) {
    	List<String> areaList = getSqlSession().selectList(HouseInfoNew.class.getName()+".selectAreaByName", name);
    	if (areaList.size()>0) {
			return areaList.get(0);
		}else {
			return "";
		}
    }
    
    @Override
	public List<GetAreaHouseCountByCityVO> getAreaHouseCountByCityV2(long area_id) {
		List<GetAreaHouseCountByCityVO> list = new ArrayList<GetAreaHouseCountByCityVO>();
		list = getSqlSession().selectList(HouseInfoNew.class.getName()+".getAreaHouseCountByCityV2", area_id);
		return list;
	}

    @Override
    public boolean isMobileOk(String mobile){
    	List<String> list=getSqlSession().selectList(HouseInfoNew.class.getName()+".isAgentMobile", mobile);
    	if (list.size()>0) {
			return false;
		}
    	List<HouseInfoNew> list1 = getSqlSession().selectList(HouseInfoNew.class.getName()+".isMobileExisted", mobile);
    	if (list1.size()>0) {
			return false;
		}
    	return true;
    }

	@Override
	public List<HouseInfoNew> isLocalMobile(String mobile) {
		List<HouseInfoNew> list = getSqlSession().selectList(HouseInfoNew.class.getName() + ".isMobileExisted", mobile);
		return list;
	}

	@Override
	public boolean isAgentMobile(String mobile) {
		List<String> list = getSqlSession().selectList(HouseInfoNew.class.getName() + ".isAgentMobile", mobile);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<State> isStated(long houseInfo_id) {
		List<State> list = new ArrayList<State>();
		list = getSqlSession().selectList(HouseInfoNew.class.getName() + ".isStated", houseInfo_id);
		return list;
	}

	@Override
	public boolean isStatedExpired(long houseInfo_id) {
		Long id = getSqlSession().selectOne(HouseInfoNew.class.getName() + ".isStatedExpired", houseInfo_id);
		return id==null;
	}

	@Override
	public boolean isStatedCountEnough(long member_id) {
		int count = getSqlSession().selectOne(HouseInfoNew.class.getName() + ".getTodayStatedCount", member_id);
		return count>=5;
	}
	
	@Override
	public boolean addState(State state) {
		int insert = getSqlSession().insert(HouseInfoNew.class.getName()+".addState", state);
		return insert>0;
	}
	
	@Override
	public List<Community> getTopTenCommunity(String keyWords) {
		List<Community> list = new ArrayList<Community>();
		list = getSqlSession().selectList(HouseInfoNew.class.getName()+".getTopTenCommunity", keyWords);
		return list;
	}

	@Override
	public int getSelfCountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfoNew.class.getName() + ".getselfHouseCount", dto);
		return count;
	}

	@Override
	public int getlevel1CountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfoNew.class.getName() + ".getLevelOneHouseCount", dto);
		return count;
	}

	@Override
	public int getlevel2CountByMonth(BonusProcessDto dto) {
		int count = getSqlSession().selectOne(HouseInfoNew.class.getName() + ".getLevelTwoHouseCount", dto);
		return count;
	}

	@Override
	public List<Community> getTopTenCommunityV2(GetCommunityV2Dto getCommunityV2Dto) {
		List<Community> list = new ArrayList<Community>();
		list = getSqlSession().selectList(HouseInfoNew.class.getName()+".getTopTenCommunityV2", getCommunityV2Dto);
		return list;
	}


	@Override
	public List<LevelDetailVO> getLevel1Detail(BonusProcessDto dto) {
		List<LevelDetailVO> list = new ArrayList<LevelDetailVO>();
		if(String.valueOf(dto.getYearMonth()).length()>6){
			list = getSqlSession().selectList(HouseInfoNew.class.getName() + ".getLevelOneDetailCondition", dto);
		}else{
			list = getSqlSession().selectList(HouseInfoNew.class.getName() + ".getLevelOneDetail", dto);
		}
		return list;
	}

	@Override
	public List<LevelDetailVO> getLevel2Detail(BonusProcessDto dto) {
		List<LevelDetailVO> list = new ArrayList<LevelDetailVO>();
		if(String.valueOf(dto.getYearMonth()).length()>6){
			list = getSqlSession().selectList(HouseInfoNew.class.getName() + ".getLevelTwoDetailCondition", dto);
		}else{
			list = getSqlSession().selectList(HouseInfoNew.class.getName() + ".getLevelTwoDetail", dto);
		}
		return list;
	}
	
	@Override
	public ExtraAward getCurrentBonusStatus(BonusProcessDto dto) {
		ExtraAward award = new ExtraAward();
		List<ExtraAward> eList = new ArrayList<ExtraAward>();
		eList = getSqlSession().selectList(HouseInfoNew.class.getName() + ".getBonusAchieveState", dto);
		return eList.size()>0?eList.get(0):award;
	}
	
	@Override
	public boolean updateHouseToApplyStatus(HouseInfoNew houseInfoNew) {
		int count = getSqlSession().update(HouseInfoNew.class.getName() + ".updateHouseToApplyStatus", houseInfoNew);
		return count>0;
	}
	
	@Override
	public boolean addActiveRecord(long houseInfo_id) {
		int insert = getSqlSession().insert(HouseInfoNew.class.getName()+".addActiveRecord", houseInfo_id);
		return insert>0;
	}

}
