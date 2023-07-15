package CleanNow01.CleanNow.Services;

import org.springframework.stereotype.Service;
import CleanNow01.CleanNow.Models.Administrador;

@Service
public interface AdministradorService {
    Administrador createAdministrador(Administrador administrador);
    Administrador getAdminByDni(int dni);
    Administrador findByUid(String uid);
}
