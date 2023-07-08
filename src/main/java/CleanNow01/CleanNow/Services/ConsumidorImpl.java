package CleanNow01.CleanNow.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import CleanNow01.CleanNow.Repository.ConsumidorRepository;
import CleanNow01.CleanNow.Models.Consumidor;

@Service
@Transactional
public class ConsumidorImpl implements ConsumidorService{

    private final ConsumidorRepository consumidorRepository;

    public ConsumidorImpl(ConsumidorRepository consumidorRepository){
        this.consumidorRepository = consumidorRepository;
    }

    @Override
    public Consumidor createConsumidor(Consumidor consumidor) {
        Consumidor person = new Consumidor(
            consumidor.getDni(),
            consumidor.getName(),
            consumidor.getLastName(),
            consumidor.getAddress(),
            consumidor.getPassword(),
            consumidor.getEmail(),
            consumidor.getPhone()
        );
        return consumidorRepository.save(person);
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
        Consumidor consumidorToUpdate = consumidorRepository.findByDni(dni);
        if(consumidor.getName() != consumidorToUpdate.getName()){
            consumidorToUpdate.setName(consumidor.getName());
        }
        if(consumidor.getLastName() != consumidorToUpdate.getLastName()){
            consumidorToUpdate.setLastName(consumidor.getLastName());
        }
        if(consumidor.getAddress() != consumidorToUpdate.getAddress()){
            consumidorToUpdate.setAddress(consumidor.getAddress());
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
    public void deleteConsumidor(int dni) {
        consumidorRepository.deleteByDni(dni);
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
    public boolean isDeleted(int dni) {
        // Consumidor consumidor = consumidorRepository.findByDni(dni);
        // if(consumidor.isDeleted()){
        //     consumidor.setDeleted(false);
        //     return true;
        // } else {
        //     consumidor.setDeleted(true);
        //     return false;
        // }
        return false;
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
    
}
