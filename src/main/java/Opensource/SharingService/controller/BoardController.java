package Opensource.SharingService.controller;

import Opensource.SharingService.dto.BoardDTO;
import Opensource.SharingService.dto.ReservationInfoDTO;
import Opensource.SharingService.entity.MemberEntity;
import Opensource.SharingService.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/board") // 게시글 저장 주소 설정
public class BoardController {
    private final BoardService boardService;


    @GetMapping("/upload")
    public String saveForm() {
        return "save";
    }

    // 파일을 올리는 거라 postman에서 form-data로 해야됌
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> save(
        @RequestParam("images") MultipartFile[] boardFile,
        @RequestParam("boardTitle") String boardTitle,
        @RequestParam("boardContents") String boardContents,
        @RequestParam("boardPrice") String boardPrice,
        @RequestParam("memberEmail") MemberEntity memberEmail
    ) throws IOException {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle(boardTitle);
        boardDTO.setBoardContents(boardContents);
        boardDTO.setBoardPrice(boardPrice);
        boardDTO.setMemberEmail(memberEmail);

        // 여기서 boardDTO를 이용하여 데이터베이스에 저장하는 로직 수행
        boardService.save(boardDTO, boardFile);

        return ResponseEntity.ok("Successfully saved!");
    }


    @PostMapping("/reservation")
    public ResponseEntity<String> reservation(@RequestBody ReservationInfoDTO reservationInfoDTO) {
        // DTO에 있는 값을 가져와서 사용할 수 있습니다.
       /* Long boardIndex = reservationInfoDTO.getBoardIndex();
        String start = reservationInfoDTO.getStart();
        String end = reservationInfoDTO.getEnd();
        String content = reservationInfoDTO.getContent();

        // 가져온 값들을 조합하여 다시 응답으로 전송할 수 있습니다.
        String reservationList = start + " - " + end + ": " + content;*/

        // 예약 정보를 저장합니다.
        try {
            boardService.saveReservation(reservationInfoDTO);
        } catch (IOException e) {
            // IOException 처리 로직
            return new ResponseEntity<>("Failed to save reservation", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 조합한 정보를 반환합니다.
        return new ResponseEntity<>("Successfully saved reservation", HttpStatus.OK); // 성공적인 응답을 JSON 형식으로 반환
    }

    /*@GetMapping("/saveReservation")
    public*/




    @GetMapping("/all")
    public String findAll(Model model) {
        // DB에서 전체 게시글 데이터를 가져와서 list.html에 보여준다.
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }


    /*@GetMapping("/update/{index}")
    public String updateForm(@PathVariable Long index, Model model) {
        BoardDTO boardDTO = boardService.findById(index);
        model.addAttribute("boardUpdate", boardDTO);
        return "update";
    } // 게시글 수정 페이지 출력 메소드.

    @PostMapping("/update")
    public String update(@RequestBody BoardDTO boardDTO, Model model) {
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board", board);
        return "detail";
//        return "redirect:/board/" + boardDTO.getId();
    } // 게시글 수정 후 상세 페이지로 이동하는 메소드

    @GetMapping("/delete/{index}")
    public String delete(@PathVariable Long index) {
        boardService.delete(index);
        return "redirect:/board/";
    } // 게시글 삭제 후 목록 페이지로 이동하는 메소드*/








    // /board/paging?page=1
    /*@GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
//        pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        // page 갯수 20개
        // 현재 사용자가 3페이지
        // 1 2 3
        // 현재 사용자가 7페이지
        // 7 8 9
        // 보여지는 페이지 갯수 3개
        // 총 페이지 갯수 8개

        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";

    } // 페이징 처리 후 목록 페이지로 이동하는 메소드*/



}