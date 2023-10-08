package Opensource.SharingService.repository;

import Opensource.SharingService.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    User save(UserEntity user);
//    Optional<User> findByName(String name);
    Optional<UserEntity> findById(Long id);
    List<UserEntity> finAll();

}
