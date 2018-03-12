package handler.board;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;
import controller.CommandHandler;

public class ContentAction implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String boardid = req.getParameter("boardid");
		if (boardid==null) boardid = "1";
		int num = Integer.parseInt(req.getParameter("num"));
		
		String pageNum = req.getParameter("pageNum");
			if (pageNum == null || pageNum == "") {
				pageNum = "1";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				BoardDBBean dbPro = BoardDBBean.getInstance();
				BoardDataBean article = dbPro.getArticle(num, boardid, "content");
				int ref = article.getRef();
				int re_level = article.getRe_level();
				int re_step = article.getRe_step();
				
				req.setAttribute("article", article);
				req.setAttribute("pageNum", pageNum);
				
			} catch (Exception e) {e.printStackTrace();}
			
			
			
	
		return "/view/content.jsp";
	}

}
