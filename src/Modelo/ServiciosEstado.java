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
@Table(name = "SERVICIOS_ESTADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiciosEstado.findAll", query = "SELECT s FROM ServiciosEstado s")
    , @NamedQuery(name = "ServiciosEstado.findByIdEstadoServicios", query = "SELECT s FROM ServiciosEstado s WHERE s.idEstadoServicios = :idEstadoServicios")
    , @NamedQuery(name = "ServiciosEstado.findByNombre", query = "SELECT s FROM ServiciosEstado s WHERE s.nombre = :nombre")})
public class ServiciosEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ESTADO_SERVICIOS")
    private BigDecimal idEstadoServicios;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoServiciosFk")
    private Collection<Servicios> serviciosCollection;

    public ServiciosEstado() {
    }

    public ServiciosEstado(BigDecimal idEstadoServicios) {
        this.idEstadoServicios = idEstadoServicios;
    }

    public ServiciosEstado(BigDecimal idEstadoServicios, String nombre) {
        this.idEstadoServicios = idEstadoServicios;
        this.nombre = nombre;
    }

    public BigDecimal getIdEstadoServicios() {
        return idEstadoServicios;
    }

    public void setIdEstadoServicios(BigDecimal idEstadoServicios) {
        this.idEstadoServicios = idEstadoServicios;
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
        hash += (idEstadoServicios != null ? idEstadoServicios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiciosEstado)) {
            return false;
        }
        ServiciosEstado other = (ServiciosEstado) object;
        if ((this.idEstadoServicios == null && other.idEstadoServicios != null) || (this.idEstadoServicios != null && !this.idEstadoServicios.equals(other.idEstadoServicios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.ServiciosEstado[ idEstadoServicios=" + idEstadoServicios + " ]";
    }
    
}
