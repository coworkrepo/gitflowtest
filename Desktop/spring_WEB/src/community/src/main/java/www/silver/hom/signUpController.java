package www.silver.hom;

import javax.inject.Inject;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import www.silver.service.IF_signUpService;
import www.silver.vo.MemberVO;

@Controller
public class signUpController {

    @Inject 
    IF_signUpService signupservice;

 // 회원가입 폼 보여주기
    @GetMapping("/signup")
    public String signUpMember(@ModelAttribute MemberVO membervo) {
    	 System.out.println("회원가입 페이지 들어옴");
        return "member/signup"; // WEB-INF/views/member/signup.jsp
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberVO membervo) {
        System.out.println("Received MemberVO: " + membervo);
        signupservice.insert(membervo); // 테스트용 주석 처리
        return "redirect:/signup";
    }
    
   

   
   

}