package Opensource.SharingService.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_file_table")
public class BoardFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long boardFileIndex; // 게시글 파일 인덱스(PK)


    @Column
    private String originalFileName; // 원본 파일명(업로드 파일명)

//    @Column
//    // private String storedFileName; // 저장된 파일명(서버 파일 경로)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardIndex")
    private BoardEntity boardEntity;

    public static BoardFileEntity toBoardFileEntity (BoardEntity boardEntity, String originalFileName, String storedFileName) {
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        // boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setBoardEntity(boardEntity);
        return boardFileEntity;
    }

}
