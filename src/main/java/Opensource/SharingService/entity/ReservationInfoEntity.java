package Opensource.SharingService.entity;

import Opensource.SharingService.dto.BoardDTO;
import Opensource.SharingService.dto.ReservationInfoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reservation_info")
public class ReservationInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_index")
    private BoardEntity boardEntity;

    private String start;
    private String end;
    private String content;

    public static BoardEntity toSaveEntity(ReservationInfoDTO reservationInfoDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setStart(reservationInfoDTO.getStart());
        boardEntity.setEnd(reservationInfoDTO.getEnd());
        boardEntity.setContent(reservationInfoDTO.getContent());

        return boardEntity;
    } // 엔티티 생성 메서드.
    // 생성자, getter, setter 등 필요한 메서드 추가
}
