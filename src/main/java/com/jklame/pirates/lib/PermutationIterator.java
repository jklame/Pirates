package com.jklame.pirates.lib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterates over all distinct permutations of an List<T>
 * 
 * @author John
 * @param <T> 
 */
public class PermutationIterator<T> implements Iterator<List<T>>
{
    private List<T> masterList;
    private List<T> current;
    private List<T> next;
    private int size;
    
    // this is the list of all items in the masterList which are equal to the first item
    private List<T> initialSegment;
    // this is the list of all items in the masterList which are not equal to the first item
    private List<T> finalSegment;
    
    private CombinationIterator initialSegmentIterator;
    private PermutationIterator<T> finalSegmentIterator;

    public PermutationIterator(final List<T> masterList)
    {
        super();
        //System.out.println(String.format("Initializing masterList: %1$s", masterList));
        validateList(masterList);
        this.masterList = new ArrayList<T>();
        this.masterList.addAll(masterList);
        this.size = masterList.size();
        setInitialAndFinalSegments();
        //System.out.println(String.format("Building CombinationIterator(%1$s, %2$s)", size, initialSegment.size()));
        this.initialSegmentIterator = new CombinationIterator(size, initialSegment.size());
        this.finalSegmentIterator = finalSegment == null || finalSegment.isEmpty() ? null : new PermutationIterator<T>(finalSegment);
        this.current = null;
        setNext();
    }

    @Override
    public boolean hasNext()
    {
        return next != null;
    }

    @Override
    public List<T> next()
    {
        current = next;
        setNext();
        return current;
    }
    
    public List<T> current()
    {
        return current;
    }
    
    private static void validateList(final List<?> list)
    {
        if (list == null)
        {
            throw new IllegalArgumentException("Master list must be non-null");
        }
        for (final Object o : list)
        {
            if (o == null)
            {
                throw new IllegalArgumentException("Master list must not contain null items");
            }
        }
    }
    
    private void setInitialAndFinalSegments()
    {
        this.initialSegment = new ArrayList<>();
        this.finalSegment = new ArrayList<>();
        if (masterList.isEmpty())
        {
            return;
        }
        final T initialT = masterList.get(0);
        for (T t : masterList)
        {
            if (initialT.equals(t))
            {
                initialSegment.add(t);
            }
            else
            {
                finalSegment.add(t);
            }
        }
    }
    
    private void setNext()
    {
        this.next = null;
        // If there is no initialSegment then this Iterator is empty
        if (initialSegment.size() == 0)
        {
            return;
        }
        
        final T initialSegmentItem = initialSegment.get(0);
        int[] initialSegmentIndices = null;
        List<T> finalSegmentPermutation = null;
        if (this.current == null)
        {
            // first time through -- get next of both initial and final segment iterators if they exist
            if (initialSegmentIterator.hasNext())
            {
                initialSegmentIndices = initialSegmentIterator.next();
                if (finalSegmentIterator != null && finalSegmentIterator.hasNext())
                {
                    finalSegmentPermutation = finalSegmentIterator.next();
                }
            }
        }
        else
        {
            // not first time through -- see if final segment iterator has next
            if (finalSegmentIterator != null && finalSegmentIterator.hasNext())
            {
                initialSegmentIndices = initialSegmentIterator.current();
                finalSegmentPermutation = finalSegmentIterator.next();
            }
            else
            {
                // final segment iterator was done -- advance the initial segment iterator and start a new final segment iterator
                if (initialSegmentIterator.hasNext())
                {
                    initialSegmentIndices = initialSegmentIterator.next();
                    this.finalSegmentIterator = finalSegment == null || finalSegment.isEmpty() ? null : new PermutationIterator<T>(finalSegment);
                    if (finalSegmentIterator != null && finalSegmentIterator.hasNext())
                    {
                        finalSegmentPermutation = finalSegmentIterator.next();
                    }
                }
            }
        }
        this.next = buildList(initialSegmentItem, initialSegmentIndices, finalSegmentPermutation);
        return;
    }
    
    private List<T> buildList(final T initialSegmentItem, final int[] initialSegmentIndices, final List<T> finalSegmentPermutation)
    {
        
        if (initialSegmentItem == null || initialSegmentIndices == null || initialSegmentIndices.length == 0)
        {
            return null;
        }
        final List<T> result = new ArrayList<>();
        if (finalSegmentPermutation != null)
        {
            result.addAll(finalSegmentPermutation);
        }
        for (int index : initialSegmentIndices)
        {
            result.add(index, initialSegmentItem);
        }
        return result;
    }
}
