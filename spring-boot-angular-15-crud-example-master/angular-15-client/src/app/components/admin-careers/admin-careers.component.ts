import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { CareerDataService } from 'src/app/service/careerData.service';
import { DialogeComponent } from '../dialoge/dialoge.component';
import { CareerData } from 'src/app/model/career';
@Component({
  selector: 'app-admin-careers',
  templateUrl: './admin-careers.component.html',
  styleUrls: ['./admin-careers.component.css']
})
export class AdminCareersComponent {
  show = false;
  ahide=true;
  userDetail!: FormGroup;
  userdata!:any[];
  userIdtoView!:number;
  successMessage: string | null = null;
  data:any;
  globalUrl = 'http://99.72.32.144:8083';
  constructor(private formBuilder : FormBuilder, private dialog:MatDialog, private careerData: CareerDataService) {}
  ngOnInit(): void {
    this.getAll();
  this.userDetail = this.formBuilder.group ({
    id:[''],
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
    skills:[''],
    resumeUrl:[''],
    otherNotes:[''],
    references: [[]],
   
  
  
  
  
    
  });

  }
  content() {
    this.show = true;
    this.ahide = false;
  
  }
  downloadResume(id:number) {
  
    this.userIdtoView = id;
    this.careerData.getUserbyId(this.userIdtoView)
      .subscribe({
        next: (res) => {
          this.show = true;
          this.ahide = false;
          this.populateForm(res);
          
          const link = this.globalUrl+res.resumeUrl;
          window.open(link,'blank');
        },
        error: (err) => {
          console.log(err);
        }
      });

  
    
  }
  
  
  getAll() {
    this.careerData.getAll().subscribe((res)=> {
      console.log(res);
      this.userdata=res;
    });
  }
  deleteUser(id: number) {
    const dialogRef = this.dialog.open(DialogeComponent, {
      data: {
        message: `Do You want to delete ${id}?`,
        showYesNoButtons: true
      }
    });
  
    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.careerData.deleteUser(id)
          .subscribe(res => {
            this.successMessage = 'Data Deleted successfully.';
            setTimeout(() => {
              this.successMessage = '';
            }, 1000);
            this.getAll();
          
          });
      }
    });
    
  }
  
  viewUser(id: number) {
    this.userIdtoView = id;
    this.careerData.getUserbyId(this.userIdtoView)
      .subscribe({
        next: (res) => {
          this.show = true;
          this.ahide = false;
          this.populateForm(res);
        },
        error: (err) => {
          console.log(err);
        }
      });
  }
  
  populateForm(user: CareerData) {
    this.userDetail.patchValue({
      id: user.id,
      firstName: user.firstName,
      lastName: user.lastName,
      phoneNumber: user.phoneNumber,
      email: user.email,
      address: user.address,
      city: user.city,
      state: user.state,
      zip: user.zip,
      workExperience: user.workExperience,
      jobType: user.jobType,
      workRestrictions: user.workRestrictions,
      hoursRestrictions: user.hoursRestrictions,
      skills:user.skills,
      resumeUrl:user.resumeUrl,
      otherNotes:user.otherNotes,
      references:user.references
    });
  }
}
