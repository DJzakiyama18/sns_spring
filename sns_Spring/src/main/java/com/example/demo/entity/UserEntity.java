package com.example.demo.entity;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="user")
public class UserEntity {
@Id
private int user_id;
private String password;
private String user_name;
private LocalDateTime created_at;
private LocalDateTime updated_at;
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public LocalDateTime getCreated_at() {
	return created_at;
}
public void setCreated_at(LocalDateTime created_at) {
	this.created_at = created_at;
}
public LocalDateTime getUpdated_at() {
	return updated_at;
}
public void setUpdated_at(LocalDateTime updated_at) {
	this.updated_at = updated_at;
}



}
