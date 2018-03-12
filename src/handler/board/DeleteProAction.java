package handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import controller.CommandHandler;

public class DeleteProAction implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String boardid = req.getParameter("boardid");
		if (boardid==null) boardid = "1";
		
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {pageNum = "1";}
			
		int num = Integer.parseInt(req.getParameter("num"));
		String passwd = req.getParameter("passwd");
		
		BoardDBBean dbPro = BoardDBBean.getInstance();
		int check = dbPro.deleteArticle(num, passwd, boardid);
		
		// Ãß°¡µÊ.
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("check", check);
		// --------------------
		
		
		return "/view/deletePro.jsp";
	}

}