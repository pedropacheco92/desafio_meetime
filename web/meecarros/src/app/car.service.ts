import { Injectable } from '@angular/core';
import { ICar } from './car';

@Injectable()
export class CarService {

  constructor() { }

  getAllCars(): ICar[] {
    return [
      {
        modelo: "Fusca"
      }, {
        modelo: "Ferrari"
      }
    ];
  }
}
