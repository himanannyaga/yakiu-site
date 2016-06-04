import {Component} from "@angular/core";
import {RouteParams} from "@angular/router-deprecated";
import {LeagueInfo} from "../interfaces";


export class LeagueConfig {
	public static leagueConfig: LeagueInfo[] = [
		{
			name: "ce",
			param: ["", "ce"],
			title: "セリーグ"
		}, {
			name: "pa",
			param: ["pa"],
			title: "パリーグ"
		}
	];
	
	public static getLeagueObj(str: string) {
		return this.leagueConfig.find((league => league.param.indexOf(str) !== -1 ));
	}
}

@Component({
	template:`
	<div>
		{{leagueInfo.title}}ランキング
	</div>
	`
})
export class Home {
	private leagueInfo: LeagueInfo;
	constructor(private params: RouteParams) {};
	private ngOnInit() {
		this.leagueInfo = LeagueConfig.getLeagueObj(this.params.get("league"));
	}
}