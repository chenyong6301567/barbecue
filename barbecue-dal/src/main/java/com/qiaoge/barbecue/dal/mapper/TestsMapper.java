package com.qiaoge.barbecue.dal.mapper;

import com.qiaoge.barbecue.dal.model.Tests;
import com.qiaoge.barbecue.dal.model.TestsExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TestsMapper {
    @SelectProvider(type=TestsSqlProvider.class, method="countByExample")
    long countByExample(TestsExample example);

    @DeleteProvider(type=TestsSqlProvider.class, method="deleteByExample")
    int deleteByExample(TestsExample example);

    @Delete({
        "delete from tests",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into tests (delete_flag, gmt_create, ",
        "gmt_modify, create_user_id, ",
        "update_user_id)",
        "values (#{deleteFlag,jdbcType=BIT}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
        "#{gmtModify,jdbcType=TIMESTAMP}, #{createUserId,jdbcType=INTEGER}, ",
        "#{updateUserId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Tests record);

    @InsertProvider(type=TestsSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Tests record);

    @SelectProvider(type=TestsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="delete_flag", property="deleteFlag", jdbcType=JdbcType.BIT),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modify", property="gmtModify", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.INTEGER)
    })
    List<Tests> selectByExample(TestsExample example);

    @Select({
        "select",
        "id, delete_flag, gmt_create, gmt_modify, create_user_id, update_user_id",
        "from tests",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="delete_flag", property="deleteFlag", jdbcType=JdbcType.BIT),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modify", property="gmtModify", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="create_user_id", property="createUserId", jdbcType=JdbcType.INTEGER),
        @Result(column="update_user_id", property="updateUserId", jdbcType=JdbcType.INTEGER)
    })
    Tests selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TestsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Tests record, @Param("example") TestsExample example);

    @UpdateProvider(type=TestsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Tests record, @Param("example") TestsExample example);

    @UpdateProvider(type=TestsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Tests record);

    @Update({
        "update tests",
        "set delete_flag = #{deleteFlag,jdbcType=BIT},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "gmt_modify = #{gmtModify,jdbcType=TIMESTAMP},",
          "create_user_id = #{createUserId,jdbcType=INTEGER},",
          "update_user_id = #{updateUserId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Tests record);
}