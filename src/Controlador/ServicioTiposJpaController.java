/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.IllegalOrphanException;
import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import Modelo.ServicioTipos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Servicios;
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
public class ServicioTiposJpaController implements Serializable {

    public ServicioTiposJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServicioTipos servicioTipos) throws PreexistingEntityException, Exception {
        if (servicioTipos.getServiciosCollection() == null) {
            servicioTipos.setServiciosCollection(new ArrayList<Servicios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Servicios> attachedServiciosCollection = new ArrayList<Servicios>();
            for (Servicios serviciosCollectionServiciosToAttach : servicioTipos.getServiciosCollection()) {
                serviciosCollectionServiciosToAttach = em.getReference(serviciosCollectionServiciosToAttach.getClass(), serviciosCollectionServiciosToAttach.getIdServicio());
                attachedServiciosCollection.add(serviciosCollectionServiciosToAttach);
            }
            servicioTipos.setServiciosCollection(attachedServiciosCollection);
            em.persist(servicioTipos);
            for (Servicios serviciosCollectionServicios : servicioTipos.getServiciosCollection()) {
                ServicioTipos oldIdTipoServicioFkOfServiciosCollectionServicios = serviciosCollectionServicios.getIdTipoServicioFk();
                serviciosCollectionServicios.setIdTipoServicioFk(servicioTipos);
                serviciosCollectionServicios = em.merge(serviciosCollectionServicios);
                if (oldIdTipoServicioFkOfServiciosCollectionServicios != null) {
                    oldIdTipoServicioFkOfServiciosCollectionServicios.getServiciosCollection().remove(serviciosCollectionServicios);
                    oldIdTipoServicioFkOfServiciosCollectionServicios = em.merge(oldIdTipoServicioFkOfServiciosCollectionServicios);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicioTipos(servicioTipos.getIdTipoServicio()) != null) {
                throw new PreexistingEntityException("ServicioTipos " + servicioTipos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServicioTipos servicioTipos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServicioTipos persistentServicioTipos = em.find(ServicioTipos.class, servicioTipos.getIdTipoServicio());
            Collection<Servicios> serviciosCollectionOld = persistentServicioTipos.getServiciosCollection();
            Collection<Servicios> serviciosCollectionNew = servicioTipos.getServiciosCollection();
            List<String> illegalOrphanMessages = null;
            for (Servicios serviciosCollectionOldServicios : serviciosCollectionOld) {
                if (!serviciosCollectionNew.contains(serviciosCollectionOldServicios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Servicios " + serviciosCollectionOldServicios + " since its idTipoServicioFk field is not nullable.");
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
            servicioTipos.setServiciosCollection(serviciosCollectionNew);
            servicioTipos = em.merge(servicioTipos);
            for (Servicios serviciosCollectionNewServicios : serviciosCollectionNew) {
                if (!serviciosCollectionOld.contains(serviciosCollectionNewServicios)) {
                    ServicioTipos oldIdTipoServicioFkOfServiciosCollectionNewServicios = serviciosCollectionNewServicios.getIdTipoServicioFk();
                    serviciosCollectionNewServicios.setIdTipoServicioFk(servicioTipos);
                    serviciosCollectionNewServicios = em.merge(serviciosCollectionNewServicios);
                    if (oldIdTipoServicioFkOfServiciosCollectionNewServicios != null && !oldIdTipoServicioFkOfServiciosCollectionNewServicios.equals(servicioTipos)) {
                        oldIdTipoServicioFkOfServiciosCollectionNewServicios.getServiciosCollection().remove(serviciosCollectionNewServicios);
                        oldIdTipoServicioFkOfServiciosCollectionNewServicios = em.merge(oldIdTipoServicioFkOfServiciosCollectionNewServicios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = servicioTipos.getIdTipoServicio();
                if (findServicioTipos(id) == null) {
                    throw new NonexistentEntityException("The servicioTipos with id " + id + " no longer exists.");
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
            ServicioTipos servicioTipos;
            try {
                servicioTipos = em.getReference(ServicioTipos.class, id);
                servicioTipos.getIdTipoServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicioTipos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Servicios> serviciosCollectionOrphanCheck = servicioTipos.getServiciosCollection();
            for (Servicios serviciosCollectionOrphanCheckServicios : serviciosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServicioTipos (" + servicioTipos + ") cannot be destroyed since the Servicios " + serviciosCollectionOrphanCheckServicios + " in its serviciosCollection field has a non-nullable idTipoServicioFk field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(servicioTipos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServicioTipos> findServicioTiposEntities() {
        return findServicioTiposEntities(true, -1, -1);
    }

    public List<ServicioTipos> findServicioTiposEntities(int maxResults, int firstResult) {
        return findServicioTiposEntities(false, maxResults, firstResult);
    }

    private List<ServicioTipos> findServicioTiposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServicioTipos.class));
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

    public ServicioTipos findServicioTipos(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServicioTipos.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioTiposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServicioTipos> rt = cq.from(ServicioTipos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
