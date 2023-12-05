package Opensource.SharingService.entity;

import Opensource.SharingService.dto.BoardDTO;
import Opensource.SharingService.dto.ReservationInfoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "reservation_info")
public class ReservationInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_index")
    private BoardEntity boardIndex;

    private String start;
    private String end;
    private String content;

    public static ReservationInfoEntity toSaveEntity(ReservationInfoDTO reservationInfoDTO) {

        ReservationInfoEntity reservationInfoEntity = new ReservationInfoEntity();

        reservationInfoEntity.setStart(reservationInfoDTO.getStart());
        reservationInfoEntity.setEnd(reservationInfoDTO.getEnd());
        reservationInfoEntity.setContent(reservationInfoDTO.getContent());

        return reservationInfoEntity;
    } // 엔티티 생성 메서드.
    // 생성자, getter, setter 등 필요한 메서드 추가
}
