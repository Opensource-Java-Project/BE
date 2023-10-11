package Opensource.SharingService.repository;

import Opensource.SharingService.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;


public interface UserRepository {
    User save(UserEntity user);
//    Optional<User> findByName(String name);
    Optional<UserEntity> findById(Long id);
    List<UserEntity> finAll();

=======
public interface UserRepository extends JpaRepository<UserEntity, Long> {
>>>>>>> 09dffda9f9909a83096b88aa0d1dbcedbddf6485
}
