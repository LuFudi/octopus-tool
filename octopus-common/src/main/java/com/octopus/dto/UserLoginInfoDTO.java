package com.octopus.dto;

import java.util.List;

/**
 * Created by shenjiaqin on 2021/12/2.
 */
public class UserLoginInfoDTO {
    /**
     * 用户ID(主账号ID)
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String avatarFilePath;
    /**
     * 公司ID
     */
    private Integer companyId;
    /**
     * 主账号标识
     */
    private Boolean masterSign;
    /**
     * 卖家中心标识
     */
    private Boolean sellerSign;
    /**
     * 运营人员标识
     */
    private Boolean operatorSign;
    private String ticket;
    private String jsessionId;
    /**
     * 商城ID
     */
    private Integer shopId;
    /**
     * 账号类型： 1，个人； 2，企业；
     */
    private Integer accountType;
    /**
     * P运营管理mall运营的userId
     */
    private Integer linkedMallUserId;
    /**
     * 集团ID
     */
    private Integer groupId;
    /**
     * 集团下属商城ID集合
     */
    private List<Integer> groupShopIds;
    /**
     * 用户请求页面Url--用于数据权限
     */
    private String requestUrl;

    public UserLoginInfoDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarFilePath() {
        return avatarFilePath;
    }

    public void setAvatarFilePath(String avatarFilePath) {
        this.avatarFilePath = avatarFilePath;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Boolean getMasterSign() {
        return masterSign;
    }

    public void setMasterSign(Boolean masterSign) {
        this.masterSign = masterSign;
    }

    public Boolean getSellerSign() {
        return sellerSign;
    }

    public void setSellerSign(Boolean sellerSign) {
        this.sellerSign = sellerSign;
    }

    public Boolean getOperatorSign() {
        return operatorSign;
    }

    public void setOperatorSign(Boolean operatorSign) {
        this.operatorSign = operatorSign;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getJsessionId() {
        return jsessionId;
    }

    public void setJsessionId(String jsessionId) {
        this.jsessionId = jsessionId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getLinkedMallUserId() {
        return linkedMallUserId;
    }

    public void setLinkedMallUserId(Integer linkedMallUserId) {
        this.linkedMallUserId = linkedMallUserId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getGroupShopIds() {
        return groupShopIds;
    }

    public void setGroupShopIds(List<Integer> groupShopIds) {
        this.groupShopIds = groupShopIds;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
