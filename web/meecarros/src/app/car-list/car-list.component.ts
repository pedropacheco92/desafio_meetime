import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  items;

  constructor() {
    this.items = [
      "carro 1",
      "carro 2"
    ]
   }

  ngOnInit() {
  }

}
