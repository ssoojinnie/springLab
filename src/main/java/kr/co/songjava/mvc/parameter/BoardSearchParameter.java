package kr.co.songjava.mvc.parameter;

import kr.co.songjava.mvc.domain.BoardType;
import lombok.Data;

import java.util.List;

@Data
public class BoardSearchParameter {
    private String keyword;
    private BoardType[] boardTypes;
    public BoardSearchParameter(){}

}
