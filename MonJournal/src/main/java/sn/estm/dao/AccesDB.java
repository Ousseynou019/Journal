package sn.estm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.estm.entites.Entree;
import sn.estm.entites.Journal;
import sn.estm.repository.EntreeRepository;
import sn.estm.repository.JournalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccesDB {

    @Autowired
    private JournalRepository jrepo;

    @Autowired
    private EntreeRepository erepo;

    @Transactional
    public void sauverJournal(Journal journal) {
        jrepo.save(journal);
    }

    @Transactional
    public void sauverEntree(Entree entree) {
        Journal j = jrepo.findByNumber(entree.getCodejour());
        j.ajouterEntree(entree);
        entree.setJournal(j);
        erepo.save(entree);
    }

    public List<Journal> obtenirJournal() {
        return jrepo.findAll();
    }

    public List<Entree> obtenirEntrees() {
        return erepo.findAll();
    }

    @Transactional
    public void supprimerEntree(Long id) {
        Optional<Entree> opt = erepo.findById(id);
        if (opt.isPresent()) {
            Entree entree = opt.get();
            Journal j = entree.getJournal();
            j.supprimerEntree(entree);
            jrepo.save(j);
            erepo.delete(entree);
        }
    }

    public Entree rechercherEntree(Long id) {
        Optional<Entree> opt = erepo.findById(id);
        return opt.orElse(null);
    }

    @Transactional
    public void modifierEntree(Entree entree) {
        Journal j = jrepo.findByNumber(entree.getCodejour());
        entree.setJournal(j);
        erepo.save(entree);
    }
}
