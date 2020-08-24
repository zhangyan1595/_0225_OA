package com.sy.mapper;

import com.sy.model.Document;
import com.sy.model.Email;
import com.sy.model.Employee;
import com.sy.model.Inform;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmailMapper {


    @Select("select * from email ")
    List<Email> selectAll(Email employee)throws Exception;


    @Delete("delete from email where id=#{id}")
    int delete(int id)throws Exception;
    @Delete("delete from email where toId=#{toId}")
    int delete1(int toId)throws Exception;

    @Insert("insert into email (toId,title,content,attachment,createtime) values (#{toId},#{title},#{content},#{attachment},#{createtime})")
    int insert(Email email)throws Exception;
    @Select("select count(*) from email ")
    int count(Email email)throws Exception;

}
