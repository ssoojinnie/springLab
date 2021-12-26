package kr.co.songjava.framework.data.util;

import kr.co.songjava.mvc.domain.BaseCodeLabelEnum;
import org.apache.commons.lang3.ObjectUtils;


public class EnumUtils {



    /*
    @param values 파라미터로 넘어온 선택 값들
    @param codeEnum 현재 출력하고 있는 Code
     */
    public static boolean isSelected(BaseCodeLabelEnum[] values, BaseCodeLabelEnum codeEnum){

        if(ObjectUtils.isEmpty(values)){
            return false;
        }
        for(BaseCodeLabelEnum value: values){
            if(value.code().equals(codeEnum.code())){
                return true;
            }
        }
        return false;
    }
}
