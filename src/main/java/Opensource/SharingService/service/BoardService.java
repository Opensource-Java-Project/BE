package Opensource.SharingService.service;

import Opensource.SharingService.dto.BoardDTO;
import Opensource.SharingService.dto.ReservationInfoDTO;
import Opensource.SharingService.entity.BoardEntity;
import Opensource.SharingService.entity.BoardFileEntity;
import Opensource.SharingService.entity.ReservationInfoEntity;
import Opensource.SharingService.repository.BoardFileRepository;
import Opensource.SharingService.repository.BoardRepository;
import Opensource.SharingService.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final ReservationRepository reservationRepository;

    public String getFileUrl(String storedFileName) {
        // 파일이 저장된 서버의 URL 경로를 구성하여 반환
        String serverBaseUrl = "https://example.com/files/"; // 파일이 호스팅되는 서버의 기본 URL
        return serverBaseUrl + storedFileName; // 저장된 파일의 이름을 기반으로 URL 생성
    }

    public void save(BoardDTO boardDTO) throws IOException {
        if (boardDTO.getBoardFile().isEmpty()) {
            // 첨부 파일 없음
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        } else {
            // 첨부 파일 있음
            /*
                1. DTO에 담긴 파일을 꺼냄
                2. 파일의 이름 가져옴
                3. 서버 저장용 이름을 만듦
                   내사진.jpg => 1231231_내사진.jpg
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. board_table에 해당 데이터 save 처리
                7. board_file_table에 해당 데이터 save 처리
             */
            MultipartFile boardFile = boardDTO.getBoardFile(); // 1
            String originalFilename = boardFile.getOriginalFilename(); // 2
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3
            String savePath = "C:/springboot_img/" + storedFileName; // 4 C:springboot_img/1231231_내사진.jpg
            boardFile.transferTo(new File(savePath)); // 5
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long saveIndex = boardRepository.save(boardEntity).getBoardIndex();
            BoardEntity boardIndex = boardRepository.findById(saveIndex).get();


            BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(boardIndex, originalFilename, storedFileName);
            boardFileRepository.save(boardFileEntity);
        }
    } // 저장 처리

    public void saveReservation(ReservationInfoDTO reservationInfoDTO) throws IOException {
        ReservationInfoEntity reservationInfoEntity = ReservationInfoEntity.toSaveEntity(reservationInfoDTO);
        reservationRepository.save(reservationInfoEntity);
    }

    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList; // 게시글 목록 가져오기
    }


    @Transactional
    public BoardDTO findById(Long boardIndex) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardIndex);
        if (optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    } // 상세 보기 처리

    public BoardDTO update(BoardDTO boardDTO){
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getBoardIndex());
    } // 수정 처리

    public void delete(Long boardIndex){
        boardRepository.deleteById(boardIndex);
    } // 삭제 처리






    /*public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // 페이지 번호
        int pageLimit = 5; // 페이지 당 게시물 수
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardIndex")));

        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getBoardIndex(), board.getBoardWriter(), board.getBoardTitle()));

        return boardDTOS;
    }*/
}

