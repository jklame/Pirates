package com.jklame.pirates.sandbox;

import java.util.Arrays;
import java.util.List;

import com.jklame.pirates.lib.PermutationSet;

public final class Test2
{
    private Test2()
    {
        // do nothing
    }
    
    public static void main(final String[] args)
    {
        final PermutationSet<Integer> p = new PermutationSet<>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1));
        
        for (List<Integer> a : p)
        {
            System.out.println(a);
        }
    }

}
