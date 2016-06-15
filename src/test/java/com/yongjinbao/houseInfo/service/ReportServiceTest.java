package com.yongjinbao.houseInfo.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yongjinbao.houseinfo.dto.GetReportGto;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.houseinfo.entity.Report;
import com.yongjinbao.houseinfo.entity.Report.REPORT_STATUS;
import com.yongjinbao.houseinfo.service.IHouseInfoService;
import com.yongjinbao.houseinfo.service.IReportService;
import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.IMemberService;

/**
 * Created by mashenwei on 2015/9/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")

public class ReportServiceTest {
	
	@Inject
	private IReportService reportService;
	
	@Inject
	private IHouseInfoService houseInfoService;
	
	@Inject
	private IMemberService memberService;
	
	@Test
	public void addReport(){
		Report report = new Report();
//		report.setFailReason("");
//		HouseInfoNew houseInfo = new HouseInfoNew();
//		houseInfo.setId(2l);
//		Member member = new Member();
//		member.setId(1l);
//		report.setHouseInfoNew(houseInfo);
//		report.setMember(member);
		try {
//			report.setHouseInfoService(houseInfoService);
//			report.setMemberService(memberService);
		report.setHouseInfo_id(2);
		report.setMember_id(1l);
		
		report.setReportReason("太帅");
		report.setStatus(REPORT_STATUS.APPLY);
			reportService.addReport(report);
			System.out.println("举报成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("举报失败!");
		}
	}
	
	@Test
	public void updateReport(){
		Report report = new Report();
		report.setId(1L);
		report.setReportReason("1121太太帅!!!");
		report.setStatus(REPORT_STATUS.SUCCESS);
//		report.setFailReason("不121212够帅!!!");
		try {
			reportService.updateReport(report);
			System.out.println("更新成功!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("更新失败!");
		}
	}
	
	
	@Test
	public void isHouseInfoReported(){
		GetReportGto getReportGto =  new GetReportGto();
		getReportGto.setHouseInfo_id(7L);
		getReportGto.setMember_id(24L);
		try {
//			boolean isReport = reportService.isHouseInfoReported(getReportGto);
//			System.out.println(isReport?"已经举报！":"可以举报！");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
