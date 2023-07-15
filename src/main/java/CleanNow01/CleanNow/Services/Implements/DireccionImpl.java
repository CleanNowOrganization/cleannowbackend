package CleanNow01.CleanNow.Services.Implements;

import org.springframework.stereotype.Service;

import CleanNow01.CleanNow.Models.Direccion;
import CleanNow01.CleanNow.Repository.DireccionRepository;
import CleanNow01.CleanNow.Services.DireccionService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionImpl implements DireccionService{

    private final DireccionRepository direccionRepository;

    public DireccionImpl(DireccionRepository direccionRepository){
        this.direccionRepository = direccionRepository;
    }

    @Override
    public Direccion getDireccionById(int dni) {
        return direccionRepository.findByConsumidorDni(dni);
    }

    @Override
    public Direccion updatDireccion(int dni, Direccion direccion) {
        Direccion direccionToUpdate = getDireccionById(dni);
        if(direccion.getCalle() != direccionToUpdate.getCalle()){
            direccionToUpdate.setCalle(direccion.getCalle());
        }
        if(direccion.getCiudad() != direccionToUpdate.getCiudad()){
            direccionToUpdate.setCiudad(direccion.getCiudad());
        }
        if(direccion.getCodigoPostal() != direccionToUpdate.getCodigoPostal()){
            direccionToUpdate.setCodigoPostal(direccion.getCodigoPostal());
        }
        return direccionRepository.save(direccionToUpdate);
    }

    @Override
    public Direccion deleteDireccion(int dni) {
        Direccion direccionToDelete = getDireccionById(dni);
        direccionRepository.delete(direccionToDelete);
        return direccionToDelete;
    }
    
}
