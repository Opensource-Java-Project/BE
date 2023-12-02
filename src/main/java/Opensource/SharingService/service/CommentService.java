package Opensource.SharingService.service;

import Opensource.SharingService.dto.CommentDTO;
import Opensource.SharingService.entity.BoardEntity;
import Opensource.SharingService.entity.CommentEntity;
import Opensource.SharingService.repository.BoardRepository;
import Opensource.SharingService.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardIndex());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getCommentIndex();
        }
        else {
            return null;
        }
    }

    public List<CommentDTO> findAll(Long boardIndex) {
        BoardEntity boardEntity = boardRepository.findById(boardIndex).get();

        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByCommentIndexDesc(boardEntity);

        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardIndex);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
