package com.yongjinbao.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yongjinbao.commons.entity.Area;
import com.yongjinbao.commons.service.IAreaService;
import com.yongjinbao.houseinfo.dto.HouseInfoExistDto;
import com.yongjinbao.houseinfo.entity.HouseInfo;
import com.yongjinbao.houseinfo.entity.HouseInfo.HouseInfo_SaleWay;
import com.yongjinbao.houseinfo.service.IHouseInfoService;
import com.yongjinbao.houseinfo.vo.AddHouseInfoVO;
import com.yongjinbao.member.entity.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class AreaServiceTest {
	@Inject
	private IAreaService areaService;

	@Inject
	private IHouseInfoService houseInfoService;

	//	public static final String houseInfoSourceFilePath = "C:\\Users\\dell\\Desktop\\9月第3部分.xls";
	public static final String houseInfoSourceFilePath = "C:\\Users\\dell\\Desktop\\test.xls";

	@Test
	public void findChildren(){
		List<Area> list = areaService.findChildren(35);
		for (Area area : list) {
			System.out.println(area.getName());
		}
	}

	@Test
	public void getAreaId(){
		System.out.println(areaService.getAreaIdByName("普陀真光"));
	}

	@Test
	public void batchInsertHouseInfo(){
		try {
			InputStream is = new FileInputStream(new File(houseInfoSourceFilePath));
			HSSFWorkbook houseInfoWBook = new HSSFWorkbook(is);
			HouseInfo houseInfo = null;
			for (int i = 0; i < houseInfoWBook.getNumberOfSheets(); i++) {
				HSSFSheet sheet = houseInfoWBook.getSheetAt(i);
				if (sheet == null) {
					continue;
				}
				for (int index = 1; index < 5555; index++) {
					HSSFRow row = sheet.getRow(index);
					try {
						if (row != null) {
							houseInfo = new HouseInfo();
							//判断是否重复
							//3--> mobile 联系号码[5列]
							HSSFCell cell = row.getCell(4);
							if (cell!=null) {
								row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
								String value = cell.getStringCellValue().trim();
								if (!value.equals("")) {
									houseInfo.setMobile(value);
								}
							}else {
								//手机号为空
								continue;
							}
							String fanKuiResult = "";//反馈结果
							if (row.getCell(13)==null||row.getCell(13).getStringCellValue().equals("")) {
								//不存在反馈结果，跳过
								//continue;
								fanKuiResult = "暂缓";
							}else {
								fanKuiResult = row.getCell(13).getStringCellValue();//反馈结果
							}

							//6-->ban楼栋号[9列]
							if (row.getCell(8)!=null) {
								row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
								if (!(row.getCell(8).getStringCellValue().trim().equals(""))) {
									houseInfo.setBan(row.getCell(8).getStringCellValue().trim());
								}
							}else {
								houseInfo.setBan("");
							}
							//房间号
							if (row.getCell(9)!=null) {
								row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
								if (!row.getCell(9).getStringCellValue().trim().equals("")) {
									houseInfo.setRoomNumber(row.getCell(9).getStringCellValue().trim());
								}
							}else {
								houseInfo.setBan("");
							}
							HouseInfoExistDto houseInfoExistDto = new HouseInfoExistDto();
							houseInfoExistDto.setBan(houseInfo.getBan());
							houseInfoExistDto.setMobile(houseInfo.getMobile());
							houseInfoExistDto.setRoomNumber(houseInfo.getRoomNumber());
							
							AddHouseInfoVO vo = isExisted(fanKuiResult, houseInfoExistDto);
							if (vo.isCanBeSaved()) {//能够存在本地的时候
								// 设置房源审核状态和是否可用
								houseInfo.setStatus(vo.getStatus());
								houseInfo.setAvailable(vo.getAvailable());
							}else {
								continue;
							}
							//1-->创建日期--
							if (row.getCell(0)!=null) {
								HSSFCell cell1 = row.getCell(0);
								houseInfo.setCreateDate(cell1.getDateCellValue());
							}
							//2-->name 业主姓名[4列]
							if (row.getCell(3)!=null) {
								row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
								houseInfo.setName(row.getCell(3).getStringCellValue().trim());
							}
							//4-->community` 小区[8列]
							if (row.getCell(7)!=null) {
								row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
								houseInfo.setCommunity(row.getCell(7).getStringCellValue().trim());
							}
							//5--> areaSize 面积[11列]
							if (row.getCell(10)!=null&&!row.getCell(10).toString().trim().isEmpty()) {
								row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
								houseInfo.setAreaSize(Float.parseFloat((row.getCell(10).getStringCellValue().trim())));
							}else {
								houseInfo.setAreaSize(0f);
							}
							//`salePrice`售价[12列]
							if (row.getCell(11)!=null) {
								row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
								if (!row.getCell(11).getStringCellValue().trim().isEmpty()) {
									houseInfo.setSalePrice(Float.parseFloat(row.getCell(11).getStringCellValue().trim()));
								}else {
									houseInfo.setSalePrice(0f);
								}
							}
							//infoPrice 系统房源10元
							houseInfo.setInfoPrice(10);
							//readTime	查看次数，初始为0
							houseInfo.setReadTime(0);
							//saleWay` 出售方式
							houseInfo.setSaleWay(HouseInfo_SaleWay.SYSTEM);
							//`member_id` 系统所有
							Member member = new Member();
							member.setId(0l);
							houseInfo.setMember(member);
							//`area_id`  版块位于第7列 查区域id
							if (row.getCell(6)!=null) {
								System.out.println("第"+index+"行正在获取区域ID");
								row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
								Area area = new Area();
								String stringCellValue = row.getCell(6).getStringCellValue();
								Long areaId=areaService.getAreaIdByName(stringCellValue);
								if (areaId==null) {
									//  yanfeng modify 获取失败时，创建第三级版块，并重新查询
									System.out.println("*********第"+index+"行获取区域ID失败************");
									areaService.addAreaByName(stringCellValue);
									area = new Area();
									//重新查询
									areaId=areaService.getAreaIdByName(stringCellValue);
								}
								area.setId(areaId);
								houseInfo.setArea(area);
								System.out.println("第"+index+"行获取区域ID结束！");
							}
							houseInfoService.add(houseInfo);
							System.out.println("*********第"+index+"行导入成功***************");
						}
					}
					catch (Exception e) {
						System.out.println("*********第"+index+"行导入失败***************");
						System.out.println("原因==>>"+e.getMessage());
						continue;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 从信息的反馈状态、远程接口、本地接口得到待插入房源的状态
	 * @param status 无效或其它
	 * @param houseInfoExistDto 包含检验是否重复房源的三大参数：手机号，楼栋号和房间号
	 * @return
	 */
	public AddHouseInfoVO isExisted(String status, HouseInfoExistDto houseInfoExistDto){
		//2015年9月29日 [修改]如果楼栋号或房间号为空，直接保存，并设为可用且可保存，状态为待审核
		AddHouseInfoVO vo = new AddHouseInfoVO();
		if (houseInfoExistDto.getRoomNumber().equals("")||houseInfoExistDto.getBan().equals("")) {
			if (houseInfoExistDto.getRoomNumber().equals("")||houseInfoExistDto.getBan().equals("")) {
				if (houseInfoService.isHouseInfoExist(houseInfoExistDto)) {
					//如果为空,只检查本地
					vo.setCanBeSaved(false);
					return vo;
				}
				vo.setCanBeSaved(true);
				vo.setAvailable(true);
				vo.setStatus(AddHouseInfoVO.HouseInfo_STATUS.APPLY);
				return vo;
			}
		}
		if (status.indexOf("无效")!=-1) {
			if (get(getHttpString(houseInfoExistDto)).indexOf("true")!=-1) {
				//线上有，则查本地
				if (houseInfoService.isHouseInfoExist(houseInfoExistDto)) {
					//本地有
					vo.setCanBeSaved(false);
				}else {
					//本地没有
					vo.setCanBeSaved(true);
					vo.setAvailable(true);
					//需要存储，且状态为审核通过
					vo.setStatus(com.yongjinbao.houseinfo.vo.AddHouseInfoVO.HouseInfo_STATUS.SUCCESS);
				}
			}else {
				//【2015年9月24日15:25】更改逻辑，无效情况下，查本地是否存在，如果有，则不存储，如果没有，则写入，但设置为不可用
				//线上没有，查本地
				if (houseInfoService.isHouseInfoExist(houseInfoExistDto)) {
					//本地有
					vo.setCanBeSaved(false);
				}else {
					//本地没有
					vo.setCanBeSaved(true);
					vo.setAvailable(false);
					//需要存储，且状态为审核通过
					vo.setStatus(com.yongjinbao.houseinfo.vo.AddHouseInfoVO.HouseInfo_STATUS.FAIL);
				}
			}
		}else {
			//不属于无效情况
			if (get(getHttpString(houseInfoExistDto)).indexOf("true")!=-1) {
				//线上有，则查本地
				if (houseInfoService.isHouseInfoExist(houseInfoExistDto)) {
					//本地有
					vo.setCanBeSaved(false);
				}else {
					//本地没有
					vo.setCanBeSaved(true);
					//需要存储，且状态为审核通过
					vo.setStatus(com.yongjinbao.houseinfo.vo.AddHouseInfoVO.HouseInfo_STATUS.SUCCESS);
					vo.setAvailable(true);
				}
			}else {
				//线上没有，查本地
				if (houseInfoService.isHouseInfoExist(houseInfoExistDto)) {
					//本地有
					vo.setCanBeSaved(false);
				}else {
					//本地没有
					vo.setCanBeSaved(true);
					vo.setAvailable(true);
					//需要存储，且状态为审核通过
					vo.setStatus(com.yongjinbao.houseinfo.vo.AddHouseInfoVO.HouseInfo_STATUS.APPLY);
				}
			}
		}
		return vo;
	}
	/**
	 * 通过url链接获取远程接口的查重返回结果
	 * **/
	public String get(String url){
		String status = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();  
		CloseableHttpResponse response = null;
		try {  
			HttpGet httpget = new HttpGet(url);  
			// 执行get请求.
			response = httpclient.execute(httpget);  
			// 获取响应实体    
			HttpEntity entity = response.getEntity();  
			// 打印响应状态    
			if (entity != null) {  
				//获取响应内容    
				status = EntityUtils.toString(entity);
			}  
		} catch (ClientProtocolException e) {  
			e.printStackTrace();  
		}  catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			// 关闭连接,释放资源    
			try {  
				httpclient.close(); 
			} catch (IOException e) {  
				System.out.println("httpClient错误\n"+"远程错误："+url);
			} 
			try {
				response.close();  
			} catch (Exception e) {
				System.out.println("response错误\n"+"远程错误："+url);
			}
		}
		return status;
	}
	//获取组装的url链接，供调用远程接口使用
	public String getHttpString(HouseInfoExistDto houseInfoExistDto){
		String url = "http://139.196.13.240:8080/yjb/houseInfo/isHouseInfoExistRemoteInvacation?"
				+ "mobile="+houseInfoExistDto.getMobile()+"&ban="+houseInfoExistDto.getBan()+"&roomNumber="+
				houseInfoExistDto.getRoomNumber();
		return url;
	}

}
