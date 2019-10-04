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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PAGOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p")
    , @NamedQuery(name = "Pagos.findByIdPago", query = "SELECT p FROM Pagos p WHERE p.idPago = :idPago")
    , @NamedQuery(name = "Pagos.findByFechaHora", query = "SELECT p FROM Pagos p WHERE p.fechaHora = :fechaHora")
    , @NamedQuery(name = "Pagos.findByMonto", query = "SELECT p FROM Pagos p WHERE p.monto = :monto")
    , @NamedQuery(name = "Pagos.findByCreado", query = "SELECT p FROM Pagos p WHERE p.creado = :creado")
    , @NamedQuery(name = "Pagos.findByModificado", query = "SELECT p FROM Pagos p WHERE p.modificado = :modificado")
    , @NamedQuery(name = "Pagos.findByContratosIdContrato", query = "SELECT p FROM Pagos p WHERE p.contratosIdContrato = :contratosIdContrato")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PAGO")
    private BigDecimal idPago;
    @Basic(optional = false)
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Basic(optional = false)
    @Column(name = "MONTO")
    private long monto;
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
    @OneToMany(mappedBy = "pagosIdPago")
    private Collection<CargoExtra> cargoExtraCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pagosIdPago")
    private Contratos contratos;
    @JoinColumn(name = "FORMA_PAGO_ID_FORMA_PAGO", referencedColumnName = "ID_FORMA_PAGO")
    @ManyToOne(optional = false)
    private FormaPago formaPagoIdFormaPago;

    public Pagos() {
    }

    public Pagos(BigDecimal idPago) {
        this.idPago = idPago;
    }

    public Pagos(BigDecimal idPago, Date fechaHora, long monto, Date creado, BigInteger contratosIdContrato) {
        this.idPago = idPago;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.creado = creado;
        this.contratosIdContrato = contratosIdContrato;
    }

    public BigDecimal getIdPago() {
        return idPago;
    }

    public void setIdPago(BigDecimal idPago) {
        this.idPago = idPago;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public long getMonto() {
        return monto;
    }

    public void setMonto(long monto) {
        this.monto = monto;
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
    public Collection<CargoExtra> getCargoExtraCollection() {
        return cargoExtraCollection;
    }

    public void setCargoExtraCollection(Collection<CargoExtra> cargoExtraCollection) {
        this.cargoExtraCollection = cargoExtraCollection;
    }

    public Contratos getContratos() {
        return contratos;
    }

    public void setContratos(Contratos contratos) {
        this.contratos = contratos;
    }

    public FormaPago getFormaPagoIdFormaPago() {
        return formaPagoIdFormaPago;
    }

    public void setFormaPagoIdFormaPago(FormaPago formaPagoIdFormaPago) {
        this.formaPagoIdFormaPago = formaPagoIdFormaPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPago != null ? idPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.idPago == null && other.idPago != null) || (this.idPago != null && !this.idPago.equals(other.idPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Pagos[ idPago=" + idPago + " ]";
    }
    
}
