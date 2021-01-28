package co.kr.datapia.domain.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository; //static 어떻게 빼지

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public Board getBoard(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        return board;
    }

    public Board addBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public Board updateBoard(Long id, String page) {
        Board board = boardRepository.findById(id).orElse(null);
        board.updatePage(page);
        return board;

    }
}
