package com.sy.mapper;

import com.sy.model.Department;
import com.sy.model.Document;
import com.sy.model.Inform;
import com.sy.model.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface JobMapper {
    @Select("select * from job")
    List<Job> selectAll()throws Exception;

    @Update("update job set name=#{name},remark=#{remark} where id=#{id}")
    int update(Job job)throws Exception;

    /*添加*/

    @Insert("insert into job (name,remark) values (#{name},#{remark})")
    int insert(Job job)throws Exception;


    /*模糊查询*/
    @Select("<script> " +
            "SELECT *" +
            "FROM job " +
            " <where> " +
            " <if test=\"name != '' and name!=null\"> name=#{name}</if> " +
            " <if test=' remark!=null'> and remark=#{remark}</if> " +
            " <if test=' id!=null and id>0'> and id=#{id}</if> " +
            " </where> " +
            " </script> ")
    List<Job> select(Job job)throws Exception;


    @Delete("delete from job where id=#{id}")
    int delete(int id)throws Exception;


    @Select("<script> " +
            "SELECT count(*)" +
            "FROM job " +
            " <where> " +
            " <if test=\"name != '' and name!=null\"> name=#{name}</if> " +
            " <if test=' remark!=null'> and remark=#{remark}</if> " +
            " </where> " +
            " </script> ")
    int count(Job job)throws Exception;



    @Select("select * from job where id=#{id}")
    Job selectPdf(int id)throws Exception;
}
