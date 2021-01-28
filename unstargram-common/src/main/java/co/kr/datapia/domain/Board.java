package co.kr.datapia.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    @Setter
    private Long id; //작성자

    @NotEmpty
    private List<String> string; //사진

    @NotEmpty
    private String page; //내용

    private Long time; //작성시간 어떤 형식이어야 하지?

    public void updatePage(String page) {
        this.page = page;
    }
}
