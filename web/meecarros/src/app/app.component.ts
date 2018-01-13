import { MatDialog } from '@angular/material';

import { CarFormComponent } from './car-form/car-form.component';
import { CarListComponent } from './car-list/car-list.component';
import { ICar } from './models/car';
import { Component, ViewChild, OnInit } from '@angular/core';
import { CarService } from './car.service';
import { PersonService } from './person.service';
import { IPerson } from './models/person';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] ,
  providers: [CarService, PersonService]
})
export class AppComponent implements OnInit {
  title: string = 'app';

  private pessoas: IPerson[];

  @ViewChild (CarListComponent) carList;

  constructor(private personService: PersonService, public dialog: MatDialog) { }

  ngOnInit() {
    this.personService.getPersons().subscribe(p => {
      this.pessoas = p;
    });
  }

  btnNovoClick(): void {
    this.showCarFormModal(null);
  }

  onSearch(value: string): void {
    let id = Number(value); // verificar erro
    this.personService.getPerson(id).subscribe(p => this.carList.pessoa = p);
    this.personService.getCarsByPersonId(id).subscribe(c => this.carList.cars = c);
  }

  onCarEdited(event: ICar): void {
    this.showCarFormModal(event);
  }

  showCarFormModal(carro: ICar) {
    let dialogRef = this.dialog.open(CarFormComponent, {
      data: { 
        pessoas: this.pessoas,
        value: carro
       },
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {       
        this.personService.saveCar(result).subscribe(c => { this.carList.cars.push(c)})
      }
    });
  }
}
