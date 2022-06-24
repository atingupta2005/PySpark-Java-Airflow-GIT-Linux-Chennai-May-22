import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class App {
 
    public static void main(String[] args) {
    	System.out.println("\n---------ArrayList------------------\n: ");
    	ArrayList();
    	
    	System.out.println("\n---------LinkedList------------------\n: ");
    	LinkedList();
    	
    	System.out.println("\n---------HashMap------------------\n: ");
    	HashMap();
    	
    	System.out.println("\n---------SortedMaps------------------\n: ");
    	SortedMaps();
    	
    	System.out.println("\n---------HashSets------------------\n: ");
    	HashSets();
    	
    	System.out.println("\n---------Queues------------------\n: ");
    	Queues();

    	System.out.println("\n---------Iterators------------------\n: ");
    	Iterators();
    }
    
    public static void ArrayList()
    {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        
        // Adding
        numbers.add(10);
        numbers.add(100);
        numbers.add(40);
 
        // Retrieving
        System.out.println(numbers.get(0));
 
        System.out.println("nIteration #1: ");
        // Indexed for loop iteration
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }
 
        // Removing items (careful!)
        numbers.remove(numbers.size() - 1);

 
        System.out.println("nIteration #2: ");
        for (Integer value : numbers) {
            System.out.println(value);
        }
 
    }
    
    public static void LinkedList()
    {
        /*
         * ArrayLists manage arrays internally.
         * [0][1][2][3][4][5] ....
         */
        List<Integer> arrayList = new ArrayList<Integer>();
         
        /*
         * LinkedLists consists of elements where each element
         * has a reference to the previous and next element
         * [0]->[1]->[2] ....
         *    <-   <-
         */
        List<Integer> linkedList = new LinkedList<Integer>();
         
        doTimings("ArrayList", arrayList);
        doTimings("LinkedList" , linkedList);
    }

    
    public static void HashMap()
    {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        
        map.put(5, "Five");
        map.put(8, "Eight");
        map.put(6, "Six");
        map.put(4, "Four");
        map.put(2, "Two");
         
        String text = map.get(6);
         
        System.out.println(text);
         
        for(Map.Entry<Integer, String> entry: map.entrySet()) {
            int key = entry.getKey();
            String value = entry.getValue();
             
            System.out.println(key + ": " + value);
        }
    }

    public static void SortedMaps()
    {
        Map<Integer, String> treeMap = new TreeMap<Integer, String>();
         
        testMap(treeMap);
    }
     
    public static void testMap(Map<Integer, String> map) {
        map.put(9, "fox");
        map.put(4, "cat");
        map.put(8, "dog");
        map.put(1, "giraffe");
        map.put(0, "swan");
        map.put(15, "bear");
        map.put(6, "snake");
        map.put(6, "pandas");
        
        for(Integer key: map.keySet()) {
            String value = map.get(key);
             
            System.out.println(key + ": " + value);
        }
    }

    public static void HashSets()
    {
        // HashSet does not retain order.
        // Set<String> set1 = new HashSet<String>();
 
        // LinkedHashSet remembers the order you added items in
        // Set<String> set1 = new LinkedHashSet<String>();
 
        // TreeSet sorts in natural order
        Set<String> set1 = new TreeSet<String>();
 
        if (set1.isEmpty()) {
            System.out.println("Set is empty at start");
        }
 
        set1.add("dog");
        set1.add("cat");
        set1.add("mouse");
        set1.add("snake");
        set1.add("bear");
 
        if (set1.isEmpty()) {
            System.out.println("Set is empty after adding (no!)");
        }
 
        // Adding duplicate items does nothing.
        set1.add("mouse");
 
        System.out.println(set1);
 
        // ///////// Iteration ////////////////
 
        for (String element : set1) {
            System.out.println(element);
        }
 
        // ////////// Does set contains a given item? //////////
        if (set1.contains("aardvark")) {
            System.out.println("Contains aardvark");
        }
 
        if (set1.contains("cat")) {
            System.out.println("Contains cat");
        }
 
        /// set2 contains some common elements with set1, and some new
 
        Set<String> set2 = new TreeSet<String>();
 
        set2.add("dog");
        set2.add("cat");
        set2.add("giraffe");
        set2.add("monkey");
        set2.add("ant");
         
        ////////////// Intersection ///////////////////
         
        Set<String> intersection = new HashSet<String>(set1);
         
        intersection.retainAll(set2);
         
        System.out.println("intersection : " + intersection);
         
        ////////////// Difference /////////////////////////
         
        Set<String> difference = new HashSet<String>(set2);
         
        difference.removeAll(set1);
        System.out.println("difference: " + difference);
    }

    public static void Queues()
    {
        //  (head) <- oooooooooooooooooooooooo <- (tail) FIFO (first in, first out)
        
        Queue<Integer> q1 = new ArrayBlockingQueue<Integer>(3);
         
        // Throws NoSuchElement exception --- no items in queue yet
        // System.out.println("Head of queue is: " + q1.element());
         
        q1.add(10);
        q1.add(20);
        q1.add(30);
         
        System.out.println("Head of queue is: " + q1.element());
         
        try {
            q1.add(40);
        } catch (IllegalStateException e) {
            System.out.println("Tried to add too many items to the queue.");
        }
         
        for(Integer value: q1) {
            System.out.println("Queue value: " + value);
        }
         
        System.out.println("Removed from queue: " + q1.remove());
        System.out.println("Removed from queue: " + q1.remove());
        System.out.println("Removed from queue: " + q1.remove());
         
        try {
            System.out.println("Removed from queue: " + q1.remove());
        } catch (NoSuchElementException e) {
            System.out.println("Tried to remove too many items from queue");
        }
         
        ////////////////////////////////////////////////////////////////////
         
        Queue<Integer> q2 = new ArrayBlockingQueue<Integer>(2);
        
        //The peek() method of Queue Interface returns the element at the front the container. 
        //It does not deletes the element in the container
        //This method returns the head of the queue
        //The method does not throws an exception when the Queue is empty, it returns null instead.
        System.out.println("Queue 2 peek: " + q2.peek());
        
        //The offer(E e) method of Queue Interface inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions
        // This method is preferable to add() method since this method does not throws an exception when the capacity of the container is full since it returns false.
        q2.offer(10);
        q2.offer(20);
         
        System.out.println("Queue 2 peek: " + q2.peek());
         
        if(q2.offer(30) == false) {
            System.out.println("Offer failed to add third item.");
        }
         
        for(Integer value: q2) {
            System.out.println("Queue 2 value: " + value);
        }
        
        
        //The poll() method of Queue Interface returns and removes the element at the front end of the container
        //It deletes the element in the container. The method does not throws an exception when the Queue is empty, it returns null instead.
        System.out.println("Queue 2 poll: " + q2.poll());
        System.out.println("Queue 2 poll: " + q2.poll());
        System.out.println("Queue 2 poll: " + q2.poll());
    }

    public static void Iterators()
    {

        LinkedList<String> animals = new LinkedList<String>();
 
        animals.add("fox");
        animals.add("cat");
        animals.add("dog");
        animals.add("rabbit");
         
        Iterator<String> it = animals.iterator();
 
        while (it.hasNext()) {
            String value = it.next();
            System.out.println(value);
             
            if(value.equals("cat")) {
                it.remove();
            }
        }
        
        System.out.println(animals);
         
    }

    
    private static void doTimings(String type, List<Integer> list) {
        
        for(int i=0; i<1E5; i++) {
            list.add(i);
        }
         
        long start = System.currentTimeMillis();
         
        /*
        // Add items at end of list
        for(int i=0; i<1E5; i++) {
            list.add(i);
        }
        */
         
        // Add items elsewhere in list
        for(int i=0; i<1E5; i++) {
            list.add(0, i);
        }
         
        long end = System.currentTimeMillis();
         
        System.out.println("Time taken: " + (end - start) + " ms for " + type);
    }
}