package Opensource.SharingService.dto;

import Opensource.SharingService.entity.ReservationInfoEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationInfoDTO {
    private String start;
    private String end;
    private String content;


    public static ReservationInfoDTO toReservationInfoDTO (ReservationInfoEntity reservationInfoEntity) {
        ReservationInfoDTO reservationInfoDTO = new ReservationInfoDTO();
        reservationInfoDTO.setStart(reservationInfoEntity.getStart());
        reservationInfoDTO.setEnd(reservationInfoEntity.getEnd());
        reservationInfoDTO.setContent(reservationInfoEntity.getContent());

        return reservationInfoDTO;
    } // 엔티티 -> DTO 변환 용 생성자

}
