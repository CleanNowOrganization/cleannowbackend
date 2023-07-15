package CleanNow01.CleanNow.Services.Implements;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import CleanNow01.CleanNow.Models.Administrador;
import CleanNow01.CleanNow.Repository.AdministrdorRepository;
import CleanNow01.CleanNow.Services.AdministradorService;

@Service
@Transactional
public class AdministradorImpl implements AdministradorService{
    
    private final AdministrdorRepository administradorRepository;

    public AdministradorImpl(AdministrdorRepository administradorRepository){
        this.administradorRepository = administradorRepository;
    }

    @Override
    public Administrador createAdministrador(Administrador administrador) {
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(administrador.getPassword());
        administrador.setPassword(encodedPassword);
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
