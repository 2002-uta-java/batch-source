import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-s-directives',
  templateUrl: './s-directives.component.html',
  styleUrls: ['./s-directives.component.css']
})
export class SDirectivesComponent implements OnInit {
  condition: boolean = false;
  winningTeams = [{ name: "Kansas City Chief", year: 2020, losingTeam: "San Francisco 49ers"}, { name: "New England Patriots", year: 2019, losingTeam: "Los Angeles Rams" }, { name: "Philadelphia Eagles", year: 2018, losingTeam: "New England Patriots" }, { name: "New England Patriots", year: 2018, losingTeam: "Atlanta Falcons" }, { name: "Denver Broncos", year: 2017, losingTeam: "Carolina Panthers" }, { name: "New England Patriots", year: 2016, losingTeam: "Seattle Seahawks" }, { name: "Seattle Seahawks", year: 2015, losingTeam: "Denver Broncos" }];

  ngOnInit(): void {
  }

  changeCondition() {
    this.condition = !this.condition;
  }

}
