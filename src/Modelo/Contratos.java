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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivans
 */
@Entity
@Table(name = "CONTRATOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratos.findAll", query = "SELECT c FROM Contratos c")
    , @NamedQuery(name = "Contratos.findByIdContrato", query = "SELECT c FROM Contratos c WHERE c.idContrato = :idContrato")
    , @NamedQuery(name = "Contratos.findByFechaInicio", query = "SELECT c FROM Contratos c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Contratos.findByFechaTermino", query = "SELECT c FROM Contratos c WHERE c.fechaTermino = :fechaTermino")
    , @NamedQuery(name = "Contratos.findByCreado", query = "SELECT c FROM Contratos c WHERE c.creado = :creado")
    , @NamedQuery(name = "Contratos.findByModificado", query = "SELECT c FROM Contratos c WHERE c.modificado = :modificado")
    , @NamedQuery(name = "Contratos.findByMonto", query = "SELECT c FROM Contratos c WHERE c.monto = :monto")})
public class Contratos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONTRATO")
    private BigDecimal idContrato;
    @Basic(optional = false)
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "FECHA_TERMINO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTermino;
    @Basic(optional = false)
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "MODIFICADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    @Basic(optional = false)
    @Column(name = "MONTO")
    private long monto;
    @JoinColumn(name = "ID_CONTRATO_ESTADO_FK", referencedColumnName = "ID_CONTRATO_ESTADO")
    @ManyToOne(optional = false)
    private ContratoEstados idContratoEstadoFk;
    @JoinColumn(name = "EMPRESAS_ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @OneToOne(optional = false)
    private Empresas empresasIdEmpresa;
    @JoinColumn(name = "PAGOS_ID_PAGO", referencedColumnName = "ID_PAGO")
    @OneToOne(optional = false)
    private Pagos pagosIdPago;
    @JoinColumn(name = "USUARIOS_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @OneToOne(optional = false)
    private Usuarios usuariosIdUsuario;

    public Contratos() {
    }

    public Contratos(BigDecimal idContrato) {
        this.idContrato = idContrato;
    }

    public Contratos(BigDecimal idContrato, Date fechaInicio, Date fechaTermino, Date creado, long monto) {
        this.idContrato = idContrato;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.creado = creado;
        this.monto = monto;
    }

    public BigDecimal getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(BigDecimal idContrato) {
        this.idContrato = idContrato;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
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

    public long getMonto() {
        return monto;
    }

    public void setMonto(long monto) {
        this.monto = monto;
    }


    public ContratoEstados getIdContratoEstadoFk() {
        return idContratoEstadoFk;
    }

    public void setIdContratoEstadoFk(ContratoEstados idContratoEstadoFk) {
        this.idContratoEstadoFk = idContratoEstadoFk;
    }

    public Empresas getEmpresasIdEmpresa() {
        return empresasIdEmpresa;
    }

    public void setEmpresasIdEmpresa(Empresas empresasIdEmpresa) {
        this.empresasIdEmpresa = empresasIdEmpresa;
    }

    public Pagos getPagosIdPago() {
        return pagosIdPago;
    }

    public void setPagosIdPago(Pagos pagosIdPago) {
        this.pagosIdPago = pagosIdPago;
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
        hash += (idContrato != null ? idContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratos)) {
            return false;
        }
        Contratos other = (Contratos) object;
        if ((this.idContrato == null && other.idContrato != null) || (this.idContrato != null && !this.idContrato.equals(other.idContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Contratos[ idContrato=" + idContrato + " ]";
    }
    
}
