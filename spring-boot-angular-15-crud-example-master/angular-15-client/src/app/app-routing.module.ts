import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IntmodComponent } from './components/intmod/intmod.component';
import { HomeComponent } from './components/home/home.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
const routes: Routes = [
  {path: 'about-us', component: AboutUsComponent},
  { path: 'interior-remodelling', component: IntmodComponent},
  { path: 'home', component: HomeComponent }
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }