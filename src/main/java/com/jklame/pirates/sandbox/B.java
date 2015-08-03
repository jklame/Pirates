package com.jklame.pirates.sandbox;

public class B extends A
{
    private String middle;

    public B(final String top, final String middle)
    {
        super(top);
        this.middle = middle;
    }

    public String getMiddle()
    {
        return middle;
    }

    public void setMiddle(final String middle)
    {
        this.middle = middle;
    }
}
