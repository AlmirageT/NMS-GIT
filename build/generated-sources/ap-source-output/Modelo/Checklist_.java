package Modelo;

import Modelo.Empresas;
import Modelo.Usuarios;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-14T22:59:36")
@StaticMetamodel(Checklist.class)
public class Checklist_ { 

    public static volatile SingularAttribute<Checklist, String> item;
    public static volatile SingularAttribute<Checklist, Usuarios> usuariosIdUsuario;
    public static volatile SingularAttribute<Checklist, Short> aprovado;
    public static volatile SingularAttribute<Checklist, BigDecimal> idChecklist;
    public static volatile SingularAttribute<Checklist, Empresas> empresasIdEmpresa;

}