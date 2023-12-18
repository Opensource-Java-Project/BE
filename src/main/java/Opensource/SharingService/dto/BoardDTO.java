package Opensource.SharingService.dto;

import Opensource.SharingService.entity.BoardEntity;
import Opensource.SharingService.entity.MemberEntity;
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


    private MemberEntity memberEmail; // 작성자
    private String boardTitle; // 제목
    private String boardContents; // 내용
    private String boardPrice;

    private Long boardIndex; // 게시글 번호
    private MultipartFile boardFile; // save.html -> Controller 파일담는용도
    private String originalFileName; // 원본 파일명
    // private String storedFileName; // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부여부 (1 or 0)

    // private List<ReservationInfoDTO> reservationList; // 여기에 예약 정보를 포함





    public static BoardDTO toBoardDTO (BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        MemberEntity memberEntity = new MemberEntity();

        boardDTO.setBoardIndex(boardEntity.getBoardIndex());
        memberEntity.setMemberEmail(boardEntity.getBoardWriter().getMemberEmail());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardPrice(boardEntity.getBoardPrice());
        boardDTO.setMemberEmail(memberEntity);




        if (boardEntity.getFileAttached() == 0) {
            boardDTO.setFileAttached(boardEntity.getFileAttached());
        } else {
            boardDTO.setFileAttached(boardEntity.getFileAttached());
        }


        return boardDTO;
    } // 엔티티 -> DTO 변환 용 생성자




}
