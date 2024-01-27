import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { fakeDB } from '../../data/fakeDatabase';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent implements OnInit {

  id: string | null = "";
  title: string = "";
  imageURL: string = "";
  description: string = "";

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(value =>
      this.id = value.get("id")
    )
    this.setValuesContent(this.id);
  }

  setValuesContent(id: string | null) {
    const result = fakeDB.filter(digimon => digimon.id == id)[0];
    
    this.title = result.title
    this.imageURL = result.imageURL;
    this.description = result.description
  }
}
