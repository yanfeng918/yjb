package com.yongjinbao.utils.activityReward;


import com.yongjinbao.member.entity.Member;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelParseToList {

	public static final String houseInfoSourceFilePath = "/Users/Mac/documents/fangdd/战区奖金.xls";

	public static List<Member> parseExcel() throws Exception {

		InputStream is = new FileInputStream(new File(houseInfoSourceFilePath));
		HSSFWorkbook houseInfoWBook = new HSSFWorkbook(is);
		List<Member> memberList = new ArrayList<Member>();
		Member member = null;

		for (int i = 0; i < houseInfoWBook.getNumberOfSheets(); i++) {
			HSSFSheet sheet = houseInfoWBook.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			for (int index = 1; index <= sheet.getLastRowNum(); index++) {
				HSSFRow row = sheet.getRow(index);
				if (row != null) {
					member = new Member();
					

					if (!row.getCell(0).toString().trim().equals("")) {

						if(row.getCell(0).getCellType()==0){
							DecimalFormat df = new DecimalFormat("0");
							String whatYourWant = df.format(row.getCell(0).getNumericCellValue());
							//System.out.println(+ row.getCell(0).getCellType() + "whatYourWant" + row.getCell(0).toString().trim());
							member.setUsername(whatYourWant);
						}else {
							member.setUsername(row.getCell(0).getStringCellValue().trim());
							//System.out.println(+row.getCell(0).getCellType() + "whatYourWant" + row.getCell(0).toString().trim());
						}



//						CellType.
//						member.setUsername(row.getCell(0).getStringCellValue().trim());

					}

					try {
						member.setBalance((10 * Float.parseFloat(((row.getCell(1))).toString())));
					}catch (Exception e){
//						e.printStackTrace();
						continue;
					}

					memberList.add(member);
				}

			}
		}

		return memberList;

	}

	public static void main(String args[]) throws Exception {
		for (Member member : ExcelParseToList.parseExcel()) {
			System.out.println(member.getUsername());
		}
		;
	}

}
