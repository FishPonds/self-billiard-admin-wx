package com.lingdu.project.billiard.domain;

import com.lingdu.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 文件创建人：波波
 * 日期：2024/5/21
 * 时间：22:08
 * 类名：SelfStoreVO 门店信息VO(包含门店信息、门店相册、门店桌台等)
 * @author 猛男波波
 */
public class SelfStoreVO extends BaseEntity {

    /**
     * 门店信息
     */
    public SelfStoreInformation store;

    /**
     * 门店桌台信息
     */
    public List<SelfTableTop> room;

    public SelfStoreVO() {
    }

    public SelfStoreVO(SelfStoreInformation store, List<SelfTableTop> room) {
        this.store = store;
        this.room = room;
    }

    public SelfStoreInformation getStore() {
        return store;
    }

    public void setStore(SelfStoreInformation store) {
        this.store = store;
    }

    public List<SelfTableTop> getRoom() {
        return room;
    }

    public void setRoom(List<SelfTableTop> room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "SelfStoreVO{" +
                "store=" + store +
                ", room=" + room +
                '}';
    }
}