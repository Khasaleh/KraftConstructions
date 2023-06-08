import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {InteriorRemodelingService} from '../../service/interior-remodeling.service'

@Component({
  selector: 'app-admin-services',
  templateUrl: './admin-services.component.html',
  styleUrls: ['./admin-services.component.css']
})
export class AdminServicesComponent {
urllinkImg: string = "../../../assets/Decks-and-Patioss 1.png";

cards = [
  { id: 1, imgSrc: './../../assets/Decks-and-Patioss 1.png' },
  { id: 2, imgSrc: './../../assets/Decks-and-Patioss 1.png' },
  { id: 3, imgSrc: './../../assets/Decks-and-Patioss 1.png' },
  { id: 4, imgSrc: './../../assets/Decks-and-Patioss 1.png' },
];
  selectedOption: string = 'interior remodeling';
  selectedOption1: string = 'interior remodeling';
  selectedOption2: string = 'interior remodeling';
  selectedOption3: string = '4 columns';
  selectedOption4: string = 'Decks and Patios';
  selectedQuantity = 4;


  
quantityOptions = [1, 2, 3, 4];
options: string[] = ['interior remodeling', 'New Additions'];
optionSevice: string[] = ['Decks and Patios', 'New Additions'];
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


// urllinkImg: string = "../../../assets/Decks-and-Patioss 1.png";

selectImage(event: any, card: any) {
  if (event.target.files) {
    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (event: any) => {
      card.imgSrc = event.target.result;
    };
  }
}
isActive: boolean = false;
interiorRemodForm!: FormGroup;
services: any[] = [];
// apiData: any;
  constructor(private formBuilder: FormBuilder, private interiorRemodelingService: InteriorRemodelingService) { }
ngOnInit(): void {

  this.getServices();

  this.interiorRemodForm = this.formBuilder.group({

    addServiceName: ['', Validators.required],
    selectServicePage: ['', Validators.required],
    isActive: [this.isActive]
  
  });

}
getServices(){
  this.interiorRemodelingService.getServiceData().subscribe(
    (response) => {
      this.services = response;
      console.log("ResponseData",response);
    },
    (error) => {
      
      console.error(error);
    }
    )
}
onSubmit() {
 
  if (this.interiorRemodForm.valid) {
    const addServiceName = this.interiorRemodForm.get('addServiceName')?.value;
    const selectServicePage = this.interiorRemodForm.get('selectServicePage')?.value;
    const isActive = this.interiorRemodForm.get('isActive')?.value;

    this.interiorRemodelingService.saveInteriorRemodelingpageData(addServiceName,selectServicePage,isActive).subscribe(
      response => {
              // Handle the API response here
              console.log("data submit successfully");
              console.log("response",response)
              this.getServices()
            },
              error => {
              // Handle any error that occurs during the API request
              console.error(error);
            }
    )
  }
  
}
toggleCheckbox(service: any) {
  service.active = !service.active;
  
  if (service.active) {
    this.interiorRemodelingService.enableService(service.id).subscribe(
      response => {
        console.log("Service enabled:", service.serviceName,response);
      },
      error => {
        console.error("Error enabling service:", error);
      }
    );
  } else {
    this.interiorRemodelingService.disableService(service.id).subscribe(
      response => {
        console.log("Service disabled:", service.serviceName,response);
      },
      error => {
        console.error("Error disabling service:", error);
      }
    );
  }
}

}
