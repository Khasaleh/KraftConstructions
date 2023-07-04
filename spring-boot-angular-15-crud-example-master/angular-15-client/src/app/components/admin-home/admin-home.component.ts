import { ChangeDetectorRef, Component } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { CKEditorComponent, ChangeEvent } from '@ckeditor/ckeditor5-angular/ckeditor.component';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { HomeServiceService } from '../../service/home-service.service'
import { HttpClient } from '@angular/common/http';
import { error } from 'jquery';

interface Image {
  image: string;
}
@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css', '../../../styles.css']
})
export class AdminHomeComponent {
  selectedOption: string = 'interior remodeling';
  options: string[] = ['interior remodeling', 'New Additions'];
  title = 'angular-template-ckeditor5-classic';
  public Editor = ClassicEditor;
  public onReady(editor: any) {
    console.log("CKEditor5 Angular Component is ready to use!", editor);
  }
  public onChange({ editor }: ChangeEvent) {
  }
  videoLink: any;
  fileURL!: File;
  onFileSelected(event: any) {
    this.fileURL = event.target.files[0];
    this.videoLink = URL.createObjectURL(this.fileURL);
    this.onVideoUploadBtnClick();
    console.log(this.fileURL)
    console.log(this.videoLink, "video link");
  }
  myForm = new FormGroup({
    name: new FormControl(''),
    email: new FormControl(''),
    phone: new FormControl('')
  });
  forms: FormGroup[] = [];
  addForm() {
    this.testimonialData.push({ heading: '', description: '', name: '' });
  }
  deleteForm(index: number) {
    this.testimonialData.splice(index, 1);
  }
  aboutUsForm!: FormGroup;
  bannerForm!: FormGroup;
  testimonialForm!: FormGroup;
  testimonialForm2!: FormGroup;
  linkStatus: boolean = false;
  testimonialData: [{ heading: string, description: string, name: string }] = [{ heading: '', description: '', name: '' }];
  serviceList: any[] = []
  test = '';
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private formBuilder: FormBuilder, private homeService: HomeServiceService,
  ) { }
  onVideoUploadBtnClick() {
    const formData = new FormData();
    formData.append('file', this.fileURL);
    this.homeService.saveVideo(formData).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.error(error);
      }
    );
  }

  apiData: any;
  selectedService!: string;
  service!: { id: number; serviceName: string; pageName: string; active: boolean; }[];
  serviceNames!: string[];
  globalUrl = 'http://99.72.32.144:8083'
  ngOnInit(): void {
    this.getTestData();
    this.selectedService = '';
    this.service = []; 
    this.serviceNames = [];
    this.aboutUsForm = this.formBuilder.group({
      textEditor: ['', Validators.required],
      addLink: ['', Validators.required],
    });

    this.bannerForm = this.formBuilder.group({
      bannerLink: ['', Validators.required],
      bannerDescription: ['', Validators.required],
      linkStatus: [this.linkStatus]
    });
    this.homeService.getHomePageData().subscribe(
      data => {
        this.apiData = data;
        console.log("ResponseData1", data);
      },
    )
    this.homeService.getHomeBannerDescription().subscribe(
      previousResponse => {
        this.bannerForm.controls['bannerLink'].setValue(previousResponse.bannerLink);
        this.bannerForm.controls['bannerDescription'].setValue(previousResponse.bannerDescription);
        this.bannerForm.controls['linkStatus'].setValue(previousResponse.linkStatus);
        console.log(previousResponse, "response for banner data");
      },
    )
    this.homeService.getHomePageData().subscribe(
      previousResponse => {
        this.aboutUsForm.controls['textEditor'].setValue(previousResponse.aboutusDescription);
        this.aboutUsForm.controls['addLink'].setValue(previousResponse.aboutusLink);
        this.videoLink = this.globalUrl + previousResponse.aboutusVideoUrl;
        console.log(previousResponse, "response for AboutUs section data");
      },
    )
    this.homeService.getTestimonialsData().subscribe(
      response => {
        console.log(response, "response for test get data");
      },
      error => {
        console.log(error, "error for test data");
      }
    )
    this.getBannerImages();
  }

  onSubmit2() {
    console.log("data for test", this.testimonialData);
    console.log(this.test);
    const testData = this.testimonialData;
    this.homeService.saveTestimonialData(testData).subscribe(
      response => {
        console.log("Response for testimonial section", response);
        this.successMessage = response?.message;
        console.log(response)
        setTimeout(() => {

          this.successMessage = '';

        }, 1000);

      },
      error => {
        this.errorMessage = error?.message;
        setTimeout(() => {

          this.errorMessage = '';

        }, 1000);
        console.error(error);

      }
    );

  }
  onServiceClick() {
    this.homeService.addServicesData(this.serviceList).subscribe(
      response => {
        this.successMessage = response?.message;
        console.log(response)
        setTimeout(() => {

          this.successMessage = '';

        }, 1000);
      },
      error => {
        console.log(error);
        this.errorMessage = error?.message;
        setTimeout(() => {

          this.errorMessage = '';

        }, 1000);

      }
    )
  }

  onSubmit1() {

    if (this.bannerForm.valid) {
      const bannerLink = this.bannerForm.get('bannerLink')?.value;
      const bannerDescription = this.bannerForm.get('bannerDescription')?.value;
      const linkStatus = this.bannerForm.get('linkStatus')?.value;
      this.homeService.saveBannerData(bannerLink, bannerDescription, linkStatus).subscribe(
        response => {
          this.successMessage = response?.message;
          console.log(response)
          setTimeout(() => {

            this.successMessage = '';

          }, 1000);
          console.log(" banner data submit successfully");
          console.log("response", response)
        },
        error => {
          console.log(error);
          this.errorMessage = error?.message;
          setTimeout(() => {
  
            this.errorMessage = '';
  
          }, 1000);
          console.error(error);
        }
      )
    }
    this.uploadImages();
  }
  onSubmit() {

    if (this.aboutUsForm.valid) {
      const textEditor = this.aboutUsForm.get('textEditor')?.value;
      const addLink = this.aboutUsForm.get('addLink')?.value;
      this.homeService.saveHomepageData(textEditor, addLink).subscribe(
        response => {
          this.successMessage = response?.message;
          console.log(response)
          setTimeout(() => {
  
            this.successMessage = '';
  
          }, 1000);
          console.log("data submit successfully");
          console.log("response", response)
        },
        error => {
          console.log(error);
          this.errorMessage = error?.message;
          setTimeout(() => {
  
            this.errorMessage = '';
  
          }, 1000);
          console.error(error);
        }
      )
    }
  }
  buttonLinkStatus() {
    this.homeService.bannerLinkStatus().subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.log(error);

      }
    )
  }
  isCardBodyVisible: boolean = false;
  toggleCardBody() {
    this.isCardBodyVisible = !this.isCardBodyVisible;
  }
  isLinkVisible: boolean = false;
  toggleLink() {
    console.log("hello world");

    this.isLinkVisible = !this.isLinkVisible;
    this.buttonLinkStatus();
  }
  images: File[] = [];
  imageLink: any[] = [];
  activeIndex = 0;
  onFileSelected1(event: any) {
    const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      this.images.push(file);
      this.imageLink.push(URL.createObjectURL(file));
    }
  }
  uploadImages1() {
    const formData = new FormData();
    for (let i = 0; i < this.images.length; i++) {
      formData.append('images', this.images[i]);
    }
    console.log(formData);
    this.homeService.saveBannnerImage(formData).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.error(error);
      }
    );
  }
  getBannerImages() {
    this.homeService.getHomePageBanner().subscribe(
      response => {
        console.log(response);

        this.imageLink = response.map((image: { imageUrl: any; }) => this.globalUrl + image.imageUrl);

      },
      error => {
        console.error(error);
      }
    )
  }

  onOptionSelected(event: Event) {
    const selectedValue = (event.target as HTMLSelectElement).value;
    if (selectedValue === 'interior') {
      this.homeService.getServiceByPage1()
        .subscribe(
          data => {
            console.log(data);

            this.service = data; // Assign the API response to the services array
            this.serviceNames = this.service.map(service => service.serviceName); // Extract the service names
            this.selectedService = '';
          });
    } else if (selectedValue === 'addition') {
      this.homeService.getServiceByPage2()
        .subscribe(
          data => {
            console.log(data);
            this.service = data; // Assign the API response to the services array
            this.serviceNames = this.service.map(service => service.serviceName); // Extract the service names
            this.selectedService = ''; // 
          });
    }
  }
  addServiceOnHomePage(serviceIds: any) {
 
    this.serviceList.push(serviceIds)
  }

  uploadImages() {
    this.uploadImages1();
    console.log(this.images);
  }
  setActiveIndex(index: number) {
    this.activeIndex = index;
  }
  testData: any
  getTestData() {
    this.homeService.getTestimonialsData().subscribe(
      response => {
        this.testData = response;
      }
    )
  }
}
