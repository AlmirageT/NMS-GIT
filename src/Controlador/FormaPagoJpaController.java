/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import Modelo.FormaPago;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Pagos;
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
public class FormaPagoJpaController implements Serializable {

    public FormaPagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormaPago formaPago) throws PreexistingEntityException, Exception {
        if (formaPago.getPagosCollection() == null) {
            formaPago.setPagosCollection(new ArrayList<Pagos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Pagos> attachedPagosCollection = new ArrayList<Pagos>();
            for (Pagos pagosCollectionPagosToAttach : formaPago.getPagosCollection()) {
                pagosCollectionPagosToAttach = em.getReference(pagosCollectionPagosToAttach.getClass(), pagosCollectionPagosToAttach.getIdPago());
                attachedPagosCollection.add(pagosCollectionPagosToAttach);
            }
            formaPago.setPagosCollection(attachedPagosCollection);
            em.persist(formaPago);
            for (Pagos pagosCollectionPagos : formaPago.getPagosCollection()) {
                FormaPago oldFormaPagoIdFormaPagoOfPagosCollectionPagos = pagosCollectionPagos.getFormaPagoIdFormaPago();
                pagosCollectionPagos.setFormaPagoIdFormaPago(formaPago);
                pagosCollectionPagos = em.merge(pagosCollectionPagos);
                if (oldFormaPagoIdFormaPagoOfPagosCollectionPagos != null) {
                    oldFormaPagoIdFormaPagoOfPagosCollectionPagos.getPagosCollection().remove(pagosCollectionPagos);
                    oldFormaPagoIdFormaPagoOfPagosCollectionPagos = em.merge(oldFormaPagoIdFormaPagoOfPagosCollectionPagos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormaPago(formaPago.getIdFormaPago()) != null) {
                throw new PreexistingEntityException("FormaPago " + formaPago + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormaPago formaPago) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormaPago persistentFormaPago = em.find(FormaPago.class, formaPago.getIdFormaPago());
            Collection<Pagos> pagosCollectionOld = persistentFormaPago.getPagosCollection();
            Collection<Pagos> pagosCollectionNew = formaPago.getPagosCollection();
            List<String> illegalOrphanMessages = null;
            for (Pagos pagosCollectionOldPagos : pagosCollectionOld) {
                if (!pagosCollectionNew.contains(pagosCollectionOldPagos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagos " + pagosCollectionOldPagos + " since its formaPagoIdFormaPago field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Pagos> attachedPagosCollectionNew = new ArrayList<Pagos>();
            for (Pagos pagosCollectionNewPagosToAttach : pagosCollectionNew) {
                pagosCollectionNewPagosToAttach = em.getReference(pagosCollectionNewPagosToAttach.getClass(), pagosCollectionNewPagosToAttach.getIdPago());
                attachedPagosCollectionNew.add(pagosCollectionNewPagosToAttach);
            }
            pagosCollectionNew = attachedPagosCollectionNew;
            formaPago.setPagosCollection(pagosCollectionNew);
            formaPago = em.merge(formaPago);
            for (Pagos pagosCollectionNewPagos : pagosCollectionNew) {
                if (!pagosCollectionOld.contains(pagosCollectionNewPagos)) {
                    FormaPago oldFormaPagoIdFormaPagoOfPagosCollectionNewPagos = pagosCollectionNewPagos.getFormaPagoIdFormaPago();
                    pagosCollectionNewPagos.setFormaPagoIdFormaPago(formaPago);
                    pagosCollectionNewPagos = em.merge(pagosCollectionNewPagos);
                    if (oldFormaPagoIdFormaPagoOfPagosCollectionNewPagos != null && !oldFormaPagoIdFormaPagoOfPagosCollectionNewPagos.equals(formaPago)) {
                        oldFormaPagoIdFormaPagoOfPagosCollectionNewPagos.getPagosCollection().remove(pagosCollectionNewPagos);
                        oldFormaPagoIdFormaPagoOfPagosCollectionNewPagos = em.merge(oldFormaPagoIdFormaPagoOfPagosCollectionNewPagos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = formaPago.getIdFormaPago();
                if (findFormaPago(id) == null) {
                    throw new NonexistentEntityException("The formaPago with id " + id + " no longer exists.");
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
            FormaPago formaPago;
            try {
                formaPago = em.getReference(FormaPago.class, id);
                formaPago.getIdFormaPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formaPago with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Pagos> pagosCollectionOrphanCheck = formaPago.getPagosCollection();
            for (Pagos pagosCollectionOrphanCheckPagos : pagosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FormaPago (" + formaPago + ") cannot be destroyed since the Pagos " + pagosCollectionOrphanCheckPagos + " in its pagosCollection field has a non-nullable formaPagoIdFormaPago field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(formaPago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormaPago> findFormaPagoEntities() {
        return findFormaPagoEntities(true, -1, -1);
    }

    public List<FormaPago> findFormaPagoEntities(int maxResults, int firstResult) {
        return findFormaPagoEntities(false, maxResults, firstResult);
    }

    private List<FormaPago> findFormaPagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormaPago.class));
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

    public FormaPago findFormaPago(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormaPago.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormaPagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormaPago> rt = cq.from(FormaPago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
