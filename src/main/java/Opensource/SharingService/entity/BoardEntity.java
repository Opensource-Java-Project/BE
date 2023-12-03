package Opensource.SharingService.entity;

    import Opensource.SharingService.dto.BoardDTO;
    import Opensource.SharingService.dto.ReservationDTO;
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

    @Column String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int fileAttached; // 1 or 0

    @Column
    private String start;

    @Column
    private String end;

    @Column
    private String content;

    @Column
    private String boardPrice;


    @ElementCollection
    private List<String> boardImage; // 이미지 URL 목록


    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();


    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardPrice(boardDTO.getBoardPrice());

        boardEntity.setFileAttached(0);
        return boardEntity;
    } // 엔티티 생성 메서드.

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardIndex(boardDTO.getBoardIndex());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardPrice(boardDTO.getBoardPrice());


        return boardEntity;
    } // 엔티티 생성 메서드.

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardPrice(boardDTO.getBoardPrice());
        boardEntity.setFileAttached(1); // 파일 있음.
        return boardEntity;
    } // 엔티티 생성 메서드.


}
