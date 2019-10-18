package Modelo;

import Modelo.Servicios;
import Modelo.TipoReporte;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-14T22:59:36")
@StaticMetamodel(Reportes.class)
public class Reportes_ { 

    public static volatile SingularAttribute<Reportes, String> descripcion;
    public static volatile SingularAttribute<Reportes, TipoReporte> tipoReporteIdTipoReporte;
    public static volatile SingularAttribute<Reportes, Servicios> serviciosIdServicio;
    public static volatile SingularAttribute<Reportes, Date> fechaEmision;
    public static volatile SingularAttribute<Reportes, BigDecimal> idReporte;

}