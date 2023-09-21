package ma.enset.clientsmvc.service;

import ma.enset.clientsmvc.entities.Chambre;
import ma.enset.clientsmvc.repositories.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChambreService {

    private final ChambreRepository chambreRepository;

    @Autowired
    public ChambreService(ChambreRepository chambreRepository) {
        this.chambreRepository = chambreRepository;
    }

    // Méthode pour marquer une chambre comme occupée
    @Transactional
    public void marquerChambreOccupee(Long chambreId) {
        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new RuntimeException("Chambre non trouvée"));

        // Vérifiez si la chambre est déjà occupée
        if (chambre.getStatut() == Chambre.Statut.OCCUPEE) {
            throw new RuntimeException("La chambre est déjà occupée");
        }

        // Marquez la chambre comme occupée
        chambre.setStatut(Chambre.Statut.OCCUPEE);
        chambreRepository.save(chambre);
    }

    // Méthode pour marquer une chambre comme libre
    @Transactional
    public void marquerChambreLibre(Long chambreId) {
        Chambre chambre = chambreRepository.findById(chambreId)
                .orElseThrow(() -> new RuntimeException("Chambre non trouvée"));

        // Vérifiez si la chambre est déjà libre
        if (chambre.getStatut() == Chambre.Statut.LIBRE) {
            throw new RuntimeException("La chambre est déjà libre");
        }

        // Marquez la chambre comme libre
        chambre.setStatut(Chambre.Statut.LIBRE);
        chambreRepository.save(chambre);
    }
}


