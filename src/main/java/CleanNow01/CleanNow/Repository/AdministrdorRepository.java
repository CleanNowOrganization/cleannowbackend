package CleanNow01.CleanNow.Repository;

import CleanNow01.CleanNow.Models.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrdorRepository extends JpaRepository<Administrador, Long>{

    Administrador findByDni(int dni);
    Administrador findByUid(String uid);

}