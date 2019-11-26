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
import Modelo.Roles;
import Modelo.Checklist;
import java.util.ArrayList;
import java.util.Collection;
import Modelo.Servicios;
import Modelo.Usuarios;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ivans
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("NoMasAccidentesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) throws PreexistingEntityException, Exception {
        if (usuarios.getChecklistCollection() == null) {
            usuarios.setChecklistCollection(new ArrayList<Checklist>());
        }
        if (usuarios.getServiciosCollection() == null) {
            usuarios.setServiciosCollection(new ArrayList<Servicios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles rolesIdRol = usuarios.getRolesIdRol();
            if (rolesIdRol != null) {
                rolesIdRol = em.getReference(rolesIdRol.getClass(), rolesIdRol.getIdRol());
                usuarios.setRolesIdRol(rolesIdRol);
            }
            Collection<Checklist> attachedChecklistCollection = new ArrayList<Checklist>();
            for (Checklist checklistCollectionChecklistToAttach : usuarios.getChecklistCollection()) {
                checklistCollectionChecklistToAttach = em.getReference(checklistCollectionChecklistToAttach.getClass(), checklistCollectionChecklistToAttach.getIdChecklist());
                attachedChecklistCollection.add(checklistCollectionChecklistToAttach);
            }
            usuarios.setChecklistCollection(attachedChecklistCollection);
            Collection<Servicios> attachedServiciosCollection = new ArrayList<Servicios>();
            for (Servicios serviciosCollectionServiciosToAttach : usuarios.getServiciosCollection()) {
                serviciosCollectionServiciosToAttach = em.getReference(serviciosCollectionServiciosToAttach.getClass(), serviciosCollectionServiciosToAttach.getIdServicio());
                attachedServiciosCollection.add(serviciosCollectionServiciosToAttach);
            }
            usuarios.setServiciosCollection(attachedServiciosCollection);
            em.persist(usuarios);
            if (rolesIdRol != null) {
                rolesIdRol.getUsuariosCollection().add(usuarios);
                rolesIdRol = em.merge(rolesIdRol);
            }
            for (Checklist checklistCollectionChecklist : usuarios.getChecklistCollection()) {
                Usuarios oldUsuariosIdUsuarioOfChecklistCollectionChecklist = checklistCollectionChecklist.getUsuariosIdUsuario();
                checklistCollectionChecklist.setUsuariosIdUsuario(usuarios);
                checklistCollectionChecklist = em.merge(checklistCollectionChecklist);
                if (oldUsuariosIdUsuarioOfChecklistCollectionChecklist != null) {
                    oldUsuariosIdUsuarioOfChecklistCollectionChecklist.getChecklistCollection().remove(checklistCollectionChecklist);
                    oldUsuariosIdUsuarioOfChecklistCollectionChecklist = em.merge(oldUsuariosIdUsuarioOfChecklistCollectionChecklist);
                }
            }
            for (Servicios serviciosCollectionServicios : usuarios.getServiciosCollection()) {
                Usuarios oldUsuariosIdUsuarioOfServiciosCollectionServicios = serviciosCollectionServicios.getUsuariosIdUsuario();
                serviciosCollectionServicios.setUsuariosIdUsuario(usuarios);
                serviciosCollectionServicios = em.merge(serviciosCollectionServicios);
                if (oldUsuariosIdUsuarioOfServiciosCollectionServicios != null) {
                    oldUsuariosIdUsuarioOfServiciosCollectionServicios.getServiciosCollection().remove(serviciosCollectionServicios);
                    oldUsuariosIdUsuarioOfServiciosCollectionServicios = em.merge(oldUsuariosIdUsuarioOfServiciosCollectionServicios);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarios(usuarios.getIdUsuario()) != null) {
                throw new PreexistingEntityException("Usuarios " + usuarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdUsuario());
            Roles rolesIdRolOld = persistentUsuarios.getRolesIdRol();
            Roles rolesIdRolNew = usuarios.getRolesIdRol();
            Collection<Checklist> checklistCollectionOld = persistentUsuarios.getChecklistCollection();
            Collection<Checklist> checklistCollectionNew = usuarios.getChecklistCollection();
            Collection<Servicios> serviciosCollectionOld = persistentUsuarios.getServiciosCollection();
            Collection<Servicios> serviciosCollectionNew = usuarios.getServiciosCollection();
            List<String> illegalOrphanMessages = null;
            for (Checklist checklistCollectionOldChecklist : checklistCollectionOld) {
                if (!checklistCollectionNew.contains(checklistCollectionOldChecklist)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Checklist " + checklistCollectionOldChecklist + " since its usuariosIdUsuario field is not nullable.");
                }
            }
            for (Servicios serviciosCollectionOldServicios : serviciosCollectionOld) {
                if (!serviciosCollectionNew.contains(serviciosCollectionOldServicios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Servicios " + serviciosCollectionOldServicios + " since its usuariosIdUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (rolesIdRolNew != null) {
                rolesIdRolNew = em.getReference(rolesIdRolNew.getClass(), rolesIdRolNew.getIdRol());
                usuarios.setRolesIdRol(rolesIdRolNew);
            }
            Collection<Checklist> attachedChecklistCollectionNew = new ArrayList<Checklist>();
            for (Checklist checklistCollectionNewChecklistToAttach : checklistCollectionNew) {
                checklistCollectionNewChecklistToAttach = em.getReference(checklistCollectionNewChecklistToAttach.getClass(), checklistCollectionNewChecklistToAttach.getIdChecklist());
                attachedChecklistCollectionNew.add(checklistCollectionNewChecklistToAttach);
            }
            checklistCollectionNew = attachedChecklistCollectionNew;
            usuarios.setChecklistCollection(checklistCollectionNew);
            Collection<Servicios> attachedServiciosCollectionNew = new ArrayList<Servicios>();
            for (Servicios serviciosCollectionNewServiciosToAttach : serviciosCollectionNew) {
                serviciosCollectionNewServiciosToAttach = em.getReference(serviciosCollectionNewServiciosToAttach.getClass(), serviciosCollectionNewServiciosToAttach.getIdServicio());
                attachedServiciosCollectionNew.add(serviciosCollectionNewServiciosToAttach);
            }
            serviciosCollectionNew = attachedServiciosCollectionNew;
            usuarios.setServiciosCollection(serviciosCollectionNew);
            usuarios = em.merge(usuarios);
            if (rolesIdRolOld != null && !rolesIdRolOld.equals(rolesIdRolNew)) {
                rolesIdRolOld.getUsuariosCollection().remove(usuarios);
                rolesIdRolOld = em.merge(rolesIdRolOld);
            }
            if (rolesIdRolNew != null && !rolesIdRolNew.equals(rolesIdRolOld)) {
                rolesIdRolNew.getUsuariosCollection().add(usuarios);
                rolesIdRolNew = em.merge(rolesIdRolNew);
            }
            for (Checklist checklistCollectionNewChecklist : checklistCollectionNew) {
                if (!checklistCollectionOld.contains(checklistCollectionNewChecklist)) {
                    Usuarios oldUsuariosIdUsuarioOfChecklistCollectionNewChecklist = checklistCollectionNewChecklist.getUsuariosIdUsuario();
                    checklistCollectionNewChecklist.setUsuariosIdUsuario(usuarios);
                    checklistCollectionNewChecklist = em.merge(checklistCollectionNewChecklist);
                    if (oldUsuariosIdUsuarioOfChecklistCollectionNewChecklist != null && !oldUsuariosIdUsuarioOfChecklistCollectionNewChecklist.equals(usuarios)) {
                        oldUsuariosIdUsuarioOfChecklistCollectionNewChecklist.getChecklistCollection().remove(checklistCollectionNewChecklist);
                        oldUsuariosIdUsuarioOfChecklistCollectionNewChecklist = em.merge(oldUsuariosIdUsuarioOfChecklistCollectionNewChecklist);
                    }
                }
            }
            for (Servicios serviciosCollectionNewServicios : serviciosCollectionNew) {
                if (!serviciosCollectionOld.contains(serviciosCollectionNewServicios)) {
                    Usuarios oldUsuariosIdUsuarioOfServiciosCollectionNewServicios = serviciosCollectionNewServicios.getUsuariosIdUsuario();
                    serviciosCollectionNewServicios.setUsuariosIdUsuario(usuarios);
                    serviciosCollectionNewServicios = em.merge(serviciosCollectionNewServicios);
                    if (oldUsuariosIdUsuarioOfServiciosCollectionNewServicios != null && !oldUsuariosIdUsuarioOfServiciosCollectionNewServicios.equals(usuarios)) {
                        oldUsuariosIdUsuarioOfServiciosCollectionNewServicios.getServiciosCollection().remove(serviciosCollectionNewServicios);
                        oldUsuariosIdUsuarioOfServiciosCollectionNewServicios = em.merge(oldUsuariosIdUsuarioOfServiciosCollectionNewServicios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = usuarios.getIdUsuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
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
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Checklist> checklistCollectionOrphanCheck = usuarios.getChecklistCollection();
            for (Checklist checklistCollectionOrphanCheckChecklist : checklistCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Checklist " + checklistCollectionOrphanCheckChecklist + " in its checklistCollection field has a non-nullable usuariosIdUsuario field.");
            }
            Collection<Servicios> serviciosCollectionOrphanCheck = usuarios.getServiciosCollection();
            for (Servicios serviciosCollectionOrphanCheckServicios : serviciosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Servicios " + serviciosCollectionOrphanCheckServicios + " in its serviciosCollection field has a non-nullable usuariosIdUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Roles rolesIdRol = usuarios.getRolesIdRol();
            if (rolesIdRol != null) {
                rolesIdRol.getUsuariosCollection().remove(usuarios);
                rolesIdRol = em.merge(rolesIdRol);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

//    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
//        EntityManager em = getEntityManager();
//        try {
//            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
//            cq.select(cq.from(Usuarios.class));
//            Query q = em.createQuery(cq);
//            if (!all) {
//                q.setMaxResults(maxResults);
//                q.setFirstResult(firstResult);
//            }
//            return q.getResultList();
//        } finally {
//            em.close();
//        }
//    }
    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        String query = "SELECT * FROM USUARIOS WHERE ROLES_ID_ROL = 2";
        List<Usuarios> listEntrada = null;

        try {
            listEntrada = em.createNativeQuery(query, Usuarios.class).getResultList();
        } catch (Exception ex) {
            ex.getMessage();
        }
        if (listEntrada == null) {
            listEntrada = new ArrayList<>();
        }
        return listEntrada;
    }

    public List<Usuarios> findUsuariosEntitiesCliente() {
        return findUsuariosEntitiesCliente(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntitiesCliente(int maxResults, int firstResult) {
        return findUsuariosEntitiesCliente(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntitiesCliente(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        String query = "SELECT * FROM USUARIOS WHERE ROLES_ID_ROL = 3";
        List<Usuarios> listEntrada = null;

        try {
            listEntrada = em.createNativeQuery(query, Usuarios.class).getResultList();
        } catch (Exception ex) {
            ex.getMessage();
        }
        if (listEntrada == null) {
            listEntrada = new ArrayList<>();
        }
        return listEntrada;
    }

    public Usuarios findUsuarios(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public boolean login(String email, String clave, Roles rolesIdRol) {
        EntityManager em = getEntityManager();
        boolean valor;
        try {
//            SELECT u FROM Usuarios u WHERE u.email = :email AND u.clave = :clave AND u.rolesIdRol = :rolesIdRol
            Query query = em.createNamedQuery("Usuarios.findUser");
            query.setParameter("email", email);
            query.setParameter("clave", clave);
            query.setParameter("rolesIdRol", rolesIdRol);
            
            List rs = query.getResultList();
            if (!rs.isEmpty()) {
                valor = true;
            } else {
                valor = false;
            }
        } catch (Exception e) {
            valor = false;
            e.getMessage();
        }
        return valor;

    }
    public List<Usuarios> buscarUsuario(String rut){
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Usuarios.findByRut");
        query.setParameter("rut", rut);
        List<Usuarios> lista = query.getResultList();
        return lista;
    }
}
