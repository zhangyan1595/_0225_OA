package com.sy.exec;

import com.sy.model.base.Result;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GrobleException {
    /*抛异常的时候需要写日志*/
    private  static Logger logger=Logger.getLogger(GrobleException.class);

    @ExceptionHandler(value = Exception.class)
    public Result doException(Exception e){
        Result result=new Result();
        result.setCode(Result.FailED_CODE);
        result.setMsg(Result.FAILED_MSG);
        if(e instanceof BizException){
            logger.info("业务异常无需处理",e);
        }else {
            logger.error("系统Bug",e);
        }
        return  result;
    }



}
