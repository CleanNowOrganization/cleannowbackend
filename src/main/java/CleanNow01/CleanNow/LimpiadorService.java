package CleanNow01.CleanNow;

    import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class LimpiadorService {
    
    private List<Limpiador> Limpiadors = new ArrayList<>();

    public Limpiador addLimpiador(Limpiador professional) {
        Limpiador newProfessional = new Limpiador(
            professional.getName(),
            professional.getLastName(),
            professional.getQuality(),

        );
        Limpiadors.add(newProfessional);
        return newProfessional;
    }

    public List<Limpiador> getLimpiadors() {
        return Limpiadors;
    }
    
    public Limpiador getProfessionalByName(String name) {
        return Limpiadors.stream()
            .filter(professional -> professional.getName().equals(name))
            .findFirst()
            .orElse (null);
    
    }
}

