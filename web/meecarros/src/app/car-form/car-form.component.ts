import { Component, OnInit, Output, EventEmitter } from '@angular/core';

import { CarService } from './../car.service';
import { ProspectService } from '../prospect.service';

import { ICar } from '../models/car';
import { IPerson } from './../models/person';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.css']
})
export class CarFormComponent implements OnInit {
  @Output() saved = new EventEmitter<ICar>();

  labelTitulo;
  private items: IPerson[];

  private cores: string[] = ["Preto", "Branco", "Verde"];

  model: ICar = {
    id: 0,
    person: {
      id: 0,
      name: ""
    },
    modelo: "",
    ano: "",
    cor: "" 
  };

  private person: string;

  constructor(private carService: CarService, private prospectService: ProspectService) { 
    this.labelTitulo = "Novo Carro";
  }
  
  ngOnInit() {
    this.prospectService.getProspects().subscribe(p => {
      this.items = p;
    });
  }

  onSubmit() {
    if (this.model.id == 0){
      this.prospectService.saveCar(this.model).subscribe(c => {
        this.saved.emit(c);
        alert("Carro salvo!");
      });
    } else {
      this.prospectService.editCar(this.model).subscribe(c => {
        this.saved.emit(c);
        alert("Carro editado!");
      });
    }   
  }
}
