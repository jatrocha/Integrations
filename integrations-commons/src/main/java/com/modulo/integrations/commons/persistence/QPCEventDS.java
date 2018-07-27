package com.modulo.integrations.commons.persistence;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import br.com.cygnus.framework.persistence.DataStoreBase;

import com.modulo.integrations.commons.persistence.model.QPCEventEntity;

@Service
public class QPCEventDS extends DataStoreBase<QPCEventEntity> {

   /**
    * @return lista todas as entidades {@link QPCEventEntity} cadastradas no banco de dados.
    */
   public List<QPCEventEntity> list() {

      final CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();

      final CriteriaQuery<QPCEventEntity> q = cb.createQuery(QPCEventEntity.class);

      final Root<QPCEventEntity> c = q.from(QPCEventEntity.class);

      return this.getEntityManager().createQuery(q.select(c)).getResultList();
   }

}
