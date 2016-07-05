package com.yongjinbao.houseInfo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.inject.Inject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yongjinbao.houseinfo.service.IHouseInfoService;
import com.yongjinbao.member.service.IMemberService;

/**
 * Created by fddxiaohui on 2015/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class HouseInfoServiceTest {

	public static final String houseInfoSourceFilePath = "C:\\Users\\fddxiaohui\\Desktop\\八月.xls";
	public static final String houseInfoSourceFilePath1 = "C:\\Users\\dell\\Desktop\\923反馈.xls";
	@Inject
	private IHouseInfoService houseInfoService;

	@Inject
	private IMemberService memberService;
	//    @Test
	//    public void getHouseInfoPage(){
	//        GetHouseInfoDto getHouseInfoDto=new GetHouseInfoDto();
	//        getHouseInfoDto.setPageSize(10);
	//        getHouseInfoDto.setPageNumber(1);
	//        getHouseInfoDto.setArea_id(792);
	////        Pager<HouseInfoNew> pager= houseInfoService.getHouseInfoNew(getHouseInfoDto);
	////        System.out.println(pager.getTotalCount());
	////        for(HouseInfoNew houseInfo:pager.getList())
	////            System.out.println(houseInfo.getCommunity());
	//    }
	//    
	//    @Test
	//    public void getHouseInfoCountByArea(){
	//    	int houseInfoCountByArea = houseInfoService.getHouseInfoCountByArea(793);
	//    	System.out.println(houseInfoCountByArea);
	//    }
	//    
	//    @Test
	//    public void getAreaHouseCountByCity(){
	//    	List<GetAreaHouseCountByCityVO> areaHouseCountByCity = houseInfoService.getAreaHouseCountByCity(793);
	//    	for (GetAreaHouseCountByCityVO getAreaHouseCountByCityVO : areaHouseCountByCity) {
	//    		System.out.println(getAreaHouseCountByCityVO.getAreaName()+"==>>房源数："+getAreaHouseCountByCityVO.getHouseInfoCount());
	//		}
	//    }
	//    
	//    @Test
	//    public void getMobileByHouseId(){
	//    	String mobile = houseInfoService.getMobileByHouseId(10);
	//    	System.out.println(mobile);
	//    }
	//    
	//    @Test
	//    public void isHouseInfoBought(){
	//    	MyBrowseInfoDto myBrowseInfoDto = new MyBrowseInfoDto();
	//		myBrowseInfoDto.setHouseInfo_id(6L);
	//		myBrowseInfoDto.setMember_id(1L);
	//		//boolean isBought = houseInfoService.isHouseInfoBought(myBrowseInfoDto);
	//    	
	////    	boolean isExist = houseInfoService.isHouseInfoBought(1, 3);
	//    	//System.out.println("是否已经购买===>>"+(isBought ?"是":"否"));
	//    	
	//    	boolean isEnough = houseInfoService.isBalanceEnoughToBrowse(200f, 6L);
	//    	//System.out.println("余额是否充足===>>"+(isEnough ?"是":"否"));
	//    }
	//    
	//    @Test
	//    public void getUpdateStatus(){
	//    	UpdateHouseInfoVO vo = houseInfoService.getUpdateStatus(9l);
	//    	//System.out.println("是否能够修改==>>"+(vo.isUpdate()?"ok":"系统房源，不能修改！"));
	//    	//System.out.println("是否能够修改==>>"+(vo.isOnlyPrice()?"只能修改价格":"可修改全部"));
	//    	
	//    }
	//    
	//    @Test
	//    public void browseHouseInfoByHouseId(){
	////    	HouseInfoNew h= houseInfoService.browseHouseInfoByHouseId(11l);
	//    	int count = houseInfoService.getTodayHouseInfoCount();
	//    	//System.out.println(count);
	//  
	//    }
	//    
	//    @Test
	//    public void getTop10Ranking(){
	//    	List<TopTenRankingVO> rankVOList = new ArrayList<TopTenRankingVO>();
	//    	rankVOList = houseInfoService.getTop10Ranking();
	//    	System.out.println("本月牛人榜");
	//    	int i = 1;
	//    	for (TopTenRankingVO topTenRankingVO : rankVOList) {
	//			System.out.println("第"+i+"名==>>"+topTenRankingVO.toString());
	//			i++;
	//		}
	//    }
	//    
	//    @Test
	//    public void batchImport(){
	//    	try {
	//			List<HouseInfoNew> list = ExcelParseToList.parseExcel();
	//			for (HouseInfoNew houseInfo : list) {
	//				Member member = new Member();
	//				member.setId(0l);
	//				houseInfo.setMember(member);
	//				Area area = new Area();
	//				area.setId(3467l);
	//				houseInfo.setArea(area);
	//				houseInfoService.add(houseInfo);
	//			}
	//		} catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//    }

	//	@Test
	//	public void isHouseInfoExisted(){
	//		HouseInfoExistDto houseInfoExistDto = new HouseInfoExistDto();
	//    	houseInfoExistDto.setBan("08号");
	//    	houseInfoExistDto.setMobile("189423335700");
	//    	houseInfoExistDto.setRoomNumber("502");
	//    		boolean isExisted = houseInfoService.isHouseInfoExist(houseInfoExistDto);
	//    		System.out.println(isExisted?"信息已经存在!":"信息可以添加！");
	//			
	//	}

	//    @Test
	//    public void redisCheck(){
	//      GetHouseInfoDto getHouseInfoDto=new GetHouseInfoDto();
	//      getHouseInfoDto.setPageSize(10);
	//      getHouseInfoDto.setPageNumber(1);
	//      getHouseInfoDto.setArea_id(792);
	//      try {
	//    	  Pager<HouseInfoNewAndFavouriteStatusVO> pager= houseInfoService.getHouseInfoNew(getHouseInfoDto);
	//    	  System.out.println(pager.getTotalCount());
	//    	  for(HouseInfoNew houseInfo:pager.getList())
	//    		  System.out.println(houseInfo.getId());
	//      } catch (Exception e) {
	//    	  // TODO: handle exception
	//    	  e.printStackTrace();
	//      }
	//    }

	@Test
	public void getArea() throws Exception{
		InputStream is ;
		HSSFWorkbook wb = null ;
		HSSFSheet sheet = null ;
		try {
			is = new FileInputStream(houseInfoSourceFilePath1);
			wb = new HSSFWorkbook(is);
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				sheet = wb.getSheetAt(i);
				if (sheet == null) {
					continue;
				}
				sheet = wb.getSheetAt(0);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		FileOutputStream out=null;

		String name = "";
		for (int index = 1; index < 37700; index++) {
			HSSFRow row = null;
			try {
				row = sheet.getRow(index);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (row == null) {
				continue;
			}
			try {
				String a = row.getCell(6).getStringCellValue();
				if (a!=null&&!a.equals("")) {
					continue;
				}
				name = row.getCell(7).toString().trim();//取小区名称

				if (!name.equals("")) {
					out = new FileOutputStream(new File(houseInfoSourceFilePath1));
					String area = houseInfoService.selectAreaByName(name);
					if (area.trim().equals("")) {
						continue;
					}
					HSSFCell tempCell = HSSFCellUtil.getCell(row, 6);
					if (tempCell!=null) {
						row.getCell(6).setCellValue(area);
					}else {
						continue;
					}

					if (out!=null&&wb!=null) {
						out.flush();
						wb.write(out);
						out.close();

					}
					System.out.println("第"+index+"行写入"+area);
				}else {
					System.out.println("第"+index+"行小区为空，跳过！");
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}finally {
			}
		}
	}

}
