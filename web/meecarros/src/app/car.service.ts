import { ICar } from './models/car';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CarService {

  private url: string = 'http://localhost:9000/v1/carros';

  results: string[];

  constructor(private http: HttpClient) { }

  getCars() {
    this.http.get(this.url).subscribe(data => console.log(data));
  }
      
  deleteCar(id: number): Observable<boolean> {
    return this.http.delete<boolean>(this.url + "/" + id);
  }

  saveCar(car: ICar): Observable<ICar> {
    return this.http.post<ICar>(this.url + "/" + car.id, car);
  }

  editCar(car: ICar): Observable<ICar> {
    return this.http.post<ICar>(this.url + "/" + car.id, car);
  }

}
