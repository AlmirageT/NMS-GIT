package Modelo;

import Modelo.Usuarios;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-14T22:59:37")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile SingularAttribute<Roles, BigDecimal> idRol;
    public static volatile CollectionAttribute<Roles, Usuarios> usuariosCollection;
    public static volatile SingularAttribute<Roles, String> nombre;

}