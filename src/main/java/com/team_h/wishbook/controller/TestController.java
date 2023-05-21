package com.team_h.wishbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	@RequestMapping("/test")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("test");
		mav.addObject("hello", "hello hello hello");
		return mav;
	}
}
