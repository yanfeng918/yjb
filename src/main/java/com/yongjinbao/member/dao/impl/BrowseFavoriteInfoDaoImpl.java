package com.yongjinbao.member.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.member.dao.IBrowseFavoriteInfoDao;
import com.yongjinbao.member.dto.GetBrowseFavoriteInfoListDto;
import com.yongjinbao.member.dto.MyBrowseInfoDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo;
import com.yongjinbao.member.vo.GetMyBrowseVO;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

/**
 * Created by yanfeng on 2015/8/20
 * @param <T>
 */
@Repository
public class BrowseFavoriteInfoDaoImpl<T> extends BaseDaoImpl<BrowseFavoriteInfo,Integer> implements IBrowseFavoriteInfoDao {

	@Override
	public void addBrowseFavoriteInfo(BrowseFavoriteInfo browseFavoriteInfo) {
		int insert = getSqlSession().insert(BrowseFavoriteInfo.class.getName()+".add", browseFavoriteInfo);
		System.out.println(insert);
	}

	@Override
	public void cancelBrowseFavoriteInfo(int id) {
		int delete = getSqlSession().delete(BrowseFavoriteInfo.class.getName()+".delete", id);
	}
	
	@Override
	public List<GetMyBrowseVO> getBrowseFavoriteHouseInfoList(GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto) {
	    List<GetMyBrowseVO> list = getSqlSession().selectList(BrowseFavoriteInfo.class.getName()+".getBrowseFavoriteHouseInfoList", getBrowseFavoriteHouseInfoListDto);
		return list;
	}

	@Override
	public List<BrowseFavoriteInfo> getBrowseInfoByHouseId(MyBrowseInfoDto myBrowseInfoDto) {
		List<BrowseFavoriteInfo> list = getSqlSession().selectList(BrowseFavoriteInfo.class.getName()+".getBrowseHouseByHouseId", myBrowseInfoDto);
		return list;
	}

    @Override
    public int getBrowseFavoriteHouseInfoCount(GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto) {
        return getSqlSession().selectOne(BrowseFavoriteInfo.class.getName()+".getBrowseFavoriteHouseInfoList"+"_count",getBrowseFavoriteHouseInfoListDto);
    }
    
    @Override
    public int getBrowseMinutesByNow(MyBrowseInfoDto myBrowseInfoDto) {
    	Integer m = new Integer(1441);
    	List<Integer> mList = getSqlSession().selectList(BrowseFavoriteInfo.class.getName()+".getBrowseMinutesByNow", myBrowseInfoDto);
    	if (mList.size()>0) {
			m = mList.get(0);
		}
    	return m==null?1441:m;
    }
}
