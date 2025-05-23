package www.silver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import www.silver.vo.BoardVO;
import www.silver.vo.PageVO;
import www.silver.dao.IF_writeDAO;

@Service
public class WriteServiceImpl implements IF_writeService {

	@Inject
	IF_writeDAO writedao;

	@Override
	public void addWrite(BoardVO boardvo) {
		// 게시글 입력 (postNum이 자동 설정됨)
		writedao.insertWrite(boardvo);
		// 입력 후 생성된 postNum 가져오기
		Long postNum = boardvo.getPostNum();
		List<String> fname = boardvo.getFilename();
		for (String filename : fname) {
			Map<String, Object> params = new HashMap<>();
			params.put("postNum", postNum);
			params.put("filename", filename);
			writedao.attachFname(params);
		}
	}

	@Override
	public List<BoardVO> boardAllView() {
		List<BoardVO> clist = writedao.selectall();
		return clist;
	}

	int pageLimit = 10; // 한 페이지당 보여줄 게시글 수
	int blockLimit = 5; // 하단에 보여줄 페이지 번호 개수

	@Override
	public List<BoardVO> pagingList(int page) {
		if (page < 1) {
			page = 1;
		}
		int pagingStart = (page - 1) * pageLimit;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pagingStart);
		pagingParams.put("limit", pageLimit);
		List<BoardVO> pagingList = writedao.pagingList(pagingParams);
		System.out.println("Paging list retrieved: " + pagingList.size() + " items for page " + page);
		return pagingList;
	}

	@Override
	public PageVO pagingParam(int page) {
	    int boardCount = writedao.boardCount();
	    System.out.println("Total board count: " + boardCount);

	    int maxPage = (int) Math.ceil((double) boardCount / pageLimit);
	    if (maxPage < 1) maxPage = 1;

	    // page 값 보정
	    if (page < 1) page = 1;
	    if (page > maxPage) page = maxPage;

	    // 시작 페이지 목록 계산
	    int startPage = ((page - 1) / blockLimit) * blockLimit + 1;
	    int endPage = startPage + blockLimit - 1;
	    if (endPage > maxPage) endPage = maxPage;

	    PageVO pagevo = new PageVO();
	    pagevo.setPage(page);
	    pagevo.setMaxPage(maxPage);
	    pagevo.setStartPage(startPage);
	    pagevo.setEndPage(endPage);

	    System.out.println("== 페이지 확인 ==");
	    System.out.println("page = " + page);
	    System.out.println("startPage = " + startPage);
	    System.out.println("endPage = " + endPage);
	    System.out.println("maxPage = " + maxPage);

	    return pagevo;
	}

	@Override
	public BoardVO textview(Long postNum) {
		BoardVO boardvo = writedao.selectOne(postNum);
		System.out.println("선택한 글 번호는 " + postNum + "번 입니다.");
		return boardvo;
	}

	@Override
	public List<String> getAttach(Long postNum) {
		return writedao.getAttach(postNum);
	}
}
