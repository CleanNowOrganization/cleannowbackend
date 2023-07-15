package CleanNow01.CleanNow.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

import org.springframework.http.HttpStatus;

import CleanNow01.CleanNow.Models.Administrador;
import CleanNow01.CleanNow.Models.Consumidor;
import CleanNow01.CleanNow.Services.AdministradorService;
import CleanNow01.CleanNow.Services.ConsumidorService;

@RestController
@RequestMapping("/admin")
public class AdministradorController {
    
    @Autowired
    private AdministradorService administradorService;
    
    @Autowired  
    private ConsumidorService ConsumidoresService;

    @PostMapping
    public ResponseEntity<String> addAdmin (@RequestBody Administrador admin){
        try{
            FirebaseAuth firebaseInstance = FirebaseAuth.getInstance();

            CreateRequest request = new CreateRequest();
            request.setEmail(admin.getEmail());
            request.setPassword(admin.getPassword());

            firebaseInstance.createUser(request);

            UserRecord user = FirebaseAuth.getInstance().getUserByEmail(admin.getEmail());

            Administrador find_admin = administradorService.getAdminByDni(admin.getDni());
            if(find_admin == null){
                admin.setUid(user.getUid());
                admin.setCreatedAt(user.getUserMetadata().getCreationTimestamp());
                admin.setRole("admin");
                administradorService.createAdministrador(admin);
                return ResponseEntity.status(HttpStatus.OK).body("Administrador creado correctamente");
            } else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Administrador ya existe");
            }
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/consumidores")
    public ResponseEntity<List<Consumidor>> getConsumidores(@RequestHeader("Authorization") String token){
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
                List<Consumidor> consumidores = ConsumidoresService.getAllConsumidors();
                return ResponseEntity.status(HttpStatus.OK).body(consumidores);
            }
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
