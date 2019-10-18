package Modelo;

import Modelo.CargoExtra;
import Modelo.Contratos;
import Modelo.FormaPago;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-14T22:59:37")
@StaticMetamodel(Pagos.class)
public class Pagos_ { 

    public static volatile SingularAttribute<Pagos, FormaPago> formaPagoIdFormaPago;
    public static volatile SingularAttribute<Pagos, Long> monto;
    public static volatile SingularAttribute<Pagos, Date> creado;
    public static volatile CollectionAttribute<Pagos, CargoExtra> cargoExtraCollection;
    public static volatile SingularAttribute<Pagos, Date> fechaHora;
    public static volatile SingularAttribute<Pagos, BigDecimal> idPago;
    public static volatile SingularAttribute<Pagos, BigInteger> contratosIdContrato;
    public static volatile SingularAttribute<Pagos, Date> modificado;
    public static volatile SingularAttribute<Pagos, Contratos> contratos;

}