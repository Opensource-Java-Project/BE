package Opensource.SharingService.controller;
import Opensource.SharingService.dto.LoginResponseDTO;
import Opensource.SharingService.dto.MemberDTO;
import Opensource.SharingService.dto.MemberEmailDTO;
import Opensource.SharingService.entity.MemberEntity;
import Opensource.SharingService.repository.MemberRepository;
import Opensource.SharingService.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {
  //생성자 주입
  private final MemberService memberService;
  private final MemberRepository memberRepository;
  // 회원 가입 페이지 출력 요청

  // 중복확인 메서드
  @PostMapping("/checkDuplicatedEmail")
  @ResponseBody
  public ResponseEntity<String> checkDuplicateEmail(@RequestBody MemberEmailDTO memberEmailDTO) {
    String memberEmail = memberEmailDTO.getMemberEmail();
    boolean isDuplicate = memberService.isEmailDuplicated(memberEmail);
    System.out.println(isDuplicate);

    if (isDuplicate) {
      return ResponseEntity.status(409).body("중복된 이메일 있음"); // 중복된 이메일이 있음
    } else {
      return ResponseEntity.status(200).body("중복된 이메일 없음");
    }
  }


  @GetMapping("/register")
  public String saveForm() {
    return "save";
  }

  //프론트에서 요청이 제대로 안들어 온다면 반환방식 검토할것 ex) @RequestBody 인지 @RequestParam인지
  @PostMapping("/register")
  public ResponseEntity<String> save(@RequestBody MemberDTO memberDTO) {
    System.out.println("MemberController.save");
    System.out.println("memberDTO = " + memberDTO);
    memberService.save(memberDTO);
    return ResponseEntity.status(200).body("회원가입 성공");
  }

  @GetMapping("/login")
  public String loginFrom() {
    return "login";
  }


  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> login(@RequestBody MemberDTO memberDTO, HttpSession session) {
    MemberDTO loginResult = memberService.login(memberDTO);
    if (loginResult != null) {
      // login 성공
      session.setAttribute("loggedInUser", loginResult);
      // LoginResponseDTO를 사용하여 JSON 응답 생성
      LoginResponseDTO responseDTO = new LoginResponseDTO(true, loginResult.getSessionToken(), loginResult.getMemberEmail());
      return ResponseEntity.ok(responseDTO);
    } else {
      // 로그인 실패 시 응답
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
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
      return "login";
    }
  }


//로그아웃
@PostMapping("/logout")
public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
  // Authorization 헤더에서 토큰 추출
  String token = extractToken(authorizationHeader);

  // 토큰 검증
  if (isValidToken(token)) {
    // 토큰이 유효하면 로그아웃 처리
    memberService.logoutByToken(token);
    // 성공 응답
    return ResponseEntity.status(200).body("로그아웃 성공");
  } else {
    // 토큰이 유효하지 않으면 실패 응답
    return ResponseEntity.status(400).body("유효하지 않은 토큰");
  }
}

//로그아웃 시 요청(헤더)에서 토큰 추출하기
  private String extractToken(String authorizationHeader) {
    // Authorization 헤더에서 "Bearer " 이후의 토큰 부분 추출
    return authorizationHeader.replace("Bearer ", "");
  }
  //로그아웃 시 토큰 검증하기 위한 메서드
  private boolean isValidToken(String token) {
    // MemberEntity에서 해당 토큰을 가진 회원을 찾음
    Optional<MemberEntity> member = memberRepository.findBySessionToken(token);

    // 회원이 존재하고 토큰이 비어있지 않으면 유효한 토큰으로 간주
    return member.isPresent() && member.get().hasSession_Token();  }


}
