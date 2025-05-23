package www.silver.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import www.silver.vo.BoardVO;

@Repository
public class WriteDAOImpl implements IF_writeDAO {

    @Inject
    SqlSession sqlSession;

    @Override
    public void insertWrite(BoardVO boardvo) {
        sqlSession.insert("www.silver.dao.IF_writeDAO.insertone", boardvo);
        System.out.println("DAO: 게시글 삽입 완료, postNum: " + boardvo.getPostNum());
    }

    @Override
    public void attachFname(Map<String, Object> params) {
        sqlSession.insert("www.silver.dao.IF_writeDAO.board_attach", params);
        System.out.println("DAO: 첨부파일 저장 완료, postNum: " + params.get("postNum") + ", filename: " + params.get("filename"));
    }

    @Override
    public List<BoardVO> selectall() {
        List<BoardVO> clist = sqlSession.selectList("www.silver.dao.IF_writeDAO.selectall");
        return clist;
    }

    @Override
    public List<BoardVO> pagingList(Map<String, Integer> pagingParams) {
        return sqlSession.selectList("www.silver.dao.IF_writeDAO.pagingList", pagingParams);
    }

    @Override
    public int boardCount() {
        int count = sqlSession.selectOne("www.silver.dao.IF_writeDAO.boardCount");
        System.out.println("DAO: Board count = " + count);
        return count;
    }

	@Override
	public BoardVO selectOne(Long postNum) {
		BoardVO boardvo = sqlSession.selectOne("www.silver.dao.IF_writeDAO.selectOne", postNum);
		return boardvo;
	}

	@Override
	public List<String> getAttach(Long postNum) {
		return sqlSession.selectList("www.silver.dao.IF_writeDAO.getAttach", postNum);
	}

	
	
}