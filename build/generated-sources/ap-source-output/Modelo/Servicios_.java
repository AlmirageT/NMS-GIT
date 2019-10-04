package Modelo;

import Modelo.Reportes;
import Modelo.ServicioTipos;
import Modelo.ServiciosEstado;
import Modelo.Usuarios;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-02T20:00:26")
@StaticMetamodel(Servicios.class)
public class Servicios_ { 

    public static volatile SingularAttribute<Servicios, String> descripcion;
    public static volatile SingularAttribute<Servicios, Date> fechaModificacion;
    public static volatile SingularAttribute<Servicios, Usuarios> usuariosIdUsuario;
    public static volatile SingularAttribute<Servicios, Date> fechaHora;
    public static volatile SingularAttribute<Servicios, ServiciosEstado> idEstadoServiciosFk;
    public static volatile SingularAttribute<Servicios, Date> fechaCreacion;
    public static volatile SingularAttribute<Servicios, BigDecimal> idServicio;
    public static volatile CollectionAttribute<Servicios, Reportes> reportesCollection;
    public static volatile SingularAttribute<Servicios, ServicioTipos> idTipoServicioFk;

}