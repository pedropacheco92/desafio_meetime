import { ICar } from './../models/car';
import { PersonService } from './../person.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { IPerson } from '../models/person';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  @Output() carEdited = new EventEmitter<ICar>();

  private cars: ICar[] = [];
  private pessoa: IPerson;

  constructor(private personService: PersonService) { }

  ngOnInit() {
  }

  onDelete(item: ICar): void {
    this.personService.deleteCar(item.id, this.pessoa.id).subscribe(b => {
      if (b) {
        this.cars = this.cars.filter(c => c.id != item.id);
        alert("Carro deletado com sucesso!");
      } else {
        alert("Não foi possível deletar!");
      }
    }); 
  }

  onEdit(item: ICar): void {
    this.carEdited.emit(item);
  }

}
