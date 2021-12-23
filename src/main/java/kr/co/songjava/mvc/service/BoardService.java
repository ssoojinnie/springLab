package kr.co.songjava.mvc.service;

import kr.co.songjava.framework.data.domain.PageRequestParameter;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.parameter.BoardParameter;
import kr.co.songjava.mvc.parameter.BoardSearchParameter;
import kr.co.songjava.mvc.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
* 게시판 서비스
* @author PSJ
 */
@Service
public class BoardService {

    @Autowired
    private BoardRepository repository;

    /*
     * 목록리턴
     * @author PSJ
     */
    public List<Board> getList(PageRequestParameter parameter){
           return repository.getList(parameter);
    }

    /*
     * 상세정보 리턴
     * @author PSJ
     */
    public Board get(int boardSeq){
        return repository.get(boardSeq);
    }

    /*
     * 등록 처리
     * @author PSJ
     */
    public void save(BoardParameter parameter){
        //조회하여 리턴된 정보
        Board board = repository.get(parameter.getBoardSeq());
        if(board == null){
            repository.save(parameter);
        }else{
            repository.update(parameter);
        }
    }

    /*
     * 삭제처리
     * @author PSJ
     */
    public void delete(int boardSeq){
        repository.delete(boardSeq);
    }

    /*
    단순 반복문을 이용한 등록 처리
     */
    public void saveList1(List<BoardParameter> list){
        for(BoardParameter parameter : list){
            repository.save(parameter);
        }
    }

    /*
    100개씩 배열에 담아서 일괄 등록 처리
     */
    public void saveList2(List<BoardParameter> boardList){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("boardList", boardList);
        repository.saveList(paramMap);
    }

}
