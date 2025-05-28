import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { DatePicker, DatePickerYearChangeEvent } from 'primeng/datepicker';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { UsuarioService } from '../../crud/usuario.service';
import { CardModule } from 'primeng/card';
import { Usuario } from '../../crud/usuario.service';

@Component({
  selector: 'app-usuario',
  imports: [ReactiveFormsModule, FormsModule, DatePicker, InputTextModule, ButtonModule, ToastModule, CardModule],
  template: `
    <p-toast></p-toast>
    <div style="width: 100%; display: flex;">
      <div style="flex: 1; display: flex; align-items: center; flex-direction: column; gap: 25px;">
        <h1>Cadastre um novo usuario  👤</h1>
        @if(this.editandoId) {
          <p>Editando Usuario: {{this.editandoId}}</p>
          <p-button (onClick)="setSelected(undefined)">Cancelar</p-button>
        }

        <div style="text-align: center;">
            <p>Nome </p>
            <input type="text" pInputText [(ngModel)]="this.usuario.nome"/>
        </div>
        <div style="text-align: center;">
            <p>Matricula</p>
            <input type="text" pInputText [(ngModel)]="this.usuario.matricula"/>
        </div>
        <div style="text-align: center;">
            <p>Curso</p>
            <input type="text" pInputText [(ngModel)]="this.usuario.curso"/>
        </div>

        <p-button (onClick)="cadastrarUsuario()">
          Salvar
        </p-button>
      </div>
      
      <div style="flex: 1;">
        <h1>Usuarios cadastrados 👥</h1>
        <div style="display: flex; flex-direction: column; gap:10px;max-height: 60vh; overflow: auto;">
          @for (item of usuarios; track $index) {
            <p-card>
              <div style="display: flex; flex-direction: column;gap: 10px;">
                <div>
                  <p>
                    <b>Titulo: </b>
                    {{item.nome}}
                  </p>
                  <p>
                    <b>Autor: </b>{{item.curso}}
                  </p>
                  <p>
                    <b>Autor: </b>{{item.matricula}}
                  </p>
                </div>
                <div style="display: flex; gap:10px;">
                  <p-button icon="pi pi-pencil" severity="info" aria-label="Editar" (onClick)="setSelected(item.id)"/>
                  <p-button icon="pi pi-trash" severity="danger" aria-label="Deletar" (onClick)="deletarUsuario(item.id)" />
                </div>
              </div>
            </p-card>
          }
        </div>

      </div>


    </div>
    <!-- <p>
      {{stringfai(this.usuarios)}}
    </p> -->
  `
})
export class UsuarioComponent {
  constructor(private messageService: MessageService, private usuarioService: UsuarioService) {
    usuarioService.refresh();
    this.usuarioService.usuarios$.subscribe({
      next: (income) => {
        this.usuarios = income;
      }
    });
  } 

  stringfai(a:any) {
    return JSON.stringify(a);
  }
  editandoId?: number = undefined;
  usuarios:Usuario[] = [];
  usuario:Usuario = {};
  anoPublicacao?: Date;

  faltando(isso: string) {
    this.messageService.add({
      severity: 'warn',
      summary: 'Atenção',
      detail: 'Preencha o campo '+isso,
      life: 10000
    });
  }

  setSelected(id?: number) {
    this.editandoId = id;
    if (!this.editandoId) {
      this.usuario = {}
    }
    this.usuarios.forEach(
      (usuario) => {
        if (usuario.id == this.editandoId) {
          this.usuario = JSON.parse(JSON.stringify(usuario));
        }
      }
    );
  }

  cadastrarUsuario() {   
    if (!this.usuario.nome) {
      this.faltando("Nome");
      return;
    }
    if (!this.usuario.curso) {
      this.faltando("Curso");
      return;
    }
    
    if (!this.usuario.matricula) {
      this.faltando("Matricula");
      return;  
    }
    
    if (this.editandoId) {
      this.usuarioService.udpateUsuario(this.usuario).subscribe({
        next: (created) => {
          this.messageService.add({
            severity: 'success',
            summary: 'Salvo',
            detail: 'usuario foi registrado com sucesso! ID: '+created.id,
            life: 3000
          });
          this.usuarioService.refresh();
        },
        error: (error) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erro',
            detail: 'Houve um erro ao salvar o usuario '+JSON.stringify(error.error.trace),
            life: 10000
          });
        }
      });
    } else {
      this.usuarioService.createUsuario(this.usuario).subscribe({
        next: (created) => {
          this.messageService.add({
            severity: 'success',
            summary: 'Salvo',
            detail: 'usuario foi registrado com sucesso! ID: '+created.id,
            life: 3000
          });
          this.usuarioService.refresh();
        },
        error: (error) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Erro',
            detail: 'Houve um erro ao salvar o usuario '+JSON.stringify(error.error.trace),
            life: 10000
          });
        }
      });
    }
  
  }

  deletarUsuario(id: number|undefined) {
    if (!id) {
      return
    }
    if (!confirm("Realmente deseja deletar? ")) {
      return;
    }
    
    this.usuarioService.deletarUsuario(id).subscribe({
      next: (x) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Salvo',
          detail: 'Registro deletado com sucesso',
          life: 3000
        });
        this.usuarioService.refresh();
      },
      error: (x) => {
      }
    });
  }
}
