import { ChangeDetectorRef, Component } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { CKEditorComponent, ChangeEvent } from '@ckeditor/ckeditor5-angular/ckeditor.component';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { HomeServiceService } from '../../service/home-service.service'
import { HttpClient } from '@angular/common/http';

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
    const newForm = new FormGroup({ ...this.myForm.controls });
    this.forms.push(newForm);
  }
  deleteForm(index: number) {
    this.forms.splice(index, 1);
  }
  aboutUsForm!: FormGroup;
  bannerForm!: FormGroup;
  linkStatus: boolean = false;

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
  ngOnInit(): void {
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
  }
  onSubmit1() {

    if (this.bannerForm.valid) {
      const bannerLink = this.bannerForm.get('bannerLink')?.value;
      const bannerDescription = this.bannerForm.get('bannerDescription')?.value;
      const linkStatus = this.bannerForm.get('linkStatus')?.value;
      this.homeService.saveBannerData(bannerLink, bannerDescription, linkStatus).subscribe(
        response => {
          console.log(" banner data submit successfully");
          console.log("response", response)
        },
        error => {
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
          console.log("data submit successfully");
          console.log("response", response)
        },
        error => {
          console.error(error);
        }
      )
    }
  }
  isCardBodyVisible: boolean = false;
  toggleCardBody() {
    this.isCardBodyVisible = !this.isCardBodyVisible;
  }
  isLinkVisible: boolean = false;
  toggleLink() {
    this.isLinkVisible = !this.isLinkVisible;
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
  uploadImages() {
    this.uploadImages1();
    console.log(this.images);
  }
  setActiveIndex(index: number) {
    this.activeIndex = index;
  }
}
