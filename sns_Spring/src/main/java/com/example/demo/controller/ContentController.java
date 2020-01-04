package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.ContentEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.form.ContentForm;
import com.example.demo.repojitory.ContentRepojitory;
import com.example.demo.repojitory.UserRepojitory;
import com.example.demo.service.ContentServiceImpl;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.session.Loginsession;

@Controller
@RequestMapping("content")
public class ContentController {
	public final ContentServiceImpl contentServiceImpl;
	public final UserServiceImpl userServiceImpl;
	public final UserRepojitory userRepojitory;
	public final ContentRepojitory contentRepojitory;
	@Autowired
	 public ContentController(ContentServiceImpl contentServiceImpl,UserServiceImpl userServiceImpl,UserRepojitory userRepojitory,ContentRepojitory contentRepojitory) {
		this.contentServiceImpl = contentServiceImpl;
		this.userServiceImpl = userServiceImpl;
		this.userRepojitory = userRepojitory;
		this.contentRepojitory = contentRepojitory;
	}

	@Autowired
	Loginsession loginsession;

	@RequestMapping("/create/{user_id}")
	public String create(@ModelAttribute  ContentForm contentForm,Model model,@PathVariable("user_id") String user_id) {
		userSet(model);
		model.addAttribute("user_id", user_id);
		return "content/create";
	}
	@RequestMapping("/complate/{user_id}")
	public String complate(@Validated ContentForm contentForm, BindingResult result,Model model,@PathVariable("user_id") int user_id) {
		if(result.hasErrors()) {
			return "content/create";
		}
		if(user_id == 1){
			UserEntity userEntity = userServiceImpl.find_by(user_id);
		ContentEntity contentEntity = new ContentEntity();
		contentEntity.setUser_name(userEntity.getUser_name());
		contentEntity.setMessage(contentForm.getMessage());
		contentEntity.setCreated_at(LocalDateTime.now());
		contentServiceImpl.save(contentEntity);
		return "redirect:/";
		}
		UserEntity userEntity = userServiceImpl.find_by(user_id);
		ContentEntity contentEntity = new ContentEntity();
		contentEntity.setUser_name(userEntity.getUser_name());
		contentEntity.setMessage(contentForm.getMessage());
		contentEntity.setCreated_at(LocalDateTime.now());
		contentServiceImpl.save(contentEntity);
		String user_name = loginsession.getUser_name();
		String password = loginsession.getPassword();
		return "redirect:/login?user_name="+ user_name +"&password="+password; //リダイレクト未実装
	}
	@RequestMapping("/update/{date_id}")
	public String update(@ModelAttribute ContentForm contentForm,Model model ,@PathVariable ("date_id") int date_id) {
		ContentEntity contentEntity =  contentServiceImpl.find_by(date_id);
		userSet(model);
		model.addAttribute("message", contentEntity.getMessage());
		model.addAttribute("date_id", date_id);
		return "content/update";
	}
@RequestMapping("/update/complate/{date_id}")
public String updateComplate(@Validated ContentForm contentForm,BindingResult result,Model model,@PathVariable("date_id") int date_id) {
	if(result.hasErrors()) {
		return "content/update";
	}
	ContentEntity contentEntity =  contentServiceImpl.find_by(date_id);
	contentEntity.setMessage(contentForm.getMessage());
	contentEntity.setUpdated_at(LocalDateTime.now());
	contentServiceImpl.save(contentEntity);
	String user_name = loginsession.getUser_name();
	String password = loginsession.getPassword();
	return "redirect:/login?user_name="+ user_name +"&password="+password;
}
@RequestMapping("/delete/{date_id}")
public String delete(@PathVariable("date_id") int date_id) {
        contentServiceImpl.delet(date_id);
        String user_name = loginsession.getUser_name();
    	String password = loginsession.getPassword();
	 return "redirect:/login?user_name="+ user_name +"&password="+password;
}

private void userSet(Model model) {
	model.addAttribute("user_name", loginsession.getUser_name());
	model.addAttribute("password", loginsession.getPassword());
	model.addAttribute("user_id", loginsession.getId());
}


}
