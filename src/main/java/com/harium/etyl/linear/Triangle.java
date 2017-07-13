package com.harium.etyl.linear;

import com.badlogic.gdx.math.Vector3;


public class Triangle {
	
	Vector3 a;
    Vector3 b;
    Vector3 c;

    public Triangle(Vector3 a, Vector3 b, Vector3 c) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Vector3 getA() {
        return a;
    }

    public void setA(Vector3 a) {
        this.a = a;
    }

    public Vector3 getB() {
        return b;
    }

    public void setB(Vector3 b) {
        this.b = b;
    }

    public Vector3 getC() {
        return c;
    }

    public void setC(Vector3 c) {
        this.c = c;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

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
