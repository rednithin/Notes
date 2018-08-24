import java.util.*;
import java.util.regex.*;

class Program {
  public static void main(String args[]) {
    /*
     * List - ArrayList
     */
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.size();

    list.add(e);
    list.add(index, element);

    list.addAll(c); // Collection
    list.addAll(index, c);

    list.remove(index);
    list.remove(o); // Object

    /*
     * Deque - LinkedList
     */
    Deque<Integer> deque = new LinkedList<Integer>();

    deque.size();
    deque.remove(o); // Object

    // Exceptions
    deque.addFirst(e);
    deque.addLast(e);

    deque.removeFirst();
    deque.removeLast();

    deque.getFirst();
    deque.getLast();

    // Special Value
    deque.offerFirst(e);
    deque.offerLast(e);

    deque.pollFirst();
    deque.pollLast();

    deque.peekFirst();
    deque.peekLast();

    /*
     * Map - HashMap(Unordered), LinkedHashMap(Sorted by Time), TreeMap(Sorted by
     * Key).
     */
    Map<Integer, String> map = new HashMap<Integer, String>();
    Map<Integer, String> map = new LinkedHashMap<Integer, String>();
    Map<Integer, String> map = new TreeMap<Integer, String>();

    map.put(key, value);
    map.putIfAbsent(key, value);

    map.get(key);
    map.getOrDefault(key, defaultValue);

    map.keySet();
    map.values();
    map.containsKey(key);
    map.size();

    map.remove(key);
    map.remove(key, value);

    /*
     * Set - HashSet(Unordered), LinkedHashSet(Sorted by Time), TreeSet(Sorted by
     * Key).
     */
    Set<Integer> set = new HashSet<Integer>();
    Set<Integer> set = new LinkedHashSet<Integer>();
    Set<Integer> set = new TreeSet<Integer>();

    set.add(arg0);
    set.remove(o);
    set.contains(o);
    set.size();
    set.addAll(c); // Union
    set.retainAll(c); // Intersection

    // Misc
    Set<Integer> union = Sets.union(set1, set2);
    Set<Integer> intersection = Sets.intersection(set1, set2);
    Set<Integer> difference = Sets.difference(set1, set2);

    /*
     * Priority Queue
     */
    PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
      @Override
      public int compare(Integer a, Integer b) {
        return b - a; // Max Heap or descending order
      }
    });

    queue.add(arg0);
    queue.remove();
    queue.peek();
    queue.poll(); // Peek + Remove

    /*
     * Regular Expressions
     */
    regexChecker("\\s\\w+\\s", str);
    regexReplace("\\s+", ", ", str);

    /*
     * String Format Specifiers
     */
    System.out.printf("%d\n", 11); // 11
    System.out.printf("%o\n", 11); // 13
    System.out.printf("%x\n", 11); // b
    System.out.printf("%f\n", 10.01234); // 10.01234
    System.out.printf("%e\n", 10.01534); // 1.001534e+01
    System.out.printf("%.2f\n", 10.01534); // 10.02
    System.out.printf("%010.2f\n", 10.01534); // 0000010.02

    // /*
    // * Miscellaneous
    // */
    Arrays.sort(arr, start, end, Comparator);
    Arrays.parallelSort(arr, start, end, Comparator);
    Collections.sort(arr, start, end, Comparator);
    Math.log10(arg0);
    Math.log(arg0);
    Math.floor(arg0);
    Math.ceil(arg0);
    Math.sin(arg0);
    Math.cos(arg0);
    Math.tan(arg0);
    Math.pow(arg0, arg1);
    Math.abs(arg0);
    Math.toRadians(arg0);
    Math.toDegrees(arg0);

    Collections.min(coll);
    Collections.max(coll);
    Collections.frequency(coll, o);
  }

  /*
   * Regular Expressions
   */
  public static String[] regexChecker(String regex, String str) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(str);

    ArrayList<String> matches = new ArrayList<String>();
    while (matcher.find()) {
      if (matcher.group().length() != 0) {
        matches.add(matcher.group());
        // System.out.println(matcher.start());
        // System.out.println(matcher.end());
        // System.out.println(matcher.group());
      }
    }

    return (String[]) (matches.toArray());
  }

  public static String stringReplace(String regex, String replaceWith, String str) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(str);

    return matcher.replaceAll(replaceWith);
  }
}