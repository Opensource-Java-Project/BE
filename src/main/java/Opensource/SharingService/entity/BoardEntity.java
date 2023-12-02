package Opensource.SharingService.entity;

    import Opensource.SharingService.dto.BoardDTO;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.util.ArrayList;
    import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto_increment

    private Long boardIndex;

    // size default - 255, nullable
    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column
    private String boardPass;

    @Column String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    @Column
    private int fileAttached; // 1 or 0

    @Column
    private String reservationStart;

    @Column
    private String reservationEnd;

    @Column
    private String reservationContent;

    @Column
    private int boardPrice;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();


    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setReservationContent(boardDTO.getReservationContent());
        boardEntity.setReservationEnd(boardDTO.getReservationEnd());
        boardEntity.setReservationStart(boardDTO.getReservationStart());
        boardEntity.setBoardPrice(boardDTO.getBoardPrice());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(0);
        return boardEntity;
    }
    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardIndex(boardDTO.getBoardIndex());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setReservationContent(boardDTO.getReservationContent());
        boardEntity.setReservationEnd(boardDTO.getReservationEnd());
        boardEntity.setReservationStart(boardDTO.getReservationStart());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        boardEntity.setBoardPrice(boardDTO.getBoardPrice());

        return boardEntity;
    }

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardPrice(boardDTO.getBoardPrice());
        boardEntity.setReservationContent(boardDTO.getReservationContent());
        boardEntity.setReservationEnd(boardDTO.getReservationEnd());
        boardEntity.setReservationStart(boardDTO.getReservationStart());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(1); // 파일 있음.
        return boardEntity;
    }

}
