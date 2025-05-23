package www.silver.dao;

import java.util.List;
import java.util.Map;

import www.silver.vo.BoardVO;

public interface IF_writeDAO {

	public void insertWrite(BoardVO boardvo);

	public void attachFname(Map<String, Object> params);

	public List<BoardVO> selectall();

	public List<BoardVO> pagingList(Map<String, Integer> pagingParams);

	public int boardCount();

	public BoardVO selectOne(Long postNum);

	public List<String> getAttach(Long postNum);


}
