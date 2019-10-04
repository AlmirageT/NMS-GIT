/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.exceptions.NonexistentEntityException;
import Controlador.exceptions.PreexistingEntityException;
import Modelo.Checklist;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Empresas;
import Modelo.Usuarios;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ivans
 */
public class ChecklistJpaController implements Serializable {

    public ChecklistJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Checklist checklist) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresas empresasIdEmpresa = checklist.getEmpresasIdEmpresa();
            if (empresasIdEmpresa != null) {
                empresasIdEmpresa = em.getReference(empresasIdEmpresa.getClass(), empresasIdEmpresa.getIdEmpresa());
                checklist.setEmpresasIdEmpresa(empresasIdEmpresa);
            }
            Usuarios usuariosIdUsuario = checklist.getUsuariosIdUsuario();
            if (usuariosIdUsuario != null) {
                usuariosIdUsuario = em.getReference(usuariosIdUsuario.getClass(), usuariosIdUsuario.getIdUsuario());
                checklist.setUsuariosIdUsuario(usuariosIdUsuario);
            }
            em.persist(checklist);
            if (empresasIdEmpresa != null) {
                empresasIdEmpresa.getChecklistCollection().add(checklist);
                empresasIdEmpresa = em.merge(empresasIdEmpresa);
            }
            if (usuariosIdUsuario != null) {
                usuariosIdUsuario.getChecklistCollection().add(checklist);
                usuariosIdUsuario = em.merge(usuariosIdUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findChecklist(checklist.getIdChecklist()) != null) {
                throw new PreexistingEntityException("Checklist " + checklist + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Checklist checklist) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Checklist persistentChecklist = em.find(Checklist.class, checklist.getIdChecklist());
            Empresas empresasIdEmpresaOld = persistentChecklist.getEmpresasIdEmpresa();
            Empresas empresasIdEmpresaNew = checklist.getEmpresasIdEmpresa();
            Usuarios usuariosIdUsuarioOld = persistentChecklist.getUsuariosIdUsuario();
            Usuarios usuariosIdUsuarioNew = checklist.getUsuariosIdUsuario();
            if (empresasIdEmpresaNew != null) {
                empresasIdEmpresaNew = em.getReference(empresasIdEmpresaNew.getClass(), empresasIdEmpresaNew.getIdEmpresa());
                checklist.setEmpresasIdEmpresa(empresasIdEmpresaNew);
            }
            if (usuariosIdUsuarioNew != null) {
                usuariosIdUsuarioNew = em.getReference(usuariosIdUsuarioNew.getClass(), usuariosIdUsuarioNew.getIdUsuario());
                checklist.setUsuariosIdUsuario(usuariosIdUsuarioNew);
            }
            checklist = em.merge(checklist);
            if (empresasIdEmpresaOld != null && !empresasIdEmpresaOld.equals(empresasIdEmpresaNew)) {
                empresasIdEmpresaOld.getChecklistCollection().remove(checklist);
                empresasIdEmpresaOld = em.merge(empresasIdEmpresaOld);
            }
            if (empresasIdEmpresaNew != null && !empresasIdEmpresaNew.equals(empresasIdEmpresaOld)) {
                empresasIdEmpresaNew.getChecklistCollection().add(checklist);
                empresasIdEmpresaNew = em.merge(empresasIdEmpresaNew);
            }
            if (usuariosIdUsuarioOld != null && !usuariosIdUsuarioOld.equals(usuariosIdUsuarioNew)) {
                usuariosIdUsuarioOld.getChecklistCollection().remove(checklist);
                usuariosIdUsuarioOld = em.merge(usuariosIdUsuarioOld);
            }
            if (usuariosIdUsuarioNew != null && !usuariosIdUsuarioNew.equals(usuariosIdUsuarioOld)) {
                usuariosIdUsuarioNew.getChecklistCollection().add(checklist);
                usuariosIdUsuarioNew = em.merge(usuariosIdUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = checklist.getIdChecklist();
                if (findChecklist(id) == null) {
                    throw new NonexistentEntityException("The checklist with id " + id + " no longer exists.");
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
            Checklist checklist;
            try {
                checklist = em.getReference(Checklist.class, id);
                checklist.getIdChecklist();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The checklist with id " + id + " no longer exists.", enfe);
            }
            Empresas empresasIdEmpresa = checklist.getEmpresasIdEmpresa();
            if (empresasIdEmpresa != null) {
                empresasIdEmpresa.getChecklistCollection().remove(checklist);
                empresasIdEmpresa = em.merge(empresasIdEmpresa);
            }
            Usuarios usuariosIdUsuario = checklist.getUsuariosIdUsuario();
            if (usuariosIdUsuario != null) {
                usuariosIdUsuario.getChecklistCollection().remove(checklist);
                usuariosIdUsuario = em.merge(usuariosIdUsuario);
            }
            em.remove(checklist);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Checklist> findChecklistEntities() {
        return findChecklistEntities(true, -1, -1);
    }

    public List<Checklist> findChecklistEntities(int maxResults, int firstResult) {
        return findChecklistEntities(false, maxResults, firstResult);
    }

    private List<Checklist> findChecklistEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Checklist.class));
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

    public Checklist findChecklist(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Checklist.class, id);
        } finally {
            em.close();
        }
    }

    public int getChecklistCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Checklist> rt = cq.from(Checklist.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
