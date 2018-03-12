package handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;

public class DeleteFormAction implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		
		// 추가됨. deleteForm에서 붙여온게 아님. 이게 있어야 Form에 ${pageNum}, ${num} 등 으로 보낼수 있음.
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("num", num);
		// ------- end
		
		return "/view/deleteForm.jsp";
	}

}
