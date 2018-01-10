import { CarService } from './../car.service';
import { Component, OnInit } from '@angular/core';
import { ProspectService } from '../prospect.service';
import { IProspect } from '../models/prospect';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.css']
})
export class CarFormComponent implements OnInit {
  labelTitulo;
  private items: IProspect[];

  constructor(private carService: CarService, private prospectService: ProspectService) { 
    this.labelTitulo = "Novo Carro";
  }
  
  ngOnInit() {
    this.prospectService.getProspects().subscribe(p => {
      this.items = p;
    });
    // console.log("--------------")
    // console.log(this.items);
  }

}
