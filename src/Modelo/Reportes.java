/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivans
 */
@Entity
@Table(name = "REPORTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reportes.findAll", query = "SELECT r FROM Reportes r")
    , @NamedQuery(name = "Reportes.findByIdReporte", query = "SELECT r FROM Reportes r WHERE r.idReporte = :idReporte")
    , @NamedQuery(name = "Reportes.findByDescripcion", query = "SELECT r FROM Reportes r WHERE r.descripcion = :descripcion")
    , @NamedQuery(name = "Reportes.findByFechaEmision", query = "SELECT r FROM Reportes r WHERE r.fechaEmision = :fechaEmision")})
public class Reportes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REPORTE")
    private BigDecimal idReporte;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "FECHA_EMISION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @JoinColumn(name = "SERVICIOS_ID_SERVICIO", referencedColumnName = "ID_SERVICIO")
    @ManyToOne(optional = false)
    private Servicios serviciosIdServicio;
    @JoinColumn(name = "TIPO_REPORTE_ID_TIPO_REPORTE", referencedColumnName = "ID_TIPO_REPORTE")
    @ManyToOne(optional = false)
    private TipoReporte tipoReporteIdTipoReporte;

    public Reportes() {
    }

    public Reportes(BigDecimal idReporte) {
        this.idReporte = idReporte;
    }

    public Reportes(BigDecimal idReporte, String descripcion, Date fechaEmision) {
        this.idReporte = idReporte;
        this.descripcion = descripcion;
        this.fechaEmision = fechaEmision;
    }

    public BigDecimal getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(BigDecimal idReporte) {
        this.idReporte = idReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Servicios getServiciosIdServicio() {
        return serviciosIdServicio;
    }

    public void setServiciosIdServicio(Servicios serviciosIdServicio) {
        this.serviciosIdServicio = serviciosIdServicio;
    }

    public TipoReporte getTipoReporteIdTipoReporte() {
        return tipoReporteIdTipoReporte;
    }

    public void setTipoReporteIdTipoReporte(TipoReporte tipoReporteIdTipoReporte) {
        this.tipoReporteIdTipoReporte = tipoReporteIdTipoReporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReporte != null ? idReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reportes)) {
            return false;
        }
        Reportes other = (Reportes) object;
        if ((this.idReporte == null && other.idReporte != null) || (this.idReporte != null && !this.idReporte.equals(other.idReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Reportes[ idReporte=" + idReporte + " ]";
    }
    
}
