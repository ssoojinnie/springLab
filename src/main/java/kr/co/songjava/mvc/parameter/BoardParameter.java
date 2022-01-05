package kr.co.songjava.mvc.parameter;

import kr.co.songjava.mvc.domain.BoardType;
import lombok.Data;

import java.util.Date;

@Data
public class BoardParameter {
    private int boardSeq;
    private String title;
    private String contents;
    private boolean delYn;
    private BoardType boardType;

    public BoardParameter(){}

    //반복문을 위한 생성자
    public BoardParameter(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
}
