import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { MenuItem, MessageService } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MenubarModule],
  template: `
    <div style="width: 100vw; height: 100vh; display: flex; flex-direction: column;">
      <div style="padding: 10px;">
        <p-menubar [model]="items"  />
      </div>
      <router-outlet style="flex: 0;"></router-outlet>
      <!-- <div style="width: 100%; height: 100%;"> -->
      <!-- </div> -->
    </div>
  `
})
export class AppComponent implements OnInit {
  title = "biblioteca"
  items: MenuItem[] | undefined;

  constructor(private router: Router) { }

  ngOnInit() {
    this.items = [
      {
        label: 'Home',
        icon: 'pi pi-home',
        command: () => {
          this.router.navigate(["/"]);
        }
      },
      {
        label: 'Livros',
        icon: 'pi pi-book',
        command: () => {
          this.router.navigate(["livro"])
        }

      },
      {
        label: 'Usuarios',
        icon: 'pi pi-user'
      },
      {
        label: 'Empréstimos',
        icon: 'pi pi-tags'
      }
    ]
  }
}
