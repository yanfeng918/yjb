package com.yongjinbao.member.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yongjinbao.houseinfo.dto.GetReportGto;
import com.yongjinbao.houseinfo.entity.Report;
import com.yongjinbao.houseinfo.service.IHouseInfoService;
import com.yongjinbao.houseinfo.service.IReportService;
import com.yongjinbao.member.dao.IBrowseFavoriteInfoDao;
import com.yongjinbao.member.dto.GetBrowseFavoriteInfoListDto;
import com.yongjinbao.member.dto.MyBrowseInfoDto;
import com.yongjinbao.member.entity.BrowseFavoriteInfo;
import com.yongjinbao.member.entity.BrowseFavoriteInfo.BrowseFavoriteStyle;
import com.yongjinbao.member.service.IBrowseFavoriteHouseInfoService;
import com.yongjinbao.member.vo.GetMyBrowseVO;
import com.yongjinbao.mybatis.entity.Pager;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;

/**
 * Created by yanfeng on 2015/8/20.
 */
@Service
public class BrowseFavoriteHouseInfoService extends BaseServiceImpl<BrowseFavoriteInfo,Integer> 
				implements IBrowseFavoriteHouseInfoService{
	
    
    @Inject
    private IBrowseFavoriteInfoDao browseFavoriteInfoDao;
    
    @Inject
    private IHouseInfoService houseInfoService;
    
    @Inject
    private IReportService reportService;
    
    @Inject
    public void setBaseDao(IBrowseFavoriteInfoDao browseFavoriteInfoDao) {
        super.setBaseDao(browseFavoriteInfoDao);
    }

	@Override
	public void addBrowseHouseInfo(BrowseFavoriteInfo browseFavoriteInfo) {
		browseFavoriteInfoDao.add(browseFavoriteInfo);
	}

	@Override
	public void addFavoriteHouseInfo(BrowseFavoriteInfo browseFavoriteInfo) {
		browseFavoriteInfoDao.add(browseFavoriteInfo);
	}

	@Override
	public void cancelBrowseFavoriteInfo(int id) {
		browseFavoriteInfoDao.cancelBrowseFavoriteInfo(id);
	}

	@Override
	public Pager<GetMyBrowseVO> getBrowseHouseInfoList(GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto) {
		return null;
	}

	@Override
	public Pager<GetMyBrowseVO> getFavoriteHouseInfoList(
			GetBrowseFavoriteInfoListDto getBrowseFavoriteHouseInfoListDto) {
        getBrowseFavoriteHouseInfoListDto.setPageOffset((getBrowseFavoriteHouseInfoListDto.getPageNumber()-1)
                *getBrowseFavoriteHouseInfoListDto.getPageSize());
        Pager<GetMyBrowseVO> pages=new Pager<GetMyBrowseVO>();
        List<GetMyBrowseVO> list=browseFavoriteInfoDao.getBrowseFavoriteHouseInfoList(getBrowseFavoriteHouseInfoListDto);
        for (GetMyBrowseVO getMyBrowseVO : list) {
        	getMyBrowseVO.setArea(houseInfoService.getHouseInfoArea(getMyBrowseVO.getId()));
        	//====BEGIN===
        	//修改 2015年9月17日 【判断数据是否已经过了举报时间】
        	if (getBrowseFavoriteHouseInfoListDto.getBrowseFavoriteStyle().equals(BrowseFavoriteStyle.Browse)) {
				MyBrowseInfoDto myBrowseInfoDto = new MyBrowseInfoDto();
				myBrowseInfoDto.setMember_id(getBrowseFavoriteHouseInfoListDto.getMember_id());
				myBrowseInfoDto.setHouseInfo_id(getMyBrowseVO.getId());
				myBrowseInfoDto.setBrowseFavoriteStyle("Browse");
				getMyBrowseVO.setIsExpired(reportService.isReportExpired(myBrowseInfoDto));
				//2015年9月18日添加是否自己已经举报
				GetReportGto getReportGto = new GetReportGto();
				getReportGto.setHouseInfo_id(getMyBrowseVO.getId());
				getReportGto.setMember_id(getBrowseFavoriteHouseInfoListDto.getMember_id());
				//2015年9月18日18:15添加查看房源当前举报的审核状态及原因，是否已经举报
				List<Report> reportList = reportService.getMyReport(getReportGto);
				boolean isReported = reportList.size()>0;
				getMyBrowseVO.setIsReported(isReported);
				if (isReported) {
					getMyBrowseVO.setCheckStatus(reportList.get(0).getStatus().getOutername());
					getMyBrowseVO.setCheckContent(reportList.get(0).getCheckContent()==null?"":reportList.get(0).getCheckContent());
				}else {
					getMyBrowseVO.setCheckStatus("");
					getMyBrowseVO.setCheckContent("");
				}
			}
        	//====END===
			if (getBrowseFavoriteHouseInfoListDto.getBrowseFavoriteStyle().equals(BrowseFavoriteStyle.Favorites)) {
				getMyBrowseVO.setMobile(getMyBrowseVO.getMobile().replace(getMyBrowseVO.getMobile().substring(3, 7), "****"));
			}
		}
        int total=browseFavoriteInfoDao.getBrowseFavoriteHouseInfoCount(getBrowseFavoriteHouseInfoListDto);
        pages.setList(list);
        pages.setTotalCount(total);
        pages.setPageOffset(getBrowseFavoriteHouseInfoListDto.getPageOffset());
        pages.setPageSize(getBrowseFavoriteHouseInfoListDto.getPageSize());
		return pages;
	}

	@Override
	public boolean isHouseInfoInMyBrowse(MyBrowseInfoDto myBrowseInfoDto) {
		List<BrowseFavoriteInfo> list = browseFavoriteInfoDao.getBrowseInfoByHouseId(myBrowseInfoDto);
		boolean isExist = false;
		if (list.size()>0) {
			isExist = true;
		}
		return isExist;
	}
	
	@Override
	public boolean isHouseInfoInMyFavorite(MyBrowseInfoDto myBrowseInfoDto) {
		return isHouseInfoInMyBrowse(myBrowseInfoDto);
	}
	
	@Override
	public BrowseFavoriteInfo getBrowseFavoriteInfo(MyBrowseInfoDto myBrowseInfoDto) {
		List<BrowseFavoriteInfo> list = browseFavoriteInfoDao.getBrowseInfoByHouseId(myBrowseInfoDto);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public int getBrowseMinutesByNow(MyBrowseInfoDto myBrowseInfoDto) {
		// TODO Auto-generated method stub
		return browseFavoriteInfoDao.getBrowseMinutesByNow(myBrowseInfoDto);
	}
}
