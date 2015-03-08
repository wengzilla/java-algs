public class Concordance {
    public static void main(String[] args) {
        System.out.println(args[0]);
        In in = new In(args[0]);
        String[] words = in.readAllStrings();
        // String[] words = StdIn.readAll().split("\\s+");
        // Not sure this is the intended behavior, I believe it's supposed to do the splitting on the input file.
        ST<String, SET<Integer>> st = new ST<String, SET<Integer>>();
        for (int i = 0; i < words.length; i++)
        {
            String s = words[i];
            if (!st.contains(s))
                st.put(s, new SET<Integer>());
            SET<Integer> pages = st.get(s);
            // set.put(i);
            // `set` variable is undefined. Also, the SET class doesn't have a put method, only an add method.
            pages.add(i);
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readString();
            SET<Integer> set = st.get(query);
            // st.get(query) can return null. We'll have to check that here so that we avoid NullPointerError.
            if (set != null) {
                for (int k : set) {
                    for (int j = -4; j < 5; j++) {
                        if (j + k < 0 || j + k > words.length - 1) continue;
                        System.out.print(words[j + k] + " ");
                    }
                    System.out.println("\n");
                }
            }
        }
    }
}
