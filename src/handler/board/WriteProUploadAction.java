package handler.board;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.BoardDBBean;
import board.BoardDataBean;
import controller.CommandHandler;

public class WriteProUploadAction implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 5-1)
		BoardDataBean article = new BoardDataBean();
		BoardDBBean dbPro=BoardDBBean.getInstance();
		
		
		// =================================================
		
		// 6) fileSave 폴더 webcontent폴더 안에 만들기
		String realFolder = ""; //웹 어플리케이션상의 절대경로
		String encType = "euc-kr"; // 인코딩 타입
		int maxSize = 5 *1024 * 1024; // 최대 업로드 될 파일 크기 .. 5MB
		ServletContext context = req.getServletContext();
		realFolder =context.getRealPath("fileSave");
		MultipartRequest multi = null;
		
		// DefaultFileRenamePolicy는 중복된 파일 업로드할때 자동으로 Rename / aaa있으면 aaa(1)로
		multi = new MultipartRequest(req, realFolder, maxSize, encType,  new DefaultFileRenamePolicy());
		
		Enumeration files = multi.getFileNames();
		String filename="";
		File file = null;
		// =================================================
		
		// 7) 
		if (files.hasMoreElements()) { // 만약 파일이 다수면 if를 while로..
			String name = (String) files.nextElement();
			filename = multi.getFilesystemName(name); // DefaultFileRenamePolicy 적용
			String original = multi.getOriginalFileName(name); // 파일 원래 이름 (추가해도되고, 안해도..?)
			String type = multi.getContentType(name); // 파일 타입 (추가해도되고, 안해도..?)
			file = multi.getFile(name);
		}
		String pageNum = multi.getParameter("pageNum");
		String boardid = multi.getParameter("boardid");
		
		if (pageNum == null || pageNum == "") {pageNum = "1";}
		if (boardid==null) boardid = "1";
		
		
		if (multi.getParameter("num") != null && !multi.getParameter("num").equals("")) {
			article.setNum(Integer.parseInt(multi.getParameter("num")));
			article.setRef(Integer.parseInt(multi.getParameter("ref")));
			article.setRe_step(Integer.parseInt(multi.getParameter("re_step")));
			article.setRe_level(Integer.parseInt(multi.getParameter("re_level")));
		}
		article.setWriter(multi.getParameter("writer"));
		article.setEmail(multi.getParameter("email"));
		article.setSubject(multi.getParameter("subject"));
		article.setPasswd(multi.getParameter("passwd"));
		article.setContent(multi.getParameter("content"));
		article.setBoardid(multi.getParameter("boardid"));
		article.setIp(req.getRemoteAddr());
		// =================================================
		// 8)
		if (file != null) {
			article.setFilename(filename);
			article.setFilesize((int)file.length());
		} else {
			article.setFilename(" ");
			article.setFilesize(0);
		}
		// =================================================
		System.out.println(article);
		
		//9) insertArticle 메소드 수정 (insertArticle들어가면 //+ 표시가 추가된부분)
		dbPro.insertArticle(article);
		
		req.setAttribute("pageNum", pageNum);
		res.sendRedirect(req.getContextPath()+"/board/list?pageNum="+pageNum+"&boardid="+boardid);
		
	
		return null;
		
	}

}
