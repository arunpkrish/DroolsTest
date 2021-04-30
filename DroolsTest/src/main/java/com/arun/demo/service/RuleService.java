package com.arun.demo.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arun.demo.model.Rule;

@Component
public class RuleService {

	@Autowired
	private KieSession session;
	
	public Rule fireRules(Rule rule) {
		
		session.insert(rule);
		session.fireAllRules();
		System.out.println(rule.getResult());
		return rule;
	}
}
