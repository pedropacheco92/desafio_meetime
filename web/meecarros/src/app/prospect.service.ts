import { Observable } from 'rxjs/Observable';
import { IPersons } from './models/persons';
import { IProspect } from './models/prospect';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class ProspectService {

  private url: string = 'http://localhost:9000/v1/prospects';

  private prospects: IProspect[];

  constructor(private http: HttpClient) { }

  getProspects(): Observable<IProspect[]>{
    return this.http.get<IProspect[]>(this.url);
  }
}
