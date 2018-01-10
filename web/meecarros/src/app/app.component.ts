import { Component } from '@angular/core';
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

  btnNovoClick(): void {
    this.novoCarroVisible = !this.novoCarroVisible;
  }
}
