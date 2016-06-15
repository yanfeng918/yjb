package com.yongjinbao.houseinfo.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.yongjinbao.houseinfo.dao.IReportDao;
import com.yongjinbao.houseinfo.dto.GetReportGto;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.houseinfo.entity.Report;
import com.yongjinbao.houseinfo.service.IHouseInfoService;
import com.yongjinbao.houseinfo.service.IReportService;
import com.yongjinbao.member.dto.MyBrowseInfoDto;
import com.yongjinbao.member.service.IBrowseFavoriteHouseInfoService;
import com.yongjinbao.mybatis.service.impl.BaseServiceImpl;

/**
 * Created by mashenwei on 2015/9/8.
 */
@Service
public class ReportService extends BaseServiceImpl<Report, Integer>implements IReportService {
	
	@Inject
	private IReportDao reportDao;
	
	@Inject
	IBrowseFavoriteHouseInfoService browseFavoriteHouseInfoService;
	
	@Inject
	IHouseInfoService houseInfoService;
	
	@Inject
    public void setBaseDao(IReportDao reportDao) {
        super.setBaseDao(reportDao);
    }
	
	@Override
	public boolean addReport(Report report) {
		boolean isSuccess = reportDao.addReport(report);
		//【修改 2015年9月18日】举报后设置被举报的houseInfo为不可用 
		HouseInfo houseInfo = new HouseInfo();
		houseInfo.setId(report.getHouseInfo_id());
		houseInfo.setAvailable(false);
		houseInfoService.update(houseInfo);
		return isSuccess;
	}

	@Override
	public boolean updateReport(Report report) {
		return reportDao.updateReport(report);
	}
	
	@Override
	public boolean isReportExpired(MyBrowseInfoDto myBrowseInfoDto) {
		int minutes = browseFavoriteHouseInfoService.getBrowseMinutesByNow(myBrowseInfoDto);
		return minutes>1440;
	}
	
	@Override
	public List<Report> getMyReport(GetReportGto getReportGto) {
		return reportDao.getMyReport(getReportGto);
	}
	
	public boolean isReported(GetReportGto getReportGto){
		return reportDao.getMyReport(getReportGto).size()>0;
	}
	

}
