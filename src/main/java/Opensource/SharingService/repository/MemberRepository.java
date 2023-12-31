package Opensource.SharingService.repository;

import Opensource.SharingService.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
    //이메일로 회원 정보 조회 (selet*from member_table where member_email=?)
Optional<MemberEntity> findByMemberEmail(String memberEmail);
Optional<MemberEntity> findBySessionToken(String sessionToken);

}


