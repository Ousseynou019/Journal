package sn.estm.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import sn.estm.dao.AccesDB;
import sn.estm.entites.Entree;
import sn.estm.entites.Journal;

import java.util.List;

@Controller
public class Controleur {

    @Autowired
    private AccesDB db;

    @GetMapping("/")
    public String acceuil() {
        return "index";
    }

    @GetMapping("/ajoutentree")
    public String chargeAjoutEntree(Model modele) {
        Entree entree = new Entree();
        modele.addAttribute("entree", entree);
        List<Journal> listejournal = db.obtenirJournal();
        modele.addAttribute("listejournal", listejournal);
        return "ajouterentree";
    }

    @GetMapping("/ajoutjournal")
    public String chargeAjoutJournal(Model modele) {
        Journal ji = new Journal();
        modele.addAttribute("ji", ji);
        return "ajouterjournal";
    }

    @PostMapping("/creercateg")
    public String saveJournal(@ModelAttribute("ji") Journal ji, BindingResult resultat) {
        if (resultat.hasErrors())
            return "ajouterjournal";

        db.sauverJournal(ji);
        return "index";
    }

    @PostMapping("/creerentree")
    public String saveEntree(@ModelAttribute("entree") Entree entree, BindingResult resultat, Model modele) {
        if (resultat.hasErrors()) {
            List<Journal> listejournal = db.obtenirJournal();
            modele.addAttribute("listejournal", listejournal);
            return "ajouterentree";
        }

        db.sauverEntree(entree);
        return "index";
    }

    @GetMapping("/listerentrees")
    public String montrerLesEntrees(Model modele) {
        List<Entree> listeentree = db.obtenirEntrees();
        modele.addAttribute("listeentree", listeentree);
        return "listeentrees";
    }

    @GetMapping("/supprimerentree/{id}")
    public String supprimeEntree(@PathVariable(value = "id") Long id) {
        db.supprimerEntree(id);
        return "redirect:/listerentrees";
    }

    @GetMapping("/editerentree/{id}")
    public String editerEntree(@PathVariable(value = "id") Long id, Model modele) {
        Entree entree = db.rechercherEntree(id);
        modele.addAttribute("entree", entree);
        List<Journal> listejournal = db.obtenirJournal();
        modele.addAttribute("listejournal", listejournal);
        return "modifierentree";
    }

    @PostMapping("/majentree")
    public String updateEntree(@ModelAttribute("entree") Entree entree, BindingResult resultat, Model modele) {
        if (resultat.hasErrors()) {
            List<Journal> listejournal = db.obtenirJournal();
            modele.addAttribute("listejournal", listejournal);
            return "modifierentree";
        }
        db.modifierEntree(entree);
        return "redirect:/listerentrees";
    }
}
