package com.yongjinbao.houseinfo.service;

import java.util.List;

import com.yongjinbao.houseinfo.dto.GetReportGto;
import com.yongjinbao.houseinfo.entity.Report;
import com.yongjinbao.member.dto.MyBrowseInfoDto;
import com.yongjinbao.mybatis.service.IBaseService;

/**
 * Created by mashenwei on 2015/9/8.
 */
public interface IReportService extends IBaseService<Report, Integer> {
	
	/**
	 * 【举报房源】t_report表添加举报信息
	 */
	public boolean addReport(Report report);
	
	/**
	 * 【举报房源】更新举报信息
	 */
	public boolean updateReport(Report report);
	
//	/**
//	 * 【举报房源】是否已经举报
//	 */
//	public boolean isHouseInfoReported(GetReportGto getReportGto);
	
	/** 【举报房源】 是否已经超过30分钟**/
	public boolean isReportExpired(MyBrowseInfoDto myBrowseInfoDto);
	
	 /** 【举报房源】 获取我与某条房源的举报信息 **/
	public List<Report> getMyReport(GetReportGto getReportGto);
	
	/**【举报房源】 是否已经举报**/
	public boolean isReported(GetReportGto getReportGto);
	
}
