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
  }
  public onChange({ editor }: ChangeEvent) {
  }
  videoLink: any;
  fileURL!: File;
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
  errorMessages: string | null = null;
  successMessages: string | null = null;
  errorMessagess: string | null = null;
  successMessagess: string | null = null;
  apiData: any;
  selectedService!: string;
  service!: { id: number; serviceName: string; pageName: string; active: boolean; }[];
  serviceNames!: string[];
  globalUrl = 'https://img.kraftconstructionco.com';
  forms: FormGroup[] = [];
  serviceData: any
  images: File[] = [];
  imageLink: any[] = [];
  testData: any;
  image: any;
  constructor(private formBuilder: FormBuilder, private homeService: HomeServiceService,
  ) { }
  ngOnInit(): void {
    this.getSrviceData();
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
      },
    )
    this.homeService.getHomeBannerDescription().subscribe(
      previousResponse => {
        this.bannerForm.controls['bannerLink'].setValue(previousResponse.bannerLink);
        this.bannerForm.controls['bannerDescription'].setValue(previousResponse.bannerDescription);
        this.bannerForm.controls['linkStatus'].setValue(previousResponse.linkStatus);
      },
    )
    this.homeService.getHomePageData().subscribe(
      previousResponse => {
        this.aboutUsForm.controls['textEditor'].setValue(previousResponse.aboutusDescription);
        this.aboutUsForm.controls['addLink'].setValue(previousResponse.aboutusLink);
        this.videoLink = this.globalUrl + previousResponse.aboutusVideoUrl;
      },
    )
    this.homeService.getTestimonialsData().subscribe(
      response => {
      },
    )
    this.getBannerImages();
  }

  onVideoUploadBtnClick() {
    const formData = new FormData();
    formData.append('file', this.fileURL);
    this.homeService.saveVideo(formData).subscribe(
      response => {
        this.successMessagess = response?.message;
        setTimeout(() => {
          this.successMessagess = '';
        }, 1000);
      },
      error => {
        this.errorMessagess = error?.message;
        setTimeout(() => {
          this.errorMessagess = '';
        }, 1000);
      }
    );
  }
  onFileSelected(event: any) {
    this.fileURL = event.target.files[0];
    this.videoLink = URL.createObjectURL(this.fileURL);
    this.onVideoUploadBtnClick();
  }
  myForm = new FormGroup({
    name: new FormControl(''),
    email: new FormControl(''),
    phone: new FormControl('')
  });
  addForm() {
    this.testimonialData.push({ heading: '', description: '', name: '' });
  }
  deleteForm(item: any, index: number) {

    this.testimonialData.splice(index, 1);
  }
  onSubmit1() {
    if (this.bannerForm.valid) {
      const bannerLink = this.bannerForm.get('bannerLink')?.value;
      const bannerDescription = this.bannerForm.get('bannerDescription')?.value;
      const linkStatus = this.bannerForm.get('linkStatus')?.value;
      this.homeService.saveBannerData(bannerLink, bannerDescription, linkStatus).subscribe(
        response => {
          this.successMessage = "Slider data added successfully";
          setTimeout(() => {
            this.successMessage = '';
          }, 1000);
        },
        error => {
          this.errorMessage = error?.message;
          setTimeout(() => {
            this.errorMessage = '';
          }, 1000);
        }
      )
      this.uploadImages1();
    }
  }
  onSubmit() {
    if (this.aboutUsForm.valid) {
      const textEditor = this.aboutUsForm.get('textEditor')?.value;
      const addLink = this.aboutUsForm.get('addLink')?.value;
      this.homeService.saveHomepageData(textEditor, addLink).subscribe(
        response => {
          this.successMessage = "About us data added successfully";
          setTimeout(() => {
            this.successMessage = '';
          }, 1000);
        },
        error => {
          this.errorMessage = error?.message;
          setTimeout(() => {
            this.errorMessage = '';
          }, 1000);
        }
      )
    }
  }
  onServiceClick() {
    this.homeService.addServicesData(this.serviceList).subscribe(
      response => {
        this.successMessages = response?.message;
        setTimeout(() => {
          this.successMessages = '';
        }, 1000);
      },
      error => {
        this.errorMessages = error?.message;
        setTimeout(() => {
          this.errorMessages = '';
        }, 1000);
      }
    )
  }
  onSubmit2() {
    const testData = this.testimonialData;
    this.deleteAllTestimonial();
    this.homeService.saveTestimonialData(testData).subscribe(
      response => {
        this.successMessage = "Data Added successfully";
        setTimeout(() => {
          this.successMessage = '';
        }, 1000);
      },
      error => {
        this.errorMessage = error?.message;
        setTimeout(() => {
          this.errorMessage = '';
        }, 1000);
      }
    );
  }
  buttonLinkStatus() {
    this.homeService.bannerLinkStatus().subscribe(
      response => {
      },
    )
  }
  isCardBodyVisible: boolean = false;
  toggleCardBody() {
    this.isCardBodyVisible = !this.isCardBodyVisible;
  }
  isLinkVisible: boolean = false;
  toggleLink() {
    this.isLinkVisible = !this.isLinkVisible;
    this.buttonLinkStatus();
  }
  activeIndex = 0;
  onFileSelected1(event: any) {
    const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      this.images.push(file);
      this.imageLink.push(URL.createObjectURL(file));
    }
  }
  deleteAllTestimonial() {
    this.homeService.deleteAllTestimonial().subscribe(
      response => {
      }
    )
  }
  uploadImages1() {
    const formData = new FormData();
    for (let i = 0; i < this.images.length; i++) {
      formData.append('images', this.images[i]);
    }
    if (formData) {
      this.homeService.saveBannnerImage(formData).subscribe(
        response => {
        }
      );
    }

  }
  getBannerImages() {
    this.homeService.getHomePageBanner().subscribe(
      response => {
        this.image = response;
        this.imageLink = response.map((image: { imageUrl: any; }) => this.globalUrl + image.imageUrl);
      }
    )
  }

  deleteImage(bannerId: number) {

    this.homeService.deleteBannerImage(bannerId).subscribe(
      response => {
        this.getBannerImages();

      }
    )
  }
  onOptionSelected(event: Event) {
    const selectedValue = (event.target as HTMLSelectElement).value;
    if (selectedValue === 'interior') {
      this.homeService.getServiceByPage1()
        .subscribe(
          data => {
            this.service = data;
            this.serviceNames = this.service.map(service => service.serviceName);
            this.selectedService = '';
          });
    } else if (selectedValue === 'addition') {
      this.homeService.getServiceByPage2()
        .subscribe(
          data => {
            this.service = data;
            this.serviceNames = this.service.map(service => service.serviceName);
            this.selectedService = ''; // 
          });
    }
  }

  getSrviceData() {
    this.homeService.getServicesData().subscribe(
      response => {
        this.serviceData = response;
      }
    )
  }
  addServiceOnHomePage(serviceIds: any) {

    this.serviceList.push(serviceIds)
  }
  setActiveIndex(index: number) {
    this.activeIndex = index;
  }

  getTestData() {
    this.homeService.getTestimonialsData().subscribe(
      response => {
        if (response.length > 0) {
          this.testimonialData = response;
        }
      }
    )
  }
}
