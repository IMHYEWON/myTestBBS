package main;

import dao.MemberDao;
import dto.MemberDto;

public class MainClass {

	public static void main(String[] args) {

		MemberDto mem = new MemberDto();
		MemberDao mDao = MemberDao.getInstance();
		
//		mem.setId("hrywehf");
//		mem.setPwd("1234");
//		mem.setEmail("hyewon@gmail.com");
//		mem.setName("haewon");
//		mem.setAuth(3);
		
		
		//mDao.addMember(mem);

		MemberDto find1 = mDao.getMember("hrywehf");
		System.out.println(find1.getEmail());
		
		boolean isMem = mDao.chkMember("hrywehf");
		System.out.println(isMem);
	}

}
