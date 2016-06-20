package com.pkuvr.manage_server.domain.custom;

import java.util.Date;

/**
 * <p>类说明:</p>
 * <p>文件名： BulletinInfo.java</p>
 * <p>创建人及时间：	宋士龙 2013-3-5</p>
 * <p>
 * <p>修改人：</p>
 * <p>修改时间：</p>
 * <p>修改描述：</p>
 **/
public class BulletinInfo {

    private Integer id;
    private String title;
    private String content;
    private Integer linkModelId;
    private String linkURL;
    private Integer type;
    private Date beginTime;
    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLinkModelId() {
        return linkModelId;
    }

    public void setLinkModelId(Integer linkModelId) {
        this.linkModelId = linkModelId;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
