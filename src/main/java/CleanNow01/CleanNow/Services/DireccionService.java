package CleanNow01.CleanNow.Services;

import org.springframework.stereotype.Service;
import CleanNow01.CleanNow.Models.Direccion;

@Service
public interface DireccionService {
    Direccion getDireccionById(int dni);
    Direccion updatDireccion(int dni, Direccion direccion);
    Direccion deleteDireccion(int dni);
}
