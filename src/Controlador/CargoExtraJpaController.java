/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import Modelo.CargoExtra;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Pagos;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ivans
 */
public class CargoExtraJpaController implements Serializable {

    public CargoExtraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CargoExtra cargoExtra) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagos pagosIdPago = cargoExtra.getPagosIdPago();
            if (pagosIdPago != null) {
                pagosIdPago = em.getReference(pagosIdPago.getClass(), pagosIdPago.getIdPago());
                cargoExtra.setPagosIdPago(pagosIdPago);
            }
            em.persist(cargoExtra);
            if (pagosIdPago != null) {
                pagosIdPago.getCargoExtraCollection().add(cargoExtra);
                pagosIdPago = em.merge(pagosIdPago);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCargoExtra(cargoExtra.getIdCargoExtra()) != null) {
                throw new PreexistingEntityException("CargoExtra " + cargoExtra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CargoExtra cargoExtra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CargoExtra persistentCargoExtra = em.find(CargoExtra.class, cargoExtra.getIdCargoExtra());
            Pagos pagosIdPagoOld = persistentCargoExtra.getPagosIdPago();
            Pagos pagosIdPagoNew = cargoExtra.getPagosIdPago();
            if (pagosIdPagoNew != null) {
                pagosIdPagoNew = em.getReference(pagosIdPagoNew.getClass(), pagosIdPagoNew.getIdPago());
                cargoExtra.setPagosIdPago(pagosIdPagoNew);
            }
            cargoExtra = em.merge(cargoExtra);
            if (pagosIdPagoOld != null && !pagosIdPagoOld.equals(pagosIdPagoNew)) {
                pagosIdPagoOld.getCargoExtraCollection().remove(cargoExtra);
                pagosIdPagoOld = em.merge(pagosIdPagoOld);
            }
            if (pagosIdPagoNew != null && !pagosIdPagoNew.equals(pagosIdPagoOld)) {
                pagosIdPagoNew.getCargoExtraCollection().add(cargoExtra);
                pagosIdPagoNew = em.merge(pagosIdPagoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = cargoExtra.getIdCargoExtra();
                if (findCargoExtra(id) == null) {
                    throw new NonexistentEntityException("The cargoExtra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CargoExtra cargoExtra;
            try {
                cargoExtra = em.getReference(CargoExtra.class, id);
                cargoExtra.getIdCargoExtra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargoExtra with id " + id + " no longer exists.", enfe);
            }
            Pagos pagosIdPago = cargoExtra.getPagosIdPago();
            if (pagosIdPago != null) {
                pagosIdPago.getCargoExtraCollection().remove(cargoExtra);
                pagosIdPago = em.merge(pagosIdPago);
            }
            em.remove(cargoExtra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CargoExtra> findCargoExtraEntities() {
        return findCargoExtraEntities(true, -1, -1);
    }

    public List<CargoExtra> findCargoExtraEntities(int maxResults, int firstResult) {
        return findCargoExtraEntities(false, maxResults, firstResult);
    }

    private List<CargoExtra> findCargoExtraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CargoExtra.class));
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

    public CargoExtra findCargoExtra(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CargoExtra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargoExtraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CargoExtra> rt = cq.from(CargoExtra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
