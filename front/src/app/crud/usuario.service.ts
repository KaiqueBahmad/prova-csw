import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, tap } from 'rxjs';


export interface Usuario {
  id?: number;
  nome?: string;
  matricula?: string;
  curso?: string;
}

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  private usuariosSubject: Subject<Usuario[]> = new Subject<Usuario[]>();
  public usuarios$: Observable<Usuario[]> = this.usuariosSubject.asObservable();
  
  public refresh() {
    this.http.get<Usuario[]>("http://localhost:8080/api/usuario").subscribe(
      (income_livros) => {
        this.usuariosSubject.next(income_livros);
      }
    );
  }

  public createUsuario(Usuario:Usuario): Observable<Usuario> {
    return this.http.post("http://localhost:8080/api/usuario", Usuario);
  }

  public udpateUsuario(Usuario:Usuario): Observable<Usuario> {
    return this.http.post("http://localhost:8080/api/usuario/"+Usuario.id, Usuario);
  }


  public deletarUsuario(id: number): Observable<string> {
    return this.http.put<string>("http://localhost:8080/api/usuario/delete/"+id, {});
  }
}
