package Opensource.SharingService.dto;


import Opensource.SharingService.entity.CommentEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long commentIndex;
    private String commentWriter;
    private String commentContents;
    private Long boardIndex; // 게시글 번호
    private LocalDateTime commentCreatedTime;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long boardIndex) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentIndex(commentEntity.getCommentIndex());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDTO.setBoardIndex(boardIndex);
        return commentDTO;
    }
}