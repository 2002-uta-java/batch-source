import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pipe-demo',
  templateUrl: './pipe-demo.component.html',
  styleUrls: ['./pipe-demo.component.css']
})
export class PipeDemoComponent implements OnInit {

  str: string = "hello world";
  num: number = 36;
  day: Date = new Date();
  strArr: string[] = ["bANANas", "StrAWBerries", "lEMONs"];
  products: string[] = ["string-cheese", "whole-milk", "candy-apple", "granola-bars"];

  constructor() { }

  ngOnInit(): void {
  }

}
