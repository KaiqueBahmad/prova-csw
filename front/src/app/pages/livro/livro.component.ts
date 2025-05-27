import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { DatePicker } from 'primeng/datepicker';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-livro',
  imports: [ReactiveFormsModule, DatePicker, InputTextModule, ButtonModule, ToastModule],
  template: `
    <p-toast></p-toast>
    <div style="width: 100%; display: flex;">
      <div style="flex: 1; display: flex; align-items: center; flex-direction: column; gap: 25px;">
        <h1>Cadastre um novo livro 📖 </h1>  
        <div style="text-align: center;">
            <p>Titulo </p>
            <input type="text" pInputText/>
        </div>
        <div style="text-align: center;">
            <p>Autor</p>
            <input type="text" pInputText/>
        </div>
        <div style="text-align: center;">
            <p>Editora</p>
            <input type="text" pInputText/>
        </div>
        <div style="text-align: center;">
            <p>Ano de Publicacao</p>
            <p-date-picker dateFormat="yy" view="year" placeholder="2025">
      
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
  constructor(private messageService: MessageService) {

  }

  cadastrarLivro() {
    this.messageService.add({
      severity: 'success',
      summary: 'Salvo',
      detail: 'Novo Livro foi registrado com sucesso!',
      life: 3000
    });
  }


}
