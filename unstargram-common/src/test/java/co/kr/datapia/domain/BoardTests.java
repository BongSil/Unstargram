package co.kr.datapia.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BoardTests {

    @Test
    public void creation() {
        Board board = Board.builder()
                .id(1234L) //작성자
                .page("카페 다녀옴")
                .build();

        //assertThat(board.getString(), is("카페 사진")); //사진, 시간도 뺌
        assertThat(board.getPage(), is("카페 다녀옴")); //내용
    }

}