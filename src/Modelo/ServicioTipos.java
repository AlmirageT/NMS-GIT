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
@Table(name = "SERVICIO_TIPOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioTipos.findAll", query = "SELECT s FROM ServicioTipos s")
    , @NamedQuery(name = "ServicioTipos.findByIdTipoServicio", query = "SELECT s FROM ServicioTipos s WHERE s.idTipoServicio = :idTipoServicio")
    , @NamedQuery(name = "ServicioTipos.findByNombre", query = "SELECT s FROM ServicioTipos s WHERE s.nombre = :nombre")})
public class ServicioTipos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_SERVICIO")
    private BigDecimal idTipoServicio;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoServicioFk")
    private Collection<Servicios> serviciosCollection;

    public ServicioTipos() {
    }

    public ServicioTipos(BigDecimal idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public ServicioTipos(BigDecimal idTipoServicio, String nombre) {
        this.idTipoServicio = idTipoServicio;
        this.nombre = nombre;
    }

    public BigDecimal getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(BigDecimal idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Servicios> getServiciosCollection() {
        return serviciosCollection;
    }

    public void setServiciosCollection(Collection<Servicios> serviciosCollection) {
        this.serviciosCollection = serviciosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoServicio != null ? idTipoServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioTipos)) {
            return false;
        }
        ServicioTipos other = (ServicioTipos) object;
        if ((this.idTipoServicio == null && other.idTipoServicio != null) || (this.idTipoServicio != null && !this.idTipoServicio.equals(other.idTipoServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.ServicioTipos[ idTipoServicio=" + idTipoServicio + " ]";
    }
    
}
