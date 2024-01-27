import { Component, OnInit } from '@angular/core';
import { fakeDB } from '../../data/fakeDatabase';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  digimon: Digimon = new Digimon();
  digimons: Digimon[] = [];

  private result: Digimon[] = [];

  constructor() {
  }

  ngOnInit(): void {
    this.result = this.shuffle(fakeDB);
    this.initValues();
  }

  private initValues(): void {
    this.digimons = this.result.slice(1);
    this.digimon = this.result[0];
  }

  private shuffle(array: Digimon[]): Digimon[] {
    for (var j, x, i = array.length; i; j = Math.floor(Math.random() * i), x = array[--i], array[i] = array[j], array[j] = x);
    return array;
  }
}

class Digimon {
  id: string = "";
  title: string = "";
  imageURL: string = "";
  description: string = "";

  constructor() { }
}