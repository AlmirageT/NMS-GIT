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
import Modelo.ServiciosEstado;
import Modelo.ServicioTipos;
import Modelo.Usuarios;
import Modelo.Reportes;
import Modelo.Servicios;
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
public class ServiciosJpaController implements Serializable {

    public ServiciosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("NoMasAccidentesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicios servicios) throws PreexistingEntityException, Exception {
        if (servicios.getReportesCollection() == null) {
            servicios.setReportesCollection(new ArrayList<Reportes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiciosEstado idEstadoServiciosFk = servicios.getIdEstadoServiciosFk();
            if (idEstadoServiciosFk != null) {
                idEstadoServiciosFk = em.getReference(idEstadoServiciosFk.getClass(), idEstadoServiciosFk.getIdEstadoServicios());
                servicios.setIdEstadoServiciosFk(idEstadoServiciosFk);
            }
            ServicioTipos idTipoServicioFk = servicios.getIdTipoServicioFk();
            if (idTipoServicioFk != null) {
                idTipoServicioFk = em.getReference(idTipoServicioFk.getClass(), idTipoServicioFk.getIdTipoServicio());
                servicios.setIdTipoServicioFk(idTipoServicioFk);
            }
            Usuarios usuariosIdUsuario = servicios.getUsuariosIdUsuario();
            if (usuariosIdUsuario != null) {
                usuariosIdUsuario = em.getReference(usuariosIdUsuario.getClass(), usuariosIdUsuario.getIdUsuario());
                servicios.setUsuariosIdUsuario(usuariosIdUsuario);
            }
            Collection<Reportes> attachedReportesCollection = new ArrayList<Reportes>();
            for (Reportes reportesCollectionReportesToAttach : servicios.getReportesCollection()) {
                reportesCollectionReportesToAttach = em.getReference(reportesCollectionReportesToAttach.getClass(), reportesCollectionReportesToAttach.getIdReporte());
                attachedReportesCollection.add(reportesCollectionReportesToAttach);
            }
            servicios.setReportesCollection(attachedReportesCollection);
            em.persist(servicios);
            if (idEstadoServiciosFk != null) {
                idEstadoServiciosFk.getServiciosCollection().add(servicios);
                idEstadoServiciosFk = em.merge(idEstadoServiciosFk);
            }
            if (idTipoServicioFk != null) {
                idTipoServicioFk.getServiciosCollection().add(servicios);
                idTipoServicioFk = em.merge(idTipoServicioFk);
            }
            if (usuariosIdUsuario != null) {
                usuariosIdUsuario.getServiciosCollection().add(servicios);
                usuariosIdUsuario = em.merge(usuariosIdUsuario);
            }
            for (Reportes reportesCollectionReportes : servicios.getReportesCollection()) {
                Servicios oldServiciosIdServicioOfReportesCollectionReportes = reportesCollectionReportes.getServiciosIdServicio();
                reportesCollectionReportes.setServiciosIdServicio(servicios);
                reportesCollectionReportes = em.merge(reportesCollectionReportes);
                if (oldServiciosIdServicioOfReportesCollectionReportes != null) {
                    oldServiciosIdServicioOfReportesCollectionReportes.getReportesCollection().remove(reportesCollectionReportes);
                    oldServiciosIdServicioOfReportesCollectionReportes = em.merge(oldServiciosIdServicioOfReportesCollectionReportes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicios(servicios.getIdServicio()) != null) {
                throw new PreexistingEntityException("Servicios " + servicios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicios servicios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicios persistentServicios = em.find(Servicios.class, servicios.getIdServicio());
            ServiciosEstado idEstadoServiciosFkOld = persistentServicios.getIdEstadoServiciosFk();
            ServiciosEstado idEstadoServiciosFkNew = servicios.getIdEstadoServiciosFk();
            ServicioTipos idTipoServicioFkOld = persistentServicios.getIdTipoServicioFk();
            ServicioTipos idTipoServicioFkNew = servicios.getIdTipoServicioFk();
            Usuarios usuariosIdUsuarioOld = persistentServicios.getUsuariosIdUsuario();
            Usuarios usuariosIdUsuarioNew = servicios.getUsuariosIdUsuario();
            Collection<Reportes> reportesCollectionOld = persistentServicios.getReportesCollection();
            Collection<Reportes> reportesCollectionNew = servicios.getReportesCollection();
            List<String> illegalOrphanMessages = null;
            for (Reportes reportesCollectionOldReportes : reportesCollectionOld) {
                if (!reportesCollectionNew.contains(reportesCollectionOldReportes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reportes " + reportesCollectionOldReportes + " since its serviciosIdServicio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEstadoServiciosFkNew != null) {
                idEstadoServiciosFkNew = em.getReference(idEstadoServiciosFkNew.getClass(), idEstadoServiciosFkNew.getIdEstadoServicios());
                servicios.setIdEstadoServiciosFk(idEstadoServiciosFkNew);
            }
            if (idTipoServicioFkNew != null) {
                idTipoServicioFkNew = em.getReference(idTipoServicioFkNew.getClass(), idTipoServicioFkNew.getIdTipoServicio());
                servicios.setIdTipoServicioFk(idTipoServicioFkNew);
            }
            if (usuariosIdUsuarioNew != null) {
                usuariosIdUsuarioNew = em.getReference(usuariosIdUsuarioNew.getClass(), usuariosIdUsuarioNew.getIdUsuario());
                servicios.setUsuariosIdUsuario(usuariosIdUsuarioNew);
            }
            Collection<Reportes> attachedReportesCollectionNew = new ArrayList<Reportes>();
            for (Reportes reportesCollectionNewReportesToAttach : reportesCollectionNew) {
                reportesCollectionNewReportesToAttach = em.getReference(reportesCollectionNewReportesToAttach.getClass(), reportesCollectionNewReportesToAttach.getIdReporte());
                attachedReportesCollectionNew.add(reportesCollectionNewReportesToAttach);
            }
            reportesCollectionNew = attachedReportesCollectionNew;
            servicios.setReportesCollection(reportesCollectionNew);
            servicios = em.merge(servicios);
            if (idEstadoServiciosFkOld != null && !idEstadoServiciosFkOld.equals(idEstadoServiciosFkNew)) {
                idEstadoServiciosFkOld.getServiciosCollection().remove(servicios);
                idEstadoServiciosFkOld = em.merge(idEstadoServiciosFkOld);
            }
            if (idEstadoServiciosFkNew != null && !idEstadoServiciosFkNew.equals(idEstadoServiciosFkOld)) {
                idEstadoServiciosFkNew.getServiciosCollection().add(servicios);
                idEstadoServiciosFkNew = em.merge(idEstadoServiciosFkNew);
            }
            if (idTipoServicioFkOld != null && !idTipoServicioFkOld.equals(idTipoServicioFkNew)) {
                idTipoServicioFkOld.getServiciosCollection().remove(servicios);
                idTipoServicioFkOld = em.merge(idTipoServicioFkOld);
            }
            if (idTipoServicioFkNew != null && !idTipoServicioFkNew.equals(idTipoServicioFkOld)) {
                idTipoServicioFkNew.getServiciosCollection().add(servicios);
                idTipoServicioFkNew = em.merge(idTipoServicioFkNew);
            }
            if (usuariosIdUsuarioOld != null && !usuariosIdUsuarioOld.equals(usuariosIdUsuarioNew)) {
                usuariosIdUsuarioOld.getServiciosCollection().remove(servicios);
                usuariosIdUsuarioOld = em.merge(usuariosIdUsuarioOld);
            }
            if (usuariosIdUsuarioNew != null && !usuariosIdUsuarioNew.equals(usuariosIdUsuarioOld)) {
                usuariosIdUsuarioNew.getServiciosCollection().add(servicios);
                usuariosIdUsuarioNew = em.merge(usuariosIdUsuarioNew);
            }
            for (Reportes reportesCollectionNewReportes : reportesCollectionNew) {
                if (!reportesCollectionOld.contains(reportesCollectionNewReportes)) {
                    Servicios oldServiciosIdServicioOfReportesCollectionNewReportes = reportesCollectionNewReportes.getServiciosIdServicio();
                    reportesCollectionNewReportes.setServiciosIdServicio(servicios);
                    reportesCollectionNewReportes = em.merge(reportesCollectionNewReportes);
                    if (oldServiciosIdServicioOfReportesCollectionNewReportes != null && !oldServiciosIdServicioOfReportesCollectionNewReportes.equals(servicios)) {
                        oldServiciosIdServicioOfReportesCollectionNewReportes.getReportesCollection().remove(reportesCollectionNewReportes);
                        oldServiciosIdServicioOfReportesCollectionNewReportes = em.merge(oldServiciosIdServicioOfReportesCollectionNewReportes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = servicios.getIdServicio();
                if (findServicios(id) == null) {
                    throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.");
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
            Servicios servicios;
            try {
                servicios = em.getReference(Servicios.class, id);
                servicios.getIdServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Reportes> reportesCollectionOrphanCheck = servicios.getReportesCollection();
            for (Reportes reportesCollectionOrphanCheckReportes : reportesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicios (" + servicios + ") cannot be destroyed since the Reportes " + reportesCollectionOrphanCheckReportes + " in its reportesCollection field has a non-nullable serviciosIdServicio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ServiciosEstado idEstadoServiciosFk = servicios.getIdEstadoServiciosFk();
            if (idEstadoServiciosFk != null) {
                idEstadoServiciosFk.getServiciosCollection().remove(servicios);
                idEstadoServiciosFk = em.merge(idEstadoServiciosFk);
            }
            ServicioTipos idTipoServicioFk = servicios.getIdTipoServicioFk();
            if (idTipoServicioFk != null) {
                idTipoServicioFk.getServiciosCollection().remove(servicios);
                idTipoServicioFk = em.merge(idTipoServicioFk);
            }
            Usuarios usuariosIdUsuario = servicios.getUsuariosIdUsuario();
            if (usuariosIdUsuario != null) {
                usuariosIdUsuario.getServiciosCollection().remove(servicios);
                usuariosIdUsuario = em.merge(usuariosIdUsuario);
            }
            em.remove(servicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicios> findServiciosEntities() {
        return findServiciosEntities(true, -1, -1);
    }

    public List<Servicios> findServiciosEntities(int maxResults, int firstResult) {
        return findServiciosEntities(false, maxResults, firstResult);
    }

    private List<Servicios> findServiciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicios.class));
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

    public Servicios findServicios(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicios.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicios> rt = cq.from(Servicios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
