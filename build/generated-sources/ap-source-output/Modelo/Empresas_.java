package Modelo;

import Modelo.Checklist;
import Modelo.Contratos;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-02T20:00:26")
@StaticMetamodel(Empresas.class)
public class Empresas_ { 

    public static volatile SingularAttribute<Empresas, String> razonSocial;
    public static volatile SingularAttribute<Empresas, Date> creado;
    public static volatile SingularAttribute<Empresas, String> direccion;
    public static volatile SingularAttribute<Empresas, BigInteger> contratosIdContrato;
    public static volatile SingularAttribute<Empresas, BigDecimal> idEmpresa;
    public static volatile SingularAttribute<Empresas, Date> modificado;
    public static volatile SingularAttribute<Empresas, Contratos> contratos;
    public static volatile CollectionAttribute<Empresas, Checklist> checklistCollection;
    public static volatile SingularAttribute<Empresas, Integer> telefono;
    public static volatile SingularAttribute<Empresas, String> nombre;

}