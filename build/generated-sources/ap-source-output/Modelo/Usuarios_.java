package Modelo;

import Modelo.Checklist;
import Modelo.Contratos;
import Modelo.Roles;
import Modelo.Servicios;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-14T22:59:36")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> clave;
    public static volatile SingularAttribute<Usuarios, Short> estado;
    public static volatile SingularAttribute<Usuarios, String> paterno;
    public static volatile SingularAttribute<Usuarios, Date> fechaModificacion;
    public static volatile SingularAttribute<Usuarios, Date> fechaNacimiento;
    public static volatile SingularAttribute<Usuarios, BigDecimal> idUsuario;
    public static volatile SingularAttribute<Usuarios, String> direccion;
    public static volatile CollectionAttribute<Usuarios, Checklist> checklistCollection;
    public static volatile SingularAttribute<Usuarios, String> nombres;
    public static volatile SingularAttribute<Usuarios, String> rut;
    public static volatile SingularAttribute<Usuarios, String> materno;
    public static volatile SingularAttribute<Usuarios, Roles> rolesIdRol;
    public static volatile SingularAttribute<Usuarios, Integer> celular;
    public static volatile SingularAttribute<Usuarios, Date> fechaCreacion;
    public static volatile SingularAttribute<Usuarios, BigInteger> contratosIdContrato;
    public static volatile SingularAttribute<Usuarios, Contratos> contratos;
    public static volatile SingularAttribute<Usuarios, Integer> telefono;
    public static volatile SingularAttribute<Usuarios, String> email;
    public static volatile CollectionAttribute<Usuarios, Servicios> serviciosCollection;

}