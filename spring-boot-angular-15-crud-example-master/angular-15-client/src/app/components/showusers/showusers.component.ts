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
          this.getAllUsers();
        });
    }
  });
}



editUser(user: User) {
  this.oldUser = user.username;
  this.userDetail.patchValue({
    username: user.username,
    email: user.email,
    firstname: user.firstname,
    lastname: user.lastname,
    password: '',
    role: user.roles && user.roles.length > 0 ? user.roles[0].name : null
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
}
