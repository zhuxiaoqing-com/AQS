package com.youxi.dataStructure.box2d3_2.collidePolygons.entity;

public class b2Polygon {
    /// The polygon vertices
    public b2Vec2 vertices[];

    /// The outward normal vectors of the polygon sides
    public b2Vec2 normals[];

    /// The centroid of the polygon
   public  b2Vec2 centroid;

    /// The external radius for rounded polygons
    public float radius;

    /// The number of polygon vertices
   public  int count;
}
