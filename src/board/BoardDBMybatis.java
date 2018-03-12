package board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class BoardDBMybatis extends MybatisConnector {
	private final String namespace = "ldg.mybatis";
	private static BoardDBMybatis instance = new BoardDBMybatis();
	private BoardDBMybatis() {}
	public static BoardDBMybatis getInstance() {
		return instance;
	}
	
	SqlSession sqlSession;
	
	// 순차적으로 수정 시작. (list부터)
	// listAction관련. ================================================== 
	public int getArticleCount(String boardid) {
		int x = 0;
		// 추가
		sqlSession= sqlSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("boardid", boardid);
		x = sqlSession.selectOne(namespace + ".getArticleCount" ,map); // 매게변수 boardid도 넣어야함. (map으로 넣음)
		// x = sqlSession.selectOne(namespace + ".getArticleCount" ,boardid); // <<== 처음엔 이렇게함. 갑자기 Hashmap으로 바꿈. 
		sqlSession.close();
		return x;
		// end. 추가
		
		
		// 다 필요 없음.
		/*String sql = "SELECT nvl(count(*),0) FROM BOARD WHERE boardid = ?";
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int number = 0;
		
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardid);
		
		rs = pstmt.executeQuery();
		if (rs.next()) { x = rs.getInt(1); }
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, rs, pstmt);
		}
		
		return x;
		*/
		
	}
	
	// 원래 메소드 내용 다 지움. 위와 같이.
	public List getArticles(int startRow, int endRow, String boardid) { 
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("boardid", boardid);
		List li = sqlSession.selectList(namespace + ".getArticles" ,map);
		sqlSession.close();
		return li;
		
	}
	// end. listAction관련. ==================================================
	// (다 수정했으니) board.xml로 가기 <180312 오전>
	
	
	
	// WriteProUploadAction 관련  ==================================================
	public void insertArticle(BoardDataBean article) {
		sqlSession= sqlSession();
		
		// select boardser.nextval from dual
		int number = sqlSession.selectOne(namespace + ".getNextNumber" ,article);
		number=number+1;
		
		// update board set re_step=re_step+1 where ref=? and re_step > ? and boardid = ?	
		if(article.getNum()!=0) {	// 0이 아니면 답글.
			sqlSession.update(namespace + ".updateRe_step" ,article);
			article.setRe_level(article.getRe_level() + 1);
			article.setRe_step(article.getRe_step() + 1);
			
		} else { // 0이면 새글 
			article.setRef(number);
			article.setRe_level(0);
			article.setRe_step(0);
		}
		article.setNum(number);
		//insert into board(num, writer, email, subject, passwd, reg_date, ref, re_step, re_level, content, ip, boardid, filename, filesize)
		//values(?,?,?,?,?, sysdate, ?,?,?,?,?,?,?,?)
		
		
		sqlSession.insert(namespace + ".insertBoard" ,article);
		sqlSession.commit(); // DML 이기 떄문에 커밋 추가.
		sqlSession.close();
	}
	// end. WriteProUploadAction 관련  ==================================================
	// <180312 오후>
	
}
