import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-portfolio',
  templateUrl: './admin-portfolio.component.html',
  styleUrls: ['./admin-portfolio.component.css']
})
export class AdminPortfolioComponent {
  urllink:string ="";
  selectFiles(event:any)
  {
    if(event.target.files){
      var reader = new FileReader()
      reader.readAsDataURL(event.target.files[0])
      reader.onload = (event:any) => {
        this.urllink = event.target.result
      }
    }
  }
}
