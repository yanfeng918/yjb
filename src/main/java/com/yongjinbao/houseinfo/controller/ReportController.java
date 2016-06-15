package com.yongjinbao.houseinfo.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yongjinbao.beans.Message;
import com.yongjinbao.houseinfo.dto.GetReportGto;
import com.yongjinbao.houseinfo.entity.Report;
import com.yongjinbao.houseinfo.entity.Report.REPORT_STATUS;
import com.yongjinbao.houseinfo.service.IReportService;
import com.yongjinbao.member.dto.MyBrowseInfoDto;
import com.yongjinbao.member.service.IBrowseFavoriteHouseInfoService;
import com.yongjinbao.member.service.IMemberService;

/**
 * Created by mashenwei on 2015/9/8.
 */
@Controller
@RequestMapping("/auth/report")
public class ReportController {

	@Inject
	IReportService reportService;
	
	@Inject
	IMemberService memberService;
	
	@Inject
	IBrowseFavoriteHouseInfoService browseFavoriteHouseInfoService;
	 /**
     * 举报
     * @param report
     * @return
     */
    @RequestMapping(value = "/addReportInfo",method = RequestMethod.POST)
    @ResponseBody
    public Message addReportInfo(Report report,long houseInfo_id, HttpServletRequest request){
    	try {
    		GetReportGto getReportGto = new GetReportGto();
    		getReportGto.setHouseInfo_id(houseInfo_id);
    		getReportGto.setMember_id(memberService.getMemberId(request));
    		if (reportService.isReported(getReportGto)) {
    			return new Message(Message.Type.error,"该房源您已经举报！");
			}
			report.setMember_id(memberService.getMemberId(request));
			report.setStatus(REPORT_STATUS.APPLY);
			report.setHouseInfo_id(houseInfo_id);
			reportService.addReport(report);
		} catch (Exception e) {
			e.printStackTrace();
            return new Message(Message.Type.error,"举报失败！请重新尝试！");
		}
        return new Message(Message.Type.success,"举报成功！");
    }
    
    /**
     * 房源是否已经过期
     */
    @RequestMapping(value = "/isReportExpired",method = RequestMethod.GET)
    @ResponseBody
    public boolean isReportExpired(long houseInfo_id,HttpServletRequest request){
    	long member_id = memberService.getMemberId(request);
		//如果用户没有举报，则取是否过期
		MyBrowseInfoDto myBrowseInfoDto = new MyBrowseInfoDto();
		myBrowseInfoDto.setMember_id(member_id);
		myBrowseInfoDto.setHouseInfo_id(houseInfo_id);
		myBrowseInfoDto.setBrowseFavoriteStyle("Browse");
		return reportService.isReportExpired(myBrowseInfoDto);
    }
}
