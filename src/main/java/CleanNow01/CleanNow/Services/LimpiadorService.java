package CleanNow01.CleanNow.Services;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import CleanNow01.CleanNow.Models.Limpiador;

@Service
public class LimpiadorService {
    
    private List<Limpiador> Limpiadors = new ArrayList<>();

    public void addLimpiador(Limpiador professional) {
        Limpiadors.add(professional);
    }

    public List<Limpiador> getLimpiadors() {
        return Limpiadors;
    }
    
    public Limpiador getProfessionalByDni(int dni) {
        return Limpiadors.stream().filter(t -> t.getDni() == dni).findFirst().get();
    }

    public Limpiador updateLimpiador(Limpiador professional) {
        Limpiador currentProfessional = getProfessionalByDni(professional.getDni());
        return currentProfessional;
    }
}

