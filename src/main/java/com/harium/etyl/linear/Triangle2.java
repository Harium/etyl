package com.harium.etyl.linear;

import com.badlogic.gdx.math.Vector2;


public class Triangle2 {
	
    Vector2 a;
    Vector2 b;
    Vector2 c;

    public Triangle2(Vector2 a, Vector2 b, Vector2 c) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Vector2 getA() {
        return a;
    }

    public void setA(Vector2 a) {
        this.a = a;
    }

    public Vector2 getB() {
        return b;
    }

    public void setB(Vector2 b) {
        this.b = b;
    }

    public Vector2 getC() {
        return c;
    }

    public void setC(Vector2 c) {
        this.c = c;
    }
    
    public boolean isInside(Vector2 p)
    {
        // interpret v1 and v2 as vectors
    	Vector2 v1 = new Vector2(b.x - a.x, b.y - a.y);
    	Vector2 v2 = new Vector2(c.x - a.x, c.y - a.y);
        
        double det = v1.x * v2.y - v2.x * v1.y;
        Vector2 tmp = new Vector2(p.x - a.x, p.y - a.y);
        double lambda = (tmp.x * v2.y - v2.x * tmp.y) / det;
        double mue = (v1.x * tmp.y - tmp.x*v1.y) / det;
        
        return (lambda >= 0 && mue >= 0 && (lambda+mue) <= 1);
    }
    
    public static boolean isInside(Vector2 x, Vector2 y, Vector2 z, Vector2 p)
    {
    	Vector2 v1 = new Vector2(y.x - x.x, y.y - x.y);
    	Vector2 v2 = new Vector2(z.x - x.x, z.y - x.y);
        
        double det = v1.x * v2.y - v2.x * v1.y;
        Vector2 tmp = new Vector2(p.x - x.x, p.y - x.y);
        double lambda = (tmp.x * v2.y - v2.x * tmp.y) / det;
        double mue = (v1.x * tmp.y - tmp.x*v1.y) / det;
        
        return (lambda > 0 && mue > 0 && (lambda+mue) < 1);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle2 triangle = (Triangle2) o;

        if (a != null ? !a.equals(triangle.a) : triangle.a != null) return false;
        if (b != null ? !b.equals(triangle.b) : triangle.b != null) return false;
        return c != null ? c.equals(triangle.c) : triangle.c == null;
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString() {
    	return "["+a+","+b+","+c+"]";
    }
}
