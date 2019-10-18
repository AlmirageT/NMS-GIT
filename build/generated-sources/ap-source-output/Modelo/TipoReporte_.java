package Modelo;

import Modelo.Reportes;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-14T22:59:36")
@StaticMetamodel(TipoReporte.class)
public class TipoReporte_ { 

    public static volatile SingularAttribute<TipoReporte, String> descripcion;
    public static volatile SingularAttribute<TipoReporte, BigDecimal> idTipoReporte;
    public static volatile CollectionAttribute<TipoReporte, Reportes> reportesCollection;

}