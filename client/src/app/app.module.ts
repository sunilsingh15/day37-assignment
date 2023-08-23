import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ListordersComponent } from './components/listorders/listorders.component';
import { NeworderComponent } from './components/neworder/neworder.component';
import { ConfirmedorderComponent } from './components/confirmedorder/confirmedorder.component';

const appRoutes: Routes = [
  {path: '', component: ListordersComponent, title: 'All Orders'},
  {path: 'order', component: NeworderComponent, title: 'Make a New Order'},
  {path: 'order/:id', component: ConfirmedorderComponent},
  {path: '**', redirectTo: '/', pathMatch: 'prefix'}
]

@NgModule({
  declarations: [
    AppComponent,
    ListordersComponent,
    NeworderComponent,
    ConfirmedorderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
