import { Component } from '@angular/core';
import { FormBuilder, FormGroup,FormArray  } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { CareerData } from 'src/app/model/career';
import { CareerDataService } from 'src/app/service/careerData.service';
import { DialogeComponent } from '../dialoge/dialoge.component';
@Component({
  selector: 'app-careers',
  templateUrl: './careers.component.html',
  styleUrls: ['./careers.component.css']
})
export class CareersComponent {
  userDetail!:FormGroup;
  userObj: CareerData = new CareerData();
  successMessage: string | null = null;
errorMessage: string | null = null;
imageLink: any;
fileURL1!: File;
states: string[] = [
  'Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia',
  'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland',
  'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey',
  'New Mexico', 'New York', 'North Carolina', 'North Dakota', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina',
  'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
];



jobType:string[]=['Yes','No'];
  constructor(private formBuilder : FormBuilder, private dialog:MatDialog, private careerData: CareerDataService) {}
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
  workExperience:[''],
  jobType:[''],
  workRestrictions:[''],
  hoursRestrictions:[''],
  resume:[''],
  otherNotes:[''],
  skills:[''],
  references:[[]],
  referencesrel:[[]],
  referencesnum:[[]],
  references1:[[]],
  referencesrel1:[[]],
  referencesnum1:[[]],
  references2:[[]],
  referencesrel2:[[]],
  referencesnum2:[[]],
});



}

onFileSelected(event: any) {
  this.fileURL1 = event.target.files[0];
  this.imageLink = URL.createObjectURL(this.fileURL1);
 
  console.log(this.fileURL1);
  console.log(this.imageLink,'image');
  

  }

handleAddressChange(address: any) {
  this.userDetail.controls['address'].setValue(address.formatted_address);
}
onClick1() {
 
  const formData = new FormData();
  formData.append('resume', this.fileURL1);
  formData.append('firstName',this.userDetail.value.firstName);
  formData.append('lastName',this.userDetail.value.lastName);
  formData.append('email',this.userDetail.value.email)
  formData.append('phoneNumber',this.userDetail.value.phoneNumber)
  formData.append('address',this.userDetail.value.address)
  formData.append('city',this.userDetail.value.city)
  formData.append('state',this.userDetail.value.state)
  formData.append('zip',this.userDetail.value.zip)
  formData.append('workExperience',this.userDetail.value.workExperience)
  formData.append('jobType',this.userDetail.value.jobType)
  formData.append('workRestrictions',this.userDetail.value.workRestrictions)
  formData.append('hoursRestrictions',this.userDetail.value.hoursRestrictions)
  formData.append('skills',this.userDetail.value.skills)
  formData.append('otherNotes',this.userDetail.value.otherNotes)
  formData.append('references[0].name',this.userDetail.value.references)
  formData.append('references[0].relationship',this.userDetail.value.referencesrel)
  formData.append('references[0].phoneNumber',this.userDetail.value.referencesnum)
  formData.append('references[1].name',this.userDetail.value.references1)
  formData.append('references[1].relationship',this.userDetail.value.referencesrel1)
  formData.append('references[1].phoneNumber',this.userDetail.value.referencesnum1)
  formData.append('references[2].name',this.userDetail.value.references2)
  formData.append('references[2].relationship',this.userDetail.value.referencesrel2)
  formData.append('references[2].phoneNumber',this.userDetail.value.referencesnum2)
  this.careerData.SaveUser(formData).subscribe(
    response => {
 
      console.log(response);
    },
    error => {

      console.error(error);
    }
  );
}





submit() {

  this.onClick1();
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

