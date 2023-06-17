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
  constructor(private requser:ReqUserService,private formbuilder: FormBuilder, private dialog : MatDialog ) {}
  ngOnInit():void {
   
    this.getAll();
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

  
show = false;
ahide=true;
content() {
  this.show = true;
  this.ahide = false;

}
getAll() {
  this.requser.getAll().subscribe((res)=> {
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
      this.requser.deleteUser(id)
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
  this.requser.getUserbyId(this.userIdtoView)
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

