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
  globalUrl = 'http://99.72.32.144:8083';
  userdata!:any[];
  userDetail!:FormGroup;
  userObj : AddUser = new AddUser();
  oldUser!: string;
  data:any;
  imageLink: any;
  fileURL!: File;
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
    console.log(result);
    this.userdata=result;
   
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
      this.adduserdata.deleteUser(username)
        .subscribe(res => {
          // this.getAllUsers();
        });
    }
   
  });
  this.getAllUsers();
}
onFileSelected(event: any) {
  this.fileURL = event.target.files[0];
  this.imageLink = URL.createObjectURL(this.fileURL);
  // this.onClick();
  console.log(this.fileURL);
  console.log(this.imageLink);
  

  }
  onClick() {
 
    const formData = new FormData();
    formData.append('profileImage', this.fileURL);
  
    this.adduserdata.saveImage(formData,this.userObj.username).subscribe(
      response => {
        // Handle the API response here
        console.log(response);
      },
      error => {
        // Handle any error that occurs during the API request
        console.error(error);
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
    role: this.userDetail.value.role,
    imageUrl:this.userDetail.value.imageUrl

  };
    console.log(updatedUser);
    const dialogRef = this.dialog.open(DialogeComponent, {
      data: {
        message: `Do you want to Update ${this.userDetail.value.username}?`,
        showYesNoButtons: true
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
    this.adduserdata.updateUser(updatedUser,this.oldUser).subscribe(res=>{
      console.log(res);
   
      this.getAllUsers();
    },err=>{
      console.log(err);
    });
  

  }

})
  }
  addUser() {
    
    
    this.userObj.username = this.userDetail.value?.username;
    this.userObj.email = this.userDetail.value?.email;
    this.userObj.firstname = this.userDetail.value?.firstname;
    this.userObj.lastname = this.userDetail.value?.lastname;
    this.userObj.password = this.userDetail.value?.password;
    this.userObj.role = [this.userDetail.value?.role];
    this.userObj.imageUrl= this.userDetail.value?.imageUrl;
    console.log(this.userObj.role)
     this.adduserdata.AddUser(this.userObj).subscribe(res=>{
      this.onClick();
      console.log(res);
      
    
      

     }, err=> {
      console.log(err)
     })
  }
}
