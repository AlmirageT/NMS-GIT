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
import Modelo.Contratos;
import Modelo.FormaPago;
import Modelo.CargoExtra;
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
public class PagosJpaController implements Serializable {

    public PagosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagos pagos) throws PreexistingEntityException, Exception {
        if (pagos.getCargoExtraCollection() == null) {
            pagos.setCargoExtraCollection(new ArrayList<CargoExtra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contratos contratos = pagos.getContratos();
            if (contratos != null) {
                contratos = em.getReference(contratos.getClass(), contratos.getIdContrato());
                pagos.setContratos(contratos);
            }
            FormaPago formaPagoIdFormaPago = pagos.getFormaPagoIdFormaPago();
            if (formaPagoIdFormaPago != null) {
                formaPagoIdFormaPago = em.getReference(formaPagoIdFormaPago.getClass(), formaPagoIdFormaPago.getIdFormaPago());
                pagos.setFormaPagoIdFormaPago(formaPagoIdFormaPago);
            }
            Collection<CargoExtra> attachedCargoExtraCollection = new ArrayList<CargoExtra>();
            for (CargoExtra cargoExtraCollectionCargoExtraToAttach : pagos.getCargoExtraCollection()) {
                cargoExtraCollectionCargoExtraToAttach = em.getReference(cargoExtraCollectionCargoExtraToAttach.getClass(), cargoExtraCollectionCargoExtraToAttach.getIdCargoExtra());
                attachedCargoExtraCollection.add(cargoExtraCollectionCargoExtraToAttach);
            }
            pagos.setCargoExtraCollection(attachedCargoExtraCollection);
            em.persist(pagos);
            if (contratos != null) {
                Pagos oldPagosIdPagoOfContratos = contratos.getPagosIdPago();
                if (oldPagosIdPagoOfContratos != null) {
                    oldPagosIdPagoOfContratos.setContratos(null);
                    oldPagosIdPagoOfContratos = em.merge(oldPagosIdPagoOfContratos);
                }
                contratos.setPagosIdPago(pagos);
                contratos = em.merge(contratos);
            }
            if (formaPagoIdFormaPago != null) {
                formaPagoIdFormaPago.getPagosCollection().add(pagos);
                formaPagoIdFormaPago = em.merge(formaPagoIdFormaPago);
            }
            for (CargoExtra cargoExtraCollectionCargoExtra : pagos.getCargoExtraCollection()) {
                Pagos oldPagosIdPagoOfCargoExtraCollectionCargoExtra = cargoExtraCollectionCargoExtra.getPagosIdPago();
                cargoExtraCollectionCargoExtra.setPagosIdPago(pagos);
                cargoExtraCollectionCargoExtra = em.merge(cargoExtraCollectionCargoExtra);
                if (oldPagosIdPagoOfCargoExtraCollectionCargoExtra != null) {
                    oldPagosIdPagoOfCargoExtraCollectionCargoExtra.getCargoExtraCollection().remove(cargoExtraCollectionCargoExtra);
                    oldPagosIdPagoOfCargoExtraCollectionCargoExtra = em.merge(oldPagosIdPagoOfCargoExtraCollectionCargoExtra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPagos(pagos.getIdPago()) != null) {
                throw new PreexistingEntityException("Pagos " + pagos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagos pagos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagos persistentPagos = em.find(Pagos.class, pagos.getIdPago());
            Contratos contratosOld = persistentPagos.getContratos();
            Contratos contratosNew = pagos.getContratos();
            FormaPago formaPagoIdFormaPagoOld = persistentPagos.getFormaPagoIdFormaPago();
            FormaPago formaPagoIdFormaPagoNew = pagos.getFormaPagoIdFormaPago();
            Collection<CargoExtra> cargoExtraCollectionOld = persistentPagos.getCargoExtraCollection();
            Collection<CargoExtra> cargoExtraCollectionNew = pagos.getCargoExtraCollection();
            List<String> illegalOrphanMessages = null;
            if (contratosOld != null && !contratosOld.equals(contratosNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Contratos " + contratosOld + " since its pagosIdPago field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (contratosNew != null) {
                contratosNew = em.getReference(contratosNew.getClass(), contratosNew.getIdContrato());
                pagos.setContratos(contratosNew);
            }
            if (formaPagoIdFormaPagoNew != null) {
                formaPagoIdFormaPagoNew = em.getReference(formaPagoIdFormaPagoNew.getClass(), formaPagoIdFormaPagoNew.getIdFormaPago());
                pagos.setFormaPagoIdFormaPago(formaPagoIdFormaPagoNew);
            }
            Collection<CargoExtra> attachedCargoExtraCollectionNew = new ArrayList<CargoExtra>();
            for (CargoExtra cargoExtraCollectionNewCargoExtraToAttach : cargoExtraCollectionNew) {
                cargoExtraCollectionNewCargoExtraToAttach = em.getReference(cargoExtraCollectionNewCargoExtraToAttach.getClass(), cargoExtraCollectionNewCargoExtraToAttach.getIdCargoExtra());
                attachedCargoExtraCollectionNew.add(cargoExtraCollectionNewCargoExtraToAttach);
            }
            cargoExtraCollectionNew = attachedCargoExtraCollectionNew;
            pagos.setCargoExtraCollection(cargoExtraCollectionNew);
            pagos = em.merge(pagos);
            if (contratosNew != null && !contratosNew.equals(contratosOld)) {
                Pagos oldPagosIdPagoOfContratos = contratosNew.getPagosIdPago();
                if (oldPagosIdPagoOfContratos != null) {
                    oldPagosIdPagoOfContratos.setContratos(null);
                    oldPagosIdPagoOfContratos = em.merge(oldPagosIdPagoOfContratos);
                }
                contratosNew.setPagosIdPago(pagos);
                contratosNew = em.merge(contratosNew);
            }
            if (formaPagoIdFormaPagoOld != null && !formaPagoIdFormaPagoOld.equals(formaPagoIdFormaPagoNew)) {
                formaPagoIdFormaPagoOld.getPagosCollection().remove(pagos);
                formaPagoIdFormaPagoOld = em.merge(formaPagoIdFormaPagoOld);
            }
            if (formaPagoIdFormaPagoNew != null && !formaPagoIdFormaPagoNew.equals(formaPagoIdFormaPagoOld)) {
                formaPagoIdFormaPagoNew.getPagosCollection().add(pagos);
                formaPagoIdFormaPagoNew = em.merge(formaPagoIdFormaPagoNew);
            }
            for (CargoExtra cargoExtraCollectionOldCargoExtra : cargoExtraCollectionOld) {
                if (!cargoExtraCollectionNew.contains(cargoExtraCollectionOldCargoExtra)) {
                    cargoExtraCollectionOldCargoExtra.setPagosIdPago(null);
                    cargoExtraCollectionOldCargoExtra = em.merge(cargoExtraCollectionOldCargoExtra);
                }
            }
            for (CargoExtra cargoExtraCollectionNewCargoExtra : cargoExtraCollectionNew) {
                if (!cargoExtraCollectionOld.contains(cargoExtraCollectionNewCargoExtra)) {
                    Pagos oldPagosIdPagoOfCargoExtraCollectionNewCargoExtra = cargoExtraCollectionNewCargoExtra.getPagosIdPago();
                    cargoExtraCollectionNewCargoExtra.setPagosIdPago(pagos);
                    cargoExtraCollectionNewCargoExtra = em.merge(cargoExtraCollectionNewCargoExtra);
                    if (oldPagosIdPagoOfCargoExtraCollectionNewCargoExtra != null && !oldPagosIdPagoOfCargoExtraCollectionNewCargoExtra.equals(pagos)) {
                        oldPagosIdPagoOfCargoExtraCollectionNewCargoExtra.getCargoExtraCollection().remove(cargoExtraCollectionNewCargoExtra);
                        oldPagosIdPagoOfCargoExtraCollectionNewCargoExtra = em.merge(oldPagosIdPagoOfCargoExtraCollectionNewCargoExtra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = pagos.getIdPago();
                if (findPagos(id) == null) {
                    throw new NonexistentEntityException("The pagos with id " + id + " no longer exists.");
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
            Pagos pagos;
            try {
                pagos = em.getReference(Pagos.class, id);
                pagos.getIdPago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Contratos contratosOrphanCheck = pagos.getContratos();
            if (contratosOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pagos (" + pagos + ") cannot be destroyed since the Contratos " + contratosOrphanCheck + " in its contratos field has a non-nullable pagosIdPago field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            FormaPago formaPagoIdFormaPago = pagos.getFormaPagoIdFormaPago();
            if (formaPagoIdFormaPago != null) {
                formaPagoIdFormaPago.getPagosCollection().remove(pagos);
                formaPagoIdFormaPago = em.merge(formaPagoIdFormaPago);
            }
            Collection<CargoExtra> cargoExtraCollection = pagos.getCargoExtraCollection();
            for (CargoExtra cargoExtraCollectionCargoExtra : cargoExtraCollection) {
                cargoExtraCollectionCargoExtra.setPagosIdPago(null);
                cargoExtraCollectionCargoExtra = em.merge(cargoExtraCollectionCargoExtra);
            }
            em.remove(pagos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagos> findPagosEntities() {
        return findPagosEntities(true, -1, -1);
    }

    public List<Pagos> findPagosEntities(int maxResults, int firstResult) {
        return findPagosEntities(false, maxResults, firstResult);
    }

    private List<Pagos> findPagosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagos.class));
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

    public Pagos findPagos(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagos> rt = cq.from(Pagos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
