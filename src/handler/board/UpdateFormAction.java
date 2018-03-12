package handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;
import controller.CommandHandler;

public class UpdateFormAction implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String boardid = req.getParameter("boardid");
		if (boardid==null) boardid="1";
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null || pageNum == "") { pageNum = "1"; }
		
		int num = Integer.parseInt(req.getParameter("num"));
		try {
			BoardDBBean dbPro = BoardDBBean.getInstance();
			BoardDataBean article = dbPro.getArticle(num, boardid, "update");
			
			// 추가됨. updateForm에서 붙여온게 아님. 이게 있어야 Form에 ${article}로 보낼수 있음.
			req.setAttribute("article", article); 
			//---------------------------------- end
		} catch (Exception e) {e.printStackTrace();}
			
		return "/view/updateForm.jsp";
	}

}
