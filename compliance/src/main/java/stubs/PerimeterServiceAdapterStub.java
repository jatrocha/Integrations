package stubs;

import java.util.ArrayList;
import java.util.List;

import br.com.cygnus.framework.log.Log;
import br.com.cygnus.framework.template.log.ILog;

import com.modulo.riskmanager.client.adapter.PerimeterServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.PerimeterFilter;
import com.modulo.riskmanager.client.to.asset.Perimeter;
import com.modulo.riskmanager.client.to.asset.Responsible;

public class PerimeterServiceAdapterStub extends PerimeterServiceAdapter {

   /** Classe de log. */
   private static final ILog<PerimeterServiceAdapterStub> LOG = Log.get(PerimeterServiceAdapterStub.class);

   /*
    * (non-Javadoc)
    * 
    * @see com.modulo.riskmanager.client.adapter.PerimeterServiceAdapter#read(com.modulo.riskmanager.client.to.Auth,
    * com.modulo.riskmanager.client.to.PerimeterFilter)
    */
   @Override
   public List<Perimeter> read(Auth auth, PerimeterFilter filter) throws LackOfAuthorizationException {

      final List<Perimeter> perimetros = new ArrayList<Perimeter>();

      Perimeter perimeter = new Perimeter();

      perimeter.setId("4f8ff633-b64a-44ab-a072-d2ae485e1fac");

      perimeter.setName("Teste");

      perimeter.setPath("Teste");

      perimeter.setResponsible(new Responsible("496a312a-71a8-41ef-8f55-af770e604319", "Administrator", "+55 (21) 2123-4600", "adm_rmng@modulo.com.br"));

      perimeter.setCreatedOn("/Date(1430149022440-0300)/");

      perimeter.setCreatedBy("administrator");

      perimeter.setUpdatedOn("/Date(1430149022440-0300)/");

      perimeter.setUpdatedBy("administrator");

      perimetros.add(perimeter);

      return perimetros;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.modulo.riskmanager.client.adapter.PerimeterServiceAdapter#create(com.modulo.riskmanager.client.to.Auth,
    * com.modulo.riskmanager.client.to.asset.Perimeter)
    */
   @Override
   public void create(Auth auth, Perimeter perimeter) throws LackOfAuthorizationException {

      LOG.debug("solicitacao de criacao para o Perimetro: ".concat(perimeter.getName()));
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.modulo.riskmanager.client.adapter.PerimeterServiceAdapter#update(com.modulo.riskmanager.client.to.Auth,
    * com.modulo.riskmanager.client.to.asset.Perimeter)
    */
   @Override
   public void update(Auth auth, Perimeter perimeter) throws LackOfAuthorizationException {

      LOG.debug("solicitacao de atualizacao do Perimetro: ".concat(perimeter.getName()));
   }

}
