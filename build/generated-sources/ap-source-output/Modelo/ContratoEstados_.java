package Modelo;

import Modelo.Contratos;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-10-14T22:59:36")
@StaticMetamodel(ContratoEstados.class)
public class ContratoEstados_ { 

    public static volatile CollectionAttribute<ContratoEstados, Contratos> contratosCollection;
    public static volatile SingularAttribute<ContratoEstados, String> nombre;
    public static volatile SingularAttribute<ContratoEstados, BigDecimal> idContratoEstado;

}