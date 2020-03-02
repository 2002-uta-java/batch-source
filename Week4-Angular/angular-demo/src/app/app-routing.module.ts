import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FirstComponent } from './components/first/first.component';
import { PipeDemoComponent } from './components/pipe-demo/pipe-demo.component';
import { DatabindComponent } from './components/databind/databind.component';
import { ClickerComponent } from './components/clicker/clicker.component';
import { DirectivesComponent } from './components/directives/directives.component';
import { ADirectivesComponent } from './components/a-directives/a-directives.component';
import { SDirectivesComponent } from './components/s-directives/s-directives.component';


const routes: Routes = [{
  path: 'first',
  component: FirstComponent
},{
  path: 'pipes',
  component: PipeDemoComponent
}, {
  path: 'databind',
  component: DatabindComponent
}, {
  path: 'clicker',
  component: ClickerComponent
},{
  path: 'directives',
  component: DirectivesComponent,
  children: [{
    path: 'attribute',
    component: ADirectivesComponent
  }, {
    path: 'structural',
    component: SDirectivesComponent
  }]
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
