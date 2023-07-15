package CleanNow01.CleanNow.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CleanNow01.CleanNow.Models.Direccion;
import CleanNow01.CleanNow.Services.DireccionService;

@RestController
@RequestMapping("/consumidores/direccion")
public class DireccionController {
    
    @Autowired
    private DireccionService direccionService;

    @GetMapping("/{id}")
    public Direccion getDireccionById(int dni){
        return direccionService.getDireccionById(dni);
    }

    @PutMapping("/{id}")
    public Direccion updateDireccion(int dni, Direccion direccion){
        return direccionService.updatDireccion(dni, direccion);
    }

    @DeleteMapping("/{id}")
    public Direccion deleteDireccion(int dni){
        return direccionService.deleteDireccion(dni);
    }

}
