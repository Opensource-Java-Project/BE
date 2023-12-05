package Opensource.SharingService.entity;

import Opensource.SharingService.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "Sharing_Table")
public class MemberEntity {
    @Id //pk지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(unique = true, nullable = false) // unique 제약조건 추가
    private String memberEmail;

    @Column(nullable = false)
    private String memberPassword;

    @Column(nullable = true)
    private String memberName;

    @Column
    private String sessionToken;

    @OneToMany(mappedBy = "boardWriter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardEntity> boards = new ArrayList<>();

    public boolean hasSession_Token() {
        return this.sessionToken != null && !this.sessionToken.isEmpty();
    }

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setSessionToken(memberDTO.getSessionToken());
        return memberEntity;
    }





}
