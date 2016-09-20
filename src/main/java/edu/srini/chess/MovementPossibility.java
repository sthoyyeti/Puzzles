package edu.srini.chess;

public class MovementPossibility {

    Position posNW;

    Position posNE;

    Position posEN;

    Position posES;

    Position posSW;

    Position posSE;

    Position posWN;

    Position posWS;

    public void reset() {
	posNW = null;
	posNE = null;

	posEN = null;
	posES = null;

	posSE = null;
	posSW = null;

	posWN = null;
	posWS = null;

    }

    public boolean isMovementPossible() {
	return ((posNW != null) || (posNE != null) || (posSE != null) || (posSW != null) || (posWN != null)
		|| (posWS != null) || (posEN != null) || (posES != null));
    }

    public boolean isNWExists() {
	return (posNW != null);
    }

    public boolean isNEExists() {
	return (posNE != null);
    }

    public boolean isSWExists() {
	return (posSW != null);
    }

    public boolean isSEExists() {
	return (posSE != null);
    }

    public boolean isENExists() {
	return (posEN != null);
    }

    public boolean isESExists() {
	return (posES != null);
    }

    public boolean isWNExists() {
	return (posWN != null);
    }

    public boolean isWSExists() {
	return (posWS != null);
    }

    public Position getPosNW() {
	return posNW;
    }

    public void setPosNW(Position posNW) {
	this.posNW = posNW;
    }

    public Position getPosNE() {
	return posNE;
    }

    public void setPosNE(Position posNE) {
	this.posNE = posNE;
    }

    public Position getPosEN() {
	return posEN;
    }

    public void setPosEN(Position posEN) {
	this.posEN = posEN;
    }

    public Position getPosES() {
	return posES;
    }

    public void setPosES(Position posES) {
	this.posES = posES;
    }

    public Position getPosSW() {
	return posSW;
    }

    public void setPosSW(Position posSW) {
	this.posSW = posSW;
    }

    public Position getPosSE() {
	return posSE;
    }

    public void setPosSE(Position posSE) {
	this.posSE = posSE;
    }

    public Position getPosWN() {
	return posWN;
    }

    public void setPosWN(Position posWN) {
	this.posWN = posWN;
    }

    public Position getPosWS() {
	return posWS;
    }

    public void setPosWS(Position posWS) {
	this.posWS = posWS;
    }

    @Override
    public String toString() {
	return "MovementPossibility [posNW=" + posNW + ", posNE=" + posNE + ", posEN=" + posEN + ", posES=" + posES
		+ ", posSW=" + posSW + ", posSE=" + posSE + ", posWN=" + posWN + ", posWS=" + posWS + "]";
    }

}
