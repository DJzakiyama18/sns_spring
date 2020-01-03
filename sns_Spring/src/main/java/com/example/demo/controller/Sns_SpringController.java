package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.ContentEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.form.LoginForm;
import com.example.demo.repojitory.ContentRepojitory;
import com.example.demo.repojitory.UserRepojitory;
import com.example.demo.service.ContentServiceImpl;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.session.Loginsession;
//@RequestMapping("/")
@Controller
public class Sns_SpringController {
	public final ContentServiceImpl contentServiceImpl;
	public final UserServiceImpl userServiceImpl;
	public final UserRepojitory userRepojitory;
	public final ContentRepojitory contentRepojitory;
	@Autowired
	public Sns_SpringController(ContentServiceImpl contentServiceImpl,UserServiceImpl userServiceImpl,UserRepojitory userRepojitory,ContentRepojitory contentRepojitory) {
		this.contentServiceImpl = contentServiceImpl;
		this.userServiceImpl = userServiceImpl;
		this.userRepojitory = userRepojitory;
		this.contentRepojitory = contentRepojitory;
	}

	@Autowired
	Loginsession loginsession;



	@RequestMapping("/" )
	public String home(@ModelAttribute LoginForm loginForm , BindingResult result,Model model) {
		loginsession.setId(1);
		userGet(model);
		return "index";
	}
	@RequestMapping("/login")
	public String mypage(@Validated LoginForm loginForm,BindingResult result,Model model) {
		if(result.hasErrors()) {
			userGet(model);
			return "index";
		}
		UserEntity userEntity = userRepojitory.loginSelect(loginForm.getUser_name(), loginForm.getPassword());
		if(userEntity == null) {
			userGet(model);
			model.addAttribute("message", "ログイン失敗");
			return "index";
		}
		List<ContentEntity> userContentList = contentRepojitory.userContentList(userEntity.getUser_name());
		loginsession.setId(userEntity.getUser_id());
		loginsession.setUser_name(userEntity.getUser_name());
		loginsession.setPassword(userEntity.getPassword());
		model.addAttribute("user_id", userEntity.getUser_id());
		model.addAttribute("user_name", userEntity.getUser_name());
	    model.addAttribute("password", userEntity.getPassword());
	   model.addAttribute("userContentList", userContentList);
		return "login";
	}

	@RequestMapping("/logout")
	public String logout( Model model ,RedirectAttributes redirect) {
		loginsession.setId(1);
		loginsession.setUser_name(null);
		loginsession.setPassword(null);
		redirect.addFlashAttribute("message", "ログアウトしました");
		return "redirect:/";
	}

	private void userGet(Model model) {
		model.addAttribute("user_id", loginsession.getId());
		model.addAttribute("user_name", loginsession.getUser_name());
		model.addAttribute("password",loginsession.getPassword());
		List<ContentEntity> contentList = contentServiceImpl.findAll();
		model.addAttribute("contentList", contentList);
	}
}
