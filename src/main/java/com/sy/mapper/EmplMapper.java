package com.sy.mapper;

import com.sy.model.Document;
import com.sy.model.Employee;
import com.sy.model.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EmplMapper {

    @Select("select e.*,d.name dname,j.name jname from employee e, department d,job j where e.did=d.id and e.jid=j.id")
    List<Employee> selectAll()throws Exception;

    @Insert("insert into employee(name,cardId,address,postCode,tel,qqNum,email,sex,party,birthday,race,education,speciality,hobby,remark,createDate,phone,did,jid)" +
            "        values(#{name},#{cardId},#{address},#{postCode},#{tel},#{qqNum},#{email},#{sex},#{party},#{birthday},#{race}," +
            "           #{education},#{speciality},#{hobby},#{remark},#{createDate},#{phone},#{did},#{jid})")
    int insert(Employee employee)throws Exception;


    @Update("update employee set " +
            "name=#{name}," +
            "cardId=#{cardId}, " +
            "address=#{address}, " +
            "postCode=#{postCode}, " +
            "tel=#{tel}," +
            "phone=#{phone}," +
            "qqNum=#{qqNum}," +
            "email=#{email}," +
            "sex=#{sex}," +
            "party=#{party}," +
            "birthday=#{birthday}," +
            "race=#{race}," +
            "education=#{education}," +
            "speciality=#{speciality}," +
            "hobby=#{hobby}," +
            "remark=#{remark}," +
            "did=#{did}," +
            "jid=#{jid} " +
            "where id = #{id}")
    int update(Employee employee)throws Exception;




    /*模糊查询*/

    @Select("<script>select e.*,job.name as jname,department.name as dname " +
            "from (employee e left join department on e.did= department.id) left join job on e.jid= job.id <where>"  +
            "<if test=\"name!=null and name !=''\">e.name=#{name}</if>" +
            "<if test=\"sex>0 and sex !=''\">and sex=#{sex}</if> " +
            "<if test=\"jid!=null and jid !=''\">and jid=#{jid}</if>" +
            "<if test=\"tel!=null and tel !=''\">and tel=#{tel}</if>" +
            "<if test=\"did!=null and did !=''\">and did=#{did}</if>" +
            "<if test=\"cardId!=null and cardId !=''\">and cardId=#{cardId}</if>" +
            "</where></script>")

    List<Employee> select(Employee e)throws Exception;



    @Delete("delete from employee where id=#{id}")
    int delete(int id)throws Exception;

    @Delete("delete from employee where uid=#{uid}")
    int deleteuid(int uid)throws Exception;
    @Delete("delete from employee where did=#{did}")
    int deletedid(int did)throws Exception;
    @Delete("delete from employee where jid=#{jid}")
    int deletejid(int jid)throws Exception;
}
                                                                                                                                                                                     