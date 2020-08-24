package com.sy.mapper;

import com.sy.model.Department;
import com.sy.model.Document;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DocumentMapper {

    @Select("<script> " +
            "SELECT document.*,account.username as uname " +
            "FROM document  left join  account on  document.uid=account.id  <where> " +
            " <if test=\"title != '' and title!=null\"> title like concat('%',concat(#{title},'%'))</if> " +
            " </where> " +
            " </script> ")
    List<Document> select(Document document)throws Exception;


@Insert("insert into document (title,remark,fileName,uid,createDate) values (#{title},#{remark},#{fileName},#{uid},#{createDate})")
    int insert(Document document)throws Exception;


    @Update("update document set title=#{title},remark=#{remark},fileName=#{fileName} where id=#{id}")
    int update(Document document)throws Exception;

@Delete("delete from document where id=#{id}")
    int delete(int id)throws Exception;


    @Delete("delete from document where uid=#{uid}")
    int delete1(int uid)throws Exception;
};


