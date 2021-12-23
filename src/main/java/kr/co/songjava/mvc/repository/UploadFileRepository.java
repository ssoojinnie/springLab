package kr.co.songjava.mvc.repository;

import kr.co.songjava.mvc.domain.UploadFile;
import kr.co.songjava.mvc.parameter.UploadFileParameter;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository {
    void save(UploadFileParameter parameter);

    UploadFile get(int uploadFileSeq);
}
