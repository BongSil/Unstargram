package co.kr.datapia.domain;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(Long id) {
        super("Could not find Board" + id);
    }
}
