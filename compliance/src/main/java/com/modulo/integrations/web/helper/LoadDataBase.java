package com.modulo.integrations.web.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Carrega.
 */
public class LoadDataBase {

   private EntityManager entityManager = null;

   /**
    * Default constructor.
    */
   public LoadDataBase() {

      super();
   }

   /**
    * @param factory {@link EntityManagerFactory}.
    */
   public LoadDataBase(EntityManagerFactory factory) {

      this();

      this.entityManager = factory.createEntityManager();

      this.load(this.entityManager);
   }

   /**
    * @return {@link EntityManager}.
    */
   protected final EntityManager getEntityManager() {

      return this.entityManager;
   }

   /**
    * Efetua a carga dos versiculos de teste.
    */
   public final void load(EntityManager entityManager) {

   }

}
