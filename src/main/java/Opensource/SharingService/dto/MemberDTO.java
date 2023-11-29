package Opensource.SharingService.dto;

// 회원정보의 필요한 내용들을 필드로 정의 .
import Opensource.SharingService.entity.MemberEntity;
import lombok.*;
import org.h2.command.Token;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
  private  Long id;
  private  String memberEmail;
  private  String memberPassword;
  private String memberName;
  private String Session_Token;

  public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setId(memberEntity.getId());
    memberDTO.setMemberEmail(memberEntity.getMemberEmail());
    memberDTO.setMemberPassword(memberEntity.getMemberPassword());
    memberDTO.setMemberName(memberEntity.getMemberName());
    memberDTO.setSession_Token(memberEntity.getSession_Token());
    return memberDTO;

  }



}
