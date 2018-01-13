import { Component, Output, EventEmitter, Inject, ChangeDetectorRef, AfterViewChecked } from '@angular/core';
import { FormsModule, FormControl, FormGroup, Validators }   from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

import { CarService } from './../car.service';
import { PersonService } from '../person.service';

import { ICar } from '../models/car';
import { IPerson } from './../models/person';

@Component({
  selector: 'app-car-form',
  templateUrl: './car-form.component.html',
  styleUrls: ['./car-form.component.css']
})
export class CarFormComponent implements AfterViewChecked {
  @Output() saved = new EventEmitter<ICar>();

  private labelTitulo;

  private selected: number;

  private items: IPerson[];

  private cores: string[] = ["Preto", "Branco", "Verde"];

  private submit: boolean;

  private year = new Date().getFullYear();

  modelo = new FormControl('', [Validators.required]);

  pessoa = new FormControl('', [Validators.required]);

  cor = new FormControl('', [Validators.required]);

  ano = new FormControl('', [Validators.required, Validators.max(this.year), Validators.min(this.year - 30)]);


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

  constructor(public dialogRef: MatDialogRef<CarFormComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private ref: ChangeDetectorRef) { 
    this.items = data.pessoas;
    this.labelTitulo = data.titulo;
    if (data.value) {
      this.model = data.value;
      this.selected = this.model.person.id;
    }
  }

  ngAfterViewChecked(): void {
    this.ref.detectChanges();
  }

  getErrorMessage(): string {
    if (this.ano.hasError('required')) {
      return "Ano não pode ser vazio";
    }
    if (this.ano.hasError('min')) {
      return "Ano não pode ser menor que " + (this.year - 30);
    }
    if (this.ano.hasError('max')) {
      return "Ano não pode ser maior que " + this.year;
    }
    return "";
  }

  onCalcelarClick(): void {    
    this.submit = false;
  }

  onSalvarClick(): void {
    this.submit = true;
  }
  
  onSubmit() {
    if (this.submit) {
      this.model.person = this.items.find(p => p.id == this.selected);
      this.dialogRef.close(this.model);
    } else {
      this.dialogRef.close(null);
    }
  }
  
}