package CleanNow01.CleanNow.Repository;

import CleanNow01.CleanNow.Models.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long>{
    List<Direccion> findAllByConsumidorDni(int dni);
    Direccion findByConsumidorDni(int dni);
    void deleteByConsumidorDni(int dni);
}