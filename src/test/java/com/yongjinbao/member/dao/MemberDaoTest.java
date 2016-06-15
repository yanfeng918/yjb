package com.yongjinbao.member.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yongjinbao.member.entity.Member;
import com.yongjinbao.member.service.impl.MemberServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-config.xml")
public class MemberDaoTest {
	
	@Inject
	private IMemberDao memberDao;
	
	@Inject
	private MemberServiceImpl memberService;

	
	@Test
	public void test01() {
//		boolean emailExists = memberDao.emailExists("1");
		Member byMobile = memberDao.findListByMobile("13122045162").get(0);
		System.out.println(byMobile.getId());

	
	}
	
	@Test
	public void testaaa() {
		Member m = new Member();
		m.setPassword("123");
		m.setSafeKeyExpire(new Date());
		memberService.add(m);
		System.out.println(1);
	}
	
	@Test
	public void generateInviteCode(){
//		try {
//			String encoding="GBK";
//			File file=new File("C:\\Users\\dell\\Desktop\\13c.txt");
//			if(file.isFile() && file.exists()){ //判断文件是否存在
//				InputStreamReader read = new InputStreamReader(
//						new FileInputStream(file),encoding);
//				BufferedReader bufferedReader = new BufferedReader(read);
//				String lineTxt = null;
//				StringBuffer buffer = new StringBuffer();
//				while((lineTxt = bufferedReader.readLine()) != null){
////					buffer.append(lineTxt+",");
//					String code = memberService.generateInviteCode();
//					buffer.append(code);
//					if (buffer) {
//						
//					}
//					System.out.println("UPDATE t_member SET inviteCode='"+code+"' WHERE id="+lineTxt+";");
//				}
////				buffer.append(")");
////				System.out.println(buffer.toString());
//				read.close();
//			}else{
//				System.out.println("找不到指定的文件");
//			}
//		} catch (Exception e) {
//			System.out.println("读取文件内容出错");
//			e.printStackTrace();
//		}
//		
		
		
		List<Member> memberList = memberDao.getAllMember();
		for (Member member : memberList) {
			String code = memberService.generateInviteCode();
			memberDao.addCode(member, code);
		}
	}

	


}
