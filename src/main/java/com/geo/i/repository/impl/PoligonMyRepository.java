package com.geo.i.repository.impl;

import com.geo.i.entity.PointCentr;
import com.geo.i.entity.PoligonMy;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PoligonMyRepository {
  private final EntityManagerFactory managerFactory;

  public PoligonMy addPoligonMy(PoligonMy poligon){
    var entityManager = managerFactory.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(poligon);
      transaction.commit();
      return poligon;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.out.println("Ошибка PoligonMyRepository addPoligonMy(): " + e);
    } finally {
      entityManager.close();
    }
    return poligon;
  }

  public PointCentr addPointCentr(PointCentr pointCentr){
    var entityManager = managerFactory.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(pointCentr);
      transaction.commit();
      return pointCentr;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      System.out.println("Ошибка PoligonMyRepository addPointCentr(): " + e);
    } finally {
      entityManager.close();
    }
    return pointCentr;
  }
}
