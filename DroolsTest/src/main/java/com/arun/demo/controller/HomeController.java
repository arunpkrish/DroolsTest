package com.arun.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.arun.demo.model.Rule;
import com.arun.demo.service.RuleService;

@Controller
public class HomeController {

	@Autowired
	private RuleService ruleService;

	@RequestMapping("/")
	public ModelAndView home() {
		System.out.println("inside home");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");

		return mv;
	}

	@RequestMapping("executeRule")
	public ModelAndView executeRule(Rule rule) {
		System.out.println("inside executeRule");

		ruleService.fireRules(rule);

		ModelAndView mv = new ModelAndView();
		mv.addObject("rule", rule);
		mv.setViewName("index");

		return mv;
	}
}
