/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ivans
 */
@Entity
@Table(name = "CHECKLIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Checklist.findAll", query = "SELECT c FROM Checklist c")
    , @NamedQuery(name = "Checklist.findByIdChecklist", query = "SELECT c FROM Checklist c WHERE c.idChecklist = :idChecklist")
    , @NamedQuery(name = "Checklist.findByItem", query = "SELECT c FROM Checklist c WHERE c.item = :item")
    , @NamedQuery(name = "Checklist.findByAprovado", query = "SELECT c FROM Checklist c WHERE c.aprovado = :aprovado")})
public class Checklist implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CHECKLIST")
    private BigDecimal idChecklist;
    @Basic(optional = false)
    @Column(name = "ITEM")
    private String item;
    @Basic(optional = false)
    @Column(name = "APROVADO")
    private short aprovado;
    @JoinColumn(name = "EMPRESAS_ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @ManyToOne(optional = false)
    private Empresas empresasIdEmpresa;
    @JoinColumn(name = "USUARIOS_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuarios usuariosIdUsuario;

    public Checklist() {
    }

    public Checklist(BigDecimal idChecklist) {
        this.idChecklist = idChecklist;
    }

    public Checklist(BigDecimal idChecklist, String item, short aprovado) {
        this.idChecklist = idChecklist;
        this.item = item;
        this.aprovado = aprovado;
    }

    public BigDecimal getIdChecklist() {
        return idChecklist;
    }

    public void setIdChecklist(BigDecimal idChecklist) {
        this.idChecklist = idChecklist;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public short getAprovado() {
        return aprovado;
    }

    public void setAprovado(short aprovado) {
        this.aprovado = aprovado;
    }

    public Empresas getEmpresasIdEmpresa() {
        return empresasIdEmpresa;
    }

    public void setEmpresasIdEmpresa(Empresas empresasIdEmpresa) {
        this.empresasIdEmpresa = empresasIdEmpresa;
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
        hash += (idChecklist != null ? idChecklist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Checklist)) {
            return false;
        }
        Checklist other = (Checklist) object;
        if ((this.idChecklist == null && other.idChecklist != null) || (this.idChecklist != null && !this.idChecklist.equals(other.idChecklist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Checklist[ idChecklist=" + idChecklist + " ]";
    }
    
}
