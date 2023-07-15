package CleanNow01.CleanNow.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CleanNow01.CleanNow.Models.Administrador;
import CleanNow01.CleanNow.Repository.AdministrdorRepository;

@Service
@Transactional
public class AdministradorImpl implements AdministradorService{
    
    private final AdministrdorRepository administradorRepository;

    public AdministradorImpl(AdministrdorRepository administradorRepository){
        this.administradorRepository = administradorRepository;
    }

    @Override
    public Administrador createAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    @Override
    public Administrador getAdminByDni(int dni) {
        return administradorRepository.findByDni(dni);
    }

    @Override
    public Administrador findByUid(String uid) {
        return administradorRepository.findByUid(uid);
    }
}
