package CleanNow01.CleanNow.Repository;

import CleanNow01.CleanNow.Models.Limpiador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LimpiadorRepository extends JpaRepository<Limpiador, Long>{
    List<Limpiador> findAll();
    Limpiador findByDni(int dni);
    void deleteByDni(int dni); 
}