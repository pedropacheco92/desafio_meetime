import { ICar } from './../models/car';
import { ProspectService } from './../prospect.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  cars: ICar[];
  
  constructor(private prospectService: ProspectService) {
    
  }
  ngOnInit() {
  }

}
