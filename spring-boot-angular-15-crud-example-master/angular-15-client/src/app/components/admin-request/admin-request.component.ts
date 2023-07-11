import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup } from '@angular/forms';
import { ReqUser } from 'src/app/model/requsr';
import { ReqUserService } from 'src/app/service/Reqest.service';
import { MatDialog } from '@angular/material/dialog';
import { DialogeComponent } from '../dialoge/dialoge.component';

@Component({
  selector: 'app-admin-request',
  templateUrl: './admin-request.component.html',
  styleUrls: ['./admin-request.component.css']
})
export class AdminRequestComponent implements OnInit {
  userdata!:any[];
  userDetail!:FormGroup;
  userIdtoView!:number;
  successMessage: string | null = null;
  errorMessage: string | null = null;


  constructor(private requser:ReqUserService,private formbuilder: FormBuilder, private dialog : MatDialog ) {}
  ngOnInit():void {
   
    this.getAllUsers();
    this.userDetail = this.formbuilder.group ({ 
      id:[''],
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
      aboutUs: [''],
      
    })

  }

  
showTable = false;
hideTable=true;

content() {
  this.showTable = true;
  this.hideTable = false;

}
getAllUsers() {
  this.requser.getAll().subscribe((res)=> {
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
      this.requser.deleteUser(id)
        .subscribe(res => {
          this.successMessage = res?.message;
          setTimeout(() => {
            this.successMessage = '';
          }, 3000);
          this.getAllUsers();
        });
    }
  });
}

viewUser(id: number) {
  this.userIdtoView = id;
  this.requser.getUserbyId(this.userIdtoView)
    .subscribe({
      next: (res) => {
        this.showTable = true;
        this.hideTable = false;
        this.populateForm(res);
      },
      error: (err) => {
        this.errorMessage= err?.message;
      }
    });
}

populateForm(user: ReqUser) {
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
    requestedServices: user.requestedServices,
    budget: user.budget,
    projectDescription: user.projectDescription,
    aboutUs: user.aboutUs
  });
}


}

