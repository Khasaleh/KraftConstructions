import { Component } from '@angular/core';
import { ContactUsService } from 'src/app/service/contact.us.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Address } from 'src/app/model/address';
import { DialogeComponent } from '../dialoge/dialoge.component';
import { MatDialog } from '@angular/material/dialog';
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-admin-contact-us',
  templateUrl: './admin-contact-us.component.html',
  styleUrls: ['./admin-contact-us.component.css',
  '../../../styles.css'
]
})
export class AdminContactUsComponent {
  userdata!:any[];
  formattedDateTime!:string | null;
  userDetail!:FormGroup;
  userDetails!:FormGroup;
  data!:any;
  successMessage: string | null = null;
  errorMessage:string | null= null;
  successMessage1: string | null = null;
  errorMessage1:string | null= null;
  constructor(private datePipe : DatePipe ,private conus:ContactUsService,private formbuilder: FormBuilder, private dialog : MatDialog ) {}
  
  ngOnInit():void {
    this.getAll();
    // const createdDate = this.data.createdDate;
    // this.formattedDateTime = this.datePipe.transform(createdDate, 'yyyy-MM-dd HH:mm:ss');  

    
    this.userDetail = this.formbuilder.group ({ 
      id:[''],
      firstname : [''],
      lastname : [''],
      email : [''],
      phonenumber: [''],
      message:[''],
      date:[''],
      time:['']
    })
    this.userDetails = this.formbuilder.group ({ 
     address:['']
    })
    this.conus.getContactAll().subscribe(
      previousValue => {
        this.userDetails.controls['address'].setValue(previousValue.address);

  })


//     let currentDate = new Date();
//     let day = currentDate.getDate();
//     let month = currentDate.getMonth() + 1;
//     let year = currentDate.getFullYear();
//     let formattedDate = day + "-" + month + "-" + year;

//     this.userDetail.patchValue({
//       date: formattedDate
//     });

//   const currentTime = new Date();


// let hours = currentTime.getHours();
// let minutes = currentTime.getMinutes();


// const ampm = hours >= 12 ? 'pm' : 'am';


// hours = hours % 12;
// hours = hours ? hours : 12;


// let minute = minutes < 10 ? '0' + minutes : minutes;

// // Format the time string
// const formattedTime = hours + ':' + minute + ' ' + ampm;

// this.userDetail.patchValue({
//   time: formattedTime
// });
}
  
        
  getAll() {
    // const user=JSON.parse(localStorage.getItem("user")!);
    // if(!user.roles.includes('ROLE_ADMIN')){
    //   const dialogRef = this.dialog.open(DialogeComponent, {
    //     data: {
    //       message: `You don't have the access`,
    //       showYesNoButtons: false
    //     }
    //   });
    // }
    // else if (user.roles.includes('ROLE_ADMIN')){
    this.conus.getAll().subscribe((res) => {
      console.log(res);
      this.userdata = res;
  })
}

  

  deleteUser(id: number) {
    const user=JSON.parse(localStorage.getItem("user")!);
    if(!user.roles.includes('ROLE_ADMIN')){
      const dialogRef = this.dialog.open(DialogeComponent, {
        data: {
          message: `You don't have the access`,
          showYesNoButtons: false
        }
      });
    }
    else if(user.roles.includes('ROLE_ADMIN')){
    const dialogRef = this.dialog.open(DialogeComponent, {
      data: {
        message: `Do You want to delete ${id}?`,
        showYesNoButtons: true,
        id:id
      }
    });
  
    dialogRef.afterClosed().subscribe(result => {
   
      if (result === true) 
     
      {
        this.conus.deleteUser(id)
          .subscribe(res => {
          
              this.successMessage = res?.message;
      
              setTimeout(() => {
                this.getAll();
                this.successMessage = '';
              }, 3000);
              
                  
                  
                 
                },err=> {
                  this.errorMessage= err?.message
                  setTimeout(() => {
                    this.errorMessage = '';
                  }, 3000);
                });
         
      }
    });
  }
}
  edit(data:Address) {
    const user=JSON.parse(localStorage.getItem("user")!);
    if(!user.roles.includes('ROLE_ADMIN')){
      const dialogRef = this.dialog.open(DialogeComponent, {
        data: {
          message: `You don't have the access`,
          showYesNoButtons: false
        }
      });
    }
    else if(user.roles.includes('ROLE_ADMIN')){
  this.conus.updateContact(data).subscribe(res=> {
    this.successMessage1 = res.message;
    setTimeout(() => {
      this.successMessage1 = '';
    }, 1000);
    console.log(res);
    this.getAll();
    
  })
}
}

}