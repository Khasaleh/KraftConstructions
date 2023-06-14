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
  constructor(private aboutus : UpdabtusService, private fb:FormBuilder ) {}
addata!: FormGroup;
imageLink: any;
fileURL!: File;
data!:any;
globalUrl="http://99.72.32.144:8083";
ngOnInit(): void {

    this.addata = this.fb.group ({
      description:[''],
      title: [''],
      imageUrl:['']
      
    });
    // this.aboutus.showdata().subscribe( previousValue => {
    //   this.addata.controls['description'].setValue(previousValue.description);
    // });
  }

submit(data: aboutusdata) {
  console.log(data);
  this.aboutus.addata(data).subscribe((result)=> {
    
    console.log(result);
   
  });


}


onFileSelected(event: any) {
  this.fileURL = event.target.files[0];
  this.imageLink = URL.createObjectURL(this.fileURL);
  this.onClick();
  console.log(this.fileURL);
  console.log(this.imageLink,'image');
  

  }
  // onFileSelected1(event: any) {
  //   this.fileURL = event.target?.files[0];
  //   this.urllink1 = URL.createObjectURL(this.fileURL);
  //   console.log(this.fileURL);
    
     
  //   } 
  onClick() {
 
    const formData = new FormData();
    formData.append('image', this.fileURL);
  
    this.aboutus.saveImage(formData).subscribe(
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

