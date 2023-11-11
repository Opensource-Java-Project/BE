package Opensource.SharingService.entity;


import Opensource.SharingService.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="comment_table")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column
    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_index")
    private BoardEntity boardEntity;

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, BoardEntity boardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;

    }
}
