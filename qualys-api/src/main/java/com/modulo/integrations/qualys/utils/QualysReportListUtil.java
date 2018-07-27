package com.modulo.integrations.qualys.utils;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.modulo.integrations.commons.persistence.model.QPCProcessedReportEntity;

@Service(value = "qualysReportListUtil")
public class QualysReportListUtil {

   @Resource
   private EntityManagerFactory factory;

   public QualysReportListUtil() {

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

      this.save(new QPCProcessedReportEntity(id));
   }

   @SuppressWarnings("unchecked")
   protected List<QPCProcessedReportEntity> list(final String reportId) {

      final EntityManager entityManager = this.factory.createEntityManager();

      return entityManager.createQuery("from QPCProcessedReportEntity WHERE processedReportId = ?").setParameter(1, reportId).getResultList();

   }

   private void save(final QPCProcessedReportEntity entity) {

      final EntityManager entityManager = this.factory.createEntityManager();

      entityManager.getTransaction().begin();

      entityManager.merge(entity);

      entityManager.getTransaction().commit();

   }

}
