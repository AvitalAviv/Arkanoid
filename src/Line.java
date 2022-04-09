//ID: 316125855

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * Line class.
 * this class creates the line and check if the line is intersecting with other line,
 * returns the intersection point and check if two lines are equals.
 *
 * @author  Avital Aviv
 * @version 1.0
 */
public class Line implements Sprite {
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private Point start;
    private Point end;
    private double length;
    private Point midPoint;
    private java.awt.Color color;

    /**
     * constructor method.
     * it creates the line by getting the two points - start and end.
     * @param start - the start point of a line.
     * @param end - the end point of the line.
     */
    public Line(Point start, Point end) {
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.length = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
        this.midPoint = new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    /**
     * constructor method.
     * it creates the values of the ball.
     * @param x1 - the x value of the start point.
     * @param y1 - the y value of the start point.
     * @param x2 - the x value of the end point.
     * @param y2 - the y value of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.length = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
        this.midPoint = new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    /**
     * constructor method.
     * it creates the values of the ball.
     * @param start - the start point of the line.
     * @param end - the end point of the line.
     * @param color - the color of the line.
     */
    public Line(Point start, Point end, java.awt.Color color) {
        this.x1 = start.getX();
        this.y1 = start.getY();
        this.x2 = end.getX();
        this.y2 = end.getY();
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.length = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
        this.midPoint = new Point((x1 + x2) / 2, (y1 + y2) / 2);
        this.color = color;
    }

    /**
     * start method.
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * end method.
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * pointIsOnLine method.
     * this method check if the c point is between.
     * @param a - start point.
     * @param b - end point.
     * @param c - the point that the method check if appear between a and b.
     * @return boolean value.
     */
    static boolean pointIsOnLine(Point a, Point b, Point c) {

        /*
          check if the c point is between a and b but without including them.
         */
        return (c.getX() < Math.max(a.getX(), b.getX())) && (c.getX() > Math.min(a.getX(), b.getX()))
                && (c.getY() < Math.max(a.getY(), b.getY())) && (c.getY() > Math.min(a.getY(), b.getY()));
    }

    /**
     * xyOnLine method.
     * check if the x or the y value of c point is on the line (between a and b).
     * @param a - start point.
     * @param b - end point.
     * @param c - the point that the method check if appear between a and b.
     * @return boolean value.
     */
    static boolean xyOnLine(Point a, Point b, Point c) {

        /*
        check if x or y value of point c is between the given points a and b
        */
        return c.getX() < Math.max(a.getX(), b.getX()) && c.getX() > Math.min(a.getX(), b.getX())
                || c.getY() < Math.max(a.getY(), b.getY()) && c.getY() > Math.min(a.getY(), b.getY());
    }

    /**
     * slopeValue method.
     * check if the x or the y value of c point is on the line (between a and b).
     * @param a - start point - l1
     * @param b - end point - l1
     * @param c - one of the points of l2
     * @return int value.
     */
    static int slopeValue(Point a, Point b, Point c) {

        /* creating the slope by compering the slope of three points */
        double slope = ((b.getY() - a.getY()) * (c.getX() - b.getX())) - ((b.getX() - a.getX())
                * (c.getY() - b.getY()));

        /* if it 0 so the points are on one line */
        if (slope == 0) {
            return 0;
        } else if (slope > 0) { /* first orientation */
            return 1;
        } else { /* second orientation */
            return 2;
        }
    }

    /**
     * isIntersecting method.
     * check if two lines are intersecting by checking the slope and special cases.
     * if there is more than 1 intersecting point it returns false. if only one point - returns true.
     * @param other line.
     * @return boolean value.
     */
    public boolean isIntersecting(Line other) {

        /* initializing the points */
        Point l1Start = this.start;
        Point l1End = this.end;
        Point l2Start = other.start();
        Point l2End = other.end();

        /* check all the possibilities in the slopes */
        int s1 = slopeValue(l1Start, l1End, l2Start);
        int s2 = slopeValue(l1Start, l1End, l2End);
        int s3 = slopeValue(l2Start, l2End, l1Start);
        int s4 = slopeValue(l2Start, l2End, l1End);

        /* if the orientations is not the same */
        if (s1 != s2 && s3 != s4) {
            return true;
        }
        int isPointsEqual = 1;
        int isPointOnline = 1;
        int isXyOnLine = 1;

        if (l1End.equals(l1Start) && l2Start.equals(l2Start) && l1Start.equals(l2Start)) {
            return true;
        }
        /* if the lines are equals */
        if (this.equals(other)) {
            return false;
        }

        /* if the starting or the end points - can be only one intersection or infinite intersections */
        if (l1End.equals(l2Start) || l1Start.equals(l2End) || l1End.equals(l2End)
                || l1Start.equals(l2Start)) {
            isPointsEqual = 0;
        }

        /*check if lines are parallel and if the point is on the line - more than one point intersection */
        if ((s1 == 0 && pointIsOnLine(l1Start, l1End, l2Start))
                || (s2 == 0 && pointIsOnLine(l1Start, l1End, l2End))
                || (s3 == 0 && pointIsOnLine(l2Start, l2End, l1Start))
                || (s4 == 0 && pointIsOnLine(l2Start, l2End, l1End))) {
            isPointOnline = 0;
        }

        /*check if only x or y is on the line */
        if ((s1 == 0 && xyOnLine(l1Start, l1End, l2Start))
                || (s2 == 0 && xyOnLine(l1Start, l1End, l2End))
                || (s3 == 0 && xyOnLine(l2Start, l2End, l1Start))
                || (s4 == 0 && xyOnLine(l2Start, l2End, l1End))) {
            isXyOnLine = 0;
        }

        /*if there is more than one intersection points - lines are on each other*/
        if (isPointsEqual == 0 && isPointOnline == 0) {
            return false;
        }
        if ((l1Start.equals(l1End) || l2End.equals(l2Start)) && (isPointOnline == 0 || isXyOnLine == 0)) {
            return true;
        }
        if (isPointsEqual == 1 && isPointOnline == 0) {
            return false;
        }
        if (isPointsEqual == 0 && isXyOnLine == 0) {
            return false;
        }

        /* only one intersection point */
        if (isPointsEqual == 0 && isPointOnline == 1) {
            return true;
        }

        /* the case that the line is a point */
        if (l1Start.equals(l1End) || l2Start.equals(l2End)) {
            if (l1Start.equals(l1End)) {
                Point p = new Point(l1Start.getX(), l1Start.getY());
                if (p.equals(l2Start) || p.equals(l2End)) {
                    return true;
                }
                return pointIsOnLine(l2Start, l2End, p);
            } else if (l2Start.equals(l2End)) {
                Point p = new Point(l2Start.getX(), l2Start.getY());
                if (p.equals(l1Start) || p.equals(l1End)) {
                    return true;
                }
                return pointIsOnLine(l1Start, l1End, p);
            }
        }

        /* return false if not fall on one of the cases */
        return false;
    }

    /**
     * intersectionWith method.
     * returning the intersection point if two lines has only one intersection point.
     * if there is more than one, it returns null.
     * @param other line.
     * @return point or null.
     */
    public Point intersectionWith(Line other) {

        /*if it finds intersection point*/
        if (this.isIntersecting(other)) {
            Point l1Start = this.start;
            Point l1End = this.end;
            Point l2Start = other.start();
            Point l2End = other.end();

            /* creating the segment equation to create a matrix 2*2 */
            double a1 = l1End.getY() - l1Start.getY();
            double b1 = l1Start.getX() - l1End.getX();
            double c1 = (a1 * (l1Start.getX())) + (b1 * (l1Start.getY()));
            double a2 = l2End.getY() - l2Start.getY();
            double b2 = l2Start.getX() - l2End.getX();
            double c2 = (a2 * (l2Start.getX())) + (b2 * (l2Start.getY()));

            /* if determinate equals to 0, so the segments has the same slope */
            double det = (a1 * b2) - (a2 * b1);
            if (det == 0) {

                /* check all the possibilities to the returning point*/
                if (l1Start.equals(l2End)) {
                    return new Point(l1Start.getX(), l1Start.getY());
                }
                if (l1End.equals(l2Start)) {
                    return new Point(l1End.getX(), l1End.getY());
                }
                if (l1End.equals(l2End)) {
                    return new Point(l1End.getX(), l1End.getY());
                }
                if (l1Start.equals(l2Start)) {
                    return new Point(l1Start.getX(), l1Start.getY());
                }
                if (l1Start.equals(l1End)) {
                    return new Point(l1End.getX(), l1End.getY());
                }
                if (l2Start.equals(l2End)) {
                    return new Point(l2End.getX(), l2End.getY());
                }
            }

            /*creating the intersection point - the determinate is not 0*/
            double x = ((b2 * c1) - (b1 * c2)) / det;
            double y = ((a1 * c2) - (a2 * c1)) / det;
            return new Point(x, y);
        } else {

            /* if has no intersection point */
            return null;
        }
    }

    /**
     * equals method.
     * returning if two lines are equals by checking the epsilon.
     * @param other line.
     * @return point or null.
     */
    public boolean equals(Line other) {
        double epsilon = Math.pow(10, -7);

        /* find of equals by calculating the absolut value of difference if smaller than epsilon */
        return ((Math.abs(this.x1 - other.x1) < epsilon && Math.abs(this.y1 - other.y1) < epsilon)
                && (Math.abs(this.x2 - other.x2) < epsilon && Math.abs(this.y2 - other.y2) < epsilon))
                || (Math.abs(this.x1 - other.x2) < epsilon && Math.abs(this.y1 - other.y2) < epsilon)
                && (Math.abs(this.x2 - other.x1) < epsilon && Math.abs(this.y2 - other.y1) < epsilon);
    }

    /**
     * closestIntersectionToStartOfLine method.
     * return null if there is no intersection between a line and a rectangle.
     * if there is an intersection it returns the closest point to the start point of the line.
     * @param rect the rectangle.
     * @return point or null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        double a = this.start.getX();
        double b = this.start.getY();
        double c = this.end.getX();
        double d = this.end.getY();

        /* creating the line */
        Line l = new Line(a, b, c, d);

        /* check intersection */
        if (!rect.intersectionPoints(l).isEmpty()) {
            java.util.List<Point> listP = new ArrayList<Point>();
            listP = rect.intersectionPoints(l);

            /* if there is one intersection */
            if (listP.size() == 1) {
                return listP.get(0);
            }

            /* if there is two intersections */
            if (l.start.distance(listP.get(0)) <= l.start.distance(listP.get(1))) {
                return listP.get(0);
            } else {
                return listP.get(1);
            }
        } else {
            return null;
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
    }

    @Override
    public void timePassed() {
    }
}