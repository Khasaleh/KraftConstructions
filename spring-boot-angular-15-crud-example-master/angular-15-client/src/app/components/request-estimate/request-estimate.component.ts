import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder} from '@angular/forms'
import { ReqUser } from 'src/app/model/requsr';
import { ReqUserService } from 'src/app/service/Reqest.service';
import { MatDialog } from '@angular/material/dialog';
import { DialogeComponent } from '../dialoge/dialoge.component' // Create a separate component for the dialog content


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
budgets: string[] = ['$25,000 to $50,000','$50,000 to $100,000','$100,000 to $150,000','$150,000 to $200,000'];
aboutUss: string[]= ['Social Platform', 'Friends and Family','other'];
selectedServices: string[] = [];
formattedAddress: string ='';
successMessage: string | null = null;
errorMessage: string | null = null;

constructor(private formBuilder : FormBuilder,private requser: ReqUserService,private dialog: MatDialog) {}
ngOnInit(): void {
this.userDetail = this.formBuilder.group ({
  firstName : [''],
  lastName : [''],
  email : [''],
  phoneNumber: [''],
  address : [''],
  city : [''],
  state: [''],
  zip:[''],
  requestedServices: [[]],
  budget: [''],
  projectDescription: [''],
  aboutUs: ['']

  
});

}


handleAddressChange(address: any) {
  this.userDetail.controls['address'].setValue(address.formatted_address);
}



submit() {
  this.userObj = this.userDetail.value; 
  console.log(this.userObj); 
  // this.openDialog();
  
  this.userDetail.reset();
  this.requser.ReqUser(this.userObj).subscribe(res => {
    this.successMessage = 'Data saved successfully.';
    setTimeout(() => {
      this.successMessage = '';
    }, 3000);
    // this.openDialog();
    console.log(res);
  }, err => {
    this.errorMessage = 'An error occurred while saving the data.';
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
    console.log(err);
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
openDialog(): void {
  const dialogRef = this.dialog.open(DialogeComponent, {
    data: {
      message: 'Submitted Successfully',
      showYesNoButtons: false,
      customButton: { label: 'Ok', action: 'custom-action' }
    }

  });
  dialogRef.afterClosed().subscribe(result => {
  
    console.log(result);
  });

 
}

}


