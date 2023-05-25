import { Component } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { CKEditorComponent, ChangeEvent } from '@ckeditor/ckeditor5-angular/ckeditor.component';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { HomeServiceService } from '../../service/home-service.service'
import { HttpClient } from '@angular/common/http';


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
  // urllink: any;
  // videoWidth: any;
  // videoHeight: any;
  fileURL:string ="";
  selectedFile: any;
  // fileURL: any;
  // selectFiles(event: any) {
  //   const file = event.target.files[0];

  //   if (file) {
  //     const reader = new FileReader();

  //     if (file.type.includes('image')) {
  //       reader.readAsDataURL(file);
  //     } else if (file.type.includes('video')) {
  //       reader.readAsArrayBuffer(file);
  //     }

  //     reader.onload = (event: any) => {
  //       if (file.type.includes('image')) {
  //         this.urllink = event.target.result;
  //       } else if (file.type.includes('video')) {
  //         const arrayBuffer = event.target.result;
  //         const blob = new Blob([new Uint8Array(arrayBuffer)], { type: 'video/mp4' });
  //         this.urllink = URL.createObjectURL(blob);
  //       }
  //     };
  //   }
  // }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.fileURL = URL.createObjectURL(this.selectedFile);
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
  // onVideoUploadBtnClick() {
  //   const video = this.urllink;
  //   this.homeService.saveVideo(video).subscribe(
  //        response => {
  //       // Handle the API response here
  //       console.log(response);
  //     },
  //       error => {
  //       // Handle any error that occurs during the API request
  //       console.error(error);
  //     }
  //   );
  // }
  onVideoUploadBtnClick() {
    // if (!this.urllink) {
    //   // No file selected, show an error message or perform the desired action
    //   return;
    // }
  
    // const file = event.target.files[0];
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
      const addLink = this.aboutUsForm.get('textEditor')?.value;
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

  // onVideoUploadBtnClick() {
  //   const video =
  //   this.homeService.saveVideo(a,b).subscribe(
  //        response => {
  //       // Handle the API response here
  //       console.log(response);
  //     },
  //       error => {
  //       // Handle any error that occurs during the API request
  //       console.error(error);
  //     }
  //   );
  // }
  //  constructor(private http: HttpClient){

  //  }
  //  ngOnInit(): void {
  // this.homeAboutUs();
  //  }
  // homeAboutUs(){
  //   this.http.get('http://99.72.32.144:8081/api/homepageabout-us').subscribe();
  // }
}
