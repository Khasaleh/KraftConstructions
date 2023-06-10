import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
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
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ColorPickerModule } from 'ngx-color-picker';
import { AdminMainComponent } from './components/admin-main/admin-main.component';
import { AdminAboutUsComponent } from './components/admin-about-us/admin-about-us.component';
import { AdminContactUsComponent } from './components/admin-contact-us/admin-contact-us.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminTestimonialComponent } from './components/admin-testimonial/admin-testimonial.component';
import { AdminCareersComponent } from './components/admin-careers/admin-careers.component';
import { AdminServicesComponent } from './components/admin-services/admin-services.component';
import { PaymentTabComponent } from './components/payment-tab/payment-tab.component';
import { AdminRequestComponent } from './components/admin-request/admin-request.component';
import { MatMenuContent, MatMenuModule } from '@angular/material/menu';
import { ShowusersComponent } from './components/showusers/showusers.component';
import { AdminServicePageComponent } from './components/admin-service-page/admin-service-page.component';
import { MatButtonModule } from '@angular/material/button';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { AdminAddCareersNewsComponent } from './components/admin-add-careers-news/admin-add-careers-news.component';
import { AuthInterceptor } from './auth.interceptor';
import { MatCommonModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatListModule } from '@angular/material/list';
import {  MatDialogModule } from '@angular/material/dialog';
import { DialogeComponent } from './components/dialoge/dialoge.component';
import { GooglePlaceModule } from 'ngx-google-places-autocomplete';




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
    AdminTestimonialComponent,
    AdminAboutUsComponent,
    AdminCareersComponent,
    AdminDashboardComponent,
    AdminDashboardComponent,
    AdminServicesComponent,
    PaymentTabComponent,
    AdminRequestComponent,
    PaymentTabComponent,
    ShowusersComponent,
    AdminServicePageComponent,
    AdminAddCareersNewsComponent,
    DialogeComponent
    
   

  ],
  imports: [
    NoopAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    CKEditorModule,
    BrowserAnimationsModule,
    ColorPickerModule,
    BrowserAnimationsModule,
    MatCommonModule,
    MatFormFieldModule,
    MatRadioModule,
    MatInputModule,
    MatSelectModule,
    MatListModule,
    MatDialogModule,
    GooglePlaceModule

    
    
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }