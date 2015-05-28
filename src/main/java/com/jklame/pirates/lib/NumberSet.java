package com.jklame.pirates.lib;

import java.util.Iterator;
import java.util.List;
import java.util.function.IntPredicate;

/**
 * A NumberSet is a set of Integers defined by a range and a membershipChecker (a defintion of a contains function)  
 * 
 * @author jlame
 */
public class NumberSet implements Iterable<Integer>
{
    private final int minNumber;
    private final int maxNumber;
    private final IntPredicate membershipChecker;
    
    public NumberSet(final int minNumber, final int maxNumber, final IntPredicate membershipChecker)
    {
        if (minNumber > maxNumber)
        {
            throw new IllegalArgumentException("Min must be no larget than Max");
        }
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
        this.membershipChecker = membershipChecker;
    }

    public final int getMinNumber()
    {
        return minNumber;
    }

    public final int getMaxNumber()
    {
        return maxNumber;
    }
    
    public final boolean contains(final int candidate)
    {
        return candidate >= minNumber && candidate <= maxNumber && membershipChecker.test(candidate);
    }
    
    public final NumberSet getComplement()
    {
        return new NumberSet(minNumber, maxNumber, membershipChecker.negate());
    }
    
    public final Iterator<List<Integer>> iterator(final int tupleSize)
    {
        return new TupleIterator<>(this, tupleSize);
    }
    
    @Override
    public final Iterator<Integer> iterator()
    {
        return new Iterator<Integer>()
        {
            private boolean nextComputed = false;
            private Integer next = null;

            @Override
            public boolean hasNext()
            {
                setNext();
                return next != null;
            }

            @Override
            public Integer next()
            {
                setNext();
                nextComputed = false;
                return next;
            }
            
            private void setNext()
            {
                if(!nextComputed)
                {
                    int nextTry = next == null ? minNumber : next + 1;
                    next = null;
                    while (next == null && nextTry <= maxNumber)
                    {
                        if (contains(nextTry))
                        {
                            next = nextTry;
                        }
                        nextTry++;
                    }
                }
                nextComputed = true;
            }
        };
    }
}
