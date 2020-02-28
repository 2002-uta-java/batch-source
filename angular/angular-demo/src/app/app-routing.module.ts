import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClickerComponent } from './components/clicker/clicker.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { DirectivesComponent } from './components/directives/directives.component';

// need path and component pair
const routes: Routes = [{
  path: 'clicker',
  component: ClickerComponent
}, {
  path: 'sDirect',
  component: SDirectivesComponent,
  children: [{
    path: 'attribute',
    component: ADirectivesComponent
  }, {
    path: 'directives',
    component: DirectivesComponent
  }]
}, {
  path: 'pipe',
  component: PipeDemoComponent
}, {
  path: 'aDirect',
  component: ADirectivesComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
