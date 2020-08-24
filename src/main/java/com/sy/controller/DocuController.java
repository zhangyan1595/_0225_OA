package com.sy.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sy.model.Document;
import com.sy.model.base.Result;
import com.sy.service.DocuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/docu")
public class DocuController {
    @Autowired
    DocuService docuService;

    private static Logger logger=Logger.getLogger(DocuController.class);

    @RequestMapping("/list.do")
    public Result select(Document document,Integer page,Integer limit)throws Exception{
        Result result=new Result();
        List<Document> documents = docuService.selectPage(document, page, limit);
        PageInfo<Document> of = PageInfo.of(documents);
        result.setCount((int)of.getTotal());
        result.setData(documents);
        result.setCode(Result.SUCCESS_CODE);
        logger.info("查询成功");
        return result;

    }

    @RequestMapping("/update.do")
    public Result update(Document document,@RequestParam("file") MultipartFile file)throws Exception{
        Result result=new Result();
        document.setFileName(file.getOriginalFilename());
        int update = docuService.update(document);
        if(update>0){
            file.transferTo(new File("C:\\Users\\Administrator.USER-20190508YF\\Desktop\\SHC\\_0225_OA\\src\\main\\webapp\\js\\upload",file.getOriginalFilename()));
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("修改文件成功");
            return result;
        }else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }

    }


  @RequestMapping("/insert.do")
    public Result insert(Document document, @RequestParam("file")MultipartFile file)throws Exception{
      String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
      Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(format);
      document.setCreateDate(parse);
      document.setFileName(file.getOriginalFilename());
      Result result=new Result();
      int insert = docuService.insert(document);
      if(insert>0){
          file.transferTo(new File("C:\\Users\\Administrator.USER-20190508YF\\Desktop\\SHC\\_0225_OA\\src\\main\\webapp\\js\\upload",file.getOriginalFilename()));
          result.setCode(Result.SUCCESS_CODE);
          result.setMsg(Result.SUCCESS_MSG);
          logger.info("添加文件成功");
          return result;
      }else {
          result.setCode(Result.FailED_CODE);
          result.setMsg(Result.FAILED_MSG);
          logger.info("业务异常");
          return result;
      }


  }


    @RequestMapping("/deleteAll.do")
    public Result delete(int[] ids)throws Exception{
        Result result=new Result();
        int count=0;
        for (int i = 0; i <ids.length ; i++) {
            int delete = docuService.delete(ids[i]);
            if(delete>0){
                ++count;
            }
        }
        System.out.println(count+"123");
        if(count==ids.length){
            result.setCode(Result.SUCCESS_CODE);
            result.setMsg(Result.SUCCESS_MSG);
            logger.info("批量删除成功");
            return result;
        }else {
            result.setCode(Result.FailED_CODE);
            result.setMsg(Result.FAILED_MSG);
            logger.info("业务异常");
            return result;
        }
    }

}
