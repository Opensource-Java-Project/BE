package Opensource.SharingService.dto;

import Opensource.SharingService.entity.BoardEntity;
import Opensource.SharingService.entity.ReservationInfoEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long boardIndex; // 게시글 번호
    private String boardWriter; // 작성자
    private String boardTitle; // 제목
    private String boardContents; // 내용
    private int boardHits; // 조회수

    private MultipartFile boardFile; // save.html -> Controller 파일담는용도
    private String originalFileName; // 원본 파일명
    private String storedFileName; // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부여부 (1 or 0)
    private List<String> boardImage; // 이미지 링크 목록

    private String boardPrice;
    private List<ReservationDTO> reservationList;




    /*public BoardDTO(Long boardIndex, String boardWriter, String boardTitle){
        this.boardIndex = boardIndex;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
    } // 상세보기 용 생성자*/

    public static BoardDTO toBoardDTO (BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardIndex(boardEntity.getBoardIndex());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardPrice(boardEntity.getBoardPrice());


        if (boardEntity.getFileAttached() == 0) {
            boardDTO.setFileAttached(boardEntity.getFileAttached());
        } else {
            boardDTO.setFileAttached(boardEntity.getFileAttached());
            /*boardDTO.setOriginalFileName(boardEntity.getBoardFileEntityList().get(0).getOriginalFileName());
            boardDTO.setStoredFileName(boardEntity.getBoardFileEntityList().get(0).getStoredFileName());*/
            boardDTO.setBoardImage(boardEntity.getBoardImage());
        }
        boardDTO.setReservationList(boardEntity.getReservationList()); // 이 부분을 추가합니다.

        return boardDTO;
    } // 엔티티 -> DTO 변환 용 생성자

    private void setReservationList(List<ReservationInfoEntity> reservationList) {
        this.reservationList = reservationList;

    }


}
