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
import Modelo.Servicios;
import Modelo.ServiciosEstado;
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
public class ServiciosEstadoJpaController implements Serializable {

    public ServiciosEstadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiciosEstado serviciosEstado) throws PreexistingEntityException, Exception {
        if (serviciosEstado.getServiciosCollection() == null) {
            serviciosEstado.setServiciosCollection(new ArrayList<Servicios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Servicios> attachedServiciosCollection = new ArrayList<Servicios>();
            for (Servicios serviciosCollectionServiciosToAttach : serviciosEstado.getServiciosCollection()) {
                serviciosCollectionServiciosToAttach = em.getReference(serviciosCollectionServiciosToAttach.getClass(), serviciosCollectionServiciosToAttach.getIdServicio());
                attachedServiciosCollection.add(serviciosCollectionServiciosToAttach);
            }
            serviciosEstado.setServiciosCollection(attachedServiciosCollection);
            em.persist(serviciosEstado);
            for (Servicios serviciosCollectionServicios : serviciosEstado.getServiciosCollection()) {
                ServiciosEstado oldIdEstadoServiciosFkOfServiciosCollectionServicios = serviciosCollectionServicios.getIdEstadoServiciosFk();
                serviciosCollectionServicios.setIdEstadoServiciosFk(serviciosEstado);
                serviciosCollectionServicios = em.merge(serviciosCollectionServicios);
                if (oldIdEstadoServiciosFkOfServiciosCollectionServicios != null) {
                    oldIdEstadoServiciosFkOfServiciosCollectionServicios.getServiciosCollection().remove(serviciosCollectionServicios);
                    oldIdEstadoServiciosFkOfServiciosCollectionServicios = em.merge(oldIdEstadoServiciosFkOfServiciosCollectionServicios);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServiciosEstado(serviciosEstado.getIdEstadoServicios()) != null) {
                throw new PreexistingEntityException("ServiciosEstado " + serviciosEstado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiciosEstado serviciosEstado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiciosEstado persistentServiciosEstado = em.find(ServiciosEstado.class, serviciosEstado.getIdEstadoServicios());
            Collection<Servicios> serviciosCollectionOld = persistentServiciosEstado.getServiciosCollection();
            Collection<Servicios> serviciosCollectionNew = serviciosEstado.getServiciosCollection();
            List<String> illegalOrphanMessages = null;
            for (Servicios serviciosCollectionOldServicios : serviciosCollectionOld) {
                if (!serviciosCollectionNew.contains(serviciosCollectionOldServicios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Servicios " + serviciosCollectionOldServicios + " since its idEstadoServiciosFk field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Servicios> attachedServiciosCollectionNew = new ArrayList<Servicios>();
            for (Servicios serviciosCollectionNewServiciosToAttach : serviciosCollectionNew) {
                serviciosCollectionNewServiciosToAttach = em.getReference(serviciosCollectionNewServiciosToAttach.getClass(), serviciosCollectionNewServiciosToAttach.getIdServicio());
                attachedServiciosCollectionNew.add(serviciosCollectionNewServiciosToAttach);
            }
            serviciosCollectionNew = attachedServiciosCollectionNew;
            serviciosEstado.setServiciosCollection(serviciosCollectionNew);
            serviciosEstado = em.merge(serviciosEstado);
            for (Servicios serviciosCollectionNewServicios : serviciosCollectionNew) {
                if (!serviciosCollectionOld.contains(serviciosCollectionNewServicios)) {
                    ServiciosEstado oldIdEstadoServiciosFkOfServiciosCollectionNewServicios = serviciosCollectionNewServicios.getIdEstadoServiciosFk();
                    serviciosCollectionNewServicios.setIdEstadoServiciosFk(serviciosEstado);
                    serviciosCollectionNewServicios = em.merge(serviciosCollectionNewServicios);
                    if (oldIdEstadoServiciosFkOfServiciosCollectionNewServicios != null && !oldIdEstadoServiciosFkOfServiciosCollectionNewServicios.equals(serviciosEstado)) {
                        oldIdEstadoServiciosFkOfServiciosCollectionNewServicios.getServiciosCollection().remove(serviciosCollectionNewServicios);
                        oldIdEstadoServiciosFkOfServiciosCollectionNewServicios = em.merge(oldIdEstadoServiciosFkOfServiciosCollectionNewServicios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = serviciosEstado.getIdEstadoServicios();
                if (findServiciosEstado(id) == null) {
                    throw new NonexistentEntityException("The serviciosEstado with id " + id + " no longer exists.");
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
            ServiciosEstado serviciosEstado;
            try {
                serviciosEstado = em.getReference(ServiciosEstado.class, id);
                serviciosEstado.getIdEstadoServicios();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviciosEstado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Servicios> serviciosCollectionOrphanCheck = serviciosEstado.getServiciosCollection();
            for (Servicios serviciosCollectionOrphanCheckServicios : serviciosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServiciosEstado (" + serviciosEstado + ") cannot be destroyed since the Servicios " + serviciosCollectionOrphanCheckServicios + " in its serviciosCollection field has a non-nullable idEstadoServiciosFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(serviciosEstado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiciosEstado> findServiciosEstadoEntities() {
        return findServiciosEstadoEntities(true, -1, -1);
    }

    public List<ServiciosEstado> findServiciosEstadoEntities(int maxResults, int firstResult) {
        return findServiciosEstadoEntities(false, maxResults, firstResult);
    }

    private List<ServiciosEstado> findServiciosEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiciosEstado.class));
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

    public ServiciosEstado findServiciosEstado(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiciosEstado.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiciosEstado> rt = cq.from(ServiciosEstado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
