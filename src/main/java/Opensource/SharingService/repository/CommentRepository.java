package Opensource.SharingService.repository;

import Opensource.SharingService.entity.BoardEntity;
import Opensource.SharingService.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByBoardEntityOrderByCommentIndexDesc(BoardEntity boardEntity);
}
