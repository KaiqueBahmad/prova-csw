import { Injectable } from '@angular/core';


export interface Livro {
  id?: number;
  titulo?: string;
  autor?: string;
  editora?: string;
  anoPublicacao?: number;
}

@Injectable({
  providedIn: 'root'
})
export class LivroService {

  constructor() { }
}
