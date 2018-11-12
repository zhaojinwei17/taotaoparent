package com.taotao.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbContentCategory implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content_category.id
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content_category.parent_id
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content_category.name
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content_category.status
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content_category.sort_order
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    private Integer sortOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content_category.is_parent
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    private Boolean isParent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content_category.created
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_content_category.updated
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    private Date updated;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content_category.id
     *
     * @return the value of tb_content_category.id
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content_category.id
     *
     * @param id the value for tb_content_category.id
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content_category.parent_id
     *
     * @return the value of tb_content_category.parent_id
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content_category.parent_id
     *
     * @param parentId the value for tb_content_category.parent_id
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content_category.name
     *
     * @return the value of tb_content_category.name
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content_category.name
     *
     * @param name the value for tb_content_category.name
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content_category.status
     *
     * @return the value of tb_content_category.status
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content_category.status
     *
     * @param status the value for tb_content_category.status
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content_category.sort_order
     *
     * @return the value of tb_content_category.sort_order
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content_category.sort_order
     *
     * @param sortOrder the value for tb_content_category.sort_order
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content_category.is_parent
     *
     * @return the value of tb_content_category.is_parent
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public Boolean getIsParent() {
        return isParent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content_category.is_parent
     *
     * @param isParent the value for tb_content_category.is_parent
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content_category.created
     *
     * @return the value of tb_content_category.created
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content_category.created
     *
     * @param created the value for tb_content_category.created
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_content_category.updated
     *
     * @return the value of tb_content_category.updated
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_content_category.updated
     *
     * @param updated the value for tb_content_category.updated
     *
     * @mbg.generated Sun Sep 16 02:47:25 CST 2018
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}