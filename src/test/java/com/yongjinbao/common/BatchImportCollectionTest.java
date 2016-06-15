//package com.yongjinbao.common;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.yongjinbao.collection.entity.Collection;
//import com.yongjinbao.collection.entity.Collection.Collection_STATUS;
//import com.yongjinbao.collection.service.ICollectionService;
//import com.yongjinbao.commons.service.IAreaService;
//
//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spring-config.xml")
//
//
//public class BatchImportCollectionTest {
//
//	@Inject
//	private IAreaService areaService;
//
//	@Inject
//	private ICollectionService collectionService;
//
//
//	public List<Collection> getExcel() {
//		Workbook readwb = null;
//		try {
//			//构建Workbook对象, 只读Workbook对象
//			//直接从本地文件创建Workbook
//			InputStream instream = new FileInputStream("C:/Users/dell/Desktop/collection.xls");
//			readwb = Workbook.getWorkbook(instream);
//			//Sheet的下标是从0开始
//			// 获取第一张Sheet表
//			Sheet readsheet = readwb.getSheet(0);
//			//获取Sheet表中所包含的总列数
//			int rsColumns = readsheet.getColumns();
//			//获取Sheet表中所包含的总行数
//			int rsRows = readsheet.getRows();
//			//获取指定单元格的对象引用
//			List<Collection> list = new ArrayList<Collection>();
//			StringBuffer bf = new StringBuffer();
//			for (int i = 2; i <rsRows; i++) {
//				Collection collection = new Collection();
//				for (int j = 0; j < rsColumns; j++) {
//					Cell cell = readsheet.getCell(j,i);
//					String contents = cell.getContents();
//					switch (j){
//					//	                        case 0:collection.setCollectPrice(Float.parseFloat(cell.getContents()));break;
//					//	                        case 1:collection.setCommunity(cell.getContents());break;
//					//	                        case 2:collection.setBan(cell.getContents());break;
//					//	                        case 3:collection.setFloor(cell.getContents());break;
//					//	                        case 4:collection.setRoomNumber(cell.getContents());break;
//					//	                        case 5:collection.setHouseShape(cell.getContents());break;
//					//	                        case 6:collection.setAreaSize(Float.parseFloat(cell.getContents()));break;
//					//	                        case 7:collection.setSalePrice(Float.parseFloat(cell.getContents()));break;
//					//	                        case 8:collection.setMember_id(0l);break;
//					//	                        case 9:collection.setDescription(cell.getContents());break;
//					//	                        case 10:collection.setAddress(cell.getContents());break;
//
//
//					case 0:collection.setCollectPrice(Float.parseFloat(contents));break;
//					case 1:
//						if (contents.equals("泰逸华庭")) {
//							continue;
//						}
//						collection.setCommunity(contents);break;
//					case 2:collection.setBan("不限");break;
//					case 3:collection.setFloor("不限");break;
//					case 4:collection.setRoomNumber("不限");break;
//					case 5:collection.setHouseShape("不限");break;
//					case 6:collection.setAreaSize(0f);break;
//					case 7:collection.setSalePrice(0f);break;
//					case 8:collection.setMember_id(0l);break;
//					case 9:collection.setDescription("不限");break;
//					case 10:collection.setAddress(contents);break;
//					case 11://区域，获取区域ID
//						Long areaId=areaService.getAreaIdByName(contents);
//						if (areaId!=null) {
//							collection.setArea_id(areaId);
//						}else {
//							bf.append(contents+"\n");
//							collection.setArea_id(792l);
//						}
//						break;
//					}
//				}
//				collection.setStatus(Collection_STATUS.COLLECTING);
//				collection.setCreateDate(new Date());
//				list.add(collection);
//			}
//			System.out.println(bf.toString());
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	@Test
//	public void importCollectionData(){
//		List<Collection> collectionList = getExcel();
//		for (Collection collection : collectionList) {
//			try {
//				collectionService.addCollection(collection);
//				System.out.println(collection.getCommunity()+"导入成功");
//			} catch (Exception e) {
//				// TODO: handle exception
//				System.out.println(collection.getCommunity()+"导入失败!\n原因："+e.getMessage());
//			}
//			
//		}
//	}
//
//}
//
