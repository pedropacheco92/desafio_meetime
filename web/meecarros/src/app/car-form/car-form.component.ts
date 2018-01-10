import { CarService } from './../car.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.css']
})
export class CarFormComponent implements OnInit {
  labelTitulo;
  items;

  constructor(private carService: CarService) { 
    this.labelTitulo = "Novo Carro";
  }
  
  ngOnInit() {
    this.items = this.carService.getAllCars();
    console.log(this.carService.getCars());
  }

}
