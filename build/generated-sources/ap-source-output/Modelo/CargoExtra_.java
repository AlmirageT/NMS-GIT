package Modelo;

import Modelo.Pagos;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-14T22:59:36")
@StaticMetamodel(CargoExtra.class)
public class CargoExtra_ { 

    public static volatile SingularAttribute<CargoExtra, BigDecimal> idCargoExtra;
    public static volatile SingularAttribute<CargoExtra, String> descripcion;
    public static volatile SingularAttribute<CargoExtra, String> tipo;
    public static volatile SingularAttribute<CargoExtra, Long> monto;
    public static volatile SingularAttribute<CargoExtra, Pagos> pagosIdPago;

}