import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IntmodComponent } from './components/intmod/intmod.component';
import { HomeComponent } from './components/home/home.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { TestimonialsComponent } from './components/testimonials/testimonials.component';
import { NewAddComponent } from './components/new-add/new-add.component';
import { RequestEstimateComponent } from './components/request-estimate/request-estimate.component';
const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'about-us', component: AboutUsComponent },
  { path: 'interior-remodelling', component: IntmodComponent },
  { path: 'home', component: HomeComponent },
  { path: 'testimonials', component: TestimonialsComponent },
  { path: 'new-additions', component: NewAddComponent }
  {path: 'new-additions', component: NewAddComponent},
  {path:'request-estimate', component: RequestEstimateComponent}
  {path: 'contact-us', component: ContactUsComponent}

];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }