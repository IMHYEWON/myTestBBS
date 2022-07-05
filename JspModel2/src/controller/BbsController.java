package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import dao.BbsDao;
import dto.BbsDto;
import net.sf.json.JSONObject;

public class BbsController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String param = req.getParameter("param");
		BbsDao dao = BbsDao.getInstance();
		
		if(param.equals("bbs")) {
			resp.sendRedirect("bbslist.jsp");
			//doBbsList(req, resp);	
		} else if (param.equals("bbswrite")) {
			
			String id 		= ifNull(req.getParameter("id"));
			String title 	= ifNull(req.getParameter("title"));
			String content 	= ifNull(req.getParameter("content"));
			System.out.println(id);
			System.out.println(title);
			System.out.println(content);
			
			if (id.equals("") || title.equals("") || content.equals("")) {
				System.out.println("값업음");
				resp.sendRedirect("./bbs/bbswrite.jsp");
			} else {
				System.out.println("값있음");
				boolean b 	= dao.writeBbs(new BbsDto(id, title, content));
				String msg = (b==false) ? "글쓰기 성공!":"글쓰기 실패";
				
				doMessage(msg, resp);			
				//resp.sendRedirect("./bbs/bbslist.jsp");
			}
			
		} else if (param.equals("bbsdetail")) {
			
			int seq = Integer.parseInt(req.getParameter("seq"));

			BbsDto dto = dao.getBbsDto(seq);
			dao.addReadCount(seq);
			dto = dao.getBbsDto(seq);
			doMessage(dto, resp);
			
			
		} else if (param.equals("bbsUpdate")) {
			
		} else if (param.equals("bbsDelete")) {
			
		}
		
	}
	
	/**
	 * doBbsList
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	protected int doBbsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String choice = ifNull(req.getParameter("choice"));
		String search = ifNull(req.getParameter("search"));
		String sPageNumber = ifNull(req.getParameter("pageNumber"));

		BbsDao dao = new BbsDao();
		int pageNumber = 0;
		if (!sPageNumber.equals("")) {
			pageNumber = Integer.parseInt(sPageNumber);
		}
		
		List<BbsDto> list = dao.getBbsPageList(choice, search, pageNumber);
		
		// 글의 총수
		int len = dao.getAllBbs(choice, search);
		return len;
	}
	
	protected void doMessage(String msg, HttpServletResponse resp)  throws ServletException, IOException {
		resp.sendRedirect("message.jsp?msg="+msg);
		
	}
	
	protected void doMessage(BbsDto dto, HttpServletResponse resp)  throws ServletException, IOException {
		
		JSONObject obj = new JSONObject();
		obj.put("ID",		 dto.getId());
		obj.put("SEQ",		 dto.getSeq());
		obj.put("READCOUNT", dto.getReadcount());
		obj.put("TITLE",  	 dto.getTitle());
		obj.put("CONTENT",	 dto.getContent());
		obj.put("WDATE",	 dto.getWdate());

		resp.setContentType("application/x-json; charset=utf-8");
		System.out.println(obj.toString());
		//resp.getWriter().print(obj);
		resp.getWriter().print(obj);
		
	}
	
	protected String ifNull(String str) {
		return str==null?"":str;
	}
}
