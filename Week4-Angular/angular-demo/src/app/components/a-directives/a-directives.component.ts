import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-a-directives',
  templateUrl: './a-directives.component.html',
  styleUrls: ['./a-directives.component.css']
})
export class ADirectivesComponent implements OnInit {

  colors: string[] = ['blue', 'red', 'green'];
  selectedColor: string = 'blue';
  formats: string[] = ['highlight', 'bold', 'italic'];
  selectedFormats: string[] = [];

  constructor() { }

  ngOnInit(): void {
  }

  updateSelectedFormats(event){
    this.selectedFormats = [];
    // console.log(event.target);
    let options = event.target.options;
    for(let option of options){
      if(option.selected){
        this.selectedFormats.push(option.value);
      }
    }
    console.log(this.selectedFormats)
  }

}
