package com.jiuqi.cosmos.elasticSearch;

import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "wantu_notice_info", type = "doc")
public class Notice {
	//id
    @JsonProperty("auto_id")
    private Long id;

    //标题
    @JsonProperty("title")
    private String title;

    //公告标签
    @JsonProperty("exchange_mc")
    private String exchangeMc;

    //公告发布时间
    @JsonProperty("create_time")
    private String originCreateTime;

    //公告阅读数量
    @JsonProperty("read_count")
    private Integer readCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExchangeMc() {
		return exchangeMc;
	}

	public void setExchangeMc(String exchangeMc) {
		this.exchangeMc = exchangeMc;
	}

	public String getOriginCreateTime() {
		return originCreateTime;
	}

	public void setOriginCreateTime(String originCreateTime) {
		this.originCreateTime = originCreateTime;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Notice() {
		super();
	}

	public Notice(Long id, String title, String exchangeMc, String originCreateTime, Integer readCount) {
		super();
		this.id = id;
		this.title = title;
		this.exchangeMc = exchangeMc;
		this.originCreateTime = originCreateTime;
		this.readCount = readCount;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", exchangeMc=" + exchangeMc + ", originCreateTime="
				+ originCreateTime + ", readCount=" + readCount + "]";
	}
    
    
}
