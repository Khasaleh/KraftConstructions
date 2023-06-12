import { Component, OnInit } from '@angular/core';
import { aboutusdata } from 'src/app/data-type';
import { UpdabtusService } from 'src/app/service/updabtus.service';
import {FormGroup,FormBuilder} from '@angular/forms';
@Component({
  selector: 'app-admin-about-us',
  templateUrl: './admin-about-us.component.html',
  styleUrls: ['./admin-about-us.component.css', '../../../styles.css']
})
export class AdminAboutUsComponent{
  constructor(private aboutusdata : UpdabtusService, private fb:FormBuilder ) {}
urllink:string ="";
urllink1:string ="";
selectedFile: any;
selectedFile1: any;
addata!: FormGroup;
ngOnInit(): void {
    this.addata = this.fb.group ({
      description:[''],
      title: [''],
      imageUrl:['']
    });
}
submit(data: aboutusdata) {
  console.log(data);
  this.aboutusdata.addata(data).subscribe((result)=> {
    console.log(result);
  })

}


onFileSelected(event: any) {
  this.selectedFile = event.target?.files[0];
  this.urllink = URL.createObjectURL(this.selectedFile);
  
    // reader.readAsDataURL(this.selectedFile);
  }
  onFileSelected1(event: any) {
    this.selectedFile1 = event.target?.files[0];
    this.urllink1 = URL.createObjectURL(this.selectedFile1);
    console.log(this.selectedFile1);
    
      // reader.readAsDataURL(this.selectedFile);
    } 
    onClick() {
     
      const formData = new FormData();
      formData.append('file', this.selectedFile);
    console.log(this.selectedFile);
      this.aboutusdata.saveImage(formData).subscribe(
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

