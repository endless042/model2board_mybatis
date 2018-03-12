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
	
	
	// listAction����. ================================================== ���������� ���� ����. (list����)
	public int getArticleCount(String boardid) {
		int x = 0;
		// �߰�
		sqlSession= sqlSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("boardid", boardid);
		x = sqlSession.selectOne(namespace + ".getArticleCount" ,map); // �ŰԺ��� boardid�� �־����. (map���� ����)
		// x = sqlSession.selectOne(namespace + ".getArticleCount" ,boardid); // <<== ó���� �̷�����. ���ڱ� Hashmap���� �ٲ�. 
		sqlSession.close();
		return x;
		// end. �߰�
		
		
		// �� �ʿ� ����.
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
	
	// ���� �޼ҵ� ���� �� ����. ���� ����.
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
	// end. listAction����. ==================================================
	// (�� ����������) board.xml�� ����

}
