package com.sy.mapper;

import com.sy.model.Account;
import com.sy.model.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DeptMapper {

    @Select("select * from department")
    List<Department> selectAll()throws Exception;

    @Update("update department set name=#{name},remark=#{remark} where id=#{id}")
    int update(Department department)throws Exception;

    /*添加*/

    @Insert("insert into department (name,remark) values (#{name},#{remark})")
    int insert(Department department)throws Exception;


    /*模糊查询*/
    @Select("<script> " +
            "SELECT *" +
            "FROM department " +
            " <where> " +
            " <if test=\"name != '' and name!=null\"> name=#{name}</if> " +
            " <if test=' remark!=null'> and remark=#{remark}</if> " +
            " <if test=' id!=null and id>0'> and id=#{id}</if> " +
            " </where> " +
            " </script> ")
    List<Department> select(Department department)throws Exception;



    @Delete("delete from department where id=#{id}")
    int delete(int id)throws Exception;




    @Select("<script> " +
            "SELECT count(*)" +
            "FROM department " +
            " <where> " +
            " <if test=\"name != '' and name!=null\"> name=#{name}</if> " +
            " <if test=' remark!=null'> and remark=#{remark}</if> " +
            " <if test=' id!=null and id>0'> and id=#{id}</if> " +
            " </where> " +
            " </script> ")
    int count(Department department)throws Exception;





}
