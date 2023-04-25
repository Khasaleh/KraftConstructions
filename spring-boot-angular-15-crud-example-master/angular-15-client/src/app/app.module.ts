import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
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
import { LoginComponent } from './components/login/login.component'

@NgModule({
  declarations: [
    AppComponent,
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
    CareersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
