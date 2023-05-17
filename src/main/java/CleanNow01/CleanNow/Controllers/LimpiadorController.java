package CleanNow01.CleanNow.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CleanNow01.CleanNow.Models.Limpiador;
import CleanNow01.CleanNow.Services.LimpiadorService;
import io.micrometer.common.lang.Nullable;

import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/Limpiador")
public class LimpiadorController {
    @Autowired
    private LimpiadorService LimpiadorService;

    // Agregar
    @PostMapping("/add")
    public ResponseEntity<String> addLimpiador(@RequestBody Limpiador professional) {
        try{
            Limpiador find_Limpiador = LimpiadorService.getProfessionalByDni(professional.getDni());
            if( find_Limpiador != null){
                LimpiadorService.addLimpiador(professional);
                return ResponseEntity.ok().body(find_Limpiador.getName());
            } else{
                return ResponseEntity.badRequest().body(find_Limpiador.getName());
            }
        } catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // Listar
    @GetMapping("/list")
    public List<Limpiador> getLimpiadors() {
        return LimpiadorService.getLimpiadors();
    }

    // Obtener por nombre
    @GetMapping("/{dni}")
    public Limpiador getLimpiadorByName(@PathVariable int dni) {
        return LimpiadorService.getProfessionalByDni(dni);
    }

    @PutMapping("/update")
    public Limpiador updateLimpiador(@RequestBody Limpiador professional) {
        return LimpiadorService.updateLimpiador(professional);
    }

}