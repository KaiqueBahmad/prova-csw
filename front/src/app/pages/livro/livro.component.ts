import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { DatePicker } from 'primeng/datepicker';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { Livro, LivroService } from '../../crud/livro.service';

@Component({
  selector: 'app-livro',
  imports: [ReactiveFormsModule, FormsModule, DatePicker, InputTextModule, ButtonModule, ToastModule],
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
            <p-date-picker dateFormat="yy" view="year" placeholder="2025" (onSelect)="changeAnoPublicacao($event)">
      
            </p-date-picker>
        </div>

        <p-button (onClick)="cadastrarLivro()">
          Salvar
        </p-button>
      </div>
      
      <div style="flex: 1;">
        <h1>Livros cadastrados 📚</h1>
      </div>

    </div>
  `
})
export class LivroComponent {
  constructor(private messageService: MessageService, private livroService: LivroService) {

  }

  livro:Livro = {};

  faltando(isso: string) {
    this.messageService.add({
      severity: 'warn',
      summary: 'Atenção',
      detail: 'Preencha o campo '+isso,
      life: 10000
    });
  }

  cadastrarLivro() {
    if (!this.livro.anoPublicacao){
      this.faltando("Ano de publicação")
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

    if (!this.livro.titulo) {
      this.faltando("Titulo");
      return;
    }

    this.livroService.createLivro(this.livro).subscribe({
      next: (created) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Salvo',
          detail: 'Novo Livro foi registrado com sucesso! '+JSON.stringify(created),
          life: 3000
        });
      },
      error: (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Erro',
          detail: 'Houve um erro ao salvar o livro '+JSON.stringify(error.error.trace),
          life: 10000
        });
      },
    });
  }

  changeAnoPublicacao(date: Date) {
    if (!date.getFullYear()) {
      this.livro.anoPublicacao = undefined;
      return;
    }
    this.livro.anoPublicacao = date.getFullYear();
  }

}
