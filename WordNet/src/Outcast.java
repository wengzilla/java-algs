public class Outcast {
    private WordNet mWordNet;

    public Outcast(WordNet wordnet) {
        mWordNet = wordnet;
    }        // constructor takes a WordNet object

    public String outcast(String[] nouns) {
        double maxDistance = -1;
        String maxNoun = "";
        double currentDistance;
        int dist;

        for (int i = 0; i < nouns.length; i++) {
            currentDistance = 0;
            for (int j = 0; j < nouns.length; j++) {
                dist = mWordNet.distance(nouns[i], nouns[j]);
                if (dist < 0) currentDistance += Integer.MAX_VALUE;
                else currentDistance += dist;
            }
            if (currentDistance > maxDistance) {
                maxDistance = currentDistance;
                maxNoun = nouns[i];
            }
        }
        return maxNoun;
    }  // given an array of WordNet nouns, return an outcast

    public static void main(String[] args) {
        WordNet wordnet = new WordNet("data/synsets.txt", "data/hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
        String fileName = "data/outcast11.txt";
        In in = new In(fileName);
        String[] nouns = in.readAllStrings();
        StdOut.println(fileName + ": " + outcast.outcast(nouns));

    } // see test client below
}