package Opensource.SharingService.service;

import Opensource.SharingService.dto.MemberDTO;
import Opensource.SharingService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
<<<<<<< HEAD
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



=======
  private final UserRepository memberRepository;
  public void save(MemberDTO memberDTO) {
  }
>>>>>>> 09dffda9f9909a83096b88aa0d1dbcedbddf6485
}
