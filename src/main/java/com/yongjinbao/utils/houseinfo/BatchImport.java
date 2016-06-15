package com.yongjinbao.utils.houseinfo;

import java.util.List;

import javax.inject.Inject;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.houseinfo.service.impl.HouseInfoService;
import com.yongjinbao.member.entity.Member;

public class BatchImport {

	@Inject
	private static HouseInfoService houseInfoService ;
	
	public static void main(String[] args) {
		try {
			List<HouseInfo> list = ExcelParseToList.parseExcel();
			for (HouseInfo houseInfo : list) {
				Member member = new Member();
				member.setId(0l);
				houseInfo.setMember(member);
				
				houseInfo.setFloor("");
				houseInfo.setDescription("");
				houseInfo.setHouseShape("");
				
				Area area = new Area();
				
				area.setId(3467l);
				houseInfo.setPhone("");
				
				
				houseInfoService.add(houseInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
