import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder, AbstractControl} from '@angular/forms'
import { MatDialog } from '@angular/material/dialog';
import { AddUser } from 'src/app/model/add-user';
import { AddUserService } from 'src/app/service/add-user.service';
import { DialogeComponent } from '../dialoge/dialoge.component';
import { Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent {
profileImage: any;
fileURL!: File;

errorMessage: string | null = null;
successMessage: string | null = null;
hidePassword = true;

togglePassword() {
  this.hidePassword = !this.hidePassword;
}
onFileSelected(event: any) {
  this.fileURL = event.target.files[0];
  this.profileImage = URL.createObjectURL(this.fileURL);
  

  }
  userDetail!: FormGroup ;
  userObj:AddUser = new AddUser();
  userList!:any[] ;


  constructor(private route:ActivatedRoute,private router:Router ,private formBuilder : FormBuilder, private adService : AddUserService, private dialog : MatDialog) { }
ngOnInit(): void {
  
  this.userDetail = this.formBuilder.group ({
    username : ['',Validators.required],
    email : ['',Validators.required],
    firstname : ['',Validators.required],
    lastname : ['',Validators.required],
    password : ['',Validators.required],
    role: ['',Validators.required],
    imageUrl:['']
    
  });
}
get getControl(): { [key: string]: AbstractControl; } {

  return this.userDetail.controls;

}
  addUser() {
    this.userDetail.markAllAsTouched();
    const user=JSON.parse(localStorage.getItem("user")!);
    if(!user.roles.includes('ROLE_ADMIN')){
      this.dialog.open(DialogeComponent, {
        data: {
          message: `You don't have the access`,
          showYesNoButtons: false
        }
      });
      return;
    }
   if(this.userDetail.valid) {
      this.userObj.username = this.userDetail.value?.username;
      this.userObj.email = this.userDetail.value?.email;
      this.userObj.firstname = this.userDetail.value?.firstname;
      this.userObj.lastname = this.userDetail.value?.lastname;
      this.userObj.password = this.userDetail.value?.password;
      this.userObj.role = [this.userDetail.value?.role];
      this.userObj.imageUrl= this.userDetail.value?.imageUrl;
      this.adService.AddUser(this.userObj).subscribe(res=>{
        this.onClick();
          this.successMessage = res?.message;

          setTimeout(() => {
            this.router.navigate(['../showusers'],{relativeTo:this.route});
            this.successMessage = '';
            
          }, 3000);
    
            },err=> {
              this.errorMessage= `You don't have the access`;
              setTimeout(() => {
                this.errorMessage = '';
              }, 3000);
            });
    }
}
  onClick() {
 
    const formData = new FormData();
    formData.append('profileImage', this.fileURL);
  
    this.adService.saveImage(formData,this.userObj.username).subscribe(
      response => {
      },
      error => {
      }
    );
  }

  showTable = false;
  openDialog(): void {
    const dialogRef = this.dialog.open(DialogeComponent, {
      data: {
        message: `${this.userObj.username} has been Submitted Successfully`,
        showYesNoButtons: false,
        customButton: { label: 'Ok', action: 'custom-action' }
      }
  
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  
   
  }
}
