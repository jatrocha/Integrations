package com.modulo.integrations.openvas.utils;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.modulo.integrations.commons.persistence.model.OpenVASProcessedReportEntity;

@Service(value = "openVASReportListUtil")
public class OpenVASReportListUtil {

   @Resource
   private EntityManagerFactory factory;

   public OpenVASReportListUtil() {

      super();
   }

   public Boolean isNotProcessed(final String id) throws IOException {

      if (StringUtils.isEmpty(id)) {

         throw new IllegalArgumentException();
      }

      if (CollectionUtils.isEmpty(this.list(id))) {

         return Boolean.TRUE;
      }

      return Boolean.FALSE;
   }

   public void register(final String id) throws IOException {

      if (StringUtils.isEmpty(id)) {

         throw new IllegalArgumentException();
      }

      this.save(new OpenVASProcessedReportEntity(id));
   }

   @SuppressWarnings("unchecked")
   protected List<OpenVASProcessedReportEntity> list(final String reportId) {

      final EntityManager entityManager = this.factory.createEntityManager();

      return entityManager.createQuery("from OpenVASProcessedReportEntity WHERE processedReportId = ?").setParameter(1, reportId).getResultList();

   }

   private void save(final OpenVASProcessedReportEntity entity) {

      final EntityManager entityManager = this.factory.createEntityManager();

      entityManager.getTransaction().begin();

      entityManager.merge(entity);

      entityManager.getTransaction().commit();

   }

}
