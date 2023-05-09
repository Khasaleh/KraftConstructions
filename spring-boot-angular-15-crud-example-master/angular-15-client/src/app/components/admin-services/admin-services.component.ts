import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-services',
  templateUrl: './admin-services.component.html',
  styleUrls: ['./admin-services.component.css']
})
export class AdminServicesComponent {
  cards = [  {    imgSrc: "../../../assets/Decks-and-Patioss 1.png",    heading: "Kitchen",    content: "simply dummy text of the printing and typesetting industry."  },  {    imgSrc: "../../../assets/Decks-and-Patioss 1.png",    heading: "Bathroom",    content: "simply dummy text of the printing and typesetting industry."  },  {    imgSrc: "../../../assets/Decks-and-Patioss 1.png",    heading: "Decks-Patios",    content: "simply dummy text of the printing and typesetting industry."  },  {    imgSrc: "../../../assets/Decks-and-Patioss 1.png",    heading: "Basements",    content: "simply dummy text of the printing and typesetting industry."  }];

  selectedOption: string = 'interior remodeling';
  selectedOption1: string = 'interior remodeling';
  selectedOption2: string = 'interior remodeling';
  selectedOption3: string = '4 columns';
  selectedQuantity = 4;


quantityOptions = [1, 2, 3, 4];
options: string[] = ['interior remodeling', 'option2', 'option3'];
option1: string[] = ['1 column', '2 columns', '3 columns','4 columns'];


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
