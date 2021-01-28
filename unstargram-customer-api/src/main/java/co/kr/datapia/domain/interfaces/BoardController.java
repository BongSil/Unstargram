package co.kr.datapia.domain.interfaces;

import co.kr.datapia.domain.application.BoardService;
import co.kr.datapia.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boards")
    public List<Board> list() {
        List<Board> boards = boardService.getBoards();
        return boards;
    }
    @GetMapping("/boards/{id}")
    public Board detail(@PathVariable("id") Long id) {
        return boardService.getBoard(id);
    }

//    @PostMapping("/restaurants/{restaurantId}/reviews")
//    public ResponseEntity<?> create(
//            @PathVariable("restaurantId") Long restaurantId,
//            @Valid @RequestBody Board resource
//    ) throws URISyntaxException {
//        Board board = BoardService.addPage(boardId, resource);
//
//        String url = "/boards/" + boardId + "/reviews/" + Board.getId();
//        return ResponseEntity.created(new URI(url)).body("{}");
//    }

//    @PostMapping("/boards")
//    public ResponseEntity<?> create(@Valid @RequestBody Board resource) throws URISyntaxException {
//        Board board = boardService.addBoard(
//                Board.builder()
//                .page(resource.getPage())
//                .build());
//
//        URI location = new URI("/boards" + board.getId());
//        return ResponseEntity.created(location).body("{}");
//    }

    @PatchMapping("/boards/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Board resource) {
        String page = resource.getPage();
        boardService.updateBoard(id, page);
        return "{}";
    }

    //@DeleteMapping("/boards/{id}")

}
