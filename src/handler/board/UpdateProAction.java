package handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDataBean;
import controller.CommandHandler;

public class UpdateProAction implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String boardid = req.getParameter("boardid");
		if (boardid==null) boardid = "1";
		String pageNum = req.getParameter("pageNum");
		if (pageNum == null || pageNum == "") {pageNum = "1";}
		
		// -------
		BoardDataBean article = new BoardDataBean();
		if (req.getParameter("num") != null && !req.getParameter("num").equals("")) {
			article.setNum(Integer.parseInt(req.getParameter("num")));
			article.setRef(Integer.parseInt(req.getParameter("ref")));
			article.setRe_step(Integer.parseInt(req.getParameter("re_step")));
			article.setRe_level(Integer.parseInt(req.getParameter("re_level")));
		}
		article.setWriter(req.getParameter("writer"));
		article.setEmail(req.getParameter("email"));
		article.setSubject(req.getParameter("subject"));
		article.setPasswd(req.getParameter("passwd"));
		article.setContent(req.getParameter("content"));
		article.setBoardid(req.getParameter("boardid"));
		article.setIp(req.getRemoteAddr());
		/*<jsp:useBean id="article" class="board.BoardDataBean">
		<jsp:setProperty name="article" property="*"/>
		</jsp:useBean>*/
		
		// ------- end
		
		 System.out.println(article);

		BoardDBBean dbPro = BoardDBBean.getInstance();
		int chk = dbPro.updateArticle(article);
		
		// -------
		req.setAttribute("chk", chk);
		req.setAttribute("pageNum", pageNum);
		// ------- end

		return "/view/updatePro.jsp";
	}

}
