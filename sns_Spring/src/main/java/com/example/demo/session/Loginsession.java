package com.example.demo.session;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
@Component
@Scope(value= "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Loginsession  implements Serializable{

	      private static final long serialVersionUID = 6334063099671792256L;
	      private int  id;
	      private String user_name;
	      private String password;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	    }

