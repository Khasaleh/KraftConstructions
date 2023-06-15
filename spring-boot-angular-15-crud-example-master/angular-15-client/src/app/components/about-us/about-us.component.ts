import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UpdabtusService } from 'src/app/service/updabtus.service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css'
]
})

export class AboutUsComponent {
 globalUrl = 'http://99.72.32.144:8083'
addata!:FormGroup;
  constructor(private aboutusdata : UpdabtusService, private fb: FormBuilder) {
    
  }
  
  data! : any;
  // desc = "Kraft Construction is a family-owned business that was founded in Cincinnati, Ohio in 1971. Over the years we have evolved into a residential remodeling company dedicated to helping homeowners create their dream spaces with our individualized design approach and our dedicated employees working in your homes that have a combined 106 years of experience."
  // abc = "We strive to make our customers happy with every project we do. Whether it's adding an outdoor space for family and friends to gather, a new addition for a growing family, or updating your kitchen or bathroom.\nWe will work with you from beginning to end to have your dream home."


 
  
  ngOnInit(): void {
    // this.addata = this.fb.group ({
    //   description:[''],
    //   title: [''],
    //   imageUrl:['']
      
    // });

    this.aboutusdata.showdata().subscribe((result)=>{
      console.log(result);
      this.data=result;
    });



  }
  
  
  
  
 
}