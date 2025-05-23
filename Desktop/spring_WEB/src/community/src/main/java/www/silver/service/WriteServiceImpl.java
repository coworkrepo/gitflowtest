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
		// �Խñ� �Է� (postNum�� �ڵ� ������)
		writedao.insertWrite(boardvo);
		// �Է� �� ������ postNum ��������
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

	int pageLimit = 10; // �� �������� ������ �Խñ� ��
	int blockLimit = 5; // �ϴܿ� ������ ������ ��ȣ ����

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

	    // page �� ����
	    if (page < 1) page = 1;
	    if (page > maxPage) page = maxPage;

	    // ���� ������ ��� ���
	    int startPage = ((page - 1) / blockLimit) * blockLimit + 1;
	    int endPage = startPage + blockLimit - 1;
	    if (endPage > maxPage) endPage = maxPage;

	    PageVO pagevo = new PageVO();
	    pagevo.setPage(page);
	    pagevo.setMaxPage(maxPage);
	    pagevo.setStartPage(startPage);
	    pagevo.setEndPage(endPage);

	    System.out.println("== ������ Ȯ�� ==");
	    System.out.println("page = " + page);
	    System.out.println("startPage = " + startPage);
	    System.out.println("endPage = " + endPage);
	    System.out.println("maxPage = " + maxPage);

	    return pagevo;
	}

	@Override
	public BoardVO textview(Long postNum) {
		BoardVO boardvo = writedao.selectOne(postNum);
		System.out.println("������ �� ��ȣ�� " + postNum + "�� �Դϴ�.");
		return boardvo;
	}

	@Override
	public List<String> getAttach(Long postNum) {
		return writedao.getAttach(postNum);
	}
}
