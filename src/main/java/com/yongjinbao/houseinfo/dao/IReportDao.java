package com.yongjinbao.houseinfo.dao;

import java.util.List;

import com.yongjinbao.houseinfo.dto.GetReportGto;
import com.yongjinbao.houseinfo.entity.Report;
import com.yongjinbao.mybatis.dao.IBaseDao;

/**
 * Created by mashenwei on 2015/9/8 
 *
 */
public interface IReportDao extends IBaseDao<Report, Integer> {
	
	/**
	 * 添加举报信息
	 * @param report
	 */
	public boolean addReport(Report report);
	
	/**
	 * 修改举报信息
	 * @param report
	 * @return
	 */
	public boolean updateReport(Report report);
	
//	/**
//	 * 是否已经举报
//	 * @param getReportGto
//	 * @return
//	 */
//	public boolean isHouseInfoReported(GetReportGto getReportGto);
	
	/**
	 * 获取我与某条房源的举报信息
	 * @param getReportGto
	 * @return
	 */
	public List<Report> getMyReport(GetReportGto getReportGto);
}
