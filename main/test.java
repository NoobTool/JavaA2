package main;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class test {
	
	public static void print(String msg) {
		System.out.println(msg);
	}
	
	public void insertionSort(ArrayList<LocalTime> arr, int n)
	{
	    int i, j;
	    LocalTime key;
	    for (i = 1; i < n; i++)
	    {
	        key = arr.get(i);
	        j = i - 1;
	 
	        /* Move elements of arr[0..i-1], that are
	        greater than key, to one position ahead
	        of their current position */
	        while (j >= 0 && arr.get(j).isAfter(key))
	        {
	            arr.set(j+1, arr.get(j));
	            j = j - 1;
	        }
	        arr.set(j+1,key);
	    }
	}
	
	public static void main(String args[]) {
		test t = new test();
		ArrayList<LocalTime> l = new ArrayList<LocalTime>();
		try {
			l.add(LocalTime.parse("18:00"));
			l.add(LocalTime.parse("14:00"));
			l.add(LocalTime.parse("09:00"));
			t.insertionSort(l,l.size());
			for(LocalTime lt: l)
				System.out.println(lt);
		}catch(Exception e) {System.out.println(e);}
		
	}	
}