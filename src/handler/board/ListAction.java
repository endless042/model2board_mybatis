package handler.board;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDBBean;
import board.BoardDBMybatis;
import controller.CommandHandler;

public class ListAction implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String boardid = req.getParameter("boardid");
		
		if (boardid==null) boardid = "1";

		int pageSize= 5;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String pageNum = req.getParameter("pageNum");
		
		if (pageNum == null || pageNum =="") {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;  
		//사칙연산은 곱셈먼저.. +1은 맨나중에! 왼쪽에서부터 오른쪽으로 차례대로 계산
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List articleList = null;
		BoardDBMybatis dbPro = BoardDBMybatis.getInstance(); // BoardDBBean ==> BoardDBMybatis로 바꾸기.
		count = dbPro.getArticleCount(boardid);
		
		//게시판에 있는 글 수 count
		if (count > 0) {
			articleList = dbPro.getArticles(startRow, endRow, boardid); 
		}
		number = count - (currentPage - 1) * pageSize;
	
		int bottomLine = 3; 
		
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine; //곱셈, 나눗셈먼저.
		int endPage = startPage + bottomLine -1;
		
		if (endPage > pageCount) endPage = pageCount;
		
		req.setAttribute("boardid", boardid);
		req.setAttribute("count", count);
		req.setAttribute("articleList", articleList);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("startPage", startPage);
		req.setAttribute("bottomLine", bottomLine);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("number", number);
		req.setAttribute("endPage", endPage);
	
			
		
		return "/view/list.jsp";
	}

}
