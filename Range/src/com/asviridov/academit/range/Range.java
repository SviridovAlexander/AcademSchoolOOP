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

    public Range getIntersection(Range other) {
        double intersectionStart = Math.max(this.from, other.from);
        double intersectionEnd = Math.min(this.to, other.to);

        if (intersectionStart > intersectionEnd || (intersectionStart == intersectionEnd)) {
            // No intersection
            return null;
        }

        if (intersectionStart == this.from && intersectionEnd == this.to) {
            // The other range is completely contained within this range
            return this;
        }

        if (intersectionStart == other.from && intersectionEnd == other.to) {
            // This range is completely contained within the other range
            return other;
        }

        // Intersection exists but is not the entire range
        return new Range(intersectionStart, intersectionEnd);
    }

    public Range[] getUnion(Range other) {
        if (this.to < other.from || other.to < this.from) {
            // No overlap, return both ranges separately
            return new Range[]{this, other};
        }

        int unionStart = (int) Math.min(this.from, other.from);
        int unionEnd = (int) Math.max(this.to, other.to);

        Range union = new Range(unionStart, unionEnd);
        return new Range[]{union};
    }

    public Range[] getDifference(Range other) {
        if (this.to < other.from || other.to < this.from) {
            // No overlap, return this range as is
            return new Range[]{this};
        }

        if (other.from <= this.from && other.to >= this.to) {
            // The other range completely covers this range, return empty array
            return new Range[]{};
        }

        if (other.from <= this.from) {
            if (other.to == this.from) {
                return new Range[]{this};
            }
            // The other range overlaps the start of this range
            Range difference = new Range(other.to, this.to);
            return new Range[]{difference};
        }

        if (other.to >= this.to) {
            if (other.from == this.to) {
                return new Range[]{this};
            }
            // The other range overlaps the end of this range
            Range difference = new Range(this.from, other.from);
            return new Range[]{difference};
        }

        // The other range is within this range, return two separate pieces
        Range difference1 = new Range(this.from, other.from);
        Range difference2 = new Range(other.to, this.to);
        return new Range[]{difference1, difference2};
    }
}