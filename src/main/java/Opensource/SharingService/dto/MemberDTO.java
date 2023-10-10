package Opensource.SharingService.dto;

// 회원정보의 필요한 내용들을 필드로 정의 .
import lombok.*;

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

}
