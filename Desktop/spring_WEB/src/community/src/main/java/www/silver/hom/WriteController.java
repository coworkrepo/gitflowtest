package www.silver.hom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import www.silver.vo.PageVO;
import www.silver.vo.BoardVO;
import www.silver.service.IF_writeService;
import www.silver.util.FileDataUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@Controller
public class WriteController {

	@Inject
	IF_writeService writeService;

	@Inject
	FileDataUtil filedatautil;

	@RequestMapping(value = "/fleaMarket", method = RequestMethod.GET)
	public String viewBoard() {
		return "redirect:boardview";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "member/write";
	}

	@RequestMapping(value = "/insertContent", method = RequestMethod.POST)
	public String saveWrite(@ModelAttribute BoardVO boardvo, MultipartFile[] file) throws Exception {
		try {
			List<String> filename = filedatautil.fileUpload(file);
			if (filename == null || filename.isEmpty()) {
				System.out.println("파일 업로드 실패 또는 파일 없음");
				filename = new ArrayList<>();
			} else {
				System.out.println("파일 업로드 결과: " + filename);
			}
			boardvo.setFilename(filename);
			System.out.println("boardvo: " + boardvo.getTitle() + ", " + boardvo.getSaleStatus());
			writeService.addWrite(boardvo);
			System.out.println("서비스 호출 완료, 뷰 반환");
			return "redirect:boardview";
		} catch (Exception e) {
			System.out.println("에러 발생: " + e.getMessage());
			e.printStackTrace();
			return "redirect:/error";
		}
	}

	@RequestMapping(value = "/boardview", method = RequestMethod.GET)
	public String boardview() {
		return "redirect:/paging?page=1";

	}

	@GetMapping("/paging")
	public String paging(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		if (page < 1) {
			return "redirect:/paging?page=1"; // 음수 페이지 리다이렉트
		}
		System.out.println("Requested page: " + page);
		List<BoardVO> pagingList = writeService.pagingList(page);
		System.out.println("Paging list size: " + pagingList.size());
		PageVO pagevo = writeService.pagingParam(page);
		if (pagevo == null) {
			System.out.println("Error: pagevo is null");
			pagevo = new PageVO();
			pagevo.setPage(1);
			pagevo.setMaxPage(1);
			pagevo.setStartPage(1);
			pagevo.setEndPage(1);
		}
		model.addAttribute("contentlist", pagingList);
		model.addAttribute("paging", pagevo);
		return "member/board";
	}

	@RequestMapping(value = "/textview", method = RequestMethod.GET)
	public String textview(@RequestParam("postNum") Long postNum, Model model) {
		BoardVO boardvo = writeService.textview(postNum);
		model.addAttribute("post", boardvo);
		List<String> attachList = writeService.getAttach(boardvo.getPostNum());
		model.addAttribute("attachList", attachList);
		return "member/detailview";
	}

}