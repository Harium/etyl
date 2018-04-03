package com.harium.etyl.util.triangulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.harium.etyl.geometry.Triangle2;

import com.badlogic.gdx.math.Vector2;

/**
 * Based on http://www.sunshine2k.de/coding/java/Polygon/Kong/Kong.html
 *
 */

public class KongTriangulation {

	private List<Vector2> points;
    private Vector<Vector2> nonconvexPoints;
    
    // orientation of polygon - true = clockwise, false = counterclockwise
    private boolean isCw;

    public KongTriangulation(List<Vector2> points)
    {
        // we have to copy the point vector as we modify it
        this.points = new Vector<Vector2>();
        for (int i = 0; i < points.size(); i++)
            this.points.add(new Vector2(points.get(i)));

        nonconvexPoints = new Vector<Vector2>();
        
        calcPolyOrientation();
        calcNonConvexPoints();
    }
    
    /*
     * This determines all concave vertices of the polygon.
     */
    private void calcNonConvexPoints()
    {
        // safety check, with less than 4 points we have to do nothing
        if (points.size() <= 3) return;
        
        // actual three points
        Vector2 p;
        Vector2 v;
        Vector2 u;
        // result value of test function
        float res = 0;
        for (int i = 0; i < points.size() - 1; i++)
        {
            p = points.get(i);
            Vector2 tmp = points.get(i+1);
            v = new Vector2();        // interpret v as vector from i to i+1
            v.x = tmp.x - p.x;
            v.y = tmp.y - p.y;
            
            // ugly - last polygon segment goes from last point to first point
            if (i == points.size() - 2)
                u = points.get(0);
            else
                u = points.get(i+2);
           
            res = u.x * v.y - u.y * v.x + v.x * p.y - v.y * p.x;
            // note: cw means res/newres is <= 0
            if ( (res > 0 && isCw) || (res <= 0 && !isCw) )
            {
                nonconvexPoints.add(tmp);
                //System.out.println("konkav point #" + (i+1) + "  Coords: " + tmp.x + "/" + tmp.y);
            }
            
        }
    }
    
    /*
     * Get the orientation of the polygon - clockwise (cw) or counter-clockwise (ccw)
     */
    private void calcPolyOrientation()
    {
        if (points.size() < 3) return;
        
        // first find point with minimum x-coord - if there are several ones take
        // the one with maximal y-coord
        int index = 0;      // index of point in vector to find
        Vector2 pointOfIndex = points.get(0);
        for (int i = 1; i < points.size(); i++)
        {
            if (points.get(i).x < pointOfIndex.x)
            {
                pointOfIndex = points.get(i);
                index = i;
            }
            else
                if (points.get(i).x == pointOfIndex.x && points.get(i).y > pointOfIndex.y)
                {
                    pointOfIndex = points.get(i);
                    index = i;
                }
        }
        
        // get vector from index-1 to index
        Vector2 prevPointOfIndex;
        if (index == 0)
            prevPointOfIndex =  points.get(points.size() - 1);
        else
            prevPointOfIndex =  points.get(index - 1);
        Vector2 v1 = new Vector2(pointOfIndex.x - prevPointOfIndex.x, pointOfIndex.y - prevPointOfIndex.y);
        // get next point
        Vector2 succPointOfIndex;
        if (index == points.size() - 1)
            succPointOfIndex = points.get(0);
        else
            succPointOfIndex = points.get(index  + 1);
        
        // get orientation
        float res = succPointOfIndex.x * v1.y - succPointOfIndex.y * v1.x + v1.x * prevPointOfIndex.y - v1.y * prevPointOfIndex.x;

        isCw = (res <= 0 ? true : false);
        //System.out.println("isCw : " + isCw);
    }
    
    /*
     * Returns true if the triangle formed by the three given points is an
     * ear considering the polygon - thus if no other point is inside and it is
     * convex. Otherwise false.
     */
    private boolean isEar(Vector2 p1, Vector2 p2, Vector2 p3)
    {
        // not convex, bye
        if (!(isConvex(p1, p2, p3))) return false;
        
        // iterate over all konkav points and check if one of them lies inside the given triangle
        for (int i = 0; i < nonconvexPoints.size(); i++)
        {
            if (Triangle2.isInside(p1, p2, p3, nonconvexPoints.get(i) ))
                return false;
        }
        return true;
    }
    
    /*
     * Returns true if the point p2 is convex considered the actual polygon. 
     * p1, p2 and p3 are three consecutive points of the polygon.
     */
    private boolean isConvex(Vector2 p1, Vector2 p2, Vector2 p3)
    {
        Vector2 v = new Vector2(p2.x - p1.x, p2.y - p1.y);
        float res = p3.x * v.y - p3.y * v.x + v.x * p1.y - v.y * p1.x;
        return !( (res > 0 && isCw) || (res <= 0 && !isCw) );
    }
    
    /*
     * This is a helper function for accessing consecutive points of the polygon
     * vector. It ensures that no IndexOutofBoundsException occurs.
     * @param index is the base index of the point to be accessed
     * @param offset to be added/subtracted to the index value
     */
    private int getIndex(int index, int offset)
    {
        int newindex;
        //System.out.println("size " + points.size() + " index:" + index + " offset:" + offset);
        if (index + offset >= points.size())
            newindex =  points.size() - (index + offset);
        else
        {
            if (index + offset < 0)
                newindex =  points.size() + (index + offset);
            else
                newindex = index + offset;
        }
        //System.out.println("new index = " + newindex);
        return newindex;
    }
    
    public List<Triangle2> triangulate() {
    	List<Triangle2> triangles = new ArrayList<Triangle2>();
    	
    	if (points.size() <= 3) return triangles;
            	        
        int index = 1;
        
        while (points.size() > 3)
        {
                                    
            if (isEar(points.get(getIndex(index, -1)), points.get(index), points.get(getIndex(index, 1))))
            {
                // cut ear
                triangles.add(new Triangle2(points.get(getIndex(index, -1)), points.get(index), points.get(getIndex(index, 1))));
                
                points.remove(points.get(index));

                index = getIndex(index, -1);
            }
            else
            {
                index = getIndex(index, 1);
            }
        }
        // add last triangle
        triangles.add(new Triangle2(points.get(0), points.get(1), points.get(2)));
        
        return triangles;
    }
    	
}
