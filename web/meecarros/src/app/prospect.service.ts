import { Observable } from 'rxjs/Observable';
import { IPersons } from './models/persons';
import { IProspect } from './models/prospect';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ICar } from './models/car';

@Injectable()
export class ProspectService {
  
  private url: string = 'http://localhost:9000/v1/prospects';
  
  private prospects: IProspect[];
  
  constructor(private http: HttpClient) { }
  
  getProspects(): Observable<IProspect[]>{
    return this.http.get<IProspect[]>(this.url);
  }
  
  getCars(id: number): Observable<ICar[]> {
    return this.http.get<ICar[]>(this.url+ "/"+ id + "/cars");
  }
  
  deleteCar(id: number, personId: number): Observable<boolean> {
    return this.http.delete<boolean>(this.url + "/" + personId + "/cars/" + id);
  }

}
