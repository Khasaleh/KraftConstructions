import { Component } from '@angular/core';
import { AddUserService } from 'src/app/service/add-user.service';
import { AddUser } from 'src/app/model/add-user';
import { FormGroup, FormBuilder } from '@angular/forms';
import {User} from 'src/app/model/user'
import { Router } from '@angular/router';
@Component({
  selector: 'app-showusers',
  templateUrl: './showusers.component.html',
  styleUrls: ['./showusers.component.css']
})
export class ShowusersComponent {
  userdata!:any[];
  userDetail!:FormGroup;
  userObj : AddUser = new AddUser();
  oldUser!: string;
  
  
  constructor(private adduserdata : AddUserService, private formBuilder: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.getAllUsers();

    this.userDetail = this.formBuilder.group ({ 
      username : [''],
      email : [''],
      firstname : [''],
      lastname : [''],
      password : [''],
      role: ['']
    });
  
    
  }
  getAllUsers() {
  this.adduserdata.getAllUsers().subscribe((result)=>{
    console.log(result);
    this.userdata=result;
   
  });
}
deleteUser(username: string) {
  this.adduserdata.deleteUser(username).subscribe(
    () => {
      this.userdata = this.userdata.filter(u => u.username !== username);
      this.getAllUsers();
    },
    err => {
      console.log(err);
    
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
    role: this.userDetail.value.role

  };
    console.log(updatedUser);

  
    this.adduserdata.updateUser(updatedUser,this.oldUser).subscribe((result)=>{
      console.log(result);
   
      this.getAllUsers();
    },err=>{
      console.log(err);
    });
  

  }

 
  
}
  

