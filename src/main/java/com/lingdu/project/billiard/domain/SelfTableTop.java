package com.lingdu.project.billiard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lingdu.framework.aspectj.lang.annotation.Excel;
import com.lingdu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 桌台信息对象 self_table_top
 *
 * @author 猛男波波
 * @date 2024-05-18
 */
public class SelfTableTop extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 桌台id
     */
    private Long tableId;

    /**
     * 桌台名称
     */
    @Excel(name = "桌台名称")
    private String tableName;

    /**
     * 桌台编码
     */
    @Excel(name = "桌台编码")
    private String tableCode;

    /**
     * 门店id
     */
    private Long selfStoreId;

    /**
     * 桌台类型
     */
    @Excel(name = "桌台类型", dictType = "self_table_type")
    private String tableType;

    /**
     * 容纳人数
     */
    @Excel(name = "容纳人数", dictType = "capacity")
    private Long capacity;

    /**
     * 桌台状态
     */
    @Excel(name = "桌台状态", dictType = "self_table_status")
    private String status;

    /**
     * 桌台描述
     */
    @Excel(name = "桌台描述")
    private String description;

    /**
     * 桌台单价(元/小时)
     */
    @Excel(name = "桌台单价(元/小时)")
    private BigDecimal price;

    /**
     * 会员卡支付设置
     */
    @Excel(name = "会员卡支付设置", dictType = "self_table_vip")
    private Integer memberPaymentSettings;

    /**
     * 会员支付价格
     */
    @Excel(name = "会员支付价格")
    private BigDecimal memberPrice;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String notes;

    /**
     * 图片
     */
    private String images;

    /**
     * 图片地址数组
     */
    private String [] imagesUrl;

    public SelfTableTop() {
    }

    public SelfTableTop(Long selfStoreId) {
        this.selfStoreId = selfStoreId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setSelfStoreId(Long selfStoreId) {
        this.selfStoreId = selfStoreId;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public Long getSelfStoreId() {
        return selfStoreId;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getTableType() {
        return tableType;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setMemberPaymentSettings(Integer memberPaymentSettings) {
        this.memberPaymentSettings = memberPaymentSettings;
    }

    public Integer getMemberPaymentSettings() {
        return memberPaymentSettings;
    }

    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String[] getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String[] imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tableId", getTableId())
                .append("tableName", getTableName())
                .append("tableCode", getTableCode())
                .append("selfStoreId", getSelfStoreId())
                .append("tableType", getTableType())
                .append("capacity", getCapacity())
                .append("status", getStatus())
                .append("description", getDescription())
                .append("price", getPrice())
                .append("memberPaymentSettings", getMemberPaymentSettings())
                .append("memberPrice", getMemberPrice())
                .append("createdAt", getCreatedAt())
                .append("notes", getNotes())
                .toString();
    }
}