package com.yongjinbao.utils.houseinfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.houseinfo.entity.HouseInfo.HouseInfo_STATUS;
import com.yongjinbao.houseinfo.entity.HouseInfo.HouseInfo_SaleWay;

public class ExcelParseToList {

	public static final String houseInfoSourceFilePath = "C:\\Users\\dell\\Desktop\\sources.xls";

	public static List<HouseInfo> parseExcel() throws Exception {

		InputStream is = new FileInputStream(new File(houseInfoSourceFilePath));
		HSSFWorkbook houseInfoWBook = new HSSFWorkbook(is);
		List<HouseInfo> houseInfoList = new ArrayList<HouseInfo>();
		HouseInfo houseInfo = null;
		for (int i = 0; i < houseInfoWBook.getNumberOfSheets(); i++) {
			HSSFSheet sheet = houseInfoWBook.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			for (int index = 1; index <= sheet.getLastRowNum(); index++) {
				HSSFRow row = sheet.getRow(index);
				if (row != null) {
					houseInfo = new HouseInfo();
					
//					  `createDate` sql实现
//					  `modifyDate` sql实现
//					  `areaSize` 面积[11列]
					if (!row.getCell(10).toString().trim().equals("")) {
						houseInfo.setAreaSize(Float.parseFloat((row.getCell(10).toString())));
					}
//					  `ban`  楼栋号[9列]
					houseInfo.setBan(row.getCell(8).toString().trim());
//					  `community` 小区[8列]
					houseInfo.setCommunity(row.getCell(7).toString());
//					  `floor` 
//					  `roomNumber` 楼层与房间号  [10列]
//					int roomNumber = Integer.parseInt(row.getCell(9).toString().trim());
//					houseInfo.setFloor(String.valueOf(roomNumber/100));
					houseInfo.setRoomNumber(row.getCell(9).toString().trim());
//					  `houseDespt` 
//					  `infoPrice`
					houseInfo.setInfoPrice(10);
//					  `mobile` 联系号码[5列]
					houseInfo.setMobile(row.getCell(4).toString().trim());
//					  `name` 业主姓名[4列]
					if (!row.getCell(3).toString().trim().equals("")) {
						houseInfo.setName(row.getCell(3).toString().trim());
					}
//					  `phone` 
//					  `readTime` 
					houseInfo.setReadTime(0);
					
//					  `salePrice`售价[12列]
					houseInfo.setSalePrice(Float.parseFloat(row.getCell(11).toString()));
//					  `saleWay` 
					houseInfo.setSaleWay(HouseInfo_SaleWay.SYSTEM);
//					  `status`
					houseInfo.setStatus(HouseInfo_STATUS.SUCCESS);
//					  `area_id` 
//					  `member_id` 
//					  `available` 
					houseInfo.setAvailable(true);
//					  `houseShape` 
					
//					houseInfo.setMember(im);
					houseInfoList.add(houseInfo);
				}

			}
		}

		return houseInfoList;

	}

}
