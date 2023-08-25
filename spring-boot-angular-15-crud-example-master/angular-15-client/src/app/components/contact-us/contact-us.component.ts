import { Component } from '@angular/core';
import {FormGroup, FormBuilder, AbstractControl} from '@angular/forms'
import { MatDialog } from '@angular/material/dialog';
import { ContactUs } from 'src/app/model/conus';
import { ContactUsService } from 'src/app/service/contact.us.service';
import { DialogeComponent } from '../dialoge/dialoge.component';
import { Validators } from '@angular/forms';
@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent {
  userDetail!:FormGroup;
  userObj: ContactUs = new ContactUs();
  data!:any;
  selectedCountryCode!:string[];
  countryCodes = [ {name: 'United States', code: '+1' }, { name: 'Canada', code: '+1' }, { name: 'Mexico', code: '+52' },{ name: 'United Kingdom', code: '+44' },
  { name: 'Australia', code: '+61' },
  { name: 'France', code: '+33' },
  { name: 'Spain', code: '+34' },
  { name: 'Italy', code: '+39' },
  { name: 'Germany', code: '+49' },
  { name: 'Russia', code: '+7' },
  { name: 'China', code: '+86' },
  { name: 'India', code: '+91' },
  { name: 'Brazil', code: '+55' },
  { name: 'South Africa', code: '+27' },
  { name: 'Egypt', code: '+20' }
] 
successMessage: string | null = null;
errorMessage: string | null = null;

  constructor(private formBuilder : FormBuilder, private conus:ContactUsService, private dialog:MatDialog) {}
ngOnInit(): void {
this.userDetail = this.formBuilder.group ({
  firstname : [''],
  lastname : [''],
  email : [''],
  phonenumber: [''],
  message:['']

  
});
this.conus.getContactAll().subscribe(result=> {
this.data=result;
})

}

submit() {
  this.userObj = this.userDetail.value;

  this.conus.saveData(this.userObj).subscribe(res => {
    this.successMessage = "Contact Us Submitted Successfully";
    setTimeout(() => {
      this.successMessage = '';
    }, 3000);
  }, err => {
    this.errorMessage = err?.message;
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  });
}

openDialog(): void {
  this.dialog.open(DialogeComponent, {
    data: {
      message: 'Submitted Successfully',
      showYesNoButtons: false,
      customButton: { label: 'Ok', action: 'custom-action' }
    }

  });

}

}
