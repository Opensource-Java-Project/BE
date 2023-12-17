package Opensource.SharingService.entity;

    import Opensource.SharingService.dto.BoardDTO;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;

    import java.util.ArrayList;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto_increment

    private Long boardIndex;

    // size default - 255, nullable


    @Column String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int fileAttached; // 1 or 0

    @Column
    private String boardPrice;


    @ElementCollection
    @CollectionTable(name = "user_emails", joinColumns = @JoinColumn(name = "user_boardWriter"))
    @Column(name = "email")
    private Set<String> emails = new HashSet<>();

    @ElementCollection
    private List<String> boardImage; // 이미지 URL 목록


    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_writer", referencedColumnName = "memberEmail")
    private MemberEntity boardWriter;


    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardPrice(boardDTO.getBoardPrice());

        boardEntity.setFileAttached(0);
        return boardEntity;
    } // 엔티티 생성 메서드.

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardIndex(boardDTO.getBoardIndex());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardPrice(boardDTO.getBoardPrice());


        return boardEntity;
    } // 엔티티 생성 메서드.

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardPrice(boardDTO.getBoardPrice());
        boardEntity.setFileAttached(1); // 파일 있음.
        return boardEntity;
    } // 엔티티 생성 메서드.


}
