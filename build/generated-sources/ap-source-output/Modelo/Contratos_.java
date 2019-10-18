package Modelo;

import Modelo.ContratoEstados;
import Modelo.Empresas;
import Modelo.Pagos;
import Modelo.Usuarios;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-14T22:59:36")
@StaticMetamodel(Contratos.class)
public class Contratos_ { 

    public static volatile SingularAttribute<Contratos, Date> creado;
    public static volatile SingularAttribute<Contratos, Long> monto;
    public static volatile SingularAttribute<Contratos, Usuarios> usuariosIdUsuario;
    public static volatile SingularAttribute<Contratos, Date> fechaInicio;
    public static volatile SingularAttribute<Contratos, Date> fechaTermino;
    public static volatile SingularAttribute<Contratos, Empresas> empresasIdEmpresa;
    public static volatile SingularAttribute<Contratos, Date> modificado;
    public static volatile SingularAttribute<Contratos, ContratoEstados> idContratoEstadoFk;
    public static volatile SingularAttribute<Contratos, Pagos> pagosIdPago;
    public static volatile SingularAttribute<Contratos, BigDecimal> idContrato;

}