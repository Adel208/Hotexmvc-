package ma.enset.clientsmvc.web;

import lombok.AllArgsConstructor;
import ma.enset.clientsmvc.entities.Chambre;
import ma.enset.clientsmvc.entities.Utilisateur;
import ma.enset.clientsmvc.repositories.ChambreRepository;
import ma.enset.clientsmvc.service.ChambreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/chambre")
@AllArgsConstructor
public class ChambreController {
    private final ChambreRepository chambreRepository;
    private final ChambreService chambreService;

    @GetMapping
    public String listerChambres(Model model) {
        List<Chambre> chambresLibres = chambreRepository.findByStatut(Chambre.Statut.LIBRE);
        System.out.println("Nombre de chambres libres : " + chambresLibres.size()); // Vérifiez la taille de la liste
        model.addAttribute("chambresLibres", chambresLibres);
        model.addAttribute("utilisateur", new Utilisateur());
        return "chambre";
    }

    @PostMapping("/ajouterChambre")
    public String ajouterChambre(@ModelAttribute Chambre chambre) { // Utilisation de @ModelAttribute pour lier le formulaire à l'objet Chambre
        chambreRepository.save(chambre);
        return "redirect:/chambre";
    }

    @GetMapping("/liste-des-chambres")
    public String listeDesChambres(Model model) {
        List<Chambre> chambres = chambreRepository.findAll();
        model.addAttribute("listChambres", chambres);
        return "redirect:/chambre"; // Rediriger vers la liste des chambres
    }

    // Ajout de méthodes pour marquer les chambres comme occupées ou libres
    @PostMapping("/{chambreId}/occupee")
    public String marquerChambreOccupee(@PathVariable Long chambreId) {
        chambreService.marquerChambreOccupee(chambreId);
        return "redirect:/chambre";
    }

    @PostMapping("/{chambreId}/libre")
    public String marquerChambreLibre(@PathVariable Long chambreId) {
        chambreService.marquerChambreLibre(chambreId);
        return "redirect:/chambre";
    }
}
