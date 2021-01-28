package co.kr.datapia.domain.interfaces;

import co.kr.datapia.domain.application.BoardService;
import co.kr.datapia.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BoardController.class)
public class BoardControllerTests {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private BoardService boardService;

    @Test
    public void list() throws Exception {
        List<Board> boards = new ArrayList<>();
        boards.add(Board.builder()
                .id(1000L)
                .page("카페 다녀옴")
                .build());
        given(boardService.getBoards()).willReturn(boards);

        mvc.perform(get("/boards"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1000")));
            }


    @Test
    public void createWithValidData() throws Exception{
        given(boardService.addBoard(any())).will(invocation -> {
            Board board = invocation.getArgument(0);
            return Board.builder()
                    .id(1234L)
                    .page(board.getPage())
                    .build();
        });

        mvc.perform(post("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"page\":\"카페 다녀옴\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/board/1234"))
                .andExpect(content().string("{}"));

        verify(boardService).addBoard(any());
    }

    @Test
    public void createWithInvalidData() throws Exception{
        mvc.perform(post("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"page\":\"카페 다녀옴\"}"))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void updateWithValidData() throws Exception {
        mvc.perform(patch("/boards/1000")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"page\":\"감성 카페\"}"))
                .andExpect(status().isOk());
        verify(boardService).updateBoard(1000L,"감성 카페");
    }
    @Test
    public void updateWithInvalidData() throws Exception {
        mvc.perform(patch("/boards/1000")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"page\":\"감성 카페\"}"))
                .andExpect(status().isBadRequest());
    }
}