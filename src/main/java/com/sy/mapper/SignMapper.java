package com.sy.mapper;

import com.sy.model.Job;
import com.sy.model.SignIn;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface SignMapper {


    @Select("<script> " +
            "SELECT *" +
            "FROM signin" +
            " <where> " +
            " <if test=\"createtime != '' and createtime!=null\"> createtime &gt;= #{createtime}</if> " +
            " </where> " +
            " </script> ")
    List<SignIn> select(SignIn signIn)throws Exception;


    @Select("select * from signin where createtime between #{begintime} and #{endtime}")
    List<SignIn> selectone(@Param("begintime") Date begintime, @Param("endtime") Date endtime)throws Exception;


    @Insert("insert into signin (uid,createtime,flag) values(#{uid},#{createtime},#{flag})")
    int insert(SignIn signIn)throws Exception;



    @Select("select * from signin where uid =#{uid} and createtime >=#{createtime}")
    List<SignIn> select1(SignIn signIn)throws Exception;


}
