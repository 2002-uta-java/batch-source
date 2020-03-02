import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-s-directives',
  templateUrl: './s-directives.component.html',
  styleUrls: ['./s-directives.component.css']
})
export class SDirectivesComponent implements OnInit {

  condition:boolean = true;

  winningTeams = [{
    name: "Kansas City Chiefs",
    year: 2020
  },{
    name: "New England Patriots",
    year: 2019
  }, {
    name: "Philadelphia Eagles",
    year: 2018
  }, {
    name: "New England Patriots",
    year: 2017
  }, {
    name: "Denver Broncos",
    year: 2016
  }];

  constructor() { }

  ngOnInit(): void {
  }

  changeCondition(){
    this.condition = !this.condition;
  }

}
