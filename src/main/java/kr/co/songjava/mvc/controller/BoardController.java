package kr.co.songjava.mvc.controller;

import io.swagger.annotations.*;
import kr.co.songjava.configuration.exception.BaseException;
import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.configuration.http.BaseResponseCode;
import kr.co.songjava.framework.data.domain.MySQLPageRequest;
import kr.co.songjava.framework.data.domain.PageRequestParameter;
import kr.co.songjava.framework.data.web.bind.annotation.RequestConfig;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.parameter.BoardParameter;
import kr.co.songjava.mvc.parameter.BoardSearchParameter;
import kr.co.songjava.mvc.service.BoardService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/*
* 게시판 서비스
* @author PSJ
 */
@Controller
@RequestMapping("/board")
@Api(tags="게시판 API")
public class BoardController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BoardService boardService;

    /*
     * 목록리턴
     * @author PSJ
     */
    @GetMapping("/list")
    //@ResponseBody
    //@ApiOperation(value="목록 조회", notes = "목록 정보 조회 가능")
    public void list(BoardSearchParameter parameter, MySQLPageRequest pageRequest, Model model)
            //@ApiParam BoardSearchParameter parameter,
            //@ApiParam MySQLPageRequest pageRequest)
    {//페이지 요청 과 검색파라미터 분리

        logger.info("pageRequest:{}", pageRequest);
        PageRequestParameter<BoardSearchParameter> pageRequestParameter= new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);

        List<Board> list = boardService.getList(pageRequestParameter);
        model.addAttribute("boardList", list);
        //return new BaseResponse<List<Board>>(boardService.getList(pageRequestParameter));
    }

    @GetMapping("/form")
    @RequestConfig(loginCheck = false)
    public void form(BoardParameter parameter, Model model){
        if(parameter.getBoardSeq() > 0){
            Board board = boardService.get(parameter.getBoardSeq());
            model.addAttribute("board", board);
        }
        model.addAttribute("parameter", parameter);
    }

    /*
     * 상세정보 리턴
     * @author PSJ
     */
    @GetMapping("/{boardSeq}")
    @ApiOperation(value="상세조회", notes="게시물번호에 해당하는 상세정보 조회 가능")
    @ApiImplicitParams({
            @ApiImplicitParam(name="boardSeq", value = "게시물 번호", example = "1")
    })
    public BaseResponse<Board> get(@PathVariable int boardSeq){
        Board board = boardService.get(boardSeq);
        if(board==null){
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"게시물"});
        }
        return new BaseResponse<Board>(boardService.get(boardSeq));
    }

    /*
     * 등록/수정 처리
     * @author PSJ
     */
    @PutMapping("/save")
    @RequestConfig(loginCheck = false)//로그인체크하려면 true
    @ApiOperation(value="등록/수정 처리", notes="신규 게시물 저장 및 기존 게시물 업데이트 가능")
    @ApiImplicitParams({
            @ApiImplicitParam(name="boardSeq", value = "게시물 번호", example = "1"),
            @ApiImplicitParam(name="title", value = "제목", example = "spring"),
            @ApiImplicitParam(name="contents", value = "내용", example = "spring 강좌")
    })
    public BaseResponse<Integer> save(BoardParameter parameter){//보통 post, put 사용, 실제로는 get 사용 지양
        //제목, 내용 필수체크
        if(StringUtils.isEmpty(parameter.getTitle())){
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"Title", "제목"});
        }
        if(StringUtils.isEmpty(parameter.getContents())){
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"Contents", "내용"});
        }
        boardService.save(parameter);
        return new BaseResponse<Integer>(parameter.getBoardSeq());
    }

    /*
     * 삭제처리
     * @author PSJ
     */
    @DeleteMapping("/{boardSeq}")
    @RequestConfig
    @ApiOperation(value="삭제 처리", notes="게시물 번호에 해당하는 정보 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name="boardSeq", value = "게시물 번호", example = "1")
    })
    public BaseResponse<Boolean> delete(@PathVariable int boardSeq){
        Board board = boardService.get(boardSeq);
        if(board==null){
            return new BaseResponse<Boolean>(false);
        }
        boardService.delete(boardSeq);
        return new BaseResponse<Boolean>(true);
    }

    /*
    대용량 등록 처리
     */
    @ApiOperation(value="대용량 등록처리1" , notes="대용량 등록처리1")
    @PutMapping("/saveList1")
    public BaseResponse<Boolean> saveList1(){
        int count =0;
        List<BoardParameter> list = new ArrayList<BoardParameter>();
        while(true) {
            count++;
            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);
            list.add(new BoardParameter(title, contents));
            if (count >= 10000) {
                break;
            }
        }
        long start = System.currentTimeMillis();
        boardService.saveList1(list);
        long end = System.currentTimeMillis();
        logger.info("실행시간 : {}", (end-start)/1000.0);
        return new BaseResponse<Boolean>(true);

    }


    /*
    대용량 등록 처리
     */
    @ApiOperation(value="대용량 등록처리2" , notes="대용량 등록처리2")
    @PutMapping("/saveList2")
    public BaseResponse<Boolean> saveList2(){
        int count =0;
        List<BoardParameter> list = new ArrayList<BoardParameter>();
        while(true) {
            count++;
            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);
            list.add(new BoardParameter(title, contents));
            if (count >= 10000) {
                break;
            }
        }
        long start = System.currentTimeMillis();
        boardService.saveList2(list);
        long end = System.currentTimeMillis();
        logger.info("실행시간 : {}", (end-start)/1000.0);
        return new BaseResponse<Boolean>(true);

    }

}
