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
  globalUrl = 'http://99.72.32.144:8083'

  // urllink:string ="../../../assets/before1.png";
  // selectFiles(event:any)
  // {
  //   if(event.target.files){
  //     var reader = new FileReader()
  //     reader.readAsDataURL(event.target.files[0])
  //     reader.onload = (event:any) => {
  //       this.urllink = event.target.result
  //     }
  //   }
  // }
  selectFiles(event: any) {
    this.urllink = event.target.files[0];
    this.imageLink = URL.createObjectURL(this.urllink);
    console.log(this.urllink);
    console.log(this.imageLink, 'image');

  }
  selectFiles2(event: any) {
    this.urllink2 = event.target.files[0];
    this.imageLink2 = URL.createObjectURL(this.urllink2);

    console.log(this.urllink2);
    console.log(this.imageLink2, 'image');
  }
  // urllinkImg: string = "../../../assets/Decks-and-Patioss 1.png";

  // selectImage(event: any, card: any) {
  //   if (event.target.files) {
  //     const reader = new FileReader();
  //     reader.readAsDataURL(event.target.files[0]);
  //     reader.onload = (event: any) => {
  //       card.imgSrc = event.target.result;
  //     };
  //   }
  // }


  errorMessage: string | null = null;
  successMessage: string | null = null;
  isActive: boolean = false;
  interiorRemodForm!: FormGroup;
  interiorRemodForm2!: FormGroup;
  isCheck: boolean = false;
  services: any[] = [];
  selectedService!: string;
  service: { id: number; serviceName: string; pageName: string; active: boolean; }[] = [];
  serviceNames!: string[];
  // apiData: any;
  constructor(private formBuilder: FormBuilder, private interiorRemodelingService: InteriorRemodelingService) { }
  ngOnInit(): void {
    this.selectedService = ''; // Initialize the selected service
    this.service = []; // Initialize the services array
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
  isCardBodyVisible: boolean = false;
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


          // Handle the API response here
          console.log("data submit successfully");
          console.log("response", response)
          this.getServices()
        },
        error => {
          this.errorMessage = "Service is not added.";

          setTimeout(() => {

            this.errorMessage = '';

          }, 1000);
          // Handle any error that occurs during the API request
          console.error(error);
        }
      )
    }
  }
  onClick1() {
    const selectedServiceId = this.service.find(service => service.serviceName === this.selectedService)?.id;

    if (selectedServiceId) {
      const formData = new FormData();
      formData.append('addPortfolio', this.interiorRemodForm2.value.isCheck);
      formData.append('serviceId', selectedServiceId.toString());
      formData.append('beforeImage', this.urllink);
      formData.append('afterImage', this.urllink2);
      formData.append('beforeImageTitle', this.interiorRemodForm2.value.beforeImage);
      formData.append('afterImageTitle', this.interiorRemodForm2.value.afterImage);
      formData.append('description', this.interiorRemodForm2.value.description);
      // Append the selected service ID

      this.interiorRemodelingService.saveSeviceDetails(formData).subscribe(
        response => {
          console.log(response, 'response for service details');
          this.successMessage = response?.message;
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

      this.onClick2(selectedServiceId);
    }
  }

  // onClick1() {

  //   const formData = new FormData();
  //   formData.append('file', this.urllink);
  //   formData.append('file', this.urllink2);
  //   formData.append('beforeImage', this.interiorRemodForm2.value.beforeImage);
  //   formData.append('afterImage', this.interiorRemodForm2.value.afterImage);
  //   formData.append('description', this.interiorRemodForm2.value.description);
  //   formData.append('isCheck', this.interiorRemodForm2.value.isCheck);
  //   this.interiorRemodelingService.saveSeviceDetails(formData).subscribe(
  //     response => {
  //       console.log(response, 'response for service details');
  //     },
  //     error => {
  //       console.log(error, 'error for service details');
  //     }
  //   )
  //   this.onClick2()
  // }
  // selectImage(event: any, card: any) {
  //   if (event.target.files) {
  //     this.urllink3 = event.target.files[0];
  //     card.imgSrc = URL.createObjectURL(this.urllink3);
  //     console.log(this.urllink3);
  //   }
  // }
  // onClick2() {
  //   const formData = new FormData();
  //   for (let i = 0; i < this.cards.length; i++) {
  //     const card = this.cards[i];
  //     if (card.imgSrc) {
  //       formData.append('files', card.imgSrc);
  //     }
  //   }

  //   this.interiorRemodelingService.saveServicesImages(formData).subscribe(
  //     response => {
  //       console.log(response, "response for service images");
  //     },
  //     error => {
  //       console.log(error, "error for service images");
  //     }
  //   );
  // }
  images: File[] = [];
  selectImage(event: any, card: any) {
    const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      this.images.push(file);
      // this.imageLink.push(URL.createObjectURL(file));
      card.imgSrc = URL.createObjectURL(file)
    }
  }
  onClick2(service: any) {
    const formData = new FormData();
    for (let i = 0; i < this.images.length; i++) {
      formData.append('images', this.images[i]);
    }
    console.log(formData);
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
            console.log(data);

            this.service = data; // Assign the API response to the services array
            this.serviceNames = this.service.map(service => service.serviceName); // Extract the service names
            this.selectedService = '';
          });
    } else if (selectedValue === 'addition') {
      this.interiorRemodelingService.getServiceByPage2()
        .subscribe(
          data => {
            console.log(data);
            this.service = data; // Assign the API response to the services array
            this.serviceNames = this.service.map(service => service.serviceName); // Extract the service names
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
        },
        error => {
          console.error("Error enabling service:", error);
        }
      );
    } else {
      this.interiorRemodelingService.disableService(service.id).subscribe(
        response => {
          console.log("Service disabled:", service.serviceName, response);
        },
        error => {
          console.error("Error disabling service:", error);
        }
      );
    }
  }
  serviceData: any;
  imageData: any;
  getOneService(event: any) {
    console.log(event.target?.value);
    this.interiorRemodelingService.getServiceById(event.target?.value).subscribe(
      response => {
        this.serviceData = response;
        // this.imageLink = response.beforeImageUrl
        // this.imageLink = response.map((image: { afterImageUrl: any; }) => this.globalUrl + image.afterImageUrl);
        this.imageLink = this.globalUrl + response.beforeImageUrl;
        this.imageLink2 = this.globalUrl + response.afterImageUrl;
        this.interiorRemodForm2.controls['beforeImage'].setValue(response.beforeImageTitle);
        this.interiorRemodForm2.controls['afterImage'].setValue(response.afterImageTitle);
        this.interiorRemodForm2.controls['description'].setValue(response.description);
        this.interiorRemodForm2.controls['isCheck'].setValue(response.addPortfolio);
        this.isCardBodyVisible = response.addPortfolio;


        // this.videoLink = this.globalUrl + previousResponse.aboutusVideoUrl;
        console.log(this.serviceData);

      },
      error => {
        console.log(error);

      }
    )
    this.getImages(event.target?.value);
  }
  getImages(service: any) {
    this.interiorRemodelingService.getServicesImages(service).subscribe(
      (response: any[]) => {
        if (response.length > 0){

          this.cards = response.map(item => ({
            id: item.id,
            imgSrc: this.globalUrl + item.imageUrl // Adjust the URL according to your API response
          }))
        }
      
        // this.imageData = response;
        
        console.log(this.imageData);
      },
      error => {
        console.log(error);

      }
    )
  }
}
