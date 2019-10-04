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
@Table(name = "FORMA_PAGO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPago.findAll", query = "SELECT f FROM FormaPago f")
    , @NamedQuery(name = "FormaPago.findByIdFormaPago", query = "SELECT f FROM FormaPago f WHERE f.idFormaPago = :idFormaPago")
    , @NamedQuery(name = "FormaPago.findByNombre", query = "SELECT f FROM FormaPago f WHERE f.nombre = :nombre")
    , @NamedQuery(name = "FormaPago.findByCantidadCuotas", query = "SELECT f FROM FormaPago f WHERE f.cantidadCuotas = :cantidadCuotas")})
public class FormaPago implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_FORMA_PAGO")
    private BigDecimal idFormaPago;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "CANTIDAD_CUOTAS")
    private long cantidadCuotas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formaPagoIdFormaPago")
    private Collection<Pagos> pagosCollection;

    public FormaPago() {
    }

    public FormaPago(BigDecimal idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public FormaPago(BigDecimal idFormaPago, String nombre, long cantidadCuotas) {
        this.idFormaPago = idFormaPago;
        this.nombre = nombre;
        this.cantidadCuotas = cantidadCuotas;
    }

    public BigDecimal getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(BigDecimal idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCantidadCuotas() {
        return cantidadCuotas;
    }

    public void setCantidadCuotas(long cantidadCuotas) {
        this.cantidadCuotas = cantidadCuotas;
    }

    @XmlTransient
    public Collection<Pagos> getPagosCollection() {
        return pagosCollection;
    }

    public void setPagosCollection(Collection<Pagos> pagosCollection) {
        this.pagosCollection = pagosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormaPago != null ? idFormaPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPago)) {
            return false;
        }
        FormaPago other = (FormaPago) object;
        if ((this.idFormaPago == null && other.idFormaPago != null) || (this.idFormaPago != null && !this.idFormaPago.equals(other.idFormaPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.FormaPago[ idFormaPago=" + idFormaPago + " ]";
    }
    
}
