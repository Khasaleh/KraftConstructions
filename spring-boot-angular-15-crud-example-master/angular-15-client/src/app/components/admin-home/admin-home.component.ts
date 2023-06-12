import { Component } from '@angular/core';
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
  
  videoLink : any;
  fileURL!: File;
  

  onFileSelected(event: any) {
    this.fileURL = event.target.files[0];
    this.videoLink = URL.createObjectURL(this.fileURL);

    this.onVideoUploadBtnClick();
  console.log(this.fileURL)

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
  //for Api integration

  aboutUsForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, private homeService: HomeServiceService) { }
  
  onVideoUploadBtnClick() {
 
    const formData = new FormData();
    formData.append('file', this.fileURL);
  
    this.homeService.saveVideo(formData).subscribe(
      response => {
        // Handle the API response here
        console.log(response);
      },
      error => {
        // Handle any error that occurs during the API request
        console.error(error);
      }
    );
  }
  
  ngOnInit(): void {

    this.aboutUsForm = this.formBuilder.group({

      textEditor: ['', Validators.required],
      addLink: ['', Validators.required],
    });

  }
  onSubmit() {

    if (this.aboutUsForm.valid) {
      const textEditor = this.aboutUsForm.get('textEditor')?.value;
      const addLink = this.aboutUsForm.get('addLink')?.value;
      this.homeService.saveHomepageData(textEditor,addLink).subscribe(
        response => {
                // Handle the API response here
                console.log("data submit successfully");
                console.log("response",response)
              },
                error => {
                // Handle any error that occurs during the API request
                console.error(error);
              }
      )
    }
    
  }
  isCardBodyVisible: boolean = false;
  toggleCardBody() {
    this.isCardBodyVisible = !this.isCardBodyVisible;
  }

  // images: Image[] = [];
  images: File[] = [];
  
  activeIndex = 0;

  // onFileSelected1(event: any) {
  //   const files = event.target.files;
  //   for (let i = 0; i < files.length; i++) {
  //     const file = files[i];
  //     const reader = new FileReader();
  //     reader.onload = (e) => {
  //       const imageUrl = reader.result as string;
  //       this.images.push({ url: imageUrl });
  //     };
  //     reader.readAsDataURL(file);
  //   }
  // }
  onFileSelected1(event: any) {
    const files = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      this.images.push(file);
    }
  }
  
  uploadImages1() {
    const formData = new FormData();
    for (let i = 0; i < this.images.length; i++) {
      formData.append('images', this.images[i]);
    }
  
    // Perform upload logic here, e.g., send formData to server
    console.log(formData);
  }

  uploadImages() {
    // Perform upload logic here, e.g., send images to server
    console.log(this.images);
  }
  getPreviewURL(image: File): string {
    return URL.createObjectURL(image);
  }
  setActiveIndex(index: number) {
    this.activeIndex = index;
  }
  
}
