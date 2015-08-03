package com.jklame.pirates.main;

import java.util.Arrays;
import java.util.List;

import com.jklame.pirates.lib.PermutationSet;

public final class TestMain
{   
    private TestMain()
    {
        // do nothing
    }
    
    public static void main(final String[] args)
    {
        final List<String> list = Arrays.asList("1", "1", "2", "2", "3", "3", "1", "1", "2", "2", "3", "3", "1", "1", "2", "2", "3", "3");
        final PermutationSet<String> set = new PermutationSet<String>(list);
        int total = 0;
        int runningPairCount = 0;
        for (final List<String> permutations : set)
        {
            int pairCount = getPairCount(permutations);
            total++;
            runningPairCount += pairCount;
            //System.out.println(String.format("%1$s: %2$s", permutations, pairCount));
        }
        System.out.println(String.format("%1$s: %2$s: %3$s.  Exp? %4$s", runningPairCount, total, (float)runningPairCount / (float)total, list.size() / 6));
    }
    
    private static int getPairCount(final List<String> list)
    {
        if (list == null || list.size() < 2)
        {
            return 0;
        }
        int count = 0;
        String first = null;
        for (final String item : list)
        {
            if (first == null)
            {
                first = item;
            }
            else
            {
                if (first.equals(item))
                {
                    count++;
                }
                first = null;
            }
        }
        return count;
    }
    
}
