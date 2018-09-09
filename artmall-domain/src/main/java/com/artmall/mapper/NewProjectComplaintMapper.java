package com.artmall.mapper;

import com.artmall.pojo.NewProjectComplaint;
import com.artmall.pojo.NewProjectComplaintExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NewProjectComplaintMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_complaint
     *
     * @mbg.generated Sun Sep 09 16:22:42 CST 2018
     */
    long countByExample(NewProjectComplaintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_complaint
     *
     * @mbg.generated Sun Sep 09 16:22:42 CST 2018
     */
    int deleteByExample(NewProjectComplaintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_complaint
     *
     * @mbg.generated Sun Sep 09 16:22:42 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_complaint
     *
     * @mbg.generated Sun Sep 09 16:22:42 CST 2018
     */
    int insert(NewProjectComplaint record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_complaint
     *
     * @mbg.generated Sun Sep 09 16:22:42 CST 2018
     */
    int insertSelective(NewProjectComplaint record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_complaint
     *
     * @mbg.generated Sun Sep 09 16:22:42 CST 2018
     */
    List<NewProjectComplaint> selectByExample(NewProjectComplaintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_complaint
     *
     * @mbg.generated Sun Sep 09 16:22:42 CST 2018
     */
    NewProjectComplaint selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_complaint
     *
     * @mbg.generated Sun Sep 09 16:22:42 CST 2018
     */
    int updateByExampleSelective(@Param("record") NewProjectComplaint record, @Param("example") NewProjectComplaintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_complaint
     *
     * @mbg.generated Sun Sep 09 16:22:42 CST 2018
     */
    int updateByExample(@Param("record") NewProjectComplaint record, @Param("example") NewProjectComplaintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_complaint
     *
     * @mbg.generated Sun Sep 09 16:22:42 CST 2018
     */
    int updateByPrimaryKeySelective(NewProjectComplaint record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_complaint
     *
     * @mbg.generated Sun Sep 09 16:22:42 CST 2018
     */
    int updateByPrimaryKey(NewProjectComplaint record);
}