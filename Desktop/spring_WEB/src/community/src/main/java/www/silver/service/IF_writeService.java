package www.silver.service;

import java.util.List;

import www.silver.vo.BoardVO;
import www.silver.vo.PageVO;

public interface IF_writeService {

	public void addWrite(BoardVO boardvo);

	public List<BoardVO> boardAllView();

	public List<BoardVO> pagingList(int page);

	public PageVO pagingParam(int page);

	public BoardVO textview(Long postNum);
	
	public List<String> getAttach(Long postNum);


	

}
 