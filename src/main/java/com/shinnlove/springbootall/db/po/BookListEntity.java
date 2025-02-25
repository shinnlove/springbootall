package com.shinnlove.springbootall.db.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BookListEntity implements Serializable {
    private Long id;

    private Long cbid;

    private String bookName;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCbid() {
        return cbid;
    }

    public void setCbid(Long cbid) {
        this.cbid = cbid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}