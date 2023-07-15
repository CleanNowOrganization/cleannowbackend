package CleanNow01.CleanNow.Controllers;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

import CleanNow01.CleanNow.Models.Consumidor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import CleanNow01.CleanNow.Services.ConsumidorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/consumidores")
public class ConsumidoresController {
    @Autowired  
    private ConsumidorService ConsumidoresService;

    // Agregar
    @PostMapping
    public ResponseEntity<String> addConsumidor(@RequestBody Consumidor consumidor) {
        try{
            FirebaseAuth firebaseInstance = FirebaseAuth.getInstance();

            CreateRequest request = new CreateRequest();
            request.setEmail(consumidor.getEmail());
            request.setPassword(consumidor.getPassword());

            firebaseInstance.createUser(request);

            UserRecord user = FirebaseAuth.getInstance().getUserByEmail(consumidor.getEmail());

            Consumidor find_Consumidor = ConsumidoresService.getConsumidorByDni(consumidor.getDni());
            if( find_Consumidor == null){
                consumidor.setUid(user.getUid());
                consumidor.setActive(true);
                consumidor.setDeleted(false);
                consumidor.setCreatedAt(user.getUserMetadata().getCreationTimestamp());
                consumidor.setUpdatedAt(user.getUserMetadata().getCreationTimestamp());
                int strength = 10;
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
                String encodedPassword = bCryptPasswordEncoder.encode(consumidor.getPassword());
                consumidor.setPassword(encodedPassword);
                ConsumidoresService.createConsumidor(consumidor);
                return ResponseEntity.status(HttpStatus.OK).body("Consumidor creado correctamente");
            } else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Consumidor ya existe");
            }
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Listar
    // @GetMapping
    // public ResponseEntity<List<Consumidor>> getConsumidores(){
    //     try{
    //         List<Consumidor> consumidores = ConsumidoresService.getAllConsumidors();
    //         return ResponseEntity.status(HttpStatus.OK).body(consumidores);
    //     } catch(Exception e){
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }

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
