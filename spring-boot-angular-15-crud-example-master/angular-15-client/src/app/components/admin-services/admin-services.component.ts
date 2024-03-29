import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  isSubmitted = false;

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
  selectFiles(event: any) {
    this.urllink = event.target.files[0];
    this.imageLink = URL.createObjectURL(this.urllink);
  }
  selectFiles2(event: any) {
    this.urllink2 = event.target.files[0];
    this.imageLink2 = URL.createObjectURL(this.urllink2);
  }
  toggleCardBody() {
    this.isCardBodyVisible = !this.isCardBodyVisible;
  }
  getServices() {
    this.interiorRemodelingService.getServiceData().subscribe(
      (response) => {
        this.services = response;
      }
    )
  }

  get getControl(): { [key: string]: AbstractControl; } {
    return this.interiorRemodForm2.controls;
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
          }, 2000);
          this.getServices()
        },
        error => {
          this.errorMessage = "Service is not added.";
          setTimeout(() => {
            this.errorMessage = '';
          }, 2000);
        }
      )
    }
  }
  onClick1() {
    if (this.selectedServicId) {
      const formData = new FormData();
      formData.append('addPortfolio', this.interiorRemodForm2.value.isCheck == '' ? false : this.interiorRemodForm2.value.isCheck);
      formData.append('serviceId', this.selectedServicId.toString());
      if (this.urllink) {
        formData.append('beforeImage', this.urllink);
      }

      if (this.urllink2) {
        formData.append('afterImage', this.urllink2);
      }
      formData.append('beforeImageTitle', this.interiorRemodForm2.value.beforeImage);
      formData.append('afterImageTitle', this.interiorRemodForm2.value.afterImage);
      formData.append('description', this.interiorRemodForm2.value.description);

      if (this.serviceData) {
        this.interiorRemodelingService.updateServiceDetails(formData, this.serviceData.id).subscribe(
          response => {
            this.successMessage = "Service details updated successfully";
            setTimeout(() => {
              this.successMessage = '';
            }, 2000);
          },
          error => {
            this.errorMessage = error?.error.message
            setTimeout(() => {
              this.errorMessage = '';
            }, 2000);
          }
        );
      } else if (!this.serviceData) {
        this.interiorRemodelingService.saveSeviceDetails(formData).subscribe(
          response => {
            this.successMessage = "Service details added successfully";
            setTimeout(() => {
              this.successMessage = '';
            }, 2000);
          },
          error => {
            this.errorMessage = error?.error.message;
            setTimeout(() => {
              this.errorMessage = '';
            }, 2000);
          }
        );
        this.onClick2(this.selectedServicId);
      }
    }
  }

  selectImage(event: any, card: any) {
    const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      this.images.push(file);
      if (card.imgSrc !== '' && card.imgSrc !== null) {
        card.imgSrc = URL.createObjectURL(file)
        this.updateImageForCard(file, card.id);
      }
      else {
        card.imgSrc = URL.createObjectURL(file)
      }
    }
  }
  updateImageForCard(imageFile: any, cardId: number) {
    const formData = new FormData();
    formData.append('image', imageFile);
    this.interiorRemodelingService.updateImages(formData, cardId).subscribe(
      response => {
        this.successMessage = response?.message;
        setTimeout(() => {
          this.successMessage = '';
        }, 2000);
      },
      error => {
        this.errorMessage = error?.message;
        setTimeout(() => {
          this.errorMessage = '';
        }, 2000);
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
      },
      error => {
      }
    );
  }

  onOptionSelected(event: any) {
    const selectedValue = event;
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
          this.successMessages = 'Service enable successfully';
          setTimeout(() => {
            this.successMessages = '';
          }, 2000);
        },
        error => {
          this.errorMessages = error?.message;
          setTimeout(() => {
            this.errorMessages = '';
          }, 2000);
        }
      );
    } else {
      this.interiorRemodelingService.disableService(service.id).subscribe(
        response => {
          this.successMessages = 'Service disable successfully';
          setTimeout(() => {
            this.successMessages = '';
          }, 2000);
        },
        error => {
          this.errorMessages = error?.message;
          setTimeout(() => {
            this.errorMessages = '';
          }, 2000);
        }
      );
    }
  }
  getOneService(event: any) {
    this.selectedServicId = event;
    this.interiorRemodelingService.getServiceById(event).subscribe(
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
          this.getImages(event);
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
    this.getImages(event);
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
      }
    )
  }
  deleteOneService(id: number) {
    this.interiorRemodelingService.deleteService(id).subscribe(
      response => {
        this.getServices();
        this.successMessages = 'Service delete successfully';
        setTimeout(() => {
          this.successMessages = '';
        }, 2000);
      },
      error => {
        ;
        this.errorMessages = error?.message;
        setTimeout(() => {
          this.errorMessages = '';
        }, 2000);
      }
    )
  }
}
