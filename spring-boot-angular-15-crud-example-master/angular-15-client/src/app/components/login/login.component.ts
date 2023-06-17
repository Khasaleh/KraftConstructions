import { Component } from '@angular/core';

import { FormBuilder } from '@angular/forms';

import { Router, ActivatedRoute } from '@angular/router';

import { AuthService } from 'src/app/service/auth.service';
interface UserData {
    username:string;
    password:string;
    role:string|null;
}



@Component({

    selector: 'app-login',
    templateUrl: './login.component.html',

    styleUrls: ['./login.component.css'

    ]

})

   
  
  

  

export class LoginComponent {

hidePassword = true;
    constructor(private builder: FormBuilder, private service: AuthService, private router: Router) {




    }



  
    userdata: UserData= {username:'', password:'',role:null};

    responsedata: any;
  



    loginform = this.builder.group({

        username: this.builder.control(''),

        password: this.builder.control('')

    });

    ngOnInit(): void {


    }
 
      

    
    proceedlogin() {

        if (this.loginform.valid) {

            this.service.proceedlogin(this.loginform.value).subscribe(result => {

                if (result != null) {

                    this.responsedata = result;
                    localStorage.setItem('token', this.responsedata.token);
                    this.userdata.role = this.responsedata.role;
                  console.log(this.userdata.role);

                    this.router.navigate(['/admin']);
                   

                    // console.log("response",this.responsedata)

                }
                
               





            
          
            })






        }
        
        

    }

}