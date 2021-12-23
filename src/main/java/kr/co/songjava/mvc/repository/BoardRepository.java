package kr.co.songjava.mvc.repository;

import kr.co.songjava.framework.data.domain.PageRequestParameter;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.parameter.BoardParameter;
import kr.co.songjava.mvc.parameter.BoardSearchParameter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BoardRepository {

    List<Board> getList(PageRequestParameter parameter);
    Board get(int boardSeq);
    void save(BoardParameter board);
    void delete(int boardSeq);
    void update(BoardParameter board);

    void saveList(Map<String, Object> paramMap);
}
