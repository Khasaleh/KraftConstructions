import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IntmodComponent } from './components/intmod/intmod.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { TestimonialsComponent } from './components/testimonials/testimonials.component';
import { NewAddComponent } from './components/new-add/new-add.component';
import { CareersComponent } from './components/careers/careers.component';
import { RequestEstimateComponent } from './components/request-estimate/request-estimate.component';
import { ContactUsComponent } from './components/contact-us/contact-us.component';
import { LoginComponent } from './components/login/login.component';

import { AddUserComponent } from './components/add-user/add-user.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { AdminHeaderComponent } from './components/admin-header/admin-header.component';
import { AdminSidenavComponent } from './components/admin-sidenav/admin-sidenav.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AdminMainComponent } from './components/admin-main/admin-main.component';
import { AdminAboutUsComponent } from './components/admin-about-us/admin-about-us.component';
import { AdminContactUsComponent } from './components/admin-contact-us/admin-contact-us.component';


@NgModule({
  declarations: [
    AppComponent,
    ContactUsComponent,
    IntmodComponent,
    AboutUsComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    AboutUsComponent,
    TestimonialsComponent,
    NewAddComponent,
    RequestEstimateComponent,
    ContactUsComponent,
    LoginComponent,
    CareersComponent,
    AdminSidenavComponent,
    AdminMainComponent,
    AdminContactUsComponent,
    AddUserComponent,
    AdminHomeComponent,
    AdminHeaderComponent,
    AdminSidenavComponent,
    AdminMainComponent,
    AdminAboutUsComponent,



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
    
 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
