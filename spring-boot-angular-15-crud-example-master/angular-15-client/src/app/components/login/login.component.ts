import { Component } from '@angular/core';

import { FormBuilder } from '@angular/forms';

import { Router, ActivatedRoute } from '@angular/router';

import { AuthService } from 'src/app/service/auth.service';




@Component({

    selector: 'app-login',
    templateUrl: './login.component.html',

    styleUrls: ['./login.component.css'

    ]

})

export class LoginComponent {


    constructor(private builder: FormBuilder, private service: AuthService, private router: Router) {




    }




    userdata: any;

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

                    console.log("Navigate to admin page");

                    this.router.navigate(['/admin']);
                   

                    // console.log("response",this.responsedata)

                }
                
               





            })






        }
        
        

    }

}