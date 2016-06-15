package com.yongjinbao.houseinfo.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yongjinbao.houseinfo.dao.IReportDao;
import com.yongjinbao.houseinfo.dto.GetReportGto;
import com.yongjinbao.houseinfo.entity.Report;
import com.yongjinbao.mybatis.dao.impl.BaseDaoImpl;

/**
 * Created by mashenwei on 2015/9/8.
 */
@Repository
public class ReportDaoImpl extends BaseDaoImpl<Report,Integer> implements IReportDao {

	@Override
	public boolean addReport(Report report) {
		int insert = getSqlSession().insert(Report.class.getName()+".addReport", report);
		return insert>0;
	}

	@Override
	public boolean updateReport(Report report) {
		int update = getSqlSession().update(Report.class.getName()+".updateReport", report);
		return update>0;
	}

	@Override
	public List<Report> getMyReport(GetReportGto getReportGto) {
		List<Report> reportList = getSqlSession().selectList(Report.class.getName()+".getMyReport", getReportGto);
		return reportList;
	}
	
}
