import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IntmodComponent } from './components/intmod/intmod.component';
import { HomeComponent } from './components/home/home.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { NewAddComponent } from './components/new-add/new-add.component';
const routes: Routes = [
  {path:'', component : HomeComponent},
  {path: 'about-us', component: AboutUsComponent},
  { path: 'interior-remodelling', component: IntmodComponent},
  { path: 'home', component: HomeComponent },
  {path: 'new-additions', component: NewAddComponent}
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }