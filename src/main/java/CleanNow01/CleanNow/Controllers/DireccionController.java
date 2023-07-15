package CleanNow01.CleanNow.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import CleanNow01.CleanNow.Models.Direccion;
import CleanNow01.CleanNow.Services.DireccionService;

@RestController
@RequestMapping("/consumidores/direccion")
public class DireccionController {
    
    @Autowired
    private DireccionService direccionService;

    @GetMapping("/{dni}")
    public ResponseEntity<Direccion> getDireccionById(@PathVariable int dni, @RequestHeader("Authorization") String token){
        try{
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

            if(decodedToken.equals(null)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            } else{
                Direccion direccion = direccionService.getDireccionById(dni);
                return ResponseEntity.status(HttpStatus.OK).body(direccion);
            }
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{dni}")
    public Direccion updateDireccion(@PathVariable int dni, Direccion direccion){
        return direccionService.updatDireccion(dni, direccion);
    }

    @DeleteMapping("/{dni}")
    public Direccion deleteDireccion(@PathVariable int dni){
        return direccionService.deleteDireccion(dni);
    }

}
