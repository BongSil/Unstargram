package co.kr.datapia.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends CrudRepository<Board, Long> {

    List<Board> findAll();

    Optional<Board> findById(Long id);

    Board save(Board board);

}
