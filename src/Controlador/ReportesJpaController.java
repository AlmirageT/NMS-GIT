/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import Modelo.Reportes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Servicios;
import Modelo.TipoReporte;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ivans
 */
public class ReportesJpaController implements Serializable {

    public ReportesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reportes reportes) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicios serviciosIdServicio = reportes.getServiciosIdServicio();
            if (serviciosIdServicio != null) {
                serviciosIdServicio = em.getReference(serviciosIdServicio.getClass(), serviciosIdServicio.getIdServicio());
                reportes.setServiciosIdServicio(serviciosIdServicio);
            }
            TipoReporte tipoReporteIdTipoReporte = reportes.getTipoReporteIdTipoReporte();
            if (tipoReporteIdTipoReporte != null) {
                tipoReporteIdTipoReporte = em.getReference(tipoReporteIdTipoReporte.getClass(), tipoReporteIdTipoReporte.getIdTipoReporte());
                reportes.setTipoReporteIdTipoReporte(tipoReporteIdTipoReporte);
            }
            em.persist(reportes);
            if (serviciosIdServicio != null) {
                serviciosIdServicio.getReportesCollection().add(reportes);
                serviciosIdServicio = em.merge(serviciosIdServicio);
            }
            if (tipoReporteIdTipoReporte != null) {
                tipoReporteIdTipoReporte.getReportesCollection().add(reportes);
                tipoReporteIdTipoReporte = em.merge(tipoReporteIdTipoReporte);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReportes(reportes.getIdReporte()) != null) {
                throw new PreexistingEntityException("Reportes " + reportes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reportes reportes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reportes persistentReportes = em.find(Reportes.class, reportes.getIdReporte());
            Servicios serviciosIdServicioOld = persistentReportes.getServiciosIdServicio();
            Servicios serviciosIdServicioNew = reportes.getServiciosIdServicio();
            TipoReporte tipoReporteIdTipoReporteOld = persistentReportes.getTipoReporteIdTipoReporte();
            TipoReporte tipoReporteIdTipoReporteNew = reportes.getTipoReporteIdTipoReporte();
            if (serviciosIdServicioNew != null) {
                serviciosIdServicioNew = em.getReference(serviciosIdServicioNew.getClass(), serviciosIdServicioNew.getIdServicio());
                reportes.setServiciosIdServicio(serviciosIdServicioNew);
            }
            if (tipoReporteIdTipoReporteNew != null) {
                tipoReporteIdTipoReporteNew = em.getReference(tipoReporteIdTipoReporteNew.getClass(), tipoReporteIdTipoReporteNew.getIdTipoReporte());
                reportes.setTipoReporteIdTipoReporte(tipoReporteIdTipoReporteNew);
            }
            reportes = em.merge(reportes);
            if (serviciosIdServicioOld != null && !serviciosIdServicioOld.equals(serviciosIdServicioNew)) {
                serviciosIdServicioOld.getReportesCollection().remove(reportes);
                serviciosIdServicioOld = em.merge(serviciosIdServicioOld);
            }
            if (serviciosIdServicioNew != null && !serviciosIdServicioNew.equals(serviciosIdServicioOld)) {
                serviciosIdServicioNew.getReportesCollection().add(reportes);
                serviciosIdServicioNew = em.merge(serviciosIdServicioNew);
            }
            if (tipoReporteIdTipoReporteOld != null && !tipoReporteIdTipoReporteOld.equals(tipoReporteIdTipoReporteNew)) {
                tipoReporteIdTipoReporteOld.getReportesCollection().remove(reportes);
                tipoReporteIdTipoReporteOld = em.merge(tipoReporteIdTipoReporteOld);
            }
            if (tipoReporteIdTipoReporteNew != null && !tipoReporteIdTipoReporteNew.equals(tipoReporteIdTipoReporteOld)) {
                tipoReporteIdTipoReporteNew.getReportesCollection().add(reportes);
                tipoReporteIdTipoReporteNew = em.merge(tipoReporteIdTipoReporteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = reportes.getIdReporte();
                if (findReportes(id) == null) {
                    throw new NonexistentEntityException("The reportes with id " + id + " no longer exists.");
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
            Reportes reportes;
            try {
                reportes = em.getReference(Reportes.class, id);
                reportes.getIdReporte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reportes with id " + id + " no longer exists.", enfe);
            }
            Servicios serviciosIdServicio = reportes.getServiciosIdServicio();
            if (serviciosIdServicio != null) {
                serviciosIdServicio.getReportesCollection().remove(reportes);
                serviciosIdServicio = em.merge(serviciosIdServicio);
            }
            TipoReporte tipoReporteIdTipoReporte = reportes.getTipoReporteIdTipoReporte();
            if (tipoReporteIdTipoReporte != null) {
                tipoReporteIdTipoReporte.getReportesCollection().remove(reportes);
                tipoReporteIdTipoReporte = em.merge(tipoReporteIdTipoReporte);
            }
            em.remove(reportes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reportes> findReportesEntities() {
        return findReportesEntities(true, -1, -1);
    }

    public List<Reportes> findReportesEntities(int maxResults, int firstResult) {
        return findReportesEntities(false, maxResults, firstResult);
    }

    private List<Reportes> findReportesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reportes.class));
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

    public Reportes findReportes(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reportes.class, id);
        } finally {
            em.close();
        }
    }

    public int getReportesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reportes> rt = cq.from(Reportes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
