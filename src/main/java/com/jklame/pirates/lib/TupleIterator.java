package com.jklame.pirates.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TupleIterator<E> implements Iterator<List<E>>
{
    private final Iterable<E> baseIterable;
    private final int tupleSize;
    
    private TupleIterator<E> baseIterator;
    private List<E> baseList;
    private Iterator<E> tailIterator;
    private final boolean isEmpty;
    
    public TupleIterator(final Iterable<E> baseIterable, final int tupleSize)
    {
        if (baseIterable == null)
        {
            throw new IllegalArgumentException("Base iterable must be non-null");
        }
        if (tupleSize < 1)
        {
            throw new IllegalArgumentException(String.format("Illegal value %1$s for tupleSize.  Value must be >= 1", tupleSize));
        }
        this.baseIterable = baseIterable;
        this.tupleSize = tupleSize;
        
        baseIterator = tupleSize == 1 ? null : new TupleIterator<E>(baseIterable, tupleSize - 1);
        baseList = baseIterator == null ? null : baseIterator.hasNext() ? baseIterator.next() : null; 
        tailIterator = baseIterable.iterator();
        isEmpty = !tailIterator.hasNext() || (tupleSize != 1 && baseList == null);        
        
    }
    
    @Override
    public boolean hasNext()
    {
        return tupleSize == 1 ? tailIterator.hasNext() : !isEmpty && (tailIterator.hasNext() || baseIterator.hasNext());
    }

    @Override
    public List<E> next()
    {
        if (tupleSize == 1)
        {
            return Arrays.asList(tailIterator.next());
        }
        if (isEmpty)
        {
            return null;
        }
        if (!tailIterator.hasNext())
        {
            if (!baseIterator.hasNext())
            {
                return null;
            }
            baseList = baseIterator.next();
            tailIterator = baseIterable.iterator();
        }
        final ArrayList<E> result = new ArrayList<>();
        result.addAll(baseList);
        result.add(tailIterator.next());
        return result;
    }
}
