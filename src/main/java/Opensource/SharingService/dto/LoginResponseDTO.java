package Opensource.SharingService.dto;

import Opensource.SharingService.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private boolean auth;
    private String token;
    private String userId;

    // 생성자 추가
    public LoginResponseDTO(boolean auth, String token, String userId) {
        this.auth = auth;
        this.token = token;
        this.userId = userId;
    }

    // MemberEntity를 LoginResponseDTO로 변환하는 메서드
    public static LoginResponseDTO fromMemberEntity(MemberEntity memberEntity) {
        return new LoginResponseDTO(
            true,  // 로그인 성공한 경우 auth는 true로 설정
            memberEntity.getSessionToken(),
            memberEntity.getMemberEmail()
        );
    }

}


