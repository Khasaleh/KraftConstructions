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

    hidePassword = true;

    togglePassword() {
      this.hidePassword = !this.hidePassword;
    }
    constructor(private builder: FormBuilder, private service: AuthService, private router: Router) {}

    responsedata: any;
    errorMessage: string | null = null;
    successMessage: string | null = null;

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
                    localStorage.setItem('user',JSON.stringify(result))
                   
                    console.log(localStorage.getItem('user'));
                   
                    this.router.navigate(['/admin']);

                }
           
            }, err => {
                // Handle error response
                this.errorMessage = err?.error.details;
        
                setTimeout(() => {
                    this.errorMessage = '';
                }, 2000);
            }
            )

        }
    
    }
    }
