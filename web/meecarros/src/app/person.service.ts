import { Observable } from 'rxjs/Observable';
import { IPerson } from './models/person';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ICar } from './models/car';

@Injectable()
export class PersonService {
  
  private url: string = 'http://localhost:9000/v1/persons';
    
  constructor(private http: HttpClient) { }
  
  getPersons(token: string): Observable<IPerson[]>{
    return this.http.get<IPerson[]>(this.url + "/" + token);
  }

}
