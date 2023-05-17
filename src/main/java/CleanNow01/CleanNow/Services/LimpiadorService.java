package CleanNow01.CleanNow.Services;

import org.springframework.stereotype.Service;
import CleanNow01.CleanNow.Models.Limpiador;
import java.util.List;

@Service
public interface LimpiadorService {
    Limpiador createLimpiador(Limpiador limpiador);
    Limpiador getLimpiadorByDni(int dni);
    List<Limpiador> getAllLimpiadors();
    Limpiador updateLimpiador(int dni, Limpiador limpiador);
    void deleteLimpiador(int dni);
    boolean isActive(int dni);
    boolean isDeleted(int dni);
}

