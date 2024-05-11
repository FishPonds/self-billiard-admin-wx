package com.lingdu.project.billiard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lingdu.framework.aspectj.lang.annotation.Excel;
import com.lingdu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 自助台球系统门店信息对象 self_store_information
 *
 * @author 猛男波波
 * @date 2024-05-03
 */
public class SelfStoreInformation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 门店ID，自增主键
     */
    private Long storeId;

    /**
     * 门店key，四位随机数
     */
    @Excel(name = "门店key，四位随机数")
    private Long storeKey;

    /**
     * 门店名称
     */
    @Excel(name = "门店名称")
    private String storeName;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long selfUserId;

    /**
     * 门店面积，单位：平方米
     */
    @Excel(name = "门店面积，单位：平方米")
    private Long storeSize;

    /**
     * 省编号
     */
    @Excel(name = "省编号")
    private Long provinceId;

    /**
     * 省名称
     */
    @Excel(name = "省名称")
    private String provinceName;

    /**
     * 市编号
     */
    @Excel(name = "市编号")
    private Long cityId;

    /**
     * 市名称
     */
    @Excel(name = "市名称")
    private String cityName;

    /**
     * 区编号
     */
    @Excel(name = "区编号")
    private Long districtId;

    /**
     * 区名称
     */
    @Excel(name = "区名称")
    private String districtName;

    /**
     * 详细地址
     */
    @Excel(name = "详细地址")
    private String address;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话")
    private String phone;

    /**
     * 联系微信图片URL
     */
    @Excel(name = "联系微信图片URL")
    private String wechatImageUrl;

    /**
     * 联系微信号
     */
    @Excel(name = "联系微信号")
    private String wechatId;

    /**
     * 营业开始时间
     */
    @Excel(name = "营业开始时间")
    private String businessHoursStart;

    /**
     * 营业终止时间
     */
    @Excel(name = "营业终止时间")
    private String businessHoursEnd;

    /**
     * 营业状态 营业状态，营业状态(0:筹建中,1:试营业,2:营业中,3:暂停营业,4:已关门)
     */
    @Excel(name = "营业状态")
    private String businessStatus;

    /**
     * 营业状态备注描述
     */
    @Excel(name = "营业状态备注描述")
    private String businessStatusNotes;

    /**
     * 门店介绍
     */
    @Excel(name = "门店介绍")
    private String storeDescription;

    /**
     * 有效期
     */
    @Excel(name = "有效期")
    private String validity;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    /**
     * 门店相册
     */
    private List<SelfStorePhotoAlbum> selfStorePhotoAlbums;

    public List<SelfStorePhotoAlbum> getSelfStorePhotoAlbums() {
        return selfStorePhotoAlbums;
    }

    public void setSelfStorePhotoAlbums(List<SelfStorePhotoAlbum> selfStorePhotoAlbums) {
        this.selfStorePhotoAlbums = selfStorePhotoAlbums;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreKey(Long storeKey) {
        this.storeKey = storeKey;
    }

    public Long getStoreKey() {
        return storeKey;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public Long getSelfUserId() {
        return selfUserId;
    }

    public void setSelfUserId(Long selfUserId) {
        this.selfUserId = selfUserId;
    }

    public void setStoreSize(Long storeSize) {
        this.storeSize = storeSize;
    }

    public Long getStoreSize() {
        return storeSize;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setWechatImageUrl(String wechatImageUrl) {
        this.wechatImageUrl = wechatImageUrl;
    }

    public String getWechatImageUrl() {
        return wechatImageUrl;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getWechatId() {
        return wechatId;
    }

    public String getBusinessHoursStart() {
        return businessHoursStart;
    }

    public void setBusinessHoursStart(String businessHoursStart) {
        this.businessHoursStart = businessHoursStart;
    }

    public String getBusinessHoursEnd() {
        return businessHoursEnd;
    }

    public void setBusinessHoursEnd(String businessHoursEnd) {
        this.businessHoursEnd = businessHoursEnd;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatusNotes(String businessStatusNotes) {
        this.businessStatusNotes = businessStatusNotes;
    }

    public String getBusinessStatusNotes() {
        return businessStatusNotes;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("storeId", getStoreId())
                .append("storeKey", getStoreKey())
                .append("storeName", getStoreName())
                .append("selfUserId", getSelfUserId())
                .append("storeSize", getStoreSize())
                .append("provinceId", getProvinceId())
                .append("provinceName", getProvinceName())
                .append("cityId", getCityId())
                .append("cityName", getCityName())
                .append("districtId", getDistrictId())
                .append("districtName", getDistrictName())
                .append("address", getAddress())
                .append("longitude", getLongitude())
                .append("latitude", getLatitude())
                .append("phone", getPhone())
                .append("wechatImageUrl", getWechatImageUrl())
                .append("wechatId", getWechatId())
                .append("businessHoursStart", getBusinessHoursStart())
                .append("businessHoursEnd", getBusinessHoursEnd())
                .append("businessStatus", getBusinessStatus())
                .append("businessStatusNotes", getBusinessStatusNotes())
                .append("storeDescription", getStoreDescription())
                .append("validity", getValidity())
                .append("createdAt", getCreatedAt())
                .append("updatedAt", getUpdatedAt())
                .toString();
    }
}