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
    
  model: ICar = {
    id: 0,
    personId: 0,
    modelo: "",
    ano: "",
    cor: "" 
  };

  modelo = new FormControl(this.model.modelo, [Validators.required]);

  pessoa = new FormControl(this.model.personId, [Validators.required]);

  cor = new FormControl(this.model.cor, [Validators.required]);

  ano = new FormControl(this.model.ano, [Validators.required, Validators.max(this.year), Validators.min(this.year - 30)]);

  carForm = new FormGroup({
    'modelo': this.modelo,
    'pessoa': this.pessoa,
    'cor': this.cor,
    'ano': this.ano
  })

  constructor(public dialogRef: MatDialogRef<CarFormComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private ref: ChangeDetectorRef) { 
    this.items = data.pessoas;
    this.labelTitulo = data.titulo;
    if (data.value) {
      this.model = data.value;
      this.selected = this.model.personId;
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
      this.model.personId = this.selected;
      this.dialogRef.close(this.model);
    } else {
      this.dialogRef.close(null);
    }
  }

  formValid(): boolean {    
    return this.carForm.valid;
  }
  
}