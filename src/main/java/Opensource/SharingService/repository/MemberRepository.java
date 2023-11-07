package Opensource.SharingService.repository;

import Opensource.SharingService.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD:src/main/java/Opensource/SharingService/repository/UserRepository.java
import java.util.List;
=======
//<<<<<<< HEAD
>>>>>>> 3dc6b6c0596fc9e52aa330516063557b7805b558:src/main/java/Opensource/SharingService/repository/MemberRepository.java
import java.util.Optional;


public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
    //이메일로 회원 정보 조회 (selet*from member_table where member_email=?)
    Optional<MemberEntity>findByMemberEmail(String UserEmail);
}

/*    User save(UserEntity user);
//    Optional<User> findByName(String name);
    Optional<UserEntity> findById(Long id);
    List<UserEntity> finAll();
<<<<<<< HEAD:src/main/java/Opensource/SharingService/repository/UserRepository.java
=======

=======
public interface UserRepository extends JpaRepository<UserEntity, Long> {
>>>>>>> 09dffda9f9909a83096b88aa0d1dbcedbddf6485
}
*/

>>>>>>> 3dc6b6c0596fc9e52aa330516063557b7805b558:src/main/java/Opensource/SharingService/repository/MemberRepository.java
