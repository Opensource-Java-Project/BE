package Opensource.SharingService.service;

import Opensource.SharingService.dto.MemberDTO;
import Opensource.SharingService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository memberRepository;
  public void save(MemberDTO memberDTO) {
  }
}
