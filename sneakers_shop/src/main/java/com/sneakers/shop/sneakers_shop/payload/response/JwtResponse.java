package com.sneakers.shop.sneakers_shop.payload.response;

import java.util.List;

import com.sneakers.shop.sneakers_shop.models.UserInfo;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private UserInfo userInfo;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, UserInfo userInfo, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.userInfo = userInfo;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
