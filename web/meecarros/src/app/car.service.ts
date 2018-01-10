import { Injectable } from '@angular/core';
import { ICar } from './car';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CarService {

  private url: string = 'http://localhost:8080/greeting';

  results: string[];

  constructor(private http: HttpClient) { }

  getCars(): void {
    console.log(1);
    this.http.get(this.url).subscribe(data => console.log(data));
    console.log(2);
  }

  private handleError(error: Response) {
    console.log(error);
    return Observable.throw(error);
} 

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
