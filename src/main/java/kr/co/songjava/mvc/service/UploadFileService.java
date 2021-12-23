package kr.co.songjava.mvc.service;

import kr.co.songjava.mvc.domain.UploadFile;
import kr.co.songjava.mvc.parameter.UploadFileParameter;
import kr.co.songjava.mvc.repository.UploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* 파일업로드 서비스
* @author PSJ
 */
@Service
public class UploadFileService {

    @Autowired
    private UploadFileRepository repository;

    /*
     * 등록 처리
     * @author PSJ
     */
    public void save(UploadFileParameter parameter){
        repository.save(parameter);
    }

    public UploadFile get(int uploadFileSeq) {
        return repository.get(uploadFileSeq);
    }
}
