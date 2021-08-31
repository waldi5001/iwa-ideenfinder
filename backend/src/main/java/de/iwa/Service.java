package de.iwa;

import de.iwa.model.Idee;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class Service {

    @PersistenceUnit
    private EntityManagerFactory emf;

    public void speichern(String bezeichnung, String beschreibung) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Idee idee = new Idee();
            idee.setBezeichnung(bezeichnung);
            idee.setBeschreibung(beschreibung);
            em.persist(idee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Idee> suchen(String suche) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Idee> query = em.createQuery("from Idee where lower(bezeichnung) like :bezeichnung or lower(beschreibung) like :beschreibung order by erstellungzeitpunkt desc", Idee.class);
            query.setParameter("bezeichnung", String.format("%%%s%%", suche.toLowerCase()));
            query.setParameter("beschreibung", String.format("%%%s%%", suche.toLowerCase()));
            List<Idee> resultList = query.getResultList();
            for (Idee i : resultList) {
                i.getMaterial().size();
                i.getKategorien().size();
            }
            return resultList;
        } finally {
            em.close();
        }
    }
}
