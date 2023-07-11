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
    Consumidor deleteLogicConsumidor(int dni);
    boolean isActive(int dni);
    Consumidor isNotActive(int dni);
    boolean login(String email, String password);
    Consumidor findByUid(String uid);
}
