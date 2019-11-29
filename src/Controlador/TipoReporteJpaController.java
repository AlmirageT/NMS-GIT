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
import Modelo.Reportes;
import Modelo.TipoReporte;
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
public class TipoReporteJpaController implements Serializable {

    public TipoReporteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("NoMasAccidentesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoReporte tipoReporte) throws PreexistingEntityException, Exception {
        if (tipoReporte.getReportesCollection() == null) {
            tipoReporte.setReportesCollection(new ArrayList<Reportes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Reportes> attachedReportesCollection = new ArrayList<Reportes>();
            for (Reportes reportesCollectionReportesToAttach : tipoReporte.getReportesCollection()) {
                reportesCollectionReportesToAttach = em.getReference(reportesCollectionReportesToAttach.getClass(), reportesCollectionReportesToAttach.getIdReporte());
                attachedReportesCollection.add(reportesCollectionReportesToAttach);
            }
            tipoReporte.setReportesCollection(attachedReportesCollection);
            em.persist(tipoReporte);
            for (Reportes reportesCollectionReportes : tipoReporte.getReportesCollection()) {
                TipoReporte oldTipoReporteIdTipoReporteOfReportesCollectionReportes = reportesCollectionReportes.getTipoReporteIdTipoReporte();
                reportesCollectionReportes.setTipoReporteIdTipoReporte(tipoReporte);
                reportesCollectionReportes = em.merge(reportesCollectionReportes);
                if (oldTipoReporteIdTipoReporteOfReportesCollectionReportes != null) {
                    oldTipoReporteIdTipoReporteOfReportesCollectionReportes.getReportesCollection().remove(reportesCollectionReportes);
                    oldTipoReporteIdTipoReporteOfReportesCollectionReportes = em.merge(oldTipoReporteIdTipoReporteOfReportesCollectionReportes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoReporte(tipoReporte.getIdTipoReporte()) != null) {
                throw new PreexistingEntityException("TipoReporte " + tipoReporte + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoReporte tipoReporte) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoReporte persistentTipoReporte = em.find(TipoReporte.class, tipoReporte.getIdTipoReporte());
            Collection<Reportes> reportesCollectionOld = persistentTipoReporte.getReportesCollection();
            Collection<Reportes> reportesCollectionNew = tipoReporte.getReportesCollection();
            List<String> illegalOrphanMessages = null;
            for (Reportes reportesCollectionOldReportes : reportesCollectionOld) {
                if (!reportesCollectionNew.contains(reportesCollectionOldReportes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reportes " + reportesCollectionOldReportes + " since its tipoReporteIdTipoReporte field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Reportes> attachedReportesCollectionNew = new ArrayList<Reportes>();
            for (Reportes reportesCollectionNewReportesToAttach : reportesCollectionNew) {
                reportesCollectionNewReportesToAttach = em.getReference(reportesCollectionNewReportesToAttach.getClass(), reportesCollectionNewReportesToAttach.getIdReporte());
                attachedReportesCollectionNew.add(reportesCollectionNewReportesToAttach);
            }
            reportesCollectionNew = attachedReportesCollectionNew;
            tipoReporte.setReportesCollection(reportesCollectionNew);
            tipoReporte = em.merge(tipoReporte);
            for (Reportes reportesCollectionNewReportes : reportesCollectionNew) {
                if (!reportesCollectionOld.contains(reportesCollectionNewReportes)) {
                    TipoReporte oldTipoReporteIdTipoReporteOfReportesCollectionNewReportes = reportesCollectionNewReportes.getTipoReporteIdTipoReporte();
                    reportesCollectionNewReportes.setTipoReporteIdTipoReporte(tipoReporte);
                    reportesCollectionNewReportes = em.merge(reportesCollectionNewReportes);
                    if (oldTipoReporteIdTipoReporteOfReportesCollectionNewReportes != null && !oldTipoReporteIdTipoReporteOfReportesCollectionNewReportes.equals(tipoReporte)) {
                        oldTipoReporteIdTipoReporteOfReportesCollectionNewReportes.getReportesCollection().remove(reportesCollectionNewReportes);
                        oldTipoReporteIdTipoReporteOfReportesCollectionNewReportes = em.merge(oldTipoReporteIdTipoReporteOfReportesCollectionNewReportes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = tipoReporte.getIdTipoReporte();
                if (findTipoReporte(id) == null) {
                    throw new NonexistentEntityException("The tipoReporte with id " + id + " no longer exists.");
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
            TipoReporte tipoReporte;
            try {
                tipoReporte = em.getReference(TipoReporte.class, id);
                tipoReporte.getIdTipoReporte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoReporte with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Reportes> reportesCollectionOrphanCheck = tipoReporte.getReportesCollection();
            for (Reportes reportesCollectionOrphanCheckReportes : reportesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoReporte (" + tipoReporte + ") cannot be destroyed since the Reportes " + reportesCollectionOrphanCheckReportes + " in its reportesCollection field has a non-nullable tipoReporteIdTipoReporte field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoReporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoReporte> findTipoReporteEntities() {
        return findTipoReporteEntities(true, -1, -1);
    }

    public List<TipoReporte> findTipoReporteEntities(int maxResults, int firstResult) {
        return findTipoReporteEntities(false, maxResults, firstResult);
    }

    private List<TipoReporte> findTipoReporteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoReporte.class));
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

    public TipoReporte findTipoReporte(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoReporte.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoReporteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoReporte> rt = cq.from(TipoReporte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
