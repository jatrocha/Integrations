package com.modulo.integrations.web.business;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.modulo.riskmanager.client.adapter.AssetServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.AssetFilter;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.asset.Asset;

public class AssetBusinessTest {

   private Mockery context;

   @Before
   public void init() {

      this.context = new Mockery() {

         {

            this.setImposteriser(ClassImposteriser.INSTANCE);

         }

      };
   }

   public void test() throws LackOfAuthorizationException {

      final AssetServiceAdapter assetServiceAdapterMock = this.context.mock(AssetServiceAdapter.class);

      final List<Asset> assets = new ArrayList<Asset>();

      this.context.checking(new Expectations() {

         {

            this.one(assetServiceAdapterMock).read(this.with(any(Auth.class)), this.with(any(AssetFilter.class)));
            this.will(returnValue(assets));

            this.one(assetServiceAdapterMock).create(this.with(any(Auth.class)), this.with(any(Asset.class)));

         }

      });

      final AssetBusiness business = new AssetBusiness();

      business.setAssetServiceAdapter(assetServiceAdapterMock);

      final Asset asset = new Asset();

      asset.setName("10.4.20.17");

      asset.setPath("CIP");

      business.with(new Auth()).with(asset).process();

      this.context.assertIsSatisfied();
   }

   @Test
   public void naoDeveGravarQuandoAssetJaExistir() throws LackOfAuthorizationException {

      final AssetServiceAdapter assetServiceAdapterMock = this.context.mock(AssetServiceAdapter.class);

      final List<Asset> assets = new ArrayList<Asset>();

      final Asset asset = new Asset();

      assets.add(asset);

      this.context.checking(new Expectations() {

         {

            this.one(assetServiceAdapterMock).read(this.with(any(Auth.class)), this.with(any(AssetFilter.class)));
            this.will(returnValue(assets));

            this.never(assetServiceAdapterMock).create(this.with(any(Auth.class)), this.with(any(Asset.class)));

         }

      });

      final AssetBusiness business = new AssetBusiness();

      business.setAssetServiceAdapter(assetServiceAdapterMock);

      business.with(new Auth()).with(asset).process();

      this.context.assertIsSatisfied();
   }

}
