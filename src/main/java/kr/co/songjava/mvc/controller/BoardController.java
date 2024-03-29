package kr.co.songjava.mvc.controller;

import io.swagger.annotations.*;
import kr.co.songjava.configuration.exception.BaseException;
import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.configuration.http.BaseResponseCode;
import kr.co.songjava.framework.data.domain.MySQLPageRequest;
import kr.co.songjava.framework.data.domain.PageRequestParameter;
import kr.co.songjava.framework.data.web.bind.annotation.RequestConfig;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.domain.BoardType;
import kr.co.songjava.mvc.domain.MenuType;
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
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.*;


/*
* 게시판 서비스
* @author PSJ
 */
@Controller
//@RequestMapping("/board")
//@Api(tags="게시판 API")
public class BoardController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BoardService boardService;

    /*
     * 목록리턴
     * @author PSJ
     */
    @GetMapping("{menuType}")
    //@GetMapping("/list/{menuType}")
    //@GetMapping("/list")
    //@ResponseBody
    //@ApiOperation(value="목록 조회", notes = "목록 정보 조회 가능")
    public String /*BaseResponse<List<Board>>*/ list(@PathVariable MenuType menuType, BoardSearchParameter parameter, MySQLPageRequest pageRequest, Model model)
            //@ApiParam BoardSearchParameter parameter,
            //@ApiParam MySQLPageRequest pageRequest)
    {//페이지 요청 과 검색파라미터 분리

        logger.info("pageRequest:{}", pageRequest);
        PageRequestParameter<BoardSearchParameter> pageRequestParameter= new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);

        logger.info("menyType : {}", menuType);

        //parameter.setBoardTypes(menuType.getArray());
        parameter.setBoardType(menuType.boardType());
        logger.info("parameter : {}", parameter);
        int totalCount = boardService.getCount(pageRequestParameter);
        List<Board> boardList = boardService.getList(pageRequestParameter);
        model.addAttribute("boardList", boardList);
        model.addAttribute("menuType", menuType);
        model.addAttribute("parameter", parameter);
        model.addAttribute("pageRequest", pageRequest);
        model.addAttribute("totalCount", totalCount);
        //return new BaseResponse<List<Board>>(boardService.getList(pageRequestParameter));
        return "/board/list";
    }

    /*
    등록 화면
     */
    @GetMapping("/{menuType}/form")
    @RequestConfig(loginCheck = false)
    public String form(@PathVariable MenuType menuType, BoardParameter parameter, Model model){
        model.addAttribute("parameter", parameter);
        return "/board/form";
    }

    /*
     * 상세정보 리턴
     * @author PSJ
     */

    /*@ApiOperation(value="상세조회", notes="게시물번호에 해당하는 상세정보 조회 가능")
    @ApiImplicitParams({
            @ApiImplicitParam(name="boardSeq", value = "게시물 번호", example = "1")
    })

     */
    @GetMapping("/{menuType}/{boardSeq}")
    public String detail(@PathVariable MenuType menuType, @PathVariable int boardSeq, Model model){
        Board board = boardService.get(boardSeq);
        if(board==null){
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"게시물"});
        }
        model.addAttribute("board", board);
        model.addAttribute("menuType", menuType);
        return "/board/detail";
                //new BaseResponse<Board>(boardService.get(boardSeq));
    }

    /*
    수정화면
     */
    @GetMapping("/{menuType}/edit/{boardSeq}")
    @RequestConfig(loginCheck = false)
    public String edit(@PathVariable MenuType menuType, @PathVariable(required = true) int boardSeq, BoardParameter parameter, Model model){
        Board board = boardService.get(parameter.getBoardSeq());
        if(board==null){
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"게시물"});
        }
        model.addAttribute("board", board);
        model.addAttribute("parameter", parameter);
        model.addAttribute("menuType", menuType);
        return "/board/form";
    }

    /*
     * 등록/수정 처리
     * @author PSJ
     */
    @PutMapping("/{menuType}/save") //응답에 본문 X
    //@PostMapping("/{menuType}/save") //응답에 본문 O
    //@ResponseBody
    @RequestConfig(loginCheck = false)//로그인체크하려면 true
    @ApiOperation(value="등록/수정 처리", notes="신규 게시물 저장 및 기존 게시물 업데이트 가능")
    @ApiImplicitParams({
            @ApiImplicitParam(name="boardSeq", value = "게시물 번호", example = "1"),
            @ApiImplicitParam(name="title", value = "제목", example = "spring"),
            @ApiImplicitParam(name="contents", value = "내용", example = "spring 강좌")
    })
    //BaseResponse<Integer>
    public String save(@PathVariable MenuType menuType, BoardParameter parameter, Model model, @RequestParam(required = false) MultipartFile uploadFile){//보통 post, put 사용, 실제로는 get 사용 지양
        logger.info("uploadFile :{}", uploadFile);

        //제목, 내용 필수체크
        if(StringUtils.isEmpty(parameter.getTitle())){
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"Title", "제목"});
        }
        if(StringUtils.isEmpty(parameter.getContents())){
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"Contents", "내용"});
        }
        parameter.setBoardType(menuType.boardType());
        boardService.save(parameter);
        //return new BaseResponse<Integer>(parameter.getBoardSeq());

        Board board = boardService.get(parameter.getBoardSeq());
        if(board==null){
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {"게시물"});
        }
        model.addAttribute("board", board);
        model.addAttribute("menuType", menuType);
        return "/board/detail";
       // return "/board/"+parameter.ge
    }

    /*
     * 삭제처리
     * @author PSJ
     */
    @DeleteMapping("/{menuType}/{boardSeq}")
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
