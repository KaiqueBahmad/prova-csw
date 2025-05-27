import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  template: `
    <p>ola mundo</p>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {
  title = 'biblioteca';
}
