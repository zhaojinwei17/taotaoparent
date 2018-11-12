package com.taotao.mapper;

import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbContentCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    long countByExample(TbContentCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    int deleteByExample(TbContentCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    int insert(TbContentCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    int insertSelective(TbContentCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    List<TbContentCategory> selectByExample(TbContentCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    TbContentCategory selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    int updateByExampleSelective(@Param("record") TbContentCategory record, @Param("example") TbContentCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    int updateByExample(@Param("record") TbContentCategory record, @Param("example") TbContentCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    int updateByPrimaryKeySelective(TbContentCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content_category
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    int updateByPrimaryKey(TbContentCategory record);
}