import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IntmodComponent } from './components/intmod/intmod.component';
import { HomeComponent } from './components/home/home.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { TestimonialsComponent } from './components/testimonials/testimonials.component';
import { NewAddComponent } from './components/new-add/new-add.component';
import { CareersComponent } from './components/careers/careers.component';
import { RequestEstimateComponent } from './components/request-estimate/request-estimate.component';
import { ContactUsComponent } from './components/contact-us/contact-us.component';
import { LoginComponent } from './components/login/login.component';
import { AddUserComponent } from './components/add-user/add-user.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component'
import { AdminMainComponent } from './components/admin-main/admin-main.component'

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'about-us', component: AboutUsComponent },
  { path: 'interior-remodelling', component: IntmodComponent },
  { path: 'home', component: HomeComponent },
  { path: 'testimonials', component: TestimonialsComponent },
  { path: 'new-additions', component: NewAddComponent },
  {path:'request-estimate', component: RequestEstimateComponent},
  {path: 'contact-us', component: ContactUsComponent},
  {path:'login', component: LoginComponent},
  { path: 'careers', component: CareersComponent },
  {path:'add-user',component:AddUserComponent},
  {
    path: 'admin',
    component: AdminMainComponent,
    children: [
      {path: '', component: AdminHomeComponent},
      { path: 'admin-home', component: AdminHomeComponent },
    ],
  },

];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }