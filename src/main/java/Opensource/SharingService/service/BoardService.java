package Opensource.SharingService.service;

import Opensource.SharingService.dto.BoardDTO;
import Opensource.SharingService.dto.ReservationDTO;
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
            BoardEntity board = boardRepository.findById(saveIndex).get();


            BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFileName);
            boardFileRepository.save(boardFileEntity);
        }
    } // 저장 처리

    /*public void saveReservation(ReservationDTO reservationDTO, ReservationInfoDTO reservationInfoDTO) throws IOException {
        if (reservationDTO != null) {
            ReservationInfoEntity reservationEntity = ReservationInfoEntity.toSaveEntity(reservationInfoDTO);
            reservationRepository.save(reservationEntity);
        }
    }*/

    public void saveReservation(List<ReservationInfoDTO> reservationList) {
        if (reservationList != null) {
            for (ReservationInfoDTO reservationInfoDTO : reservationList) {
                // ReservationInfoDTO에서 필요한 정보 추출
                Long boardIndex = reservationInfoDTO.getBoardIndex();
                String start = reservationInfoDTO.getStart();
                String end = reservationInfoDTO.getEnd();
                String content = reservationInfoDTO.getContent();

                // 필요한 정보 활용 (예시로 출력)
                System.out.println("Board Index: " + boardIndex);
                System.out.println("Start: " + start);
                System.out.println("End: " + end);
                System.out.println("Content: " + content);

                // 필요한 정보를 활용하여 다른 작업 수행 가능
                // boardIndex, start, end, content 등을 활용하여 원하는 로직을 수행할 수 있음
            }
        }
    }

    @Transactional
    public List<ReservationInfoDTO> findAllAndProcessReservation() {
        List<ReservationInfoEntity> reservationEntityList = reservationRepository.findAll();
        List<ReservationInfoDTO> processedReservationDTOList = new ArrayList<>();

        for (ReservationInfoEntity reservationEntity : reservationEntityList) {
            // 필요한 필드 추출
            Long boardIndex = reservationEntity.getBoardIndex();
            String start = reservationEntity.getStart();
            String end = reservationEntity.getEnd();
            String content = reservationEntity.getContent();

            // 추출한 필드를 활용하여 새로운 객체 생성
            ReservationInfoDTO processedReservationInfoDTO = new ReservationInfoDTO();
            ReservationInfoDTO.setBoardIndex(boardIndex);
            ReservationInfoDTO.setStart(start);
            ReservationInfoDTO.setEnd(end);
            ReservationInfoDTO.setContent(content);

            // 처리된 객체를 리스트에 추가
            processedReservationDTOList.add(processedReservationInfoDTO);
        }

        return processedReservationDTOList;
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

