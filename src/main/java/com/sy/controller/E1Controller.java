package com.sy.controller;

import com.sy.model.base.Result;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/email1")
public class E1Controller {
    private static Logger logger = Logger.getLogger(EmailController.class);
    @RequestMapping("/upload.do")
    public ResponseEntity<byte[]> upload( HttpServletRequest httpServletRequest) throws Exception{
      String filename=httpServletRequest.getParameter("fileName");
        System.out.println(filename);
       /* String path= httpServletRequest.getServletContext().getRealPath("/");*/
       String path="C:\\Users\\Administrator.USER-20190508YF\\Desktop\\SHC\\_0225_OA\\src\\main\\webapp\\js\\upload";
        String realpath=path+"/"+filename;
        File file=new File(realpath);
        if (file.exists()) {
            byte[] bytes1 = FileUtils.readFileToByteArray(file);
            //响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment;fileName="+filename);
            /*状态码*/
            HttpStatus ok = HttpStatus.OK;
            logger.info("下载附件成功");
            return new ResponseEntity<>(bytes1, headers, ok);
        } else {
            logger.info("业务异常");
            return null;
        }


    }
}
