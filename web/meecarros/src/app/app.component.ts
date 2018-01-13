import { MatDialog } from '@angular/material';

import { CarFormComponent } from './car-form/car-form.component';
import { CarListComponent } from './car-list/car-list.component';
import { ICar } from './models/car';
import { Component, ViewChild, OnInit } from '@angular/core';
import { CarService } from './car.service';
import { ProspectService } from './prospect.service';
import { IPerson } from './models/person';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] ,
  providers: [CarService, ProspectService]
})
export class AppComponent implements OnInit {
  title: string = 'app';

  private pessoas: IPerson[];

  @ViewChild (CarListComponent) carList;

  constructor(private prospectService: ProspectService, public dialog: MatDialog) { }

  ngOnInit() {
    this.prospectService.getProspects().subscribe(p => {
      this.pessoas = p;
    });
  }

  btnNovoClick(): void {
    this.showCarFormModal(null);
  }

  onSearch(value: string): void {
    let id = Number(value); // verificar erro
    this.carList.personId = id;
    this.prospectService.getCars(id).subscribe(c => this.carList.cars = c);
  }

  onCarSaved(event: ICar): void {
    this.carList.cars.push(event);
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
      console.log(result);
    });
  }
}
