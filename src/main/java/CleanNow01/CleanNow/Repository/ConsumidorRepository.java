package CleanNow01.CleanNow.Repository;

import CleanNow01.CleanNow.Models.Consumidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConsumidorRepository extends JpaRepository<Consumidor, Long>{
    List<Consumidor> findAll();
    Consumidor findByDni(int dni);
    Consumidor findByEmail(String email);
    void deleteByDni(int dni);
}
