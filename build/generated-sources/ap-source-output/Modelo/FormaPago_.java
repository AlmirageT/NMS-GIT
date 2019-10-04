package Modelo;

import Modelo.Pagos;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-02T20:00:26")
@StaticMetamodel(FormaPago.class)
public class FormaPago_ { 

    public static volatile SingularAttribute<FormaPago, BigDecimal> idFormaPago;
    public static volatile CollectionAttribute<FormaPago, Pagos> pagosCollection;
    public static volatile SingularAttribute<FormaPago, String> nombre;
    public static volatile SingularAttribute<FormaPago, Long> cantidadCuotas;

}