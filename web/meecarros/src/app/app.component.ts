import { Component } from '@angular/core';
import { CarService } from './car.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] ,
  providers: [CarService]
})
export class AppComponent {
  title: string = 'app';
  novoCarroVisible: boolean = false;

  btnNovoClick(): void {
    this.novoCarroVisible = !this.novoCarroVisible;
  }
}
