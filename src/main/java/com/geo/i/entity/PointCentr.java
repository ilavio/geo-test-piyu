package com.geo.i.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.locationtech.jts.geom.Point;

@Data
@Entity
@Table(name = "poligontest")
public class PointCentr {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "names")
  private String names;

  @Column(name = "geommy")
  //@Type(type = "org.hibernate.spatial.GeometryType")
  private Point geommy;
}
