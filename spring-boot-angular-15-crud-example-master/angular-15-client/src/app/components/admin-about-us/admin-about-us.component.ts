import { Component, OnInit } from '@angular/core';
import { aboutusdata } from 'src/app/data-type';
import { UpdabtusService } from 'src/app/service/updabtus.service';
import {FormGroup,FormBuilder} from '@angular/forms';
import { footerImage } from 'src/app/model/footer';

@Component({
  selector: 'app-admin-about-us',
  templateUrl: './admin-about-us.component.html',
  styleUrls: ['./admin-about-us.component.css', '../../../styles.css']
})
export class AdminAboutUsComponent{
  constructor(private aboutus : UpdabtusService, private fb:FormBuilder ) {}
addata!: FormGroup;
userDetail!:FormGroup;
imageLink: any;
fileURL!: File;
imageLink1: any;
fileURL1!: File;
title!:string;
userdata!:any;
data!:any;
globalUrl="http://99.72.32.144:8083";
successMessage: string | null = null;
errorMessage: string | null = null;
ngOnInit(): void {

    this.addata = this.fb.group ({
      description:[''],
      imageUrl:['']
      
    });
    this.aboutus.showdata().subscribe(
      previousValue => {
        this.addata.controls['description'].setValue(previousValue?.description);
    
    }) 
    this.userDetail = this.fb.group ({
      title:[''],
      footerImage:['']
    })
    this.aboutus.getFootImage().subscribe( 
      previousValue1 => {
        this.userDetail.controls['title'].setValue(previousValue1?.title);
 
      }
    )

  }

submit(data: aboutusdata) {
  console.log(data);
  this.aboutus.addata(data).subscribe((result)=> {
    this.onClick();
    this.successMessage='Data Saved Successfully'

setTimeout(() => {
  this.successMessage = '';
}, 1000);

    
    console.log(result);
   
  },err=> {
    this.errorMessage='Unable to save data'
    setTimeout(() => {
      this.errorMessage = '';
    }, 1000);
  });


}
submit1() {

    this.onClick1();

    this.successMessage='Data Saved Successfully'

    setTimeout(() => {
      this.successMessage = '';
    }, 1000);
    

       
   


}



onFileSelected(event: any) {
  this.fileURL = event.target.files[0];
  this.imageLink = URL.createObjectURL(this.fileURL);
 
  console.log(this.fileURL);
  console.log(this.imageLink,'image');
  

  }
  onFileSelected1(event: any) {
    this.fileURL1 = event.target?.files[0];
    this.imageLink1 = URL.createObjectURL(this.fileURL1);
    console.log(this.fileURL1);
    
     
    } 
  onClick() {
 
    const formData = new FormData();
    formData.append('image', this.fileURL);
  
    this.aboutus.saveImage(formData).subscribe(
      response => {
   
        console.log(response);
      },
      error => {
  
        console.error(error);
      }
    );
  }
  onClick1() {
 
    const formData = new FormData();
    formData.append('footerImage', this.fileURL1);
    formData.append('title',this.userDetail.value.title)
    this.aboutus.saveFootImage(formData).subscribe(
      response => {
   
        console.log(response);
      },
      error => {
  
        console.error(error);
      }
    );
  }

  
}

