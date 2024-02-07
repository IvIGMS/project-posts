package com.example.demo.models.dto;

import java.util.Date;


public class PostDTO {
	private Long id;
	private Long userId;
	private String text;
    private Date createdAt;
    private Date updatedAt;
    
	public PostDTO(Long id, Long userId, String text, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.text = text;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}
