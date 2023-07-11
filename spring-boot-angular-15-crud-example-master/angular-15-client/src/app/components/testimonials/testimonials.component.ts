import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Testimonial } from 'src/app/model/testimonial.model';
import { TestimonialService } from 'src/app/service/testmon.service';

@Component({
  selector: 'app-testimonials',
  templateUrl: './testimonials.component.html',
  styleUrls: ['./testimonials.component.css',
  '../../../styles.css']
})

export class TestimonialsComponent {
  userdata!:any[];
  imagedata!:any;
  userDetail!:FormGroup;
  userObj: Testimonial = new Testimonial();
  successMessage: string | null = null;
  errorMessage: string | null = null;
  userIdtoView!: number;
  globalUrl = 'http://99.72.32.144:8083';
  constructor(private formBuilder : FormBuilder, private testimonialService: TestimonialService,private dialog: MatDialog) {}
  sliderdata!:any;
  ngOnInit(): void {
  this.getApprovedTest();
  this.getSlider();
  this.testimonialService.getImage().subscribe((res)=> {
    
    this.imagedata=res;
  })
  
  
  
  this.userDetail = this.formBuilder.group ({
    yourReview:[''],
    yourEmail:[''],
    description:['',Validators.maxLength(250)],
    workExperience:[''],
    yourName:[''],
    date1:[''],
    imageUrl:['']
  });
  
  
}
get getControl(): { [key: string]: AbstractControl; } {

  return this.userDetail.controls;

}
submit() {
  this.userObj = this.userDetail.value;
  this.testimonialService.PostTest(this.userObj).subscribe(res => {
    this.successMessage = 'Data saved successfully.';
    setTimeout(() => {
      this.successMessage = '';
    }, 3000);
   
  }, err => {
    this.errorMessage = 'An error occurred while saving the data.';
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  })

  
}
getApprovedTest() {
  this.testimonialService.getapproveTest().subscribe((res)=> {
    console.log(res);
    this.userdata=res;
  });
}

getSlider() {
this.testimonialService.getSlider().subscribe((res)=> {
  this.sliderdata=res;
})
}


}
