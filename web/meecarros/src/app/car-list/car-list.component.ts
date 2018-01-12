import { ICar } from './../models/car';
import { ProspectService } from './../prospect.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  @Output() carEdited = new EventEmitter<ICar>();
  
  cars: ICar[] = [];
  personId: number;
  
  constructor(private prospectService: ProspectService) {
    
  }
  ngOnInit() {
  }

  onDelete(event: any): void {
    let id: number = event.srcElement.id.replace( /^\D+/g, '');
    this.prospectService.deleteCar(id, this.personId)
      .subscribe(b => {
        if (b){
          this.cars = this.cars.filter(c => c.id != id);
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
