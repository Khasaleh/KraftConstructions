import { Component } from '@angular/core';
import {FormGroup, FormBuilder} from '@angular/forms'
import { ReqUser } from 'src/app/model/requsr';
import { ReqUserService } from 'src/app/service/Reqest.service';
@Component({
  selector: 'app-request-estimate',
  templateUrl: './request-estimate.component.html',
  styleUrls: ['./request-estimate.component.css']
})
export class RequestEstimateComponent {
userDetail!:FormGroup;
userObj: ReqUser = new ReqUser();

constructor(private formBuilder : FormBuilder,private requser: ReqUserService) {}
ngOnInit(): void {
this.userDetail = this.formBuilder.group ({
  firstName : [''],
  phoneNumber:[''],
  lastName : [''],
  email : [''],
  address : [''],
  city : [''],
  state: [''],
  zip:[''],
  requestedServices:[''],
  budget: [''],
  projectDescription: [''],
  aboutUs: ['']

  
});
}
submit() {
   
  this.userObj.firstName = this.userDetail.value?.firstName;
  this.userObj.lastName = this.userDetail.value?.lastName;
  this.userObj.email = this.userDetail.value?.email;
  this.userObj.address = this.userDetail.value?.address;
  this.userObj.city = this.userDetail.value?.city;
  this.userObj.state = this.userDetail.value?.state;
  this.userObj.zip = this.userDetail.value?.zip;
  this.userObj.requestedServices = [this.userDetail.value?.requestedServices];
  this.userObj.budget = this.userDetail.value.budget;
  this.userObj.projectDescription = this.userDetail.value?.projectDescription;
  this.userObj.aboutUs = this.userDetail.value?.aboutUs;
  console.log(this.userObj);
  this.requser.ReqUser(this.userObj).subscribe(res=>{
    console.log(res);
  
    

   }, err=> {
    console.log(err)
   })
}
}
