package com.delightintl.demo.sort.pratice;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class BruteCollinearPoints {
    private int num;
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        num = 0;
        Stack<LineSegment> stack = new Stack<>();
        int len = points.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    Point p1 = points[i];
                    Point p2 = points[j];
                    Point p3 = points[k];
                    double slope1 = p1.slopeTo(p2);
                    double slope2 = p2.slopeTo(p3);
                    if (slope1 != slope2)
                        continue;
                    for (int l = k + 1; l < len; l++) {
                        Point p4 = points[l];
                        double slope3 = p3.slopeTo(p4);
                        if (slope2 != slope3)
                            continue;
                        else {
                            Point[] tmps = new Point[]{p1, p2, p3, p4};
                            num++;
                            Point max = tmps[0];
                            Point min = tmps[0];
                            for (int tmp = 0; tmp < tmps.length; tmp++) {
                                if (max.compareTo(tmps[tmp]) < 0)
                                    max = tmps[tmp];
                                if (min.compareTo(tmps[tmp]) > 0)
                                    min = tmps[tmp];
                            }
                            stack.push(new LineSegment(min, max));
                        }
                    }
                }
            }
        }
        Iterator<LineSegment> iterator = stack.iterator();
        segments = new LineSegment[stack.size()];
        int i = 0;
        while (iterator.hasNext()) {
            segments[i++] = iterator.next();
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return num;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
