/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.ContratoEstados;
import Modelo.Contratos;
import Modelo.Pagos;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ivans
 */
public class ContratosJpaController implements Serializable {

    public ContratosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("NoMasAccidentesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contratos contratos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContratoEstados idContratoEstadoFk = contratos.getIdContratoEstadoFk();
            if (idContratoEstadoFk != null) {
                idContratoEstadoFk = em.getReference(idContratoEstadoFk.getClass(), idContratoEstadoFk.getIdContratoEstado());
                contratos.setIdContratoEstadoFk(idContratoEstadoFk);
            }
            Pagos pagosIdPago = contratos.getPagosIdPago();
            if (pagosIdPago != null) {
                pagosIdPago = em.getReference(pagosIdPago.getClass(), pagosIdPago.getIdPago());
                contratos.setPagosIdPago(pagosIdPago);
            }
            em.persist(contratos);
            if (idContratoEstadoFk != null) {
                idContratoEstadoFk.getContratosCollection().add(contratos);
                idContratoEstadoFk = em.merge(idContratoEstadoFk);
            }
            if (pagosIdPago != null) {
                Contratos oldContratosOfPagosIdPago = pagosIdPago.getContratos();
                if (oldContratosOfPagosIdPago != null) {
                    oldContratosOfPagosIdPago.setPagosIdPago(null);
                    oldContratosOfPagosIdPago = em.merge(oldContratosOfPagosIdPago);
                }
                pagosIdPago.setContratos(contratos);
                pagosIdPago = em.merge(pagosIdPago);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findContratos(contratos.getIdContrato()) != null) {
                throw new PreexistingEntityException("Contratos " + contratos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contratos contratos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contratos persistentContratos = em.find(Contratos.class, contratos.getIdContrato());
            ContratoEstados idContratoEstadoFkOld = persistentContratos.getIdContratoEstadoFk();
            ContratoEstados idContratoEstadoFkNew = contratos.getIdContratoEstadoFk();
            Pagos pagosIdPagoOld = persistentContratos.getPagosIdPago();
            Pagos pagosIdPagoNew = contratos.getPagosIdPago();
            if (idContratoEstadoFkNew != null) {
                idContratoEstadoFkNew = em.getReference(idContratoEstadoFkNew.getClass(), idContratoEstadoFkNew.getIdContratoEstado());
                contratos.setIdContratoEstadoFk(idContratoEstadoFkNew);
            }
            if (pagosIdPagoNew != null) {
                pagosIdPagoNew = em.getReference(pagosIdPagoNew.getClass(), pagosIdPagoNew.getIdPago());
                contratos.setPagosIdPago(pagosIdPagoNew);
            }
            contratos = em.merge(contratos);
            if (idContratoEstadoFkOld != null && !idContratoEstadoFkOld.equals(idContratoEstadoFkNew)) {
                idContratoEstadoFkOld.getContratosCollection().remove(contratos);
                idContratoEstadoFkOld = em.merge(idContratoEstadoFkOld);
            }
            if (idContratoEstadoFkNew != null && !idContratoEstadoFkNew.equals(idContratoEstadoFkOld)) {
                idContratoEstadoFkNew.getContratosCollection().add(contratos);
                idContratoEstadoFkNew = em.merge(idContratoEstadoFkNew);
            }
            if (pagosIdPagoOld != null && !pagosIdPagoOld.equals(pagosIdPagoNew)) {
                pagosIdPagoOld.setContratos(null);
                pagosIdPagoOld = em.merge(pagosIdPagoOld);
            }
            if (pagosIdPagoNew != null && !pagosIdPagoNew.equals(pagosIdPagoOld)) {
                Contratos oldContratosOfPagosIdPago = pagosIdPagoNew.getContratos();
                if (oldContratosOfPagosIdPago != null) {
                    oldContratosOfPagosIdPago.setPagosIdPago(null);
                    oldContratosOfPagosIdPago = em.merge(oldContratosOfPagosIdPago);
                }
                pagosIdPagoNew.setContratos(contratos);
                pagosIdPagoNew = em.merge(pagosIdPagoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = contratos.getIdContrato();
                if (findContratos(id) == null) {
                    throw new NonexistentEntityException("The contratos with id " + id + " no longer exists.");
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
            Contratos contratos;
            try {
                contratos = em.getReference(Contratos.class, id);
                contratos.getIdContrato();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contratos with id " + id + " no longer exists.", enfe);
            }
            ContratoEstados idContratoEstadoFk = contratos.getIdContratoEstadoFk();
            if (idContratoEstadoFk != null) {
                idContratoEstadoFk.getContratosCollection().remove(contratos);
                idContratoEstadoFk = em.merge(idContratoEstadoFk);
            }
            Pagos pagosIdPago = contratos.getPagosIdPago();
            if (pagosIdPago != null) {
                pagosIdPago.setContratos(null);
                pagosIdPago = em.merge(pagosIdPago);
            }
            em.remove(contratos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contratos> findContratosEntities() {
        return findContratosEntities(true, -1, -1);
    }

    public List<Contratos> findContratosEntities(int maxResults, int firstResult) {
        return findContratosEntities(false, maxResults, firstResult);
    }

    private List<Contratos> findContratosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contratos.class));
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
    
    public List<Contratos> buscarContrato(String rutCliente){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Contratos.findByRutCliente");
        query.setParameter("rutCliente", rutCliente);
        List<Contratos> lista = query.getResultList();
        return lista;
    }
    

    public Contratos findContratos(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contratos.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getContratosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contratos> rt = cq.from(Contratos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
