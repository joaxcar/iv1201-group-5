package se.kth.iv1201.recruitmentapplicationgroup5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	@RequestMapping(value = {"/login", "/register", "/profile" }, method = RequestMethod.GET)
	public String index() {
		return "";
	}
}
