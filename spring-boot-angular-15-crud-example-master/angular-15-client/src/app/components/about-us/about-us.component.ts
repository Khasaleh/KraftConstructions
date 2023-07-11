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
  
  aboutusData! : any;
  footerData!:any;

 
  
  ngOnInit(): void {
  

    this.aboutusdata.showdata().subscribe((result)=>{
      this.aboutusData=result;
    });
    this.aboutusdata.getFootImage().subscribe((result)=> {
      this.footerData=result;
    });



  }

}