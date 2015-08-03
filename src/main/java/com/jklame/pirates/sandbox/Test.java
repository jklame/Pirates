package com.jklame.pirates.sandbox;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public final class Test
{   
    private Test()
    {
        // do nothing
    }
    
    public static void main(final String[] args)
    {
        final int multiplier = 2;
        final int choices = 2;
        final boolean trackEndStates = true;
        
        final int repetitions = 10000;
        int totalTrials = 0;
        
        final MultiSet<String> endStates = new MultiSet<>();
        
        for (int i = 0; i < repetitions; i++)
        {
            final Generator g = new Generator(multiplier, choices);
            g.generate();
            totalTrials += g.getFinalTrials();
            if (trackEndStates)
            {
                endStates.add(Arrays.toString(g.getFinalCounts()));
            }
        }
        
        System.out.println(String.format("For (m,c) = (%1$s, %2$s), expected number of trials after %3$s reps was %4$s", multiplier, choices, repetitions, (float)(totalTrials) / (float)repetitions));
        if (trackEndStates)
        {
            System.out.println("Final State Distribution");
            System.out.println(endStates.toString());
        }        
        //System.out.println(String.format("Finished after %1$s trials.  Final Counts: %2$s", g.getFinalTrials(), Arrays.toString(g.getFinalCounts())));
        
    }
    
    public static void testRandom(final String[] args)
    {
        final double[] p = new double[] {0.28, 0.5};
        
        final int[] testCounts = new int[p.length + 1];
        for (int i = 0; i < 10000; i++)
        {
            testCounts[randomPositiveBoundedInt(p)]++;
        }
        System.out.println(Arrays.toString(testCounts));
        
    }
    
    /**
     * Checks to see if every element of an int array is positive and divisible by n
     */
    private static boolean isArrayPositiveAndDivisible(final int[] a, final int n)
    {
        for (final int item : a)
        {
            if (item <= 0 || (item % n) != 0)
            {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * Returns a random integer between 0 and weights.length inclusive
     * where the likelihood of J is weights[J] for 0 <= J < weights.length
     * and the likelihood of weights.length is 1 - sum(weights);
     * 
     * Note that sum(weights) must be < 1.0
     * 
     * @param weights
     * @return a random integer between 0 and weights.length
     */
    private static int randomPositiveBoundedInt(final double[] weights)
    {
        if (weights == null)
        {
            throw new IllegalArgumentException("weights array must be non-null");
        }
        if (weights.length == 0)
        {
            return  0;
        }
        if (arraySum(weights) >= 1.0)
        {
            throw new IllegalArgumentException("weights array must sum to a number strictly less than 1.0");
        }
        final double selector = Math.random();
        double currentWeight = 0.0;
        for (int index = 0; index < weights.length; index++)
        {
            currentWeight += weights[index];
            if (selector < currentWeight)
            {
                return index;
            }
        }
        return weights.length;
    }
    
    private static double arraySum(final double[] array)
    {
        double result = 0.0;
        for (final double item : array)
        {
            result += item;
        }
        return result;
    }

    private static class Generator
    {
        private final int multiplier;
        private final int choices;
        
        private int finalTrials;
        private final int[] finalCounts;
        
        public Generator(final int multiplier, final int choices)
        {
            super();
            this.choices = choices;
            this.multiplier = multiplier;
            finalTrials = 0;
            finalCounts = new int[choices];
        }
        
        public void generate()
        {
            final double[] weights = new double[choices - 1];
            Arrays.fill(weights, 1.0 / choices);
            
            final boolean success = false;
            while (!success)
            {
                finalTrials++;
                finalCounts[randomPositiveBoundedInt(weights)]++;
                if (isArrayPositiveAndDivisible(finalCounts, multiplier))
                {
                    break;
                }
            }
        }

        @SuppressWarnings("unused")
        public final int getMultiplier()
        {
            return multiplier;
        }

        @SuppressWarnings("unused")
        public final int getChoices()
        {
            return choices;
        }

        public final int getFinalTrials()
        {
            return finalTrials;
        }

        public final int[] getFinalCounts()
        {
            return finalCounts;
        }
    }
    
    private static class MultiSet<T>
    {
        private final HashMap<T, Integer> map;

        public MultiSet()
        {
            super();
            map = new HashMap<>();
        }
        
        public void add(final T t)
        {
            map.put(t, 1 + getCount(t));
        }
        
        public int getCount(final T t)
        {
            final Integer currentCount = map.get(t);
            return currentCount == null ? 0 : currentCount.intValue();
        }

        @Override
        public String toString()
        {
            ValueComparator<T> bvc =  new ValueComparator<>(map);
            TreeMap<T, Integer> sortedMap = new TreeMap<T, Integer>(bvc);
            sortedMap.putAll(map);
            return String.format("MultiSet [map=%s]", sortedMap);
        }
        
    }
    
    private static class ValueComparator<T> implements Comparator<T> 
    {

        private final Map<T, Integer> base;
        
        public ValueComparator(final Map<T, Integer> base) 
        {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.    
        @Override
        public int compare(final T a, final T b) 
        {
            if (base.get(a) >= base.get(b)) 
            {
                return -1;
            }
            return 1;
        }
    }    
    
}
