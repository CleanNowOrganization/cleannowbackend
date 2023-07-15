package CleanNow01.CleanNow.Services.Implements;

import java.util.List;

import org.springframework.stereotype.Service;
import CleanNow01.CleanNow.Models.Limpiador;
import CleanNow01.CleanNow.Repository.LimpiadorRepository;
import CleanNow01.CleanNow.Services.LimpiadorService;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LimpiadorImpl implements LimpiadorService{
    
    private final LimpiadorRepository limpiadorRepository; 

    public LimpiadorImpl(LimpiadorRepository limpiadorRepository){
        this.limpiadorRepository = limpiadorRepository;
    }

    @Override
    public Limpiador createLimpiador(Limpiador limpiador) {
        Limpiador person = new Limpiador(
            limpiador.getDni(),
            limpiador.getName(),
            limpiador.getLastName(),
            limpiador.getEmail(),
            limpiador.getPhone(),
            limpiador.isChecked_records(),
            limpiador.isAvailable()
        );
        return limpiadorRepository.save(person);
    }

    @Override
    public Limpiador getLimpiadorByDni(int dni) {
        return limpiadorRepository.findByDni(dni);
    }

    @Override
    public List<Limpiador> getAllLimpiadors() {
        return limpiadorRepository.findAll();
    }

    @Override
    public Limpiador updateLimpiador(int dni, Limpiador limpiador) {
        Limpiador limpiadorToUpdate = limpiadorRepository.findByDni(dni);
        if(limpiador.getName() != limpiadorToUpdate.getName()){
            limpiadorToUpdate.setName(limpiador.getName());
        }
        if(limpiador.getLastName() != limpiadorToUpdate.getLastName()){
            limpiadorToUpdate.setLastName(limpiador.getLastName());
        }
        if(limpiador.getPhone() != limpiadorToUpdate.getPhone()){
            limpiadorToUpdate.setPhone(limpiador.getPhone());
        }
        if(limpiador.isActive()==false && limpiador.isActive() != limpiadorToUpdate.isActive()){
            limpiadorToUpdate.setActive(false);
        } else if(limpiador.isActive()==true && limpiador.isActive() != limpiadorToUpdate.isActive()){
            limpiadorToUpdate.setActive(true);
        }
        if(limpiador.isDeleted()==true && limpiador.isDeleted() != limpiadorToUpdate.isDeleted()){
            limpiadorToUpdate.setDeleted(true);
        } else if(limpiador.isDeleted()==false && limpiador.isDeleted() != limpiadorToUpdate.isDeleted()){
            limpiadorToUpdate.setDeleted(false);
        }
        if(limpiador.getEmail() != limpiadorToUpdate.getEmail()){
            limpiadorToUpdate.setEmail(limpiador.getEmail());
        }
        if(limpiador.isChecked_records() != limpiadorToUpdate.isChecked_records()){
            limpiadorToUpdate.setChecked_records(limpiador.isChecked_records());
        }
        if(limpiador.isAvailable() != limpiadorToUpdate.isAvailable()){
            limpiadorToUpdate.setAvailable(limpiador.isAvailable());
        }
        return limpiadorRepository.save(limpiadorToUpdate);
    }

    @Override
    public void deleteLimpiador(int dni) {
        limpiadorRepository.deleteByDni(dni);
    }

    @Override
    public boolean isActive(int dni) {
        // Limpiador limpiador = limpiadorRepository.findByDni(dni);
        // if(limpiador.isActive()){
        //     limpiador.setActive(false);
        //     return false;
        // } else {
        //     limpiador.setActive(true);
        //     return true;
        // }
        return true;
    }

    @Override
    public boolean isDeleted(int dni) {
        // Limpiador limpiador = limpiadorRepository.findByDni(dni);
        // if(limpiador.isDeleted()){
        //     limpiador.setDeleted(false);
        //     return false;
        // } else {
        //     limpiador.setDeleted(true);
        //     return true;
        // }
        return false;
    }
}


