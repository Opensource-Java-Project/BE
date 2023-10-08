package Opensource.SharingService.repository;

import Opensource.SharingService.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
//    Optional<User> findByName(String name);
    Optional<User> findById(Long id);
    List<User> finAll();

}
