import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { DatePicker, DatePickerYearChangeEvent } from 'primeng/datepicker';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { Livro, LivroService } from '../../crud/livro.service';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-livro',
  imports: [ReactiveFormsModule, FormsModule, DatePicker, InputTextModule, ButtonModule, ToastModule, CardModule],
  template: `
    <p-toast></p-toast>
    <div style="width: 100%; display: flex;">
      <div style="flex: 1; display: flex; align-items: center; flex-direction: column; gap: 25px;">
        <h1>Cadastre um novo livro 📖 </h1>  
        <div style="text-align: center;">
            <p>Titulo </p>
            <input type="text" pInputText [(ngModel)]="this.livro.titulo"/>
        </div>
        <div style="text-align: center;">
            <p>Autor</p>
            <input type="text" pInputText [(ngModel)]="this.livro.autor"/>
        </div>
        <div style="text-align: center;">
            <p>Editora</p>
            <input type="text" pInputText [(ngModel)]="this.livro.editora"/>
        </div>
        <div style="text-align: center;">
            <p>Ano de Publicacao</p>
            <p-date-picker dateFormat="yy" view="year" placeholder="2025" [(ngModel)]="anoPublicacao">
      
            </p-date-picker>
        </div>

        <p-button (onClick)="cadastrarLivro()">
          Salvar
        </p-button>
      </div>
      
      <div style="flex: 1;">
        <h1>Livros cadastrados 📚</h1>
        <div style="display: flex; flex-direction: column; gap:10px;max-height: 60vh; overflow: auto;">
          @for (item of livros; track $index) {
            <p-card>
              <div style="display: flex; flex-direction: column;gap: 10px;">
                <div>
                  <p>
                    <b>Titulo: </b>
                    {{item.titulo}}
                  </p>
                  <p>
                    <b>Autor: </b>{{item.autor}}
                  </p>
                  <p>
                    <b>Autor: </b>{{item.editora}}
                  </p>
                  <p>
                    <b>Ano Publicacao: </b>{{item.anoPublicacao}}
                  </p>
                </div>
                <div style="display: flex; gap:10px;">
                  <p-button icon="pi pi-pencil" severity="info" aria-label="Editar" />
                  <p-button icon="pi pi-trash" severity="danger" aria-label="Deletar" (onClick)="deletarLivro(item.id)" />
                </div>
              </div>
            </p-card>
          }
        </div>

      </div>


    </div>
    <!-- <p>
      {{stringfai(this.livros)}}
    </p> -->
  `
})
export class LivroComponent {
  constructor(private messageService: MessageService, private livroService: LivroService) {
    livroService.refresh();
    this.livroService.livro$.subscribe({
      next: (income) => {
        this.livros = income;
      }
    });
  } 

  stringfai(a:any) {
    return JSON.stringify(a);
  }

  livros:Livro[] = [];
  livro:Livro = {};
  anoPublicacao?: Date;

  faltando(isso: string) {
    this.messageService.add({
      severity: 'warn',
      summary: 'Atenção',
      detail: 'Preencha o campo '+isso,
      life: 10000
    });
  }

  cadastrarLivro() {
    this.livro.anoPublicacao = this.anoPublicacao? this.anoPublicacao.getFullYear() : undefined;
    
    if (!this.livro.titulo) {
      this.faltando("Titulo");
      return;
    }
    if (!this.livro.autor) {
      this.faltando("Autor");
      return;
    }
    
    if (!this.livro.editora) {
      this.faltando("Editora");
      return;  
    }
    
    if (!this.livro.anoPublicacao){
      this.faltando("Ano de publicação")
      return;
    }

    this.livroService.createLivro(this.livro).subscribe({
      next: (created) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Salvo',
          detail: 'Novo Livro foi registrado com sucesso! ID: '+created.id,
          life: 3000
        });
        this.livroService.refresh();
      },
      error: (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Erro',
          detail: 'Houve um erro ao salvar o livro '+JSON.stringify(error.error.trace),
          life: 10000
        });
      }
    });
  }

  deletarLivro(id: number|undefined) {
    if (!id) {
      return
    }
    if (!confirm("Realmente deseja deletar? ")) {
      return;
    }
    
    this.livroService.deletarLivro(id).subscribe({
      next: (x) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Salvo',
          detail: 'Registro deletado com sucesso',
          life: 3000
        });
        this.livroService.refresh();
      },
      error: (x) => {
      }
    });
  }
}
