import { Component, OnInit } from '@angular/core';
import { AddUserService } from 'src/app/service/add-user.service';
import { AddUser } from 'src/app/model/add-user';
import { FormGroup, FormBuilder } from '@angular/forms';
import {User} from 'src/app/model/user'
import { Router } from '@angular/router';
import { DialogeComponent } from '../dialoge/dialoge.component';
import { MatDialog } from '@angular/material/dialog';
@Component({
  selector: 'app-showusers',
  templateUrl: './showusers.component.html',
  styleUrls: ['./showusers.component.css']
})
export class ShowusersComponent implements OnInit {
  globalUrl = 'https://img.kraftconstructionco.com'
  userdata!:any[];
  userDetail!:FormGroup;
  userObj : AddUser = new AddUser();
  oldUser!: string;
  data:any;
  imageLink: any;
  fileURL!: File;
  successMessage: string | null = null;
errorMessage: string | null = null;
  constructor(private adduserdata : AddUserService, private formBuilder: FormBuilder, private router: Router, private dialog : MatDialog) {}

  ngOnInit(): void {
    this.getAllUsers();

    this.userDetail = this.formBuilder.group ({ 
      username : [''],
      email : [''],
      firstname : [''],
      lastname : [''],
      password : [''],
      role: [''],
      imageUrl:['']
    });
  
    
  }
  getAllUsers() {
  this.adduserdata.getAllUsers().subscribe((result)=>{
    
    this.userdata = result;
  },
  error => {
    this.errorMessage=error?.message;
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  });
}
deleteUser(username: string) {
  const dialogRef = this.dialog.open(DialogeComponent, {
    data: {
      message: `Do You want to delete ${username}?`,
      showYesNoButtons: true
    }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result === true) {
      const userList= this.adduserdata.getAllUsers();
      
      this.adduserdata.deleteUser(username)
        .subscribe(res => {
          this.successMessage= res?.message;
          setTimeout(() => {
            this.successMessage = '';
          }, 3000);
          this.getAllUsers();
        
        }, err=>{
          this.errorMessage=err?.message;
          setTimeout(() => {
            this.errorMessage = '';
          }, 3000);
        }
        );
        
    }
   
  });
 
 
}
onFileSelected(event: any) {
  this.fileURL = event.target.files[0];
  this.imageLink = URL.createObjectURL(this.fileURL);
  
  

  }
  onClick() {
 
    const formData = new FormData();
    formData.append('profileImage', this.fileURL);
  
    this.adduserdata.saveImage(formData,this.userObj.username).subscribe(
      response => {
      }
    );
  }


editUser(user: User) {
  this.oldUser = user.username;
  this.userDetail.patchValue({
    username: user.username,
    email: user.email,
    firstname: user.firstname,
    lastname: user.lastname,
    password: '',
    role: user.roles && user.roles.length > 0 ? user.roles[0].name : null,
    imageUrl: user.imageUrl
  });
}
  updateUser() {
    
    const updatedUser: AddUser = {
    username: this.userDetail.value.username,
    email : this.userDetail.value.email,
    firstname : this.userDetail.value.firstname,
    lastname : this.userDetail.value.lastname,
    password : this.userDetail.value.password,
    role: [this.userDetail.value.role],
    imageUrl:this.userDetail.value.imageUrl

  };
    const dialogRef = this.dialog.open(DialogeComponent, {
      data: {
        message: `Do you want to Update ${this.userDetail.value.username}?`,
        showYesNoButtons: true
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
    this.adduserdata.updateUser(updatedUser,this.oldUser).subscribe(res=>{
      this.successMessage= res?.message;
      setTimeout(() => {
        this.successMessage = '';
      }, 3000);   
      this.getAllUsers();
    },err=>{
      this.errorMessage= err?.message;
      setTimeout(() => {
        this.errorMessage = '';
      }, 3000);
    });
  
  }

})
  }
  addUser() {
    
    const user=JSON.parse(localStorage.getItem("user")!);
    if(!user.roles.includes('ROLE_ADMIN')){
      const dialogRef = this.dialog.open(DialogeComponent, {
        data: {
          message: `You don't have the access`,
          showYesNoButtons: false
        }
      });
    }
    this.userObj.username = this.userDetail.value?.username;
    this.userObj.email = this.userDetail.value?.email;
    this.userObj.firstname = this.userDetail.value?.firstname;
    this.userObj.lastname = this.userDetail.value?.lastname;
    this.userObj.password = this.userDetail.value?.password;
    this.userObj.role = [this.userDetail.value?.role];
    this.userObj.imageUrl= this.userDetail.value?.imageUrl;
     this.adduserdata.AddUser(this.userObj).subscribe(res=>{
      this.onClick();
      this.successMessage= res?.message;
      setTimeout(() => {
        this.successMessage = '';
      }, 3000);
      this.getAllUsers();
    
      

     }, err=> {
      this.errorMessage= err?.message;
      setTimeout(() => {
        this.errorMessage = '';
      }, 1000);
     })
     
  }
}