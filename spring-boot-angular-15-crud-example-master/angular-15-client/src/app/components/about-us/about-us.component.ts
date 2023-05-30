import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ImageService } from 'src/app/service/image.service';
import { UpdabtusService } from 'src/app/service/updabtus.service';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css'
]
})

export class AboutUsComponent {
 
  constructor(private aboutusdata : UpdabtusService, private builder: FormBuilder, public imageService: ImageService ) {}
  
  userdata : any;
  desc = "Kraft Construction is a family-owned business that was founded in Cincinnati, Ohio in 1971. Over the years we have evolved into a residential remodeling company dedicated to helping homeowners create their dream spaces with our individualized design approach and our dedicated employees working in your homes that have a combined 106 years of experience."
  abc = "We strive to make our customers happy with every project we do. Whether it's adding an outdoor space for family and friends to gather, a new addition for a growing family, or updating your kitchen or bathroom.\nWe will work with you from beginning to end to have your dream home."
  


  showdata = this.builder.group({

    title: this.builder.control(''),

    description: this.builder.control('')

});
  
  ngOnInit(): void {
    this.aboutusdata.showdata().subscribe((result)=>{
      console.log(result);
      this.userdata=result;
    });
    
  }
  
  
  
  
 
}