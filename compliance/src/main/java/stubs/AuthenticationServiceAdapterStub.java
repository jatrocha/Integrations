package stubs;

import com.modulo.riskmanager.client.adapter.AuthenticationServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.Application;
import com.modulo.riskmanager.client.to.Auth;

public class AuthenticationServiceAdapterStub extends AuthenticationServiceAdapter {

   /**
    * @see com.modulo.riskmanager.client.adapter.AuthenticationServiceAdapter#authenticate(com.modulo.riskmanager.client.to.Application)
    */
   @Override
   public Auth authenticate(Application application) throws LackOfAuthorizationException {

      return this.getAuthStub();
   }

   private Auth getAuthStub() {

      final String token = "hN/nB85Qoj6KbK/CagNSoGgZrY/eyRh8YVjM9oWYx2gh/mobDmiwReEsceL7v/HPq8Xmu7u2R50diYobuQ/mrdElB9+Enf5GAjcsh9D87WNdEskByX+lTgCcvgMaLJR5PMnlxqj+B4O1HdnNQa6TgM2l";

      final String type = "bearer";

      final Integer expires = Integer.valueOf("86400");

      return new Auth(token, type, expires);
   }

}
