import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DatePicker } from 'primeng/datepicker';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-livro',
  imports: [ReactiveFormsModule, DatePicker, InputTextModule, ButtonModule],
  template: `
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

        <p-button>
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

}
