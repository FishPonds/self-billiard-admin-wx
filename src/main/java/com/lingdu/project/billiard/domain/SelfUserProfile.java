package com.lingdu.project.billiard.domain;

/**
 * 文件创建人：波波
 * 日期：2024/5/10
 * 时间：22:57
 * 类名：SelfUserProfile
 * @author 猛男波波
 */
public class SelfUserProfile {

    private SelfUser selfUser;
    private SelfMerchants selfMerchants;

    public SelfUser getSelfUser() {
        return selfUser;
    }

    public void setSelfUser(SelfUser selfUser) {
        this.selfUser = selfUser;
    }

    public SelfMerchants getSelfMerchants() {
        return selfMerchants;
    }

    public void setSelfMerchants(SelfMerchants selfMerchants) {
        this.selfMerchants = selfMerchants;
    }
}