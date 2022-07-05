package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dto.MemberDto;
import net.sf.json.JSONObject;

/**
 * 회원가입, 로그인 관련 Controller
 * @author BTC-N02
 *
 */
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}


	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
	
		String param = req.getParameter("param");
		if(param.equals("login")) {
			resp.sendRedirect("member/login.jsp");
		} else if (param.equals("regi")) {
			resp.sendRedirect("member/regi.jsp");
		} else if (param.equals("idcheck")) {
			String id = req.getParameter("id");
			System.out.println("id ::: "+ id);
			
			MemberDao dao = MemberDao.getInstance();
			boolean b = dao.getId(id);
			String str = "NO";
			if(b == false) {
				str = "YES";
			}
			
			JSONObject obj = new JSONObject();
			obj.put("msg", str);
			
			resp.setContentType("application/x-json; charset=utf-8");
			resp.getWriter().print(obj);
		} else if (param.equals("regiAf")) {

			String id	 = req.getParameter("id");
			String pwd 	 = req.getParameter("pwd");
			String name	 = req.getParameter("name");
			String email = req.getParameter("email");			
			
			MemberDao dao = MemberDao.getInstance();
			boolean b = dao.addMember(new MemberDto(id, pwd, name, email, 0));
			
			String msg = "REGIOK";
			if(b == false) {
				msg = "REGINO";
			}
			
			resp.sendRedirect("message.jsp?msg="+msg);
			
		} else if (param.equals("loginAf")) {

			String id	 = req.getParameter("id");
			String pwd 	 = req.getParameter("pwd");
	
			
			MemberDao dao = MemberDao.getInstance();
			MemberDto mem = dao.login(new MemberDto(id, pwd, null, null, 0));
			
			String msg = "LOGINOK";
			if(mem != null && !mem.getId().equals("")){
				msg = "LOGINNO";
			}
			
			resp.sendRedirect("message.jsp?msg="+msg);
			
		}
	}

	
	
}
