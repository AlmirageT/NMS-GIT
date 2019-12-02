/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    , @NamedQuery(name = "Checklist.findByItem1", query = "SELECT c FROM Checklist c WHERE c.item1 = :item1")
    , @NamedQuery(name = "Checklist.findByAprovado1", query = "SELECT c FROM Checklist c WHERE c.aprovado1 = :aprovado1")
    , @NamedQuery(name = "Checklist.findByItem2", query = "SELECT c FROM Checklist c WHERE c.item2 = :item2")
    , @NamedQuery(name = "Checklist.findByAprovado2", query = "SELECT c FROM Checklist c WHERE c.aprovado2 = :aprovado2")
    , @NamedQuery(name = "Checklist.findByItem3", query = "SELECT c FROM Checklist c WHERE c.item3 = :item3")
    , @NamedQuery(name = "Checklist.findByAprovado3", query = "SELECT c FROM Checklist c WHERE c.aprovado3 = :aprovado3")
    , @NamedQuery(name = "Checklist.findByItem4", query = "SELECT c FROM Checklist c WHERE c.item4 = :item4")
    , @NamedQuery(name = "Checklist.findByAprovado4", query = "SELECT c FROM Checklist c WHERE c.aprovado4 = :aprovado4")
    , @NamedQuery(name = "Checklist.findByItem5", query = "SELECT c FROM Checklist c WHERE c.item5 = :item5")
    , @NamedQuery(name = "Checklist.findByAprovado5", query = "SELECT c FROM Checklist c WHERE c.aprovado5 = :aprovado5")
    ,@NamedQuery(name = "Checklist.findByUsuario", query = "SELECT c FROM Checklist c WHERE c.usuariosIdUsuario = :usuariosIdUsuario")})
public class Checklist implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CHECKLIST")
    private BigDecimal idChecklist;
    @Basic(optional = false)
    @Column(name = "ITEM_1")
    private String item1;
    @Basic(optional = false)
    @Column(name = "APROVADO_1")
    private BigInteger aprovado1;
    @Basic(optional = false)
    @Column(name = "ITEM_2")
    private String item2;
    @Basic(optional = false)
    @Column(name = "APROVADO_2")
    private BigInteger aprovado2;
    @Basic(optional = false)
    @Column(name = "ITEM_3")
    private String item3;
    @Basic(optional = false)
    @Column(name = "APROVADO_3")
    private BigInteger aprovado3;
    @Basic(optional = false)
    @Column(name = "ITEM_4")
    private String item4;
    @Basic(optional = false)
    @Column(name = "APROVADO_4")
    private BigInteger aprovado4;
    @Basic(optional = false)
    @Column(name = "ITEM_5")
    private String item5;
    @Basic(optional = false)
    @Column(name = "APROVADO_5")
    private BigInteger aprovado5;
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

    public Checklist(BigDecimal idChecklist, String item1, BigInteger aprovado1, String item2, BigInteger aprovado2, String item3, BigInteger aprovado3, String item4, BigInteger aprovado4, String item5, BigInteger aprovado5) {
        this.idChecklist = idChecklist;
        this.item1 = item1;
        this.aprovado1 = aprovado1;
        this.item2 = item2;
        this.aprovado2 = aprovado2;
        this.item3 = item3;
        this.aprovado3 = aprovado3;
        this.item4 = item4;
        this.aprovado4 = aprovado4;
        this.item5 = item5;
        this.aprovado5 = aprovado5;
    }
    public Checklist(String item1,String item2, String item3, String item4, String item5) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
    }

    public BigDecimal getIdChecklist() {
        return idChecklist;
    }

    public void setIdChecklist(BigDecimal idChecklist) {
        this.idChecklist = idChecklist;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public BigInteger getAprovado1() {
        return aprovado1;
    }

    public void setAprovado1(BigInteger aprovado1) {
        this.aprovado1 = aprovado1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public BigInteger getAprovado2() {
        return aprovado2;
    }

    public void setAprovado2(BigInteger aprovado2) {
        this.aprovado2 = aprovado2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public BigInteger getAprovado3() {
        return aprovado3;
    }

    public void setAprovado3(BigInteger aprovado3) {
        this.aprovado3 = aprovado3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public BigInteger getAprovado4() {
        return aprovado4;
    }

    public void setAprovado4(BigInteger aprovado4) {
        this.aprovado4 = aprovado4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public BigInteger getAprovado5() {
        return aprovado5;
    }

    public void setAprovado5(BigInteger aprovado5) {
        this.aprovado5 = aprovado5;
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
