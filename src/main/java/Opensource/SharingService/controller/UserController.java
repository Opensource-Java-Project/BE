package Opensource.SharingService.controller;
import Opensource.SharingService.dto.MemberDTO;
import Opensource.SharingService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
  //생성자 주입
  private final UserService memberService;
  // 회원 가입 페이지 출력 요청
  @GetMapping("/member/save")
  public String saveForm() {return "save";}

  @PostMapping("/member/save")
  public String save(@ModelAttribute MemberDTO memberDTO){
    System.out.println("MemberConteroller.save");
    System.out.println("memberDTO = " + memberDTO);
    memberService.save(memberDTO);
    return "index";
  }

}
