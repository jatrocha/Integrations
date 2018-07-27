package com.modulo.integrations.web.business;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.modulo.riskmanager.client.adapter.AssetServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.AssetFilter;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.enums.AssetType;

@Service(value = "assetBusiness")
public class AssetBusiness {

   @Resource
   public AssetServiceAdapter assetServiceAdapter;

   private Auth auth;

   private Asset asset;

   public AssetBusiness with(final Auth auth) {

      this.auth = auth;

      return this;

   }

   public Asset process() throws LackOfAuthorizationException {

      List<Asset> retorno = null;

      synchronized (this) {

         retorno = this.assetServiceAdapter.read(this.auth, new AssetFilter(AssetType.TECNOLOGIA).withName(this.asset.getName()));

         if (retorno.isEmpty()) {

            this.asset.setId(this.assetServiceAdapter.create(this.auth, this.asset));

            return this.asset;
         }

      }

      return retorno.iterator().next();

   }

   protected final void setAssetServiceAdapter(final AssetServiceAdapter assetServiceAdapter) {

      this.assetServiceAdapter = assetServiceAdapter;
   }

   public AssetBusiness with(final Asset asset) {

      this.asset = asset;

      return this;
   }

}
