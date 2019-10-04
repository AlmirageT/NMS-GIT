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
@Table(name = "TIPO_REPORTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoReporte.findAll", query = "SELECT t FROM TipoReporte t")
    , @NamedQuery(name = "TipoReporte.findByIdTipoReporte", query = "SELECT t FROM TipoReporte t WHERE t.idTipoReporte = :idTipoReporte")
    , @NamedQuery(name = "TipoReporte.findByDescripcion", query = "SELECT t FROM TipoReporte t WHERE t.descripcion = :descripcion")})
public class TipoReporte implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_REPORTE")
    private BigDecimal idTipoReporte;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoReporteIdTipoReporte")
    private Collection<Reportes> reportesCollection;

    public TipoReporte() {
    }

    public TipoReporte(BigDecimal idTipoReporte) {
        this.idTipoReporte = idTipoReporte;
    }

    public TipoReporte(BigDecimal idTipoReporte, String descripcion) {
        this.idTipoReporte = idTipoReporte;
        this.descripcion = descripcion;
    }

    public BigDecimal getIdTipoReporte() {
        return idTipoReporte;
    }

    public void setIdTipoReporte(BigDecimal idTipoReporte) {
        this.idTipoReporte = idTipoReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Reportes> getReportesCollection() {
        return reportesCollection;
    }

    public void setReportesCollection(Collection<Reportes> reportesCollection) {
        this.reportesCollection = reportesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoReporte != null ? idTipoReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoReporte)) {
            return false;
        }
        TipoReporte other = (TipoReporte) object;
        if ((this.idTipoReporte == null && other.idTipoReporte != null) || (this.idTipoReporte != null && !this.idTipoReporte.equals(other.idTipoReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.TipoReporte[ idTipoReporte=" + idTipoReporte + " ]";
    }
    
}
