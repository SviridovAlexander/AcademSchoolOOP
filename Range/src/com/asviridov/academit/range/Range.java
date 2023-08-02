package com.asviridov.academit.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        double intersectionFrom = Math.max(from, range.from);
        double intersectionTo = Math.min(to, range.to);

        if (intersectionFrom >= intersectionTo) {
            return null;
        }

        return new Range(intersectionFrom, intersectionTo);
    }

    public Range[] getUnion(Range range) {
        if (to < range.from || range.to < from) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (to <= range.from || range.to <= from) {
            return new Range[]{new Range(from, to)};
        }

        if (range.from <= from && range.to >= to) {
            return new Range[0];
        }

        if (range.from <= from) {
            return new Range[]{new Range(range.to, to)};
        }

        if (range.to >= to) {
            return new Range[]{new Range(from, range.from)};
        }

        return new Range[]{new Range(from, range.from), new Range(range.to, to)};
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }
}