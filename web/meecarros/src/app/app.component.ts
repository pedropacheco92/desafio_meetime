import { MatDialog, MatSnackBar } from '@angular/material';

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
export class AppComponent {

  private pessoas: IPerson[];

  private showBtn: boolean = false;

  @ViewChild (CarListComponent) carList;

  constructor(private personService: PersonService, private carService: CarService, public dialog: MatDialog, public snackBar: MatSnackBar) { }

  btnNovoClick(): void {
    let dialogRef = this.dialog.open(CarFormComponent, {
      data: { 
        pessoas: this.pessoas,
        titulo: "Novo Carro"
       },
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {       
        this.carService.saveCar(result).subscribe(c => { this.carList.onJustSaved(c)})
      }
    });
  }

  onSearch(value: string): void {
    console.log(value);
    this.personService.getPersons(value).subscribe(persons => {
      console.log(persons);
      if (!persons) {
        this.snackBar.open("Este token não existe no Pipedrive!", null, { duration: 2000, verticalPosition: 'top' });
      } else if (!persons.length){
        this.snackBar.open("Não existem pessoas cadastradas com esse token!", null, { duration: 2000, verticalPosition: 'top' });
      } else {
        this.snackBar.open("Token encontrado com " + persons.length + "pessoas", null, { duration: 2000, verticalPosition: 'top' });
        this.showBtn = true;
        this.pessoas = persons;
        this.carList.pessoas = persons;
      }
    });
  }
}
