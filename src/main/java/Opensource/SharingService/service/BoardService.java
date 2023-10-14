package Opensource.SharingService.service;

import Opensource.SharingService.dto.BoardDTO;
import Opensource.SharingService.entity.BoardFileEntity;
import Opensource.SharingService.repository.BoardFileRepository;
import Opensource.SharingService.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        if (boardDTO.)
    }

}
