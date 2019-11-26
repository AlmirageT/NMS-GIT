/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Checklist;
import Modelo.Empresas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ivans
 */
public class EmpresasJpaController implements Serializable {

    public EmpresasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("NoMasAccidentesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresas empresas) throws PreexistingEntityException, Exception {
        if (empresas.getChecklistCollection() == null) {
            empresas.setChecklistCollection(new ArrayList<Checklist>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Checklist> attachedChecklistCollection = new ArrayList<Checklist>();
            for (Checklist checklistCollectionChecklistToAttach : empresas.getChecklistCollection()) {
                checklistCollectionChecklistToAttach = em.getReference(checklistCollectionChecklistToAttach.getClass(), checklistCollectionChecklistToAttach.getIdChecklist());
                attachedChecklistCollection.add(checklistCollectionChecklistToAttach);
            }
            empresas.setChecklistCollection(attachedChecklistCollection);
            em.persist(empresas);
            for (Checklist checklistCollectionChecklist : empresas.getChecklistCollection()) {
                Empresas oldEmpresasIdEmpresaOfChecklistCollectionChecklist = checklistCollectionChecklist.getEmpresasIdEmpresa();
                checklistCollectionChecklist.setEmpresasIdEmpresa(empresas);
                checklistCollectionChecklist = em.merge(checklistCollectionChecklist);
                if (oldEmpresasIdEmpresaOfChecklistCollectionChecklist != null) {
                    oldEmpresasIdEmpresaOfChecklistCollectionChecklist.getChecklistCollection().remove(checklistCollectionChecklist);
                    oldEmpresasIdEmpresaOfChecklistCollectionChecklist = em.merge(oldEmpresasIdEmpresaOfChecklistCollectionChecklist);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpresas(empresas.getIdEmpresa()) != null) {
                throw new PreexistingEntityException("Empresas " + empresas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresas empresas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas persistentEmpresas = em.find(Empresas.class, empresas.getIdEmpresa());
            Collection<Checklist> checklistCollectionOld = persistentEmpresas.getChecklistCollection();
            Collection<Checklist> checklistCollectionNew = empresas.getChecklistCollection();
            List<String> illegalOrphanMessages = null;
            for (Checklist checklistCollectionOldChecklist : checklistCollectionOld) {
                if (!checklistCollectionNew.contains(checklistCollectionOldChecklist)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Checklist " + checklistCollectionOldChecklist + " since its empresasIdEmpresa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Checklist> attachedChecklistCollectionNew = new ArrayList<Checklist>();
            for (Checklist checklistCollectionNewChecklistToAttach : checklistCollectionNew) {
                checklistCollectionNewChecklistToAttach = em.getReference(checklistCollectionNewChecklistToAttach.getClass(), checklistCollectionNewChecklistToAttach.getIdChecklist());
                attachedChecklistCollectionNew.add(checklistCollectionNewChecklistToAttach);
            }
            checklistCollectionNew = attachedChecklistCollectionNew;
            empresas.setChecklistCollection(checklistCollectionNew);
            empresas = em.merge(empresas);
            for (Checklist checklistCollectionNewChecklist : checklistCollectionNew) {
                if (!checklistCollectionOld.contains(checklistCollectionNewChecklist)) {
                    Empresas oldEmpresasIdEmpresaOfChecklistCollectionNewChecklist = checklistCollectionNewChecklist.getEmpresasIdEmpresa();
                    checklistCollectionNewChecklist.setEmpresasIdEmpresa(empresas);
                    checklistCollectionNewChecklist = em.merge(checklistCollectionNewChecklist);
                    if (oldEmpresasIdEmpresaOfChecklistCollectionNewChecklist != null && !oldEmpresasIdEmpresaOfChecklistCollectionNewChecklist.equals(empresas)) {
                        oldEmpresasIdEmpresaOfChecklistCollectionNewChecklist.getChecklistCollection().remove(checklistCollectionNewChecklist);
                        oldEmpresasIdEmpresaOfChecklistCollectionNewChecklist = em.merge(oldEmpresasIdEmpresaOfChecklistCollectionNewChecklist);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = empresas.getIdEmpresa();
                if (findEmpresas(id) == null) {
                    throw new NonexistentEntityException("The empresas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas empresas;
            try {
                empresas = em.getReference(Empresas.class, id);
                empresas.getIdEmpresa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Checklist> checklistCollectionOrphanCheck = empresas.getChecklistCollection();
            for (Checklist checklistCollectionOrphanCheckChecklist : checklistCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empresas (" + empresas + ") cannot be destroyed since the Checklist " + checklistCollectionOrphanCheckChecklist + " in its checklistCollection field has a non-nullable empresasIdEmpresa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empresas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresas> findEmpresasEntities() {
        return findEmpresasEntities(true, -1, -1);
    }

    public List<Empresas> findEmpresasEntities(int maxResults, int firstResult) {
        return findEmpresasEntities(false, maxResults, firstResult);
    }

    private List<Empresas> findEmpresasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Empresas findEmpresas(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresas> rt = cq.from(Empresas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
