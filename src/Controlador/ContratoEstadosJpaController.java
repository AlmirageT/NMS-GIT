/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import Modelo.ContratoEstados;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Contratos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ivans
 */
public class ContratoEstadosJpaController implements Serializable {

    public ContratoEstadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ContratoEstados contratoEstados) throws PreexistingEntityException, Exception {
        if (contratoEstados.getContratosCollection() == null) {
            contratoEstados.setContratosCollection(new ArrayList<Contratos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Contratos> attachedContratosCollection = new ArrayList<Contratos>();
            for (Contratos contratosCollectionContratosToAttach : contratoEstados.getContratosCollection()) {
                contratosCollectionContratosToAttach = em.getReference(contratosCollectionContratosToAttach.getClass(), contratosCollectionContratosToAttach.getIdContrato());
                attachedContratosCollection.add(contratosCollectionContratosToAttach);
            }
            contratoEstados.setContratosCollection(attachedContratosCollection);
            em.persist(contratoEstados);
            for (Contratos contratosCollectionContratos : contratoEstados.getContratosCollection()) {
                ContratoEstados oldIdContratoEstadoFkOfContratosCollectionContratos = contratosCollectionContratos.getIdContratoEstadoFk();
                contratosCollectionContratos.setIdContratoEstadoFk(contratoEstados);
                contratosCollectionContratos = em.merge(contratosCollectionContratos);
                if (oldIdContratoEstadoFkOfContratosCollectionContratos != null) {
                    oldIdContratoEstadoFkOfContratosCollectionContratos.getContratosCollection().remove(contratosCollectionContratos);
                    oldIdContratoEstadoFkOfContratosCollectionContratos = em.merge(oldIdContratoEstadoFkOfContratosCollectionContratos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContratoEstados(contratoEstados.getIdContratoEstado()) != null) {
                throw new PreexistingEntityException("ContratoEstados " + contratoEstados + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ContratoEstados contratoEstados) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContratoEstados persistentContratoEstados = em.find(ContratoEstados.class, contratoEstados.getIdContratoEstado());
            Collection<Contratos> contratosCollectionOld = persistentContratoEstados.getContratosCollection();
            Collection<Contratos> contratosCollectionNew = contratoEstados.getContratosCollection();
            List<String> illegalOrphanMessages = null;
            for (Contratos contratosCollectionOldContratos : contratosCollectionOld) {
                if (!contratosCollectionNew.contains(contratosCollectionOldContratos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Contratos " + contratosCollectionOldContratos + " since its idContratoEstadoFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Contratos> attachedContratosCollectionNew = new ArrayList<Contratos>();
            for (Contratos contratosCollectionNewContratosToAttach : contratosCollectionNew) {
                contratosCollectionNewContratosToAttach = em.getReference(contratosCollectionNewContratosToAttach.getClass(), contratosCollectionNewContratosToAttach.getIdContrato());
                attachedContratosCollectionNew.add(contratosCollectionNewContratosToAttach);
            }
            contratosCollectionNew = attachedContratosCollectionNew;
            contratoEstados.setContratosCollection(contratosCollectionNew);
            contratoEstados = em.merge(contratoEstados);
            for (Contratos contratosCollectionNewContratos : contratosCollectionNew) {
                if (!contratosCollectionOld.contains(contratosCollectionNewContratos)) {
                    ContratoEstados oldIdContratoEstadoFkOfContratosCollectionNewContratos = contratosCollectionNewContratos.getIdContratoEstadoFk();
                    contratosCollectionNewContratos.setIdContratoEstadoFk(contratoEstados);
                    contratosCollectionNewContratos = em.merge(contratosCollectionNewContratos);
                    if (oldIdContratoEstadoFkOfContratosCollectionNewContratos != null && !oldIdContratoEstadoFkOfContratosCollectionNewContratos.equals(contratoEstados)) {
                        oldIdContratoEstadoFkOfContratosCollectionNewContratos.getContratosCollection().remove(contratosCollectionNewContratos);
                        oldIdContratoEstadoFkOfContratosCollectionNewContratos = em.merge(oldIdContratoEstadoFkOfContratosCollectionNewContratos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = contratoEstados.getIdContratoEstado();
                if (findContratoEstados(id) == null) {
                    throw new NonexistentEntityException("The contratoEstados with id " + id + " no longer exists.");
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
            ContratoEstados contratoEstados;
            try {
                contratoEstados = em.getReference(ContratoEstados.class, id);
                contratoEstados.getIdContratoEstado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contratoEstados with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Contratos> contratosCollectionOrphanCheck = contratoEstados.getContratosCollection();
            for (Contratos contratosCollectionOrphanCheckContratos : contratosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ContratoEstados (" + contratoEstados + ") cannot be destroyed since the Contratos " + contratosCollectionOrphanCheckContratos + " in its contratosCollection field has a non-nullable idContratoEstadoFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(contratoEstados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ContratoEstados> findContratoEstadosEntities() {
        return findContratoEstadosEntities(true, -1, -1);
    }

    public List<ContratoEstados> findContratoEstadosEntities(int maxResults, int firstResult) {
        return findContratoEstadosEntities(false, maxResults, firstResult);
    }

    private List<ContratoEstados> findContratoEstadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ContratoEstados.class));
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

    public ContratoEstados findContratoEstados(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ContratoEstados.class, id);
        } finally {
            em.close();
        }
    }

    public int getContratoEstadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ContratoEstados> rt = cq.from(ContratoEstados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
