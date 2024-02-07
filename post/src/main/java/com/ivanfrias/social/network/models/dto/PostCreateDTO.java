package com.ivanfrias.social.network.models.dto;


public class PostCreateDTO {
	private Long userId;
	private String text;
	
	public PostCreateDTO(Long userId, String text) {
		this.userId = userId;
		this.text = text;
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
}
