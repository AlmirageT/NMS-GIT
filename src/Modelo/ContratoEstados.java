/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ivans
 */
@Entity
@Table(name = "CONTRATO_ESTADOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoEstados.findAll", query = "SELECT c FROM ContratoEstados c")
    , @NamedQuery(name = "ContratoEstados.findByIdContratoEstado", query = "SELECT c FROM ContratoEstados c WHERE c.idContratoEstado = :idContratoEstado")
    , @NamedQuery(name = "ContratoEstados.findByNombre", query = "SELECT c FROM ContratoEstados c WHERE c.nombre = :nombre")})
public class ContratoEstados implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONTRATO_ESTADO")
    private BigDecimal idContratoEstado;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContratoEstadoFk")
    private Collection<Contratos> contratosCollection;

    public ContratoEstados() {
    }

    public ContratoEstados(BigDecimal idContratoEstado) {
        this.idContratoEstado = idContratoEstado;
    }

    public ContratoEstados(BigDecimal idContratoEstado, String nombre) {
        this.idContratoEstado = idContratoEstado;
        this.nombre = nombre;
    }

    public BigDecimal getIdContratoEstado() {
        return idContratoEstado;
    }

    public void setIdContratoEstado(BigDecimal idContratoEstado) {
        this.idContratoEstado = idContratoEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Contratos> getContratosCollection() {
        return contratosCollection;
    }

    public void setContratosCollection(Collection<Contratos> contratosCollection) {
        this.contratosCollection = contratosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratoEstado != null ? idContratoEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoEstados)) {
            return false;
        }
        ContratoEstados other = (ContratoEstados) object;
        if ((this.idContratoEstado == null && other.idContratoEstado != null) || (this.idContratoEstado != null && !this.idContratoEstado.equals(other.idContratoEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.ContratoEstados[ idContratoEstado=" + idContratoEstado + " ]";
    }
    
}
