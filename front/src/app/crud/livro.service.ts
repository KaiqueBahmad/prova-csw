import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


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

  constructor(private http: HttpClient) { }



  public createLivro(livro:Livro): Observable<Livro> {
    return this.http.post("http://localhost:8080/api/livro", livro)
  }
}
