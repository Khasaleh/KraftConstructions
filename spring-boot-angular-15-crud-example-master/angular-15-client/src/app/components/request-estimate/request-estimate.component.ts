import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder, AbstractControl} from '@angular/forms'
import { ReqUser } from 'src/app/model/requsr';
import { ReqUserService } from 'src/app/service/Reqest.service';
import { MatDialog } from '@angular/material/dialog';
import { DialogeComponent } from '../dialoge/dialoge.component' // Create a separate component for the dialog content
import { Validators } from '@angular/forms';
@Component({
  selector: 'app-request-estimate',
  templateUrl: './request-estimate.component.html',
  styleUrls: ['./request-estimate.component.css']
})
export class RequestEstimateComponent {
userDetail!:FormGroup;
userObj: ReqUser = new ReqUser();
requestedServices = ['General Contracting', 'New Additions', 'Kitchen Remodeling', 'Design Services', 'Home Remodelling', 'Basement Remodelling', 'Outdoor Living Spaces', 'Other Services'];
states: string[] = [
  'Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia',
  'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland',
  'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey',
  'New Mexico', 'New York', 'North Carolina', 'North Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina',
  'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
];
budgets: string[] = ['$25,000 to $50,000','$50,000 to $100,000','$100,000 to $150,000','$150,000 to $200,000', 'None of the Above'];
aboutUss: string[]= ['Social Media Platform', 'Friends and Family', 'Advertisement', 'Saw a Kraft Vehicle', 'other'];
selectedServices: string[] = [];
formattedAddress: string ='';
successMessage: string | null = null;
errorMessage: string | null = null;

constructor(private formBuilder : FormBuilder,private requser: ReqUserService,private dialog: MatDialog) {}
ngOnInit(): void {
this.userDetail = this.formBuilder.group ({
  firstName : ['',Validators.required],
  lastName : ['',Validators.required],
  email : ['',Validators.required],
  phoneNumber: ['',Validators.required],
  address : ['',Validators.required],
  city : ['',Validators.required],
  state: ['',Validators.required],
  zip:['',[Validators.required,Validators.pattern('^[0-9]+$')]],
  requestedServices: [[]],
  budget: ['',Validators.required],
  projectDescription: ['',Validators.required],
  aboutUs: ['',Validators.required]


});

}


handleAddressChange(address: any) {
  this.userDetail.controls['address'].setValue(address.formatted_address);
}
get getControl(): { [key: string]: AbstractControl; } {

  return this.userDetail.controls;

}


submit() {
  this.userDetail.markAllAsTouched();
  this.userObj = this.userDetail.value;

  this.userDetail.reset();
  this.requser.ReqUser(this.userObj).subscribe(res => {
    this.successMessage =  res.message;
    setTimeout(() => {
      this.successMessage = '';
    }, 3000);
  }, err => {
    this.errorMessage = err?.message;
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  })

}
toggleService(service: string) {
  const requestedServices = this.userDetail.controls['requestedServices'].value as string[];
  if (requestedServices.includes(service)) {
    this.userDetail.controls['requestedServices'].setValue(requestedServices.filter(item => item !== service));
  } else {
    this.userDetail.controls['requestedServices'].setValue([...requestedServices, service]);
  }
}
}

