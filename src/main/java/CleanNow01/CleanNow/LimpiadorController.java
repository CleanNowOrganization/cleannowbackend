package CleanNow01.CleanNow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/Limpiador")
public class LimpiadorController {
    @Autowired
    private LimpiadorService LimpiadorService;

    // Agregar
    @PostMapping("/add")
    public Limpiador addLimpiador(@RequestBody Limpiador professional) {
        return LimpiadorService.addLimpiador(professional);
    }

    // Listar
    @GetMapping("/list")
    public List<Limpiador> getLimpiadors() {
        return LimpiadorService.getLimpiadors();
    }

    // Obtener por nombre
    @GetMapping("/{name}")
    public Limpiador getLimpiadorByName(@PathVariable String name) {
        return LimpiadorService.getProfessionalByName(name);
    }

}