package com.jklame.pirates.lib;

import java.util.Arrays;
import java.util.Iterator;

public class CombinationSet implements Iterable<int[]>
{
	private final int n;
	private final int k;

	public CombinationSet(final int n, final int k)
	{
		super();
		if (k < 1 || k > n)
		{
			throw new IllegalArgumentException(String.format("Illegal (n,k) of (%1$s, %2$s).  Must have 1 <= k <= n.", n, k));
		}
		this.n = n;
		this.k = k;
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
	public Iterator<int[]> iterator()
	{
		return new CombinationIterator(n, k);
	}
	
	public static void main(final String[] args)
	{
		final CombinationSet c = new CombinationSet(1, 1);
		for (final int[] x : c)
		{
			System.out.println(Arrays.toString(x));
		}
	}
}
