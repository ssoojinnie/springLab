package kr.co.songjava.session.parameter;

import lombok.Data;

@Data
public class MemberSaveParameter {

    private String authId;
    private String name;
    private String phoneNumber;
    private String memberId;

}
