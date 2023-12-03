package Opensource.SharingService.dto;

import Opensource.SharingService.entity.BoardEntity;
import Opensource.SharingService.entity.ReservationInfoEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReservationDTO {
    private Long boardIndex;
    private List<ReservationInfoDTO> reservationList;


    public static ReservationDTO toReservationDTO (ReservationInfoEntity reservationInfoEntity) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setBoardIndex(reservationInfoEntity.getReservationIndex());

        reservationDTO.setReservationList(reservationInfoEntity.getReservationList()); //



        return reservationDTO;
    } // 엔티티 -> DTO 변환 용 생성자

}
