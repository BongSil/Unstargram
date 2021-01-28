package co.kr.datapia.domain.application;

import co.kr.datapia.domain.Board;
import co.kr.datapia.domain.BoardNotFoundException;
import co.kr.datapia.domain.BoardRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class BoardServiceTests {

    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockBoardRepository();
        boardService = new BoardService(boardRepository);
    }

    private void mockBoardRepository() {
        Board board = Board.builder()
                .id(1234L)
                .page("카페 다녀옴")
                .build();
        List<Board> boards = new ArrayList<>();
        boards.add(board);

        given(boardRepository.findAll()).willReturn(boards);
        given(boardRepository.findById(1234L)).willReturn(Optional.of(board));
    }
    @Test
    public void getBoards() {
        List<Board> boards = boardService.getBoards();
        Board board = boards.get(0);
        assertThat(board.getId(), is(1000L)); //오류
    }
    @Test
    public void getBoardWithExisted() {
        Board board = boardService.getBoard(1000L);
        assertThat(board.getId(), is(1000L)); //오류
    }
    @Test(expected = BoardNotFoundException.class)
    public void getBoardWithNotExisted() {
        boardService.getBoard(404L);
    }
    @Test
    public void addBoard() { //이해가 안가는 코드
        given(boardRepository.save(any())).will(invocation -> {
            Board board = invocation.getArgument(0);
            board.setId(1234L);
            return board;
        });

        Board board = Board.builder()
                .page("식당 다녀옴")
                .build();
        Board created = boardService.addBoard(board);
        assertThat(created.getId(), is(1234L));
    }
    @Test
    public void updateBoard() {
        Board board = Board.builder()
                .id(1000L)
                .page("카페 다녀옴")
                .build();
        given(boardRepository.findById(1000L)).willReturn(Optional.of(board));
        boardService.updateBoard(1000L, "카페 추천");
        assertThat(board.getPage(), is("카페 추천"));
    }
}