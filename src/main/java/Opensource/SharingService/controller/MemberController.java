package Opensource.SharingService.controller;
import Opensource.SharingService.dto.MemberDTO;
import Opensource.SharingService.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
  //생성자 주입
  private final MemberService memberService;
  // 회원 가입 페이지 출력 요청

  // 중복확인 메서드
  @PostMapping("/register")
  @ResponseBody
  public ResponseEntity<String> checkDuplicateEmail(@RequestParam String email) {
    boolean isDuplicate = memberService.isEmailDuplicated(email);

    if (isDuplicate) {
      return ResponseEntity.status(409).body("중복된 이메일 있음"); // 중복된 이메일이 있음
    } else {
      return ResponseEntity.status(200).body("중복된 이메일 없음");
    }
  }
  //


  @GetMapping("/member/save")
  public String saveForm() {
    return "save";
  }

  @PostMapping("/member/save")
  public String save(@ModelAttribute MemberDTO memberDTO) {
    System.out.println("MemberController.save");
    System.out.println("memberDTO = " + memberDTO);
    memberService.save(memberDTO);
    return "login";
  }

  @GetMapping("/member/login")
  public String loginFrom() {
    return "login";
  }

  @PostMapping("/member/login")
  public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
    MemberDTO loginResult = memberService.login(memberDTO);
    if (loginResult != null) {
      // login 성공
      session.setAttribute("loggedInUser", loginResult);
      return "redirect:/main";
    } else {
      // login 실패
      return "login";
    }
  }

  //추가
  @GetMapping("/main")
  public String mainPage(HttpSession session) {
    MemberDTO loggedInUser = (MemberDTO) session.getAttribute("loggedInUser");

    if (loggedInUser != null) {
      // 로그인 상태
      return "main";
    } else {
      // 로그인되어 있지 않은 경우 로그인 페이지로 리다이렉트
      return "redirect:/member/login";
    }
  }


/*
  @GetMapping("/logout")
  public String logout(HttpSession session) {
    MemberDTO loggedInUser = (MemberDTO) session.getAttribute("loggedInUser");

    if (loggedInUser != null) {
      // 세션에서 사용자 정보 제거
      session.removeAttribute("loggedInUser");

      // 세션 토큰이 존재하면 해당 토큰을 이용해 로그아웃 처리
      String sessionToken = loggedInUser.getSession_Token();
      if (sessionToken != null && !sessionToken.isEmpty()) {
        memberService.logoutByToken(sessionToken);
      }
    }

    return "redirect:/member/login"; // 로그아웃 후 이동할 페이지
  }
*/


}
