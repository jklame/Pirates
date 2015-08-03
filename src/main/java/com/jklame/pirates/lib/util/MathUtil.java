package com.jklame.pirates.lib.util;

public final class MathUtil
{
    private MathUtil()
    {
        // do nothing
    }
    
    public static long nChooseK(final int n, final int k)
    {
        if (k < 0 || n < 0 || k > n)
        {
            throw new IllegalArgumentException("parameters must be non-negative with k <= n");
        }
        if (k == 0 || k == n)
        {
            return 1;
        }
        return nChooseK(n - 1, k - 1) + nChooseK(n - 1, k);
    }
    
    public static long f(final int a, final int b)
    {
        return nChooseK(a + b, b) - g(a, b);
    }
    
    public static long g(final int a, final int b)
    {
        if (a <= 0 || b <= 0)
        {
            return 0;
        }
        return g(a - 1, b) + g(a, b - 1) + h(a - 1, b) + h(a, b - 1);
    }
    
    public static long h(final int a, final int b)
    {
        if (a <= 0 || b <= 0 || a % 2 == 1 || b % 2 == 1)
        {
            return 0;
        }
        return f(a, b - 1) + f(a - 1, b);
    }
    
    public static void main(final String[] args)
    {
        for (int t = 4; t <= 20; t += 2)
        {
            int tCount = 0;
            for (int a = 2; a < t; a += 2)
            {
                final int b = t - a;
                //final long f = f(a, b);
                //final long g = g(a, b);
                final long h = h(a, b);
                tCount += h;
                //System.out.println(String.format("h(%s, %s) = %s", a, b, h));
                //System.out.println(String.format("for (a,b) = (%s, %s): (f, g, h) = (%s, %s, %s)", a, b, f, g, h));
            }
            System.out.printf("Number of complete reachable sequences of size %s = %s = 6(%s).\n", t, tCount, tCount / 6.0);
        }
    }
}
