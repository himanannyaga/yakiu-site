package com.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.repository.TeamRepository;

@Controller
@RequestMapping("api/tyokin")
public class TyokinController {
	@Autowired
	private TeamRepository repository;
	
//	@RequestMapping()
//	public TeamRank get(@RequestBody @Valid TeamRank teamRank) {
//		Query query = new BasicQuery("{ type: '" + teamRank.getType() + "'}")
//		.limit(1)
//		.with(new Sort(Sort.Direction.DESC, "updated"));;
//		return repository.findLastInfo("hoge", new Sort(Sort.Direction.DESC, "updated"))
//	}
	
}
