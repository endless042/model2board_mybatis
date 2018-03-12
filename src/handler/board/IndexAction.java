package handler.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommandHandler;

public class IndexAction implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		return "/view/index.jsp"; //ÀÌ°Å ¹é´ÜÀÓ!! À¥ÄÜÅÙÃ÷ ¹Ø¿¡ °æ·Î
	}

}
