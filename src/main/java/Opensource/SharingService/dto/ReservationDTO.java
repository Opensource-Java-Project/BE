package Opensource.SharingService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationDTO {
    private Long boardIndex;
    private List<ReservationInfoDTO> reservationList;
}
