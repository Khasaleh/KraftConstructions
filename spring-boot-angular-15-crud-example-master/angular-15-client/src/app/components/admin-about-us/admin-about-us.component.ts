import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-about-us',
  templateUrl: './admin-about-us.component.html',
  styleUrls: ['./admin-about-us.component.css']
})
export class AdminAboutUsComponent {
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
urllink1:string ="";
selectFiles2(event:any)
{
  if(event.target.files){
    var reader1 = new FileReader()
    reader1.readAsDataURL(event.target.files[0])
    reader1.onload = (event:any) => {
      this.urllink1 = event.target.result
    }
  }
}
}
console.log("hello world");
