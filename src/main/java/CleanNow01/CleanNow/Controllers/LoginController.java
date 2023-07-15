package CleanNow01.CleanNow.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import CleanNow01.CleanNow.Models.Administrador;
import CleanNow01.CleanNow.Models.Consumidor;
import CleanNow01.CleanNow.Models.Request.LoginModel;
import CleanNow01.CleanNow.Services.AdministradorService;
import CleanNow01.CleanNow.Services.ConsumidorService;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    @Autowired  
    private ConsumidorService ConsumidoresService;

    @Autowired
    private AdministradorService AdministradorService;

    @PostMapping("/consumidores")
    public ResponseEntity<Consumidor> loginConsumidor(@RequestHeader("Authorization") String token ,@RequestBody LoginModel loginRequest) {
        try {
            // Dividir el encabezado de autorización en "Bearer" y el token
            String[] parts = token.split(" ");
            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                // El encabezado de autorización no es válido
                throw new IllegalArgumentException("Invalid Authorization header. Must be 'Bearer [token]'");
            }
            // Verificar el token de Firebase
            String tokenAuth = parts[1];
            // Verificar el token de Firebase
            FirebaseAuth firebaseInstance = FirebaseAuth.getInstance();
            FirebaseToken decodedToken = firebaseInstance.verifyIdToken(tokenAuth);

            String uid = decodedToken.getUid();
            System.out.println(uid);

            // System.out.println(decodedToken.getEmail());
            // Buscar el usuario en la base de datos
            Consumidor find_Consumidor = ConsumidoresService.findByUid(uid);
            // System.out.println(find_Consumidor.getEmail());
            if (find_Consumidor.getEmail().equals(decodedToken.getEmail())) {
                return ResponseEntity.status(HttpStatus.OK).body(find_Consumidor);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<Administrador> loginAdmin(@RequestHeader("Authorization") String token ,@RequestBody LoginModel loginRequest) {
            System.out.println("HOLA GENTE: " + token);
        try {
            // Dividir el encabezado de autorización en "Bearer" y el token
            String[] parts = token.split(" ");
            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                // El encabezado de autorización no es válido
                throw new IllegalArgumentException("Invalid Authorization header. Must be 'Bearer [token]'");
            }
            // Verificar el token de Firebase
            String tokenAuth = parts[1];
            System.out.println(tokenAuth);
            // Verificar el token de Firebase
            FirebaseAuth firebaseInstance = FirebaseAuth.getInstance();
            FirebaseToken decodedToken = firebaseInstance.verifyIdToken(tokenAuth);

            String uid = decodedToken.getUid();
            System.out.println(uid);

            // Buscar el usuario en la base de datos
            Administrador find_admin = AdministradorService.findByUid(uid);

            if (find_admin.getEmail().equals(decodedToken.getEmail())) {
                return ResponseEntity.status(HttpStatus.OK).body(find_admin);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }    
}
