package Opensource.SharingService.controller;
import Opensource.SharingService.dto.MemberDTO;
import Opensource.SharingService.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {
  //생성자 주입
  private final MemberService memberService;
  // 회원 가입 페이지 출력 요청

  // 중복확인 메서드
  @PostMapping("/checkDuplicateEmail")
  @ResponseBody
  public String checkDuplicateEmail(@RequestParam String email){
    boolean isDuplicate = memberService.isEmailDuplicated(email);

    if(isDuplicate){
      return "T"; // 중복된 이메일이 있음
    }else {
      return "login";
    }
  }
  //


  @GetMapping("/member/save")
  public String saveForm() {return "save";}

  @PostMapping("/member/save")
  public String save(@ModelAttribute MemberDTO memberDTO){
    System.out.println("MemberController.save");
    System.out.println("memberDTO = " + memberDTO);
    memberService.save(memberDTO);
    return "login";
  }
  @GetMapping("/member/login")
  public String loginForm() {
    return "login";
  }

  @PostMapping("/member/login")
  public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
    MemberDTO loginResult = memberService.login(memberDTO);
    if (loginResult != null) {
      // login 성공
      session.setAttribute("loginEmail", loginResult.getMemberEmail());
      return "main";
    } else {
      // login 실패
      return "login";
    }
  }

}
