package stubs;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.cygnus.framework.log.Log;
import br.com.cygnus.framework.template.log.ILog;

import com.modulo.riskmanager.client.adapter.AssetServiceAdapter;
import com.modulo.riskmanager.client.exception.LackOfAuthorizationException;
import com.modulo.riskmanager.client.to.AssetFilter;
import com.modulo.riskmanager.client.to.Auth;
import com.modulo.riskmanager.client.to.asset.Asset;
import com.modulo.riskmanager.client.to.asset.CollectionParameters;
import com.modulo.riskmanager.client.to.asset.Components;
import com.modulo.riskmanager.client.to.asset.DnsName;
import com.modulo.riskmanager.client.to.asset.IpAddress;
import com.modulo.riskmanager.client.to.asset.MappingCriteria;
import com.modulo.riskmanager.client.to.asset.NetbiosName;
import com.modulo.riskmanager.client.to.asset.Responsible;
import com.modulo.riskmanager.enums.AssetType;

public class AssetServiceAdapterStub extends AssetServiceAdapter {

   /** Classe de log. */
   private static final ILog<AssetServiceAdapterStub> LOG = Log.get(AssetServiceAdapterStub.class);

   /**
    * @see com.modulo.riskmanager.client.adapter.AssetServiceAdapter#list(com.modulo.riskmanager.client.to.Auth, com.modulo.riskmanager.client.to.AssetFilter)
    */
   @Override
   public List<Asset> list(Auth auth, AssetFilter filter) throws LackOfAuthorizationException {

      return this.listar();
   }

   private List<Asset> listar() {

      List<Asset> assets = new ArrayList<Asset>();

      assets.add(this.getAssetNovo());

      assets.add(this.getAssetAtualizado());

      return assets;
   }

   private Asset getAssetAtualizado() {

      final Asset asset = new Asset();

      asset.setId("75b5c05d-d74b-43f7-9283-3b8fd9aff38f");

      asset.setName("OpenVAS7");

      asset.setDescription(StringUtils.EMPTY);

      asset.setLatitude(Double.valueOf(-22.903651));

      asset.setLongitude(Double.valueOf(-43.176037));

      asset.setGeolocationDescription(StringUtils.EMPTY);

      asset.setPath("Servidores > OpenVAS7");

      asset.setResponsible(new Responsible("496a312a-71a8-41ef-8f55-af770e604319", "Administrator", "+55 (21) 2123-4600", "adm_rmng@modulo.com.br"));

      asset.setCreatedOn("'\\/Date(1418661286557-0200)\\/");

      asset.setCreatedBy("administrator");

      asset.setUpdatedOn("\\/Date(1422375013100-0200)\\/");

      asset.setUpdatedBy("administrator");

      asset.setAssetType(AssetType.TECNOLOGIA.toString());

      asset.setRelevance(Integer.valueOf(3));

      asset.setAnalysisFrequency(Integer.valueOf(0));

      asset.setCollectionParameters(new CollectionParameters("192.168.2.107", "Debian", "modSIC - Localhost"));

      final List<Components> components = new ArrayList<Components>(1);

      components.add(new Components("2fbf3f6c-313d-40ce-b0b3-40acea8725c2", "Debian - Apache 2.0.59/2.2.6 (Unix)", StringUtils.EMPTY));

      asset.setComponents(components);

      asset.setMappingCriteria(new MappingCriteria(new NetbiosName("Inherited"), new IpAddress("Inherited"), new DnsName("Inherited")));

      return asset;
   }

   private Asset getAssetNovo() {

      final Asset assetNovo = new Asset();

      assetNovo.setId("5cda9151-ad60-47d0-a31c-23669f1d8c80");

      assetNovo.setName("Risk Manager - 8.4");

      assetNovo.setDescription(StringUtils.EMPTY);

      assetNovo.setLatitude(Double.valueOf(-22.903651));

      assetNovo.setLongitude(Double.valueOf(-43.176037));

      assetNovo.setGeolocationDescription(StringUtils.EMPTY);

      assetNovo.setPath("Servidores > Risk Manager - 8.4");

      assetNovo.setResponsible(new Responsible("496a312a-71a8-41ef-8f55-af770e604319", "Administrator", "+55 (21) 2123-4600", "adm_rmng@modulo.com.br"));

      assetNovo.setCreatedOn("'\\/Date(1418661286557-0200)\\/");

      assetNovo.setCreatedBy("administrator");

      assetNovo.setUpdatedOn("\\/Date(1422375013100-0200)\\/");

      assetNovo.setUpdatedBy("administrator");

      assetNovo.setAssetType(AssetType.TECNOLOGIA.toString());

      assetNovo.setRelevance(Integer.valueOf(3));

      assetNovo.setAnalysisFrequency(Integer.valueOf(0));

      assetNovo.setCollectionParameters(new CollectionParameters("192.168.2.106", "Windows PT-BR", "modSIC - Localhost"));

      final List<Components> components = new ArrayList<Components>(1);

      components.add(new Components("149387ac-5fc2-4a0a-8762-1f57f01c96e9", "win-67mvjeneak0 - MS Windows Srv 2012", StringUtils.EMPTY));

      assetNovo.setComponents(components);

      assetNovo.setMappingCriteria(new MappingCriteria(new NetbiosName("AssetName"), new IpAddress("AssetName"), new DnsName("AssetName")));

      return assetNovo;
   }

   /**
    * @see com.modulo.riskmanager.client.adapter.AssetServiceAdapter#update(com.modulo.riskmanager.client.to.Auth, com.modulo.riskmanager.client.to.asset.Asset)
    */
   @Override
   public void update(Auth auth, Asset asset) throws LackOfAuthorizationException {

      LOG.debug("solicitacao de atualizacao do Asset: ".concat(asset.getId()));
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.modulo.riskmanager.client.adapter.AssetServiceAdapter#create(com.modulo.riskmanager.client.to.Auth, com.modulo.riskmanager.client.to.asset.Asset)
    */
   @Override
   public String create(Auth auth, Asset asset) throws LackOfAuthorizationException {

      LOG.debug("solicitacao de criacao para o Asset: ".concat(asset.getName()));

      return "d266095f-924a-4121-85ce-64d57ca80bbe";
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.modulo.riskmanager.client.adapter.AssetServiceAdapter#read(com.modulo.riskmanager.client.to.Auth, com.modulo.riskmanager.client.to.AssetFilter)
    */
   @Override
   public List<Asset> read(Auth auth, AssetFilter filter) throws LackOfAuthorizationException {

      LOG.debug("Retornando Assets");

      return this.listar();
   }

}
