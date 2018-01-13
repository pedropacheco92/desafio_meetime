import { CarService } from './../car.service';
import { CarFormComponent } from './../car-form/car-form.component';
import { Component, Output, EventEmitter } from '@angular/core';
import { MatDialog, MatSnackBar } from '@angular/material';

import { ICar } from './../models/car';
import { PersonService } from './../person.service';
import { IPerson } from '../models/person';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent {

  private cars: ICar[] = [];

  public pessoas: IPerson[];

  constructor(private carService: CarService, public dialog: MatDialog, public snackBar: MatSnackBar) { }

  onDelete(item: ICar): void {
    this.carService.deleteCar(item.id).subscribe(b => {
      if (b) {
        this.cars = this.cars.filter(c => c.id != item.id);
        this.snackBar.open("Carro deletado com sucesso!", null, { duration: 2000, verticalPosition: 'top' });
      } else {
        this.snackBar.open("Não foi possível deletar!", null, { duration: 2000, verticalPosition: 'top' });
      }
    }); 
  }

  onEdit(item: ICar): void {
    let dialogRef = this.dialog.open(CarFormComponent, {
      data: { 
        pessoas: this.pessoas,
        titulo: "Editar Carro",
        value: item
       },
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {       
        this.carService.editCar(result).subscribe(c => {
          if (c) {
            this.snackBar.open("Carro editado com sucesso!", null, { duration: 2000, verticalPosition: 'top' });
          }
        });
      }
    });
  }

  onJustSaved(car: ICar): void {
      this.cars.push(car);    
  }

}
