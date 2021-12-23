package kr.co.songjava.mvc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.co.songjava.configuration.GlobalConfig;
import kr.co.songjava.configuration.exception.BaseException;
import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.configuration.http.BaseResponseCode;
import kr.co.songjava.mvc.parameter.UploadFileParameter;
import kr.co.songjava.mvc.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/*
 * 게시판 서비스
 * @author PSJ
 */
@RestController
@RequestMapping("/file")
@Api(tags="파일 API")
public class FileController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GlobalConfig config;

    @Autowired
    private UploadFileService uploadFileService;

    //@GetMapping
    @PostMapping("/save") //공통 파일업로드 구현강의 변경
    @ApiOperation(value = "업로드", notes = "")
    public BaseResponse<Boolean> save(@RequestParam("uploadFile") MultipartFile multipartFile){
        logger.debug("multipartFile:{}", multipartFile);
        if(multipartFile==null ||multipartFile.isEmpty()){
            throw new BaseException(BaseResponseCode.DATA_IS_NULL);
        }
        logger.debug("config:{}", config);
        //파일관리를 위해 업로드 날짜 단위로 디렉토리 관리
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String uploadFilePath = config.getUploadFilePath() +currentDate+"/";
        logger.debug("uploadFilePath:{}", uploadFilePath);

        //확장자 가져오기
        String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1, multipartFile.getOriginalFilename().length());
        //파일명은 유일해야하므로
        //파일명이 한글인경우 깨지지 않게, 파일명이 지나치게 길지 않게
        //보안 관점
        //ex 파일명이 1234.txt 이면 1235.txt 방식으로 url 로 짐작해서? 접근할 가능성 있음
        String filename = UUID.randomUUID().toString() + "." + prefix;

        //디렉토리가 없다면 생성
        File folder = new File(uploadFilePath);
        if(!folder.isDirectory()){
            folder.mkdirs();
        }
        String pathname = uploadFilePath + filename;
        String resourcePathname = config.getUploadResourcePath()+currentDate +"/"+filename;
        File dest = new File(pathname);
        logger.debug("dest:{}", dest);
        try {
            multipartFile.transferTo(dest);

            UploadFileParameter parameter = new UploadFileParameter();
            parameter.setContentType(multipartFile.getContentType());
            parameter.setOriginalFilename(multipartFile.getOriginalFilename());
            parameter.setFilename(filename);
            parameter.setPathname(pathname);
            parameter.setSize((int)multipartFile.getSize());
            //브라우저에서 접근할수 있는 경로 지정
            parameter.setResourcePathname(resourcePathname);
            uploadFileService.save(parameter);
        }catch (IllegalStateException | IOException e){
            logger.error("e", e);
        }
        return new BaseResponse<Boolean>(true);
    }

}
