import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './components/about-us/about-us.component';
const routes: Routes = [
  { path: '', redirectTo: 'tutorials', pathMatch: 'full',
}
// {
//   path: '/about-us', redirectTo :'about-us'
// }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }