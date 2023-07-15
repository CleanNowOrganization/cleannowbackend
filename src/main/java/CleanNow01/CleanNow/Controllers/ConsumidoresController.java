package CleanNow01.CleanNow.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import CleanNow01.CleanNow.Models.Consumidor;
import CleanNow01.CleanNow.Models.Request.LoginModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import CleanNow01.CleanNow.Services.ConsumidorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/consumidores")
public class ConsumidoresController {
    @Autowired  
    private ConsumidorService ConsumidoresService;

    @PostMapping("/auth/login")
    public ResponseEntity<Consumidor> login(@RequestHeader("Authorization") String token ,@RequestBody LoginModel loginModel) {
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
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(tokenAuth);
            String uid = decodedToken.getUid();
            // Buscar el usuario en la base de datos
            Consumidor find_Consumidor = ConsumidoresService.findByUid(uid);
            if (find_Consumidor != null) {
                return ResponseEntity.status(HttpStatus.OK).body(find_Consumidor);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    // Agregar
    @PostMapping
    public ResponseEntity<String> addConsumidor(@RequestBody Consumidor professional) {
        try{
            Consumidor find_Consumidor = ConsumidoresService.getConsumidorByDni(professional.getDni());
            if( find_Consumidor == null){
                ConsumidoresService.createConsumidor(professional);
                return ResponseEntity.status(HttpStatus.OK).body("Consumidor creado correctamente");
            } else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Consumidor ya existe");
            }
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

         // Listar
    @GetMapping
    public ResponseEntity<List<Consumidor>> getConsumidores(){
        try{
            List<Consumidor> consumidores = ConsumidoresService.getAllConsumidors();
            return ResponseEntity.status(HttpStatus.OK).body(consumidores);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener por dni
    @GetMapping("/{dni}")
    public ResponseEntity<Consumidor> getConsumidorByDni(@PathVariable int dni) {
        try {
            Consumidor find_Consumidor = ConsumidoresService.getConsumidorByDni(dni);
            if (find_Consumidor != null) {
                return ResponseEntity.status(HttpStatus.OK).body(find_Consumidor);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{dni}")
    public ResponseEntity<String> updateConsumidor(@PathVariable int dni, @RequestBody Consumidor consumidor) {
        try {
            Consumidor find_Consumidor = ConsumidoresService.getConsumidorByDni(dni);
            if (find_Consumidor != null) {
                ConsumidoresService.updateConsumidor(dni, consumidor);
                return ResponseEntity.status(HttpStatus.OK).body("Consumidor actualizado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consumidor no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{dni}/desactivar")
    public ResponseEntity<String> desactivarConsumidor(@PathVariable int dni) {
        try {
            Consumidor find_Consumidor = ConsumidoresService.getConsumidorByDni(dni);
            if (find_Consumidor != null) {
                ConsumidoresService.isNotActive(dni);
                return ResponseEntity.status(HttpStatus.OK).body("Consumidor desactivado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consumidor no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
