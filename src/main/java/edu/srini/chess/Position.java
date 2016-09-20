package edu.srini.chess;

public class Position {
    int x;
    int y;
    Position(int anX, int aY){
	x = anX;
	y = aY;
    }
    public Position(Position bp) {
	this.x = bp.x;
	this.y = bp.y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    
    
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    @Override
    public String toString() {
	return "Position [x=" + x + ", y=" + y + "]";
    }
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + x;
	result = prime * result + y;
	return result;
    }
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true; 
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Position other = (Position) obj;
	if (x != other.x)
	    return false;
	if (y != other.y)
	    return false;
	return true;
    }
    
    

}
