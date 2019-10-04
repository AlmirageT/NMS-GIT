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
import Modelo.ContratoEstados;
import Modelo.Contratos;
import Modelo.Empresas;
import Modelo.Pagos;
import Modelo.Usuarios;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ivans
 */
public class ContratosJpaController implements Serializable {

    public ContratosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contratos contratos) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Empresas empresasIdEmpresaOrphanCheck = contratos.getEmpresasIdEmpresa();
        if (empresasIdEmpresaOrphanCheck != null) {
            Contratos oldContratosOfEmpresasIdEmpresa = empresasIdEmpresaOrphanCheck.getContratos();
            if (oldContratosOfEmpresasIdEmpresa != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Empresas " + empresasIdEmpresaOrphanCheck + " already has an item of type Contratos whose empresasIdEmpresa column cannot be null. Please make another selection for the empresasIdEmpresa field.");
            }
        }
        Pagos pagosIdPagoOrphanCheck = contratos.getPagosIdPago();
        if (pagosIdPagoOrphanCheck != null) {
            Contratos oldContratosOfPagosIdPago = pagosIdPagoOrphanCheck.getContratos();
            if (oldContratosOfPagosIdPago != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Pagos " + pagosIdPagoOrphanCheck + " already has an item of type Contratos whose pagosIdPago column cannot be null. Please make another selection for the pagosIdPago field.");
            }
        }
        Usuarios usuariosIdUsuarioOrphanCheck = contratos.getUsuariosIdUsuario();
        if (usuariosIdUsuarioOrphanCheck != null) {
            Contratos oldContratosOfUsuariosIdUsuario = usuariosIdUsuarioOrphanCheck.getContratos();
            if (oldContratosOfUsuariosIdUsuario != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Usuarios " + usuariosIdUsuarioOrphanCheck + " already has an item of type Contratos whose usuariosIdUsuario column cannot be null. Please make another selection for the usuariosIdUsuario field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ContratoEstados idContratoEstadoFk = contratos.getIdContratoEstadoFk();
            if (idContratoEstadoFk != null) {
                idContratoEstadoFk = em.getReference(idContratoEstadoFk.getClass(), idContratoEstadoFk.getIdContratoEstado());
                contratos.setIdContratoEstadoFk(idContratoEstadoFk);
            }
            Empresas empresasIdEmpresa = contratos.getEmpresasIdEmpresa();
            if (empresasIdEmpresa != null) {
                empresasIdEmpresa = em.getReference(empresasIdEmpresa.getClass(), empresasIdEmpresa.getIdEmpresa());
                contratos.setEmpresasIdEmpresa(empresasIdEmpresa);
            }
            Pagos pagosIdPago = contratos.getPagosIdPago();
            if (pagosIdPago != null) {
                pagosIdPago = em.getReference(pagosIdPago.getClass(), pagosIdPago.getIdPago());
                contratos.setPagosIdPago(pagosIdPago);
            }
            Usuarios usuariosIdUsuario = contratos.getUsuariosIdUsuario();
            if (usuariosIdUsuario != null) {
                usuariosIdUsuario = em.getReference(usuariosIdUsuario.getClass(), usuariosIdUsuario.getIdUsuario());
                contratos.setUsuariosIdUsuario(usuariosIdUsuario);
            }
            em.persist(contratos);
            if (idContratoEstadoFk != null) {
                idContratoEstadoFk.getContratosCollection().add(contratos);
                idContratoEstadoFk = em.merge(idContratoEstadoFk);
            }
            if (empresasIdEmpresa != null) {
                empresasIdEmpresa.setContratos(contratos);
                empresasIdEmpresa = em.merge(empresasIdEmpresa);
            }
            if (pagosIdPago != null) {
                pagosIdPago.setContratos(contratos);
                pagosIdPago = em.merge(pagosIdPago);
            }
            if (usuariosIdUsuario != null) {
                usuariosIdUsuario.setContratos(contratos);
                usuariosIdUsuario = em.merge(usuariosIdUsuario);
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

    public void edit(Contratos contratos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contratos persistentContratos = em.find(Contratos.class, contratos.getIdContrato());
            ContratoEstados idContratoEstadoFkOld = persistentContratos.getIdContratoEstadoFk();
            ContratoEstados idContratoEstadoFkNew = contratos.getIdContratoEstadoFk();
            Empresas empresasIdEmpresaOld = persistentContratos.getEmpresasIdEmpresa();
            Empresas empresasIdEmpresaNew = contratos.getEmpresasIdEmpresa();
            Pagos pagosIdPagoOld = persistentContratos.getPagosIdPago();
            Pagos pagosIdPagoNew = contratos.getPagosIdPago();
            Usuarios usuariosIdUsuarioOld = persistentContratos.getUsuariosIdUsuario();
            Usuarios usuariosIdUsuarioNew = contratos.getUsuariosIdUsuario();
            List<String> illegalOrphanMessages = null;
            if (empresasIdEmpresaNew != null && !empresasIdEmpresaNew.equals(empresasIdEmpresaOld)) {
                Contratos oldContratosOfEmpresasIdEmpresa = empresasIdEmpresaNew.getContratos();
                if (oldContratosOfEmpresasIdEmpresa != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Empresas " + empresasIdEmpresaNew + " already has an item of type Contratos whose empresasIdEmpresa column cannot be null. Please make another selection for the empresasIdEmpresa field.");
                }
            }
            if (pagosIdPagoNew != null && !pagosIdPagoNew.equals(pagosIdPagoOld)) {
                Contratos oldContratosOfPagosIdPago = pagosIdPagoNew.getContratos();
                if (oldContratosOfPagosIdPago != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Pagos " + pagosIdPagoNew + " already has an item of type Contratos whose pagosIdPago column cannot be null. Please make another selection for the pagosIdPago field.");
                }
            }
            if (usuariosIdUsuarioNew != null && !usuariosIdUsuarioNew.equals(usuariosIdUsuarioOld)) {
                Contratos oldContratosOfUsuariosIdUsuario = usuariosIdUsuarioNew.getContratos();
                if (oldContratosOfUsuariosIdUsuario != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Usuarios " + usuariosIdUsuarioNew + " already has an item of type Contratos whose usuariosIdUsuario column cannot be null. Please make another selection for the usuariosIdUsuario field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idContratoEstadoFkNew != null) {
                idContratoEstadoFkNew = em.getReference(idContratoEstadoFkNew.getClass(), idContratoEstadoFkNew.getIdContratoEstado());
                contratos.setIdContratoEstadoFk(idContratoEstadoFkNew);
            }
            if (empresasIdEmpresaNew != null) {
                empresasIdEmpresaNew = em.getReference(empresasIdEmpresaNew.getClass(), empresasIdEmpresaNew.getIdEmpresa());
                contratos.setEmpresasIdEmpresa(empresasIdEmpresaNew);
            }
            if (pagosIdPagoNew != null) {
                pagosIdPagoNew = em.getReference(pagosIdPagoNew.getClass(), pagosIdPagoNew.getIdPago());
                contratos.setPagosIdPago(pagosIdPagoNew);
            }
            if (usuariosIdUsuarioNew != null) {
                usuariosIdUsuarioNew = em.getReference(usuariosIdUsuarioNew.getClass(), usuariosIdUsuarioNew.getIdUsuario());
                contratos.setUsuariosIdUsuario(usuariosIdUsuarioNew);
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
            if (empresasIdEmpresaOld != null && !empresasIdEmpresaOld.equals(empresasIdEmpresaNew)) {
                empresasIdEmpresaOld.setContratos(null);
                empresasIdEmpresaOld = em.merge(empresasIdEmpresaOld);
            }
            if (empresasIdEmpresaNew != null && !empresasIdEmpresaNew.equals(empresasIdEmpresaOld)) {
                empresasIdEmpresaNew.setContratos(contratos);
                empresasIdEmpresaNew = em.merge(empresasIdEmpresaNew);
            }
            if (pagosIdPagoOld != null && !pagosIdPagoOld.equals(pagosIdPagoNew)) {
                pagosIdPagoOld.setContratos(null);
                pagosIdPagoOld = em.merge(pagosIdPagoOld);
            }
            if (pagosIdPagoNew != null && !pagosIdPagoNew.equals(pagosIdPagoOld)) {
                pagosIdPagoNew.setContratos(contratos);
                pagosIdPagoNew = em.merge(pagosIdPagoNew);
            }
            if (usuariosIdUsuarioOld != null && !usuariosIdUsuarioOld.equals(usuariosIdUsuarioNew)) {
                usuariosIdUsuarioOld.setContratos(null);
                usuariosIdUsuarioOld = em.merge(usuariosIdUsuarioOld);
            }
            if (usuariosIdUsuarioNew != null && !usuariosIdUsuarioNew.equals(usuariosIdUsuarioOld)) {
                usuariosIdUsuarioNew.setContratos(contratos);
                usuariosIdUsuarioNew = em.merge(usuariosIdUsuarioNew);
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
            Empresas empresasIdEmpresa = contratos.getEmpresasIdEmpresa();
            if (empresasIdEmpresa != null) {
                empresasIdEmpresa.setContratos(null);
                empresasIdEmpresa = em.merge(empresasIdEmpresa);
            }
            Pagos pagosIdPago = contratos.getPagosIdPago();
            if (pagosIdPago != null) {
                pagosIdPago.setContratos(null);
                pagosIdPago = em.merge(pagosIdPago);
            }
            Usuarios usuariosIdUsuario = contratos.getUsuariosIdUsuario();
            if (usuariosIdUsuario != null) {
                usuariosIdUsuario.setContratos(null);
                usuariosIdUsuario = em.merge(usuariosIdUsuario);
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
