package com.vstar.lib.widget.imagezoom;

public interface Easing {

    double easeOut(double time, double start, double end, double duration);

    double easeIn(double time, double start, double end, double duration);

    double easeInOut(double time, double start, double end, double duration);
}
