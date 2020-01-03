package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.UserEntity;
import com.example.demo.form.UserForm;
import com.example.demo.form.UserNameForm;
import com.example.demo.form.UserPasswordForm;
import com.example.demo.repojitory.UserRepojitory;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.session.Loginsession;

@Controller
@RequestMapping("user")
public class UserController {

		public final UserServiceImpl userServiceImpl;
		public final UserRepojitory userRepojitory;

		@Autowired
		  public UserController(UserServiceImpl userServiceImpl,UserRepojitory userRepojitory) {
			this.userServiceImpl = userServiceImpl;
			this.userRepojitory = userRepojitory;
		}

		@Autowired
		Loginsession loginsession;



@RequestMapping("/create")
public String create(@ModelAttribute UserForm userForm, Model model) {
	model.addAttribute("user_id", loginsession.getId());
return "user/create";
}
@RequestMapping("/complate")
public String complate(@Validated UserForm userForm,BindingResult result,Model model) {
	if(result.hasErrors()) {
		return "user/create";
	}

	if(!(userForm.getPassword().equals(userForm.getPassword2()))) {
		model.addAttribute("message", "パスワードが一致しません");
		return "user/create";
	}


	if(userRepojitory.userName(userForm.getUser_name()) != null) {
		model.addAttribute("message", "そのユーザー名はすでに使用されています");
		return "user/create";
	}
	UserEntity userEntity = new UserEntity();
	userEntity.setUser_name(userForm.getUser_name());
	userEntity.setPassword(userForm.getPassword());
	userEntity.setCreated_at(LocalDateTime.now());
	userServiceImpl.save(userEntity);
	return "redirect:/login";
}
@RequestMapping("/update_name")
public String update_Name(@ModelAttribute UserNameForm userNameForm,Model model) {
	modelSet(model);
	return "user/update_name";
}
@RequestMapping("/complate_name")
public String complate_Name(@Validated UserNameForm userNameForm, BindingResult result,Model model) {
	if(result.hasErrors()) {
		modelSet(model);
		return "user/update_name";
	}
	if(userRepojitory.userName(userNameForm.getUser_name()) != null) {
		modelSet(model);
		model.addAttribute("message", "そのユーザー名はすでに使用されています");
		return "user/update_name";
	}
	UserEntity userEntity = userServiceImpl.find_by(loginsession.getId());
	userEntity.setUser_name(userNameForm.getUser_name());
	userEntity.setUpdated_at(LocalDateTime.now());
	userServiceImpl.save(userEntity);
	loginsession.setUser_name(userEntity.getUser_name());
	List<String> list = userGet();
	return "redirect:/login?user_name="+ list.get(0) +"&password="+list.get(1);
}


@RequestMapping("/update_password")
public String update_Password(@ModelAttribute UserPasswordForm userPasswordForm,Model model) {
	modelSet(model);
	return "user/update_password";
}
@RequestMapping("/complate_password")
public String complate_Password(@Validated UserPasswordForm userPasswordForm,BindingResult result,Model model) {
	if(result.hasErrors()) {
		modelSet(model);
		return "user/update_password";
	}
	if(!(userPasswordForm.getPassword().equals(userPasswordForm.getPassword2()))) {
		modelSet(model);
		model.addAttribute("message", "パスワードが一致しません");
		return "user/update_password";
	}
	UserEntity userEntity = userServiceImpl.find_by(loginsession.getId());
	userEntity.setPassword(userPasswordForm.getPassword());
	userEntity.setUpdated_at(LocalDateTime.now());
	userServiceImpl.save(userEntity);
	loginsession.setPassword(userEntity.getPassword());
	List<String> list = userGet();
	return "redirect:/login?user_name="+ list.get(0) +"&password="+list.get(1);
}
private List<String> userGet() {
	List<String> list = new ArrayList<String>();
	String user_name = loginsession.getUser_name();
	String password = loginsession.getPassword();
	list.add(user_name);
	list.add(password);
	return list;
}
private void modelSet(Model model) {
	UserEntity userEntity = userServiceImpl.find_by(loginsession.getId());
	model.addAttribute("user_name", userEntity.getUser_name());
	model.addAttribute("password", userEntity.getPassword());
}
}