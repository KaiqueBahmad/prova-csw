import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, tap } from 'rxjs';


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

  private livroSubject: Subject<Livro[]> = new Subject<Livro[]>();
  public livro$: Observable<Livro[]> = this.livroSubject.asObservable();
  
  public refresh() {
    this.http.get<Livro[]>("http://localhost:8080/api/livro").subscribe(
      (income_livros) => {
        this.livroSubject.next(income_livros);
      }
    );
  }

  public createLivro(livro:Livro): Observable<Livro> {
    return this.http.post("http://localhost:8080/api/livro", livro);
  }

  public udpateLivro(livro:Livro): Observable<Livro> {
    return this.http.post("http://localhost:8080/api/livro/"+livro.id, livro);
  }


  public deletarLivro(id: number): Observable<string> {
    return this.http.put<string>("http://localhost:8080/api/livro/delete/"+id, {});
  }
}
