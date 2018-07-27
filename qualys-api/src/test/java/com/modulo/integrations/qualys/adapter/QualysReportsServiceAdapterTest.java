package com.modulo.integrations.qualys.adapter;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import stubs.QualysReportListStub;
import br.com.cygnus.framework.util.PropertiesUtil;

import com.modulo.integrations.qualys.to.QualysAuth;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import static junit.framework.Assert.assertFalse;

public class QualysReportsServiceAdapterTest {

   protected static final Integer HTTP_CODE_200 = Integer.valueOf(200);

   private static final String SERVICE_URL = PropertiesUtil.getInstance().getString("QPC.Report.List");

   private QualysReportsServiceAdapter adapter = null;

   private Mockery context;

   @Before
   public void init() {

      this.adapter = new QualysReportsServiceAdapter();

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);

         }

      };
   }

   @SuppressWarnings("unchecked")
   @Test
   @Ignore
   public void deveListarRelatorios() throws Exception {

      final Client client = this.context.mock(Client.class);

      final Builder builder = this.context.mock(Builder.class);

      final WebResource webResource = this.context.mock(WebResource.class);

      final ClientResponse response = this.context.mock(ClientResponse.class);

      final String xmlReportList = QualysReportListStub.getXML();

      final QualysAuth auth = new QualysAuth();

      this.context.checking(new Expectations() {

         {

            this.one(client).resource(this.with(SERVICE_URL.concat("?action=list")));
            this.will(returnValue(webResource));

            this.one(webResource).header("Authorization", "Basic ".concat(auth.getToken()));
            this.will(returnValue(builder));

            this.one(builder).header("X-Requested-With", "List Reports");
            this.will(returnValue(builder));

            this.one(builder).accept(this.with(new String[] { MediaType.APPLICATION_JSON }));
            this.will(returnValue(builder));

            this.one(builder).get(this.with(ClientResponse.class));
            this.will(returnValue(response));

            this.one(response).getStatus();
            this.will(returnValue(HTTP_CODE_200));

            // this.one(response).getEntity(this.with(any(GenericType.class)));
            // this.will(returnValue(xmlReportList));

         }

      });

      this.adapter.setClient(client);

      assertFalse(StringUtils.isEmpty(this.adapter.list(auth)));

      this.context.assertIsSatisfied();
   }

}
