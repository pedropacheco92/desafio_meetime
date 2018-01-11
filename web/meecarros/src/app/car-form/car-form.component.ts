import { CarService } from './../car.service';
import { Component, OnInit } from '@angular/core';
import { ProspectService } from '../prospect.service';
import { IProspect } from '../models/prospect';
import { ICar } from '../models/car';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.css']
})
export class CarFormComponent implements OnInit {
  labelTitulo;
  private items: IProspect[];
  private model: ICar = {
    id: 3,
    token: 3,
    modelo: "string",
    ano: "string",
    cor: "string" 
  };

  constructor(private carService: CarService, private prospectService: ProspectService) { 
    this.labelTitulo = "Novo Carro";
  }
  
  ngOnInit() {
    this.prospectService.getProspects().subscribe(p => {
      this.items = p;
    });
  }

  onSubmit() {
    console.log("salvou");
    console.log(this.model);
  }

}
