import { Component } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { CKEditorComponent, ChangeEvent } from '@ckeditor/ckeditor5-angular/ckeditor.component';
import { FormControl, FormGroup } from '@angular/forms';
import {HomeServiceService} from '../../service/home-service.service'


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
  urllink: string = "";
  videoWidth: any;
  videoHeight: any;

  selectFiles(event: any) {
    const file = event.target.files[0];

    if (file) {
      const reader = new FileReader();

      if (file.type.includes('image')) {
        reader.readAsDataURL(file);
      } else if (file.type.includes('video')) {
        reader.readAsArrayBuffer(file);
      }

      reader.onload = (event: any) => {
        if (file.type.includes('image')) {
          this.urllink = event.target.result;
        } else if (file.type.includes('video')) {
          const arrayBuffer = event.target.result;
          const blob = new Blob([new Uint8Array(arrayBuffer)], { type: 'video/mp4' });
          this.urllink = URL.createObjectURL(blob);
        }
      };
    }
  }
  myForm = new FormGroup({
    name: new FormControl(''),
    email: new FormControl(''),
    phone: new FormControl('')
  });
  forms: FormGroup[] = [];
  addForm() {
    const newForm = new FormGroup({...this.myForm.controls});
    this.forms.push(newForm);
  }
  deleteForm(index: number) {
    this.forms.splice(index, 1);
  }
//for Api integration

constructor(private homeService: HomeServiceService) { }

onSaveButtonClick() {
  this.homeService.getHomepageData().toPromise().then(
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

}
