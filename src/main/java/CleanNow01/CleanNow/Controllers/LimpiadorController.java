package CleanNow01.CleanNow.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CleanNow01.CleanNow.Models.Limpiador;
import CleanNow01.CleanNow.Services.LimpiadorService;

import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/Limpiador")
public class LimpiadorController {
    @Autowired
    private LimpiadorService LimpiadorService;

    // Agregar
    @PostMapping
    public ResponseEntity<String> addLimpiador(@RequestBody Limpiador professional) {
        try{
            Limpiador find_Limpiador = LimpiadorService.getLimpiadorByDni(professional.getDni());
            if( find_Limpiador == null){
                LimpiadorService.createLimpiador(professional);
                return ResponseEntity.status(HttpStatus.OK).body("Limpiador creado correctamente");
            } else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Limpiador ya existe");
            }
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Listar
    @GetMapping
    public ResponseEntity<List<Limpiador>> getLimpiadors() {
        try {
            List<Limpiador> Limpiadors = LimpiadorService.getAllLimpiadors();
            return ResponseEntity.status(HttpStatus.OK).body(Limpiadors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener por dni
    @GetMapping("/{dni}")
    public ResponseEntity<Limpiador> getLimpiadorByDni(@PathVariable int dni) {
        try {
            Limpiador find_Limpiador = LimpiadorService.getLimpiadorByDni(dni);
            if (find_Limpiador != null) {
                return ResponseEntity.status(HttpStatus.OK).body(find_Limpiador);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar
    @PutMapping("/actualizar")
    public ResponseEntity<String> updateLimpiador(@RequestBody Limpiador professional) {
        try {
            LimpiadorService.updateLimpiador(professional.getDni(), professional);
            return ResponseEntity.status(HttpStatus.OK).body("Limpiador actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<String> deleteFisicalLimpiador(@PathVariable int dni) {
        try{
            LimpiadorService.deleteLimpiador(dni);
            return ResponseEntity.status(HttpStatus.OK).body("Limpiador eliminado correctamente");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}