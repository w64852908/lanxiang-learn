package com.lanxiang.model.dto;

/**
 * Created by lanxiang on 16/9/2.
 */
public class AddressDTO {

    private String id;

    private String userId;

    private String province;

    private String city;

    private String town;

    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public AddressDTO(String id, String userId, String province, String city, String town, String detail) {
        this.id = id;
        this.userId = userId;
        this.province = province;
        this.city = city;
        this.town = town;
        this.detail = detail;
    }

    public AddressDTO() {

    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
