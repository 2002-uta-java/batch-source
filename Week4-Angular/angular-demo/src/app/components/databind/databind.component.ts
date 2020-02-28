import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-databind',
  templateUrl: './databind.component.html',
  styleUrls: ['./databind.component.css']
})
export class DatabindComponent implements OnInit {

  person1 = {name: "Holly", age: 46, email: "holly@gmail.com"};
  
  person2 = {name: "Herold", age:53, email:"hjenkins25@gmail.com"};


  constructor() { }

  ngOnInit(): void {
  }

}
