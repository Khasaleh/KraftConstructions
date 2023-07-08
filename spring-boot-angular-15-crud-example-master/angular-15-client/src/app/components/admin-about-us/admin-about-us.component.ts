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
  headData!: FormGroup;
footerData!:FormGroup;
HeadImage: any;
fileURL!: File;
FooterImage: any;
fileURL1!: File;
title!:string;
userdata!:any;
data!:any;
globalUrl="http://99.72.32.144:8083";
successMessage: string | null = null;
errorMessage: string | null = null;
successMessage1: string | null = null;
errorMessage1: string | null = null;
ngOnInit(): void {

    this.headData = this.fb.group ({
      description:[''],
      imageUrl:['']
      
    });
    this.aboutus.showdata().subscribe(
      previousValue => {
        this.headData.controls['description'].setValue(previousValue?.description);
        this.headData.controls['imageUrl'].setValue(previousValue.imageUrl);
        this.HeadImage = this.globalUrl + previousValue.imageUrl;
    }) 
    this.footerData = this.fb.group ({
      title:[''],
      footerImage:['']
    })
    this.aboutus.getFootImage().subscribe( 
      previousValue1 => {
        this.footerData.controls['title'].setValue(previousValue1?.title);
        this.footerData.controls['footerImage'].setValue(previousValue1?.footerImage);
        this.FooterImage = this.globalUrl + previousValue1.footerImageUrl;
      }
    )

  }

submit(data: aboutusdata) {
  console.log(data);
  this.aboutus.addata(data).subscribe((result)=> {
    this.saveImageData();
      this.successMessage1 = result?.message;

      setTimeout(() => {
        this.successMessage1 = '';
      }, 3000);
      
          
          
         
        },err=> {
          this.errorMessage1= err?.message
          setTimeout(() => {
            this.errorMessage1 = '';
          }, 3000);
        });


}

  onHeaderFileSelected(event: any) {
    this.fileURL = event.target.files[0];
    this.HeadImage = URL.createObjectURL(this.fileURL);

    }
    onFooterFileSelected(event: any) {
      this.fileURL1 = event.target?.files[0];
      this.FooterImage = URL.createObjectURL(this.fileURL1);
      } 
    saveImageData() {
  
      const formData = new FormData();
      formData.append('image', this.fileURL);
    
      this.aboutus.saveImage(formData).subscribe(
        response => {
          this.successMessage1 = response?.message;

          setTimeout(() => {
            this.successMessage1 = '';
          }, 3000);
    },err=> {
            this.errorMessage1= err?.message
            setTimeout(() => {
              this.errorMessage1 = '';
            }, 3000);
        });
    }
    submitFooterData() {
  
      const formData = new FormData();
      formData.append('footerImage', this.fileURL1);
      formData.append('title',this.footerData.value.title)
      this.aboutus.saveFootImage(formData).subscribe(
        response => {
          this.successMessage = response?.message;

          setTimeout(() => {
            this.successMessage = '';
          }, 3000);

            },err=> {
              this.errorMessage= err?.message
              setTimeout(() => {
                this.errorMessage = '';
              }, 3000);
            });
    }

    
  }

