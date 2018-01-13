import { Observable } from 'rxjs/Observable';
import { IPerson } from './models/person';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ICar } from './models/car';

@Injectable()
export class PersonService {
  
  private url: string = 'http://localhost:9000/v1/persons';
    
  constructor(private http: HttpClient) { }
  
  getPersons(): Observable<IPerson[]>{
    return this.http.get<IPerson[]>(this.url);
  }

  getPerson(id: number): Observable<IPerson> {
    return this.http.get<IPerson>(this.url + "/" + id);
  }
  
  getCarsByPersonId(id: number): Observable<ICar[]> {
    return this.http.get<ICar[]>(this.url+ "/"+ id + "/cars");
  }
  
  deleteCar(id: number, personId: number): Observable<boolean> {
    return this.http.delete<boolean>(this.url + "/" + personId + "/cars/" + id);
  }

  saveCar(car: ICar): Observable<ICar> {
    return this.http.post<ICar>(this.url + "/" + car.person.id + "/cars/" + car.id, car);
  }

  editCar(car: ICar): Observable<ICar> {
    return this.http.put<ICar>(this.url + "/" + car.person.id + "/cars/" + car.id, car);
  }
}
