import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
// import { Databind } from './components/databind/databind.component';
import { ClickerComponent } from './components/clicker/clicker.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { FormsModule } from '@angular/forms';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { ConvertToSpacePipe } from './pipes/convert-to-space.pipe';
import { NavComponent } from './components/nav/nav.component';
import { DirectivesComponent } from './components/directives/directives.component';

@NgModule({
  declarations: [
    AppComponent,
    ClickerComponent,
    SDirectivesComponent,
    ADirectivesComponent,
    PipeDemoComponent,
    ConvertToSpacePipe,
    NavComponent,
    DirectivesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
