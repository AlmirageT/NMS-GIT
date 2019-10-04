/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "SERVICIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s")
    , @NamedQuery(name = "Servicios.findByIdServicio", query = "SELECT s FROM Servicios s WHERE s.idServicio = :idServicio")
    , @NamedQuery(name = "Servicios.findByFechaHora", query = "SELECT s FROM Servicios s WHERE s.fechaHora = :fechaHora")
    , @NamedQuery(name = "Servicios.findByDescripcion", query = "SELECT s FROM Servicios s WHERE s.descripcion = :descripcion")
    , @NamedQuery(name = "Servicios.findByFechaCreacion", query = "SELECT s FROM Servicios s WHERE s.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Servicios.findByFechaModificacion", query = "SELECT s FROM Servicios s WHERE s.fechaModificacion = :fechaModificacion")})
public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_SERVICIO")
    private BigDecimal idServicio;
    @Basic(optional = false)
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviciosIdServicio")
    private Collection<Reportes> reportesCollection;
    @JoinColumn(name = "ID_ESTADO_SERVICIOS_FK", referencedColumnName = "ID_ESTADO_SERVICIOS")
    @ManyToOne(optional = false)
    private ServiciosEstado idEstadoServiciosFk;
    @JoinColumn(name = "ID_TIPO_SERVICIO_FK", referencedColumnName = "ID_TIPO_SERVICIO")
    @ManyToOne(optional = false)
    private ServicioTipos idTipoServicioFk;
    @JoinColumn(name = "USUARIOS_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuarios usuariosIdUsuario;

    public Servicios() {
    }

    public Servicios(BigDecimal idServicio) {
        this.idServicio = idServicio;
    }

    public Servicios(BigDecimal idServicio, Date fechaHora, String descripcion, Date fechaCreacion) {
        this.idServicio = idServicio;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    public BigDecimal getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(BigDecimal idServicio) {
        this.idServicio = idServicio;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @XmlTransient
    public Collection<Reportes> getReportesCollection() {
        return reportesCollection;
    }

    public void setReportesCollection(Collection<Reportes> reportesCollection) {
        this.reportesCollection = reportesCollection;
    }

    public ServiciosEstado getIdEstadoServiciosFk() {
        return idEstadoServiciosFk;
    }

    public void setIdEstadoServiciosFk(ServiciosEstado idEstadoServiciosFk) {
        this.idEstadoServiciosFk = idEstadoServiciosFk;
    }

    public ServicioTipos getIdTipoServicioFk() {
        return idTipoServicioFk;
    }

    public void setIdTipoServicioFk(ServicioTipos idTipoServicioFk) {
        this.idTipoServicioFk = idTipoServicioFk;
    }

    public Usuarios getUsuariosIdUsuario() {
        return usuariosIdUsuario;
    }

    public void setUsuariosIdUsuario(Usuarios usuariosIdUsuario) {
        this.usuariosIdUsuario = usuariosIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idServicio != null ? idServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.idServicio == null && other.idServicio != null) || (this.idServicio != null && !this.idServicio.equals(other.idServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Servicios[ idServicio=" + idServicio + " ]";
    }
    
}
