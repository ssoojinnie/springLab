package kr.co.songjava.mvc.repository;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Setter
@Getter
@ToString
@Entity(name = "T_MEMBER") //실제 테이블명과 클래스명이 다를경우
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberSeq;

    @Column(name = "MEMBER_ID")
    private String memberId;


    @Enumerated(EnumType.STRING)
    @Column(name="MEMBER_TYPE")
    private MemberType memberType;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Column(name = "DEPART_NAME")
    private String departName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TEL_NUMBER")
    private String telNumber;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    private String zipcode;
    private String address;
    @Column(name = "ADDRESS_DETAIL")
    private String addressDetail;
    @Column(name = "AGREE_TERMS")
    private boolean agreeTerms;
    @Column(name = "LOGIN_DATE")
    private Date loginDate;
    @Column(name = "DEL_DATE")
    private Date delDate;
    @Column(name = "JOIN_DATE")
    private Date joinDate;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
}
