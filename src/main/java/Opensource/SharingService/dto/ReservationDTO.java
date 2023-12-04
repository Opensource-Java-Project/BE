package Opensource.SharingService.dto;

import Opensource.SharingService.entity.BoardEntity;
import Opensource.SharingService.entity.ReservationInfoEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationDTO {
    private List<ReservationInfoDTO> reservationList;
}
