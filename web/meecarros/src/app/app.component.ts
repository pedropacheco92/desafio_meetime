import { CarListComponent } from './car-list/car-list.component';
import { ICar } from './models/car';
import { Component, ViewChild } from '@angular/core';
import { CarService } from './car.service';
import { ProspectService } from './prospect.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] ,
  providers: [CarService, ProspectService]
})
export class AppComponent {
  title: string = 'app';
  novoCarroVisible: boolean = false;

  @ViewChild (CarListComponent) carList;

  constructor(private prospectService: ProspectService) { }

  btnNovoClick(): void {
    this.novoCarroVisible = !this.novoCarroVisible;
  }

  onSearch(value: string): void {
    let id = Number(value); // verificar erro
    this.prospectService.getCars(id).subscribe(c => this.carList.cars = c);
  }
}
