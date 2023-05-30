import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { RespuestaIniciarSesion } from 'src/app/shared/model/auth/respuesta.iniciar.sesion';
import { AutenticacionModule } from '../autenticacion.module';
@Component({
  selector: 'qp-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router) {}

  username: string = '';
  password: string = '';

  iniciarSesion(): void {
    console.log(this.username)
    console.log(this.password)
    this.authService.login(this.username, this.password).subscribe(
      () => {
        this.router.navigate(['/registro-vehiculos'])
      },
      (error) => {
        alert("Correo o contraseña invalido")
      }
    );
  }
}
