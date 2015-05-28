package com.jklame.pirates.lib;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PermutationSet<T> implements Iterable<List<T>>
{
    private final List<T> masterList;

    public PermutationSet(final List<T> masterList)
    {
        super();
        this.masterList = masterList;
    }

    @Override
    public Iterator<List<T>> iterator()
    {
        return new PermutationIterator<T>(masterList);
    }
    
    public static void main(final String[] args)
    {
        final List<String> list = Arrays.asList("a", "a", "a", "a", "b", "a", "c");
        final PermutationSet<String> set = new PermutationSet<String>(list);
        for (final List<String> permutations : set)
        {
            System.out.println(permutations);
        }
        
    }
}
