package CleanNow01.CleanNow.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import CleanNow01.CleanNow.Repository.ConsumidorRepository;
import CleanNow01.CleanNow.Models.Consumidor;
import CleanNow01.CleanNow.Models.Direccion;

@Service
@Transactional
public class ConsumidorImpl implements ConsumidorService{

    private final ConsumidorRepository consumidorRepository;

    public ConsumidorImpl(ConsumidorRepository consumidorRepository){
        this.consumidorRepository = consumidorRepository;
    }

    @Override
    public Consumidor createConsumidor(Consumidor consumidor) {
        
        consumidor.setCreatedAt(LocalDateTime.now());
        consumidor.setUpdatedAt(LocalDateTime.now());
        consumidor.setActive(true);
        consumidor.setUid(consumidor.getUid());

        System.out.println(consumidor.toString());
        for (Direccion direccion : consumidor.getDirecciones()) {
            direccion.setConsumidor(consumidor);
            direccion.setCreatedAt(LocalDateTime.now());
            direccion.setUpdatedAt(LocalDateTime.now());
        }

        return consumidorRepository.save(consumidor);
    }

    @Override
    public Consumidor getConsumidorByDni(int dni) {
        return consumidorRepository.findByDni(dni);
    }

    @Override
    public List<Consumidor> getAllConsumidors() {
        return consumidorRepository.findAll();
    }

    @Override
    public Consumidor updateConsumidor(int dni, Consumidor consumidor) {
        Consumidor consumidorToUpdate = getConsumidorByDni(dni);
        if(consumidor.getName() != consumidorToUpdate.getName()){
            consumidorToUpdate.setName(consumidor.getName());
        }
        if(consumidor.getLastName() != consumidorToUpdate.getLastName()){
            consumidorToUpdate.setLastName(consumidor.getLastName());
        }
        if(consumidor.getDirecciones() != consumidorToUpdate.getDirecciones()){
            consumidorToUpdate.setDirecciones(consumidor.getDirecciones());
        }
        if(consumidor.getPhone() != consumidorToUpdate.getPhone()){
            consumidorToUpdate.setPhone(consumidor.getPhone());
        }
        if(consumidor.getEmail() != consumidorToUpdate.getEmail()){
            consumidorToUpdate.setEmail(consumidor.getEmail());
        }
        if(consumidor.isActive()==false && consumidor.isActive() != consumidorToUpdate.isActive()){
            consumidorToUpdate.setActive(false);
        } else if(consumidor.isActive()==true && consumidor.isActive() != consumidorToUpdate.isActive()){
            consumidorToUpdate.setActive(true);
        }
        if(consumidor.isDeleted()==true && consumidor.isDeleted() != consumidorToUpdate.isDeleted()){
            consumidorToUpdate.setDeleted(true);
        } else if(consumidor.isDeleted()==false && consumidor.isDeleted() != consumidorToUpdate.isDeleted()){
            consumidorToUpdate.setDeleted(false);
        }
        return consumidorRepository.save(consumidorToUpdate);
    }

    @Override
    public Consumidor deleteLogicConsumidor(int dni) {
        Consumidor consumidor = consumidorRepository.findByDni(dni);
        consumidor.setDeleted(false);
        return consumidorRepository.save(consumidor);
    }

    @Override
    public boolean isActive(int dni) {
        // Consumidor consumidor = consumidorRepository.findByDni(dni);
        // if(consumidor.isActive()){
        //     consumidor.setActive(false);
        //     return false;
        // } else {
        //     consumidor.setActive(true);    
        //     return false;
        // }
        return true;
    }

    @Override
    public boolean login(String email, String password) {
        Consumidor consumidor = consumidorRepository.findByEmail(email);
        if(consumidor.getPassword().equals(password) && consumidor.isActive() && !consumidor.isDeleted()){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Consumidor isNotActive(int dni) {
        Consumidor consumidor = consumidorRepository.findByDni(dni);
        consumidor.setActive(false);
        return consumidorRepository.save(consumidor);
    }

    @Override
    public Consumidor findByUid(String uid) {
        return consumidorRepository.findByUid(uid);
    }
    
}
