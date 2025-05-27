import { Routes } from '@angular/router';
import { LivroComponent } from './pages/livro/livro.component';
import { UsuarioComponent } from './pages/usuario/usuario.component';
import { EmprestimoComponent } from './pages/emprestimo/emprestimo.component';
import { HomeComponent } from './pages/home/home.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'livro',
        component: LivroComponent
    },
    {
        path: 'usuario',
        component: UsuarioComponent
    },
    {
        path: 'emprestimo',
        component: EmprestimoComponent
    }
];
