package com.delightintl.demo.sort.pratice;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.Iterator;
import java.util.Comparator;


public class FastCollinearPoints {
    private final int num;
    private final LineSegment[] segments;
    private List<Line> list;
    private Point[] points;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] pointsIn) {
        this.list = new ArrayList<>();
        if(pointsIn == null) throw new IllegalArgumentException("there is no point");
        int len=pointsIn.length;
        for(int i=0;i<len;i++) if(pointsIn[i]==null) throw new IllegalArgumentException("exist null point");
        points = new Point[len];
        for(int i=0;i<len;i++) points[i] = pointsIn[i];
        Arrays.sort(points);
        for(int i=1;i<len;i++) if(points[i-1].compareTo(points[i])==0) throw new IllegalArgumentException("exist repeated point");

        for (int i = 0; i < len; i++) {
            Point[] quickPoints = Arrays.copyOf(points, len);
            getLine(quickPoints, points[i].slopeOrder());
        }
        Line[] lines = list.toArray(new Line[0]);
        if (lines.length == 0) {
            segments = new LineSegment[0];
            num = segments.length;
            return;
        }
        list = null;
        Arrays.sort(lines);
        Stack<Line> stack = new Stack<>();
        stack.push(lines[0]);
        Line compareLine = lines[0];
        for (int i = 1; i < lines.length; i++) {
            Line line = lines[i];
            if (!compareLine.equals(line)) {
                Point p1 = compareLine.points[0];
                Point p2 = compareLine.points[1];
                Point p3 = line.points[0];
                Point p4 = line.points[1];
                if (p1.compareTo(p3) == 0) {
                    p3 = p4;
                }
                if (p1.slopeOrder().compare(p2, p3) == 0) {
                    Point[] points1 = new Point[]{p1, p2, p3, p4};
                    Arrays.sort(points1);
                    line = new Line(new Point[]{points1[0], points1[3]});
                    stack.pop();
                }
                stack.push(line);
                compareLine = line;
            }
        }
        segments = new LineSegment[stack.size()];
        int i = 0;
        Iterator<Line> iterator = stack.iterator();
        while (iterator.hasNext()) {
            Line line = iterator.next();
            segments[i++] = new LineSegment(line.points[0], line.points[1]);
        }
        num = segments.length;
    }

    // the number of line segments
    public int numberOfSegments() {
        return num;
    }

    public LineSegment[] segments() {
        LineSegment[] lineSegments = Arrays.copyOf(segments, segments.length);
        return lineSegments;
    }

    private class Line implements Comparable<Line> {
        Point[] points;
        double slope;

        public Line(Point[] points) {
            this.points = points;
            this.slope = points[0].slopeTo(points[1]);
        }


        @Override
        public int compareTo(Line o) {
            if (slope > o.slope) {
                return 1;
            } else if (slope < o.slope) {
                return -1;
            }
            return points[0].compareTo(o.points[0]);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!obj.getClass().equals(Line.class)) return false;
            Line line = (Line) obj;
            boolean p1 = line.points[0].compareTo(points[0]) == 0;
            boolean p2 = line.points[0].compareTo(points[1]) == 0;
            boolean p3 = line.points[1].compareTo(points[0]) == 0;
            boolean p4 = line.points[1].compareTo(points[1]) == 0;
            boolean e = Double.compare(slope, line.slope) == 0;
            if ((p1 || p2 || p3 || p4) && e) {
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return slope + "";
        }
    }


    private void getLine(Point[] points, Comparator<Point> comparator) {
        int length = points.length;
        for (int i = 0; i < points.length; i++) {
            Point[] partitionPoints = Arrays.copyOf(points, length);
            swap(partitionPoints, i, length - 1);
            int[] partition = partition(partitionPoints, 0, length - 1, comparator);
            int lo = partition[0];
            int hi = partition[1];
            if (hi - lo + 1 >= 3) {
                Point[] sortPoints = new Point[hi - lo + 1];
                for (int j = 0; j < sortPoints.length; j++)
                    sortPoints[j] = partitionPoints[lo++];
                Arrays.sort(sortPoints);
                Line line = new Line(new Point[]{sortPoints[0], sortPoints[sortPoints.length - 1]});
                list.add(line);
            }
        }
    }

    private int[] partition(Point[] arr, int lo, int hi, Comparator<Point> comparator) {
        int less = lo - 1;
        int more = hi;
        while (lo < more) {
            int compare = comparator.compare(arr[lo], arr[hi]);
            if (compare < 0) {
                swap(arr, ++less, lo++);
            } else if (compare > 0) {
                swap(arr, lo, --more);
            } else {
                lo++;
            }
        }
        swap(arr, more, hi);
        return new int[]{less + 1, more};
    }


    private void swap(Point[] arr, int i, int j) {
        Point tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
