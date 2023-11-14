package Opensource.SharingService.service;

import Opensource.SharingService.dto.MemberDTO;
import Opensource.SharingService.entity.MemberEntity;
import Opensource.SharingService.repository.MemberRepository;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor


public class MemberService {
  private final MemberRepository memberRepository;

  // 중복확인 메서드
  public boolean isEmailDuplicated(String memberEmail){
    Optional<MemberEntity> existingMember = memberRepository.findByMemberEmail(memberEmail);
    return existingMember != null;
  }
//


  public void save(MemberDTO memberDTO) {
    // 1. dto -> entity 변환
    // 2. repository의 save 메서드 호출
    MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
    memberRepository.save(memberEntity);
    // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
  }

  public MemberDTO login(MemberDTO memberDTO) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
    Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
    if (byMemberEmail.isPresent()) {
      // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
      MemberEntity memberEntity = byMemberEmail.get();
      if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
        // 비밀번호 일치
        // entity -> dto 변환 후 리턴
        MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);

        //로그인 성공 시 토큰 생성 및 설정
        String token = AuthService.generateToken(memberDTO.getMemberEmail());
        dto.setToken(token);
        return dto;
      } else {
        // 비밀번호 불일치(로그인실패)
        return null;
      }
    } else {
      // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
      return null;



    }
  }

  // 랜덤 토큰 생성 메서드
  public class KeyGenerator{

    public static String generateRandomKey(){
      SecureRandom secureRandom = new SecureRandom();
      byte[] key = new byte[32];
      secureRandom.nextBytes(key);
      return Base64.getEncoder().encodeToString(key);
    }
  }

  public class AuthService {

    private  static final String SECRET_KEY =  KeyGenerator.generateRandomKey();

    public static String generateToken(String memberEmail) {
      Date now = new Date();
      Date expiryData = new Date(now.getTime() + 100000); // 100000초 동안 유효

      return Jwts.builder()
          .setSubject(memberEmail)
          .setIssuedAt(now)
          .setExpiration(expiryData)
          .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
          .compact();
    }

  }


}




