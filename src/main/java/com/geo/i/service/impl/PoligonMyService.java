package com.geo.i.service.impl;

import com.geo.i.entity.PointCentr;
import com.geo.i.entity.PointForMap;
import com.geo.i.entity.PoligonMy;
import com.geo.i.repository.impl.PoligonMyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.algorithm.Centroid;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PoligonMyService {

  private final PoligonMyRepository repository;
  private final int SRID = 4326;

  /**
   * Метод создания многоугольника
   *
   * @param listPoint List<PointForMap>
   * @return PoligonMy
   */
  public PoligonMy createPoligon(List<PointForMap> listPoint) {
    GeometryFactory geomFac = new GeometryFactory();
    PoligonMy poligonMy = new PoligonMy();
    Coordinate[] coordinates = new Coordinate[(listPoint.size() + 1)];

    for (int i = 0; i < listPoint.size(); i++) {
      coordinates[i] = new Coordinate(listPoint.get(i).getLatitude(),
          listPoint.get(i).getLongitude());
    }
    coordinates[listPoint.size()] = new Coordinate(listPoint.get(0).getLatitude(),
        listPoint.get(0).getLongitude());

    LinearRing ring = geomFac.createLinearRing(coordinates);
    Polygon polygon = geomFac.createPolygon(ring);
    polygon.setSRID(SRID);
    poligonMy.setGeommy(polygon);
    poligonMy.setNames("Samara");

    return poligonMy;
  }

  /**
   * Метод расчета центра геометрии
   *
   * @param poligonMy PoligonMy
   * @return PointCentr
   */
  public PointCentr calculationOfGeometryCenter(PoligonMy poligonMy) {
    GeometryFactory geomFac = new GeometryFactory();
    var centr = new Centroid(poligonMy.getGeommy());
    Point point = geomFac.createPoint(centr.getCentroid());
    point.setSRID(SRID);
    var pointCentr = new PointCentr();
    pointCentr.setGeommy(point);
    pointCentr.setNames("Samara-Centr");
    return pointCentr;
  }

  /**
   * Метод добавления в базу данных
   *
   * @param poligonMy
   * @return PoligonMy
   */
  public PoligonMy addPoligon(PoligonMy poligonMy) {
    repository.addPoligonMy(poligonMy);
    return poligonMy;
  }

  /**
   * Метод добавления в базу данных
   *
   * @param pointCentr PointCentr
   * @return PointCentr
   */
  public PointCentr addPointCentr(PointCentr pointCentr) {
    repository.addPointCentr(pointCentr);
    return pointCentr;
  }
}
