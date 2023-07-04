import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
    
    console.log(this.imagedata);
   
    
    let currentDate = new Date();
      let day = currentDate.getDate();
      let month = currentDate.getMonth() + 1;
      let year = currentDate.getFullYear();
      let formattedDate = day + "-" + month + "-" + year;
      this.userDetail.patchValue({
        date1: formattedDate
      });
  

  })
  
  
  
  this.userDetail = this.formBuilder.group ({
    yourReview:[''],
    yourEmail:[''],
    description:[''],
    workExperience:[''],
    yourName:[''],
    date1:[''],
    imageUrl:['']
  });
  
}
submit() {
  this.userObj = this.userDetail.value;
  this.testimonialService.PostTest(this.userObj).subscribe(res => {
    this.successMessage = 'Data saved successfully.';
    setTimeout(() => {
      this.successMessage = '';
    }, 3000);
   
    console.log(res);
  }, err => {
    this.errorMessage = 'An error occurred while saving the data.';
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
    console.log(err);
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
  console.log(res);
  this.sliderdata=res;
})
}


}
