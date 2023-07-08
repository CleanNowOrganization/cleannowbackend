package CleanNow01.CleanNow.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CleanNow01.CleanNow.Models.Consumidor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import CleanNow01.CleanNow.Services.ConsumidorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/consumidores")
public class ConsumidoresController {
    @Autowired  
    private ConsumidorService ConsumidoresService;

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
}
