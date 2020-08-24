package com.sy.mapper;

import com.sy.model.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface AccountMapper {
    @Select("select * from account where loginname= #{loginname} and password= #{password}")
    Account selectbyname(@Param("loginname") String loginname, @Param("password") String password)throws Exception;

    @Select("select * from account ")
    List<Account> selectAll()throws  Exception;

    @Delete("delete from account where id=#{id}")
    int delete(int id)throws Exception;


    /*模糊查询*/
@Select("<script> " +
        "SELECT *" +
        "FROM account " +
        " <where> " +
        " <if test=\"username != '' and username!=null\"> username=#{username}</if> " +
        " <if test=\"loginname != '' and loginname!=null\"> loginname=#{loginname}</if> " +
        " <if test=' status!=null'> and status=#{status}</if> " +
        " </where> " +
        " </script> ")
    List<Account> select(Account account)throws Exception;

    /*修改*/
    @Update("update account set username=#{username},status=#{status},loginname=#{loginname},password=#{password} where id=#{id}")
    int update(Account account)throws Exception;


    /*添加*/

    @Insert("insert into account (username,status,loginname,password,createDate) values (#{username},#{status},#{loginname},#{password},#{createDate})")
    int insert(Account account)throws Exception;


    @Select("<script> " +
            "SELECT *" +
            "FROM account " +
            " <where> " +
            " <if test=\"username != '' and username!=null\"> username=#{username}</if> " +
            " <if test=' status!=null'> and status=#{status}</if> " +
            " </where> " +
            " </script> ")
    int count(Account account)throws Exception;

}
