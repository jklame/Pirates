package com.jklame.pirates.sandbox;

public class C extends B
{
    private final String bottom;

    public C(final String top, final String middle, final String bottom)
    {
        super(top, middle);
        this.bottom = bottom;
    }

    public String getBottom()
    {
        return bottom;
    }

}
