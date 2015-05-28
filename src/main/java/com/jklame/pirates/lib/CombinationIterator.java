package com.jklame.pirates.lib;

import java.util.Iterator;

/**
 * Iterates through all distinct increasing int[] of size k from elements {0,1,..., n-1}
 * 
 * @author John
 *
 */
public class CombinationIterator implements Iterator<int[]>
{
	private final int n;
	private final int k;
	int[] current;
	int[] next;

	public CombinationIterator(final int n, final int k)
	{
		super();
		if (k < 1 || k > n)
		{
			throw new IllegalArgumentException(String.format("Illegal (n,k) of (%1$s, %2$s).  Must have 1 <= k <= n.", n, k));
		}
		this.n = n;
		this.k = k;
		this.current = null;
		setNext();
	}

	public int getN()
	{
		return n;
	}

	public int getK()
	{
		return k;
	}

	@Override
	public boolean hasNext()
	{
		return next != null;
	}

	@Override
	public int[] next()
	{
		current = next;
		setNext();
		return current;
	}

	public int[] current()
	{
		return current;
	}

	private void setNext()
	{
		// if current is null, initialize
		if (current == null)
		{
			this.next = new int[k];
			for (int i = 0; i < k; i++)
			{
				next[i] = i;
			}
			return;
		}

		// locate the largest incrementable index -- if this is -1 we are done
		int maxIncrementableIndex = k - 1;
		while (maxIncrementableIndex >= 0)
		{
			// see if we can increment at maxIncrementableIndex
			if (current[maxIncrementableIndex] < n - k + maxIncrementableIndex)
			{
				break;
			}

			// we couldn't increment at maxIncrementableIndex, try the next
			// lower index
			maxIncrementableIndex--;
		}

		if (maxIncrementableIndex < 0)
		{
			next = null;
		}
		else
		{
			next = new int[k];
			for (int index = 0; index < maxIncrementableIndex; index++)
			{
				next[index] = current[index];
			}
			next[maxIncrementableIndex] = current[maxIncrementableIndex] + 1;
			for (int index = maxIncrementableIndex + 1; index < next.length; index++)
			{
				next[index] = next[index - 1] + 1;
			}
		}
	}
}
