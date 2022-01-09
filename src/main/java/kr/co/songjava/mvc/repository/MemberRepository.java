package kr.co.songjava.mvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {
    //CrudRepository.class 에 기본적으로 count(), delete(), deleteAll(), deleteById(ID)
    //existsById(ID), findAll(), findAll(iterable<ID>), findById(ID), save, saveAll(interable<ID>)
    //기본 function 외에 4개 추가했음



    List<Member> findByName(String name);
    boolean existsByName(String name);
    int countByName(String name);
    List<MemberMappingName> findNameMappingByName(String name);
}
