import { Component, Output, EventEmitter, Inject } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { CarService } from './../car.service';
import { ProspectService } from '../prospect.service';

import { ICar } from '../models/car';
import { IPerson } from './../models/person';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.css']
})
export class CarFormComponent {
  @Output() saved = new EventEmitter<ICar>();

  labelTitulo;
  private items: IPerson[];

  private cores: string[] = ["Preto", "Branco", "Verde"];

  model: ICar = {
    id: 0,
    person: {
      id: 0,
      name: ""
    },
    modelo: "",
    ano: "",
    cor: "" 
  };

  private person: string;

  constructor(public dialogRef: MatDialogRef<CarFormComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { 
    this.labelTitulo = "Novo Carro";
    this.items = data.pessoas;
    if (data.value) {
      this.model = data.value;
    }
  }

  onSubmit() {
    this.dialogRef.close(this.model);
  //   if (this.model.id == 0){
  //     this.prospectService.saveCar(this.model).subscribe(c => {
  //       this.saved.emit(c);
  //       alert("Carro salvo!");
  //     });
  //   } else {
  //     this.prospectService.editCar(this.model).subscribe(c => {
  //       this.saved.emit(c);
  //       alert("Carro editado!");
  //     });
  //   }   
  }
  
}
