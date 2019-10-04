/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivans
 */
@Entity
@Table(name = "EMPRESAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresas.findAll", query = "SELECT e FROM Empresas e")
    , @NamedQuery(name = "Empresas.findByIdEmpresa", query = "SELECT e FROM Empresas e WHERE e.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "Empresas.findByNombre", query = "SELECT e FROM Empresas e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empresas.findByRazonSocial", query = "SELECT e FROM Empresas e WHERE e.razonSocial = :razonSocial")
    , @NamedQuery(name = "Empresas.findByDireccion", query = "SELECT e FROM Empresas e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Empresas.findByTelefono", query = "SELECT e FROM Empresas e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empresas.findByCreado", query = "SELECT e FROM Empresas e WHERE e.creado = :creado")
    , @NamedQuery(name = "Empresas.findByModificado", query = "SELECT e FROM Empresas e WHERE e.modificado = :modificado")
    , @NamedQuery(name = "Empresas.findByContratosIdContrato", query = "SELECT e FROM Empresas e WHERE e.contratosIdContrato = :contratosIdContrato")})
public class Empresas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EMPRESA")
    private BigDecimal idEmpresa;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "TELEFONO")
    private Integer telefono;
    @Basic(optional = false)
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "MODIFICADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    @Basic(optional = false)
    @Column(name = "CONTRATOS_ID_CONTRATO")
    private BigInteger contratosIdContrato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresasIdEmpresa")
    private Collection<Checklist> checklistCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "empresasIdEmpresa")
    private Contratos contratos;

    public Empresas() {
    }

    public Empresas(BigDecimal idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresas(BigDecimal idEmpresa, String nombre, Date creado, BigInteger contratosIdContrato) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.creado = creado;
        this.contratosIdContrato = contratosIdContrato;
    }

    public BigDecimal getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(BigDecimal idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    public BigInteger getContratosIdContrato() {
        return contratosIdContrato;
    }

    public void setContratosIdContrato(BigInteger contratosIdContrato) {
        this.contratosIdContrato = contratosIdContrato;
    }

    @XmlTransient
    public Collection<Checklist> getChecklistCollection() {
        return checklistCollection;
    }

    public void setChecklistCollection(Collection<Checklist> checklistCollection) {
        this.checklistCollection = checklistCollection;
    }

    public Contratos getContratos() {
        return contratos;
    }

    public void setContratos(Contratos contratos) {
        this.contratos = contratos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresas)) {
            return false;
        }
        Empresas other = (Empresas) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Empresas[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
