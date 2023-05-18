package CleanNow01.CleanNow.Services;

import org.springframework.stereotype.Service;
import CleanNow01.CleanNow.Models.Consumidor;
import java.util.List;

@Service
public interface ConsumidorService {
    Consumidor createConsumidor(Consumidor consumidor);
    Consumidor getConsumidorByDni(int dni);
    List<Consumidor> getAllConsumidors();
    Consumidor updateConsumidor(int dni, Consumidor consumidor);
    void deleteConsumidor(int dni);
    boolean isActive(int dni);
    boolean isDeleted(int dni);
    boolean login(String email, String password);
}
