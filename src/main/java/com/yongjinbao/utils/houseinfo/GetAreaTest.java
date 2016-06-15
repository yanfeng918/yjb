package com.yongjinbao.utils.houseinfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.yongjinbao.houseinfo.service.impl.HouseInfoService;

public class GetAreaTest {

	@Inject
	private static HouseInfoService houseInfoService ;

	public static final String houseInfoSourceFilePath1 = "C:\\Users\\dell\\Desktop\\7temp.xls";

	//	public static final String houseInfoSourceFilePath = "C:\\Users\\dell\\Desktop\\9.xls";

	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream(new File(houseInfoSourceFilePath1));
		HSSFWorkbook wb = new HSSFWorkbook(is);
		
//		FileInputStream is = new FileInputStream(new File(houseInfoSourceFilePath1));
//		POIFSFileSystem ps = new POIFSFileSystem(is);
//		HSSFWorkbook wb = new HSSFWorkbook(ps);
		FileOutputStream out=null;
		HSSFSheet sheet = wb.getSheetAt(0);

		String name = "";
		for (int index = 1; index < 24700; index++) {
			HSSFRow row = sheet.getRow(index);
			if (row != null) {
				try {
					if (row.getCell(7)==null||row.getCell(6)!=null) {
						continue;
					}
					name = row.getCell(7).toString().trim();//取小区名称
					if (row.getCell(6)==null) {
						if (!name.equals("")) {
							out = new FileOutputStream(new File(houseInfoSourceFilePath1));
							String area = houseInfoService.selectAreaByName(name);
							if (area.trim().equals("")) {
								continue;
							}
							row.getCell(6).setCellValue(area);
							out.flush();
							wb.write(out);
							out.close();
							System.out.println("第"+index+"行写入"+area);
						}else {
							System.out.println("第"+index+"行小区为空，跳过！");
							continue;
						}
					}else {
						System.out.println("第"+index+"行已有区域版块。跳过！");
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					continue;
				}finally {
					//if (out!=null){
					//	out.close();
					//	is.close();
					//}
				}
			}
		}

	}


}
