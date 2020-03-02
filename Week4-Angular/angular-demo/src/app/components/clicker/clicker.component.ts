import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clicker',
  templateUrl: './clicker.component.html',
  styleUrls: ['./clicker.component.css']
})
export class ClickerComponent implements OnInit {

  count:number = 0;
  countColor:string ="red-text";

  constructor() { }

  ngOnInit(): void {
  }

  increment(value: number){
    this.count += value;

    if(this.count%5==0){
      this.countColor ="red-text";
    } else {
      this.countColor ="blue-text";
    }

  }

}
