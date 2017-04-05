package com.zcwfeng.fastdev.ui.widget;

public class Line {
    public int x1;
	public int y1;
	public int x2;
	public int y2;

	public Line() {
	}

	public Line(int x1, int y1,int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public Line(Line src) {
		this.x1 = src.x1;
		this.y1 = src.y1;
		this.x2 = src.x2;
		this.y2 = src.y2;
	}

	public void set(int x1, int y1,int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public final void negate() {
		x1 = -x1;
		y1 = -y1;
		x2 = -x2;
		y2 = -y2;
	}

	public final void offset(int dx, int dy) {
		x1 += dx;
		y1 += dy;
		x2 += dx;
		y2 += dy;
	}

	public final boolean equals(int x1, int y1,int x2, int y2) {
		return this.x1 == x1 && this.y1 == y1 && this.x2 == x2 && this.y2 == y2;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Line) {
			Line p = (Line) o;
			return this.x1 == p.x1 && this.y1 == p.y1 && this.x2 == p.x2 && this.y2 == p.y2;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Line(" + x1 + ", " + y1 +  x2 + ", " + y2 +")";
	}
}
