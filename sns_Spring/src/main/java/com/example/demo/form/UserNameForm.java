package com.example.demo.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserNameForm {
	@NotEmpty(message = "値を入力してください")
	@Size(min = 0, max = 16, message = "16文字以内でお願いします")
	@Pattern(regexp = "[0-9a-z]*", message = "半角英数字でお願いします")
	private String user_name;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
