import { Component } from '@angular/core';
import {FormGroup, FormBuilder, FormControl, Form} from '@angular/forms'
import { AddUser } from 'src/app/model/add-user';
import { AddUserService } from 'src/app/service/add-user.service';
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent {
  userDetail!: FormGroup;
  userObj: AddUser = new AddUser();
  userList : AddUser[] = [];
  constructor(private formBuilder : FormBuilder, private adService : AddUserService) {}
ngOnInit():void {
  this.getAllUsers();
  this.userDetail = this.formBuilder.group({
    username : [''],
    email : [''],
    firstname : [''],
    lastname : [''],
    password : [''],
    role : ['']
    
  });
}
  addUser() {
     console.log(this.userDetail.value);
    this.userObj.id = this.userDetail.value.id;
    this.userObj.username = this.userDetail.value.username;
    this.userObj.email = this.userDetail.value.email;
    this.userObj.firstname = this.userDetail.value.firstname;
    this.userObj.lastname = this.userDetail.value.lastname;
    this.userObj.password = this.userDetail.value.password;
    this.userObj.role = this.userDetail.value.role;
     this.adService.AddUser(this.userObj).subscribe(res=>{
      console.log(res);
      this.getAllUsers();
     }, err=> {
      console.log(err)
     })
  }
  getAllUsers() {
    this.adService.getAllUsers().subscribe(res=> {
      this.userList=res;
    }, err=>{
      console.log("error");
    })
  }
  editUser (usr : AddUser) {
    this.userDetail.controls['id'].setValue(usr.id);
    this.userDetail.controls['username'].setValue(usr.username);
    this.userDetail.controls['email'].setValue(usr.email);
    this.userDetail.controls['firstname'].setValue(usr.firstname);
    this.userDetail.controls['lastname'].setValue(usr.lastname);
    this.userDetail.controls['password'].setValue(usr.password);
    this.userDetail.controls['role'].setValue(usr.role);
  }
  showTable = false;
  UpdateUser() {
    this.userObj.id = this.userDetail.value.id;
    this.userObj.username = this.userDetail.value.username;
    this.userObj.email = this.userDetail.value.email;
    this.userObj.firstname = this.userDetail.value.firstname;
    this.userObj.lastname = this.userDetail.value.lastname;
    this.userObj.password = this.userDetail.value.password;
    this.userObj.role = this.userDetail.value.role;
    this.adService.UpdateUser(this.userObj).subscribe(res=> {
      console.log(res);
      this.getAllUsers();
    },err=> {
      console.log(err);
    })
  }

}
