import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { InteriorRemodelingService } from '../../service/interior-remodeling.service'
import { error, event } from 'jquery';

@Component({
  selector: 'app-admin-services',
  templateUrl: './admin-services.component.html',
  styleUrls: ['./admin-services.component.css']
})
export class AdminServicesComponent {
  urllinkImg: string = "../../../assets/Decks-and-Patioss 1.png";

  cards = [
    { id: 1, imgSrc: '' },
    { id: 2, imgSrc: '' },
    { id: 3, imgSrc: '' },
    { id: 4, imgSrc: '' },
  ];
  selectedOption: string = 'interior remodeling';
  selectedOption1: string = 'interior remodeling';
  selectedOption2: string = 'interior remodeling';
  selectedOption3: string = '4 columns';
  selectedOption4: string = 'Decks and Patios';
  selectedQuantity = 4;
  quantityOptions = [1, 2, 3, 4];
  options: string[] = ['Interior Remodelling', 'New Addition'];
  optionSevice: string[] = ['Decks and Patios', 'New Additions'];
  option1: string[] = ['1 column', '2 columns', '3 columns', '4 columns'];
  urllink!: File;
  imageLink: any;
  urllink2!: File;
  imageLink2: any;
  urllink3!: File
  globalUrl = 'https://img.kraftconstructionco.com';
  errorMessage: string | null = null;
  successMessage: string | null = null;
  errorMessages: string | null = null;
  successMessages: string | null = null;
  isActive: boolean = false;
  interiorRemodForm!: FormGroup;
  interiorRemodForm2!: FormGroup;
  isCheck: boolean = false;
  services: any[] = [];
  selectedService!: string;
  service: { id: number; serviceName: string; pageName: string; active: boolean; }[] = [];
  serviceNames!: string[];
  images: File[] = [];
  isCardBodyVisible: boolean = false;
  serviceData: any;
  imageData: any;
  selectedServicId: any;

  selectFiles(event: any) {
    this.urllink = event.target.files[0];
    this.imageLink = URL.createObjectURL(this.urllink);
  }
  selectFiles2(event: any) {
    this.urllink2 = event.target.files[0];
    this.imageLink2 = URL.createObjectURL(this.urllink2);
  }

