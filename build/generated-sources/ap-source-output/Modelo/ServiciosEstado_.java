package Modelo;

import Modelo.Servicios;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-02T20:00:26")
@StaticMetamodel(ServiciosEstado.class)
public class ServiciosEstado_ { 

    public static volatile SingularAttribute<ServiciosEstado, BigDecimal> idEstadoServicios;
    public static volatile SingularAttribute<ServiciosEstado, String> nombre;
    public static volatile CollectionAttribute<ServiciosEstado, Servicios> serviciosCollection;

}