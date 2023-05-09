import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-services',
  templateUrl: './admin-services.component.html',
  styleUrls: ['./admin-services.component.css']
})
export class AdminServicesComponent {
  selectedOption: string = 'interior remodeling';
  selectedOption1: string = 'interior remodeling';

  options: string[] = ['interior remodeling', 'option2', 'option3'];

urllink:string ="../../../assets/before1.png";
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

urllink1:string ="../../../assets/after1.png";
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