  constructor(private formBuilder: FormBuilder, private interiorRemodelingService: InteriorRemodelingService) { }
  ngOnInit(): void {
    this.selectedService = '';
    this.service = [];
    this.serviceNames = [];
    this.getServices();
    this.interiorRemodForm = this.formBuilder.group({
      addServiceName: ['', Validators.required],
      selectServicePage: ['', Validators.required],
      isActive: [this.isActive]
    });
    this.interiorRemodForm2 = this.formBuilder.group({
      beforeImage: ['', Validators.required],
      afterImage: ['', Validators.required],
      description: ['', Validators.required],
      isCheck: [this.isCheck]
    });
  }
  toggleCardBody() {
    this.isCardBodyVisible = !this.isCardBodyVisible;
  }
  getServices() {
    this.interiorRemodelingService.getServiceData().subscribe(
      (response) => {
        this.services = response;
        console.log("ResponseData", response);
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

      this.interiorRemodelingService.saveInteriorRemodelingpageData(addServiceName, selectServicePage, isActive).subscribe(
        response => {
          this.successMessage = "Service added Successfully.";
          setTimeout(() => {
            this.successMessage = '';
          }, 1000);
          console.log("response", response)
          this.getServices()
        },
        error => {
          this.errorMessage = "Service is not added.";
          setTimeout(() => {
            this.errorMessage = '';
          }, 1000);
          console.error(error);
        }
      )
    }
  }
  // onClick1() {
  //   if (this.selectedServicId) {
  //     const formData = new FormData();
  //     formData.append('addPortfolio', this.interiorRemodForm2.value.isCheck);
  //     formData.append('serviceId', this.selectedServicId.toString());
  //     formData.append('beforeImage', this.urllink);
  //     formData.append('afterImage', this.urllink2);
  //     formData.append('beforeImageTitle', this.interiorRemodForm2.value.beforeImage);
  //     formData.append('afterImageTitle', this.interiorRemodForm2.value.afterImage);
  //     formData.append('description', this.interiorRemodForm2.value.description);

  //     this.interiorRemodelingService.saveSeviceDetails(formData).subscribe(
  //       response => {
  //         console.log(response, 'response for service details');
  //         this.successMessage = "service details added succesfully";
  //         setTimeout(() => {
  //           this.successMessage = '';

  //         }, 1000);
  //       },
  //       error => {
  //         console.log(error, 'error for service details');
  //         this.errorMessage = error?.message;
  //         setTimeout(() => {
  //           this.errorMessage = '';
  //         }, 1000);
  //       }
  //     );
  //     this.onClick2(this.selectedServicId);
  //   }
  // }



  onClick1() {
    if (this.selectedServicId) {
      const formData = new FormData();
      formData.append('addPortfolio', this.interiorRemodForm2.value.isCheck);
      formData.append('serviceId', this.selectedServicId.toString());
      // formData.append('beforeImage', this.urllink);
      // formData.append('afterImage', this.urllink2);
      if (this.urllink) {
        formData.append('beforeImage', this.urllink);
      }
      
      if (this.urllink2) {
        formData.append('afterImage', this.urllink2);
      }
      formData.append('beforeImageTitle', this.interiorRemodForm2.value.beforeImage);
      formData.append('afterImageTitle', this.interiorRemodForm2.value.afterImage);
      formData.append('description', this.interiorRemodForm2.value.description);
  
      // Check if the service data exists
      if (this.serviceData) {
        this.interiorRemodelingService.updateServiceDetails(formData, this.serviceData.id).subscribe(
          response => {
            console.log(response, 'response for updated service details');
            this.successMessage = "Service details updated successfully";
            setTimeout(() => {
              this.successMessage = '';
            }, 2000);
          },
          error => {
            console.log(error, 'error for updating service details');
            this.errorMessage = error?.message;
            setTimeout(() => {
              this.errorMessage = '';
            }, 2000);
          }
        );
      } else {
        this.interiorRemodelingService.saveSeviceDetails(formData).subscribe(
          response => {
            console.log(response, 'response for service details');
            this.successMessage = "Service details added successfully";
            setTimeout(() => {
              this.successMessage = '';
            }, 1000);
          },
          error => {
            console.log(error, 'error for service details');
            this.errorMessage = error?.message;
            setTimeout(() => {
              this.errorMessage = '';
            }, 1000);
          }
        );
      }
      
      this.onClick2(this.selectedServicId);
    }
  }
  
  selectImage(event: any, card: any) {
    const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      this.images.push(file);
      if(card.imgSrc !== '' && card.imgSrc !== null){
        card.imgSrc = URL.createObjectURL(file)
        this.updateImageForCard(file, card.id);
      }
      else{
        card.imgSrc = URL.createObjectURL(file)
      }
    }
  }
  updateImageForCard(imageFile: any, cardId: number) {
    const formData = new FormData();
    formData.append('image', imageFile); 
    this.interiorRemodelingService.updateImages(formData, cardId).subscribe(
      response => {
        console.log('Image updated successfully for card with ID:', cardId, response);
        this.successMessage = response?.message;
        setTimeout(() => {
          this.successMessage = '';
        }, 1000);
      },
      error => {
        console.error('Error updating image for card with ID:', cardId, error);
        this.errorMessage = error?.message;
        setTimeout(() => {
          this.errorMessage = '';
        }, 1000);
      }
    );
  }
  onClick2(service: any) {
    const formData = new FormData();
    for (let i = 0; i < this.images.length; i++) {
      formData.append('images', this.images[i]);
    }
    this.interiorRemodelingService.saveServicesImages(formData, service).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.error(error);
      }
    );
  }

  onOptionSelected(event: Event) {
    const selectedValue = (event.target as HTMLSelectElement).value;
    if (selectedValue === 'interior') {
      this.interiorRemodelingService.getServiceByPage1()
        .subscribe(
          data => {
            this.services = data;
            this.service = data;
            this.serviceNames = this.service.map(service => service.serviceName);
            this.selectedService = '';
          });
    } else if (selectedValue === 'addition') {
      this.interiorRemodelingService.getServiceByPage2()
        .subscribe(
          data => {
            console.log(data);
            this.services = data;
            this.service = data;
            this.serviceNames = this.service.map(service => service.serviceName);
            this.selectedService = ''; // 
          });
    }
  }
  toggleCheckbox(service: any) {
    service.active = !service.active;
    if (service.active) {
      this.interiorRemodelingService.enableService(service.id).subscribe(
        response => {
          console.log("Service enabled:", service.serviceName, response);
          this.successMessages = 'Service enable successfully';
          setTimeout(() => {
            this.successMessages = '';
          }, 1000);
        },
        error => {
          console.error("Error enabling service:", error);
          this.errorMessages = error?.message;
          setTimeout(() => {
            this.errorMessages = '';
          }, 1000);
        }
      );
    } else {
      this.interiorRemodelingService.disableService(service.id).subscribe(
        response => {
          console.log("Service disabled:", service.serviceName, response);
          this.successMessages = 'Service disable successfully';
          setTimeout(() => {
            this.successMessages = '';
          }, 1000);
        },
        error => {
          console.error("Error disabling service:", error);
          this.errorMessages = error?.message;
          setTimeout(() => {
            this.errorMessages= '';
          }, 1000);
        }
      );
    }
  }
  getOneService(event: any) {
    console.log(event.target?.value);
    this.selectedServicId = event.target?.value
    this.interiorRemodelingService.getServiceById(event.target?.value).subscribe(
      response => {
        if (response) {
          this.serviceData = response;
          this.imageLink = this.globalUrl + response.beforeImageUrl;
          this.imageLink2 = this.globalUrl + response.afterImageUrl;
          this.interiorRemodForm2.controls['beforeImage'].setValue(response.beforeImageTitle);
          this.interiorRemodForm2.controls['afterImage'].setValue(response.afterImageTitle);
          this.interiorRemodForm2.controls['description'].setValue(response.description);
          this.interiorRemodForm2.controls['isCheck'].setValue(response.addPortfolio);
          this.isCardBodyVisible = response.addPortfolio;
          console.log(this.serviceData,"hurry");
          this.getImages(event.target?.value);
        }
        else {
          this.imageLink = null;
          this.imageLink2 = null;
          this.interiorRemodForm2.controls['beforeImage'].setValue('');
          this.interiorRemodForm2.controls['afterImage'].setValue('');
          this.interiorRemodForm2.controls['description'].setValue('');
          this.interiorRemodForm2.controls['isCheck'].setValue('');
          this.isCardBodyVisible = false;
        }
      },
      error => {
        this.imageLink = null;
        this.imageLink2 = null;
        this.interiorRemodForm2.controls['beforeImage'].setValue('');
        this.interiorRemodForm2.controls['afterImage'].setValue('');
        this.interiorRemodForm2.controls['description'].setValue('');
        this.interiorRemodForm2.controls['isCheck'].setValue('');
        this.isCardBodyVisible = false;
      }
    );
    this.getImages(event.target?.value);
  }
  getImages(service: any) {
    this.interiorRemodelingService.getServicesImages(service).subscribe(
      (response: any[]) => {
        if (response.length > 0) {
          this.cards = response.map(item => ({
            id: item.id,
            imgSrc: this.globalUrl + item.imageUrl
          }))
        }
        else {
          this.cards = [
            { id: 1, imgSrc: '' },
            { id: 2, imgSrc: '' },
            { id: 3, imgSrc: '' },
            { id: 4, imgSrc: '' },
          ];
        }
      },
      error => {
        console.log(error);
      }
    )
  }
  deleteOneService(id: number){
  this.interiorRemodelingService.deleteService(id).subscribe(
    response => {
      // this.successMessage = ''
      console.log(response,"delete service");
      this.getServices();
      this.successMessages = 'Service delete successfully';
      setTimeout(() => {
        this.successMessages = '';
      }, 1000);  
    },
    error => {
      console.error(error);
      this.errorMessages = error?.message;
      setTimeout(() => {
        this.errorMessages= '';
      }, 1000);
    }
  )
  }
}
