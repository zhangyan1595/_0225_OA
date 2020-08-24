package com.sy.mapper;

import com.sy.model.Employee;
import com.sy.model.Inform;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface InformMapper {
    @Select("<script> " +
            "SELECT *" +
            "FROM inform " +
            " <where> " +
            " <if test=\"title != '' and title!=null\"> title=#{title}</if> " +
            " <if test=\"content != '' and content!=null\"> and content=#{content}</if> " +
            " </where> " +
            " </script> ")
    List<Inform> select(Inform inform)throws Exception;


    @Select("select * from inform ")
    List<Inform> selectAll()throws Exception;

    @Update("update inform set title=#{title},content=#{content},createDate=#{createDate} where id=#{id}")
    int update(Inform inform)throws Exception;



    @Delete("delete from inform where id=#{id}")
    int delete(int id)throws Exception;


    @Insert("insert into inform (title,content,createDate) values (#{title},#{content},#{createDate})")
    int insert(Inform inform)throws Exception;


    @Select("<script> " +
            "SELECT count(*)" +
            "FROM inform " +
            " <where> " +
            " <if test=\"title != '' and title!=null\"> title=#{title}</if> " +
            " <if test=\"content != '' and content!=null\"> and content=#{content}</if> " +
            " </where> " +
            " </script> ")
    int count(Inform inform)throws Exception;

}
