import { Component,Input } from '@angular/core';
import { FormService } from '../service/add-user.service';
import { ContactUsComponent } from '../components/contact-us/contact-us.component';
@Component({
  selector: 'app-admin-contact-us',
  templateUrl: './admin-contact-us.component.html',
  styleUrls: ['./admin-contact-us.component.css']
})
export class AdminContactUsComponent {

  firstname = "junaid";
  lastname!:string;
  email = "abc@gmail.com";
  phone="+139992222";
  message!:string;
  
  constructor(private formService : FormService) {}
    
  ngOnInit() {}
        
    submitForm() {
      console.log(this.firstname);
      // this.formService.submitForm().subscribe(response=>console.log(response));
    // alert("Hello");
  }
}

