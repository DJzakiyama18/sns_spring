package com.example.demo.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



public class ContentForm {
@NotEmpty(message ="値を入力してください" )
@Size(min = 0, max = 255, message = "文字以内でおねがいします")
private String message;

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
}
