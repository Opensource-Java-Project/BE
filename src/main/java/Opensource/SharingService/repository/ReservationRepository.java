package Opensource.SharingService.repository;

import Opensource.SharingService.entity.BoardEntity;
import Opensource.SharingService.entity.ReservationInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationInfoEntity, Long> {
}
