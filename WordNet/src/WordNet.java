import java.util.ArrayList;

public class WordNet {
    private ArrayList<String> mSynsets;
    private RedBlackBST<String, ArrayList<Integer>> mNouns;
    private Digraph mDigraph;
    private SAP mSAP;

    private static final int _ID = 0;
    private static final int _SYNSET = 1;
    private static final int _GLOSS = 2;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        mNouns = new RedBlackBST<String, ArrayList<Integer>>();
        mSynsets = new ArrayList<String>();

        In synsetsIn = new In(synsets);
        parseSynsets(synsetsIn);
        parseNouns();

        mDigraph = new Digraph(mSynsets.size());

        In hypernymsIn = new In(hypernyms);
        parseHypernyms(hypernymsIn);

        mSAP = new SAP(mDigraph);
    }

    private void parseHypernyms(In hypernymsIn) {
        String currentHypernym;
        while (hypernymsIn.hasNextLine()) {
            currentHypernym = hypernymsIn.readLine();
            String[] edges = currentHypernym.split(",");
            int origin = Integer.parseInt(edges[0]);
            for (int i = 1; i < edges.length; i++) {
                mDigraph.addEdge(origin, Integer.parseInt(edges[i]));
            }
        }
    }

    private void parseSynsets(In synsetsIn) {
        String[] line;
        while (synsetsIn.hasNextLine()) {
            line = synsetsIn.readLine().split(",");
            mSynsets.add(Integer.parseInt(line[_ID]), line[_SYNSET]);
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return mNouns.keys();
    }

    private void parseNouns() {
        for (int i = 0; i < mSynsets.size(); i++) {
            String[] nouns = mSynsets.get(i).split(" ");
            for (String noun : nouns) {
                if (mNouns.contains(noun)) mNouns.get(noun).add(i);
                else {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    mNouns.put(noun, list);
                }
            }
        }
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return mNouns.contains(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        return mSAP.length(mNouns.get(nounA), mNouns.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        int ancestorId = mSAP.ancestor(mNouns.get(nounA), mNouns.get(nounB));
        if (ancestorId < 0) return null;
        return mSynsets.get(ancestorId);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordNet = new WordNet("data/synsets.txt", "data/hypernyms.txt");
//        System.out.println("Number of nouns: " + ((Queue<String>) wordNet.nouns()).size());
        String word1 = "Black_Plague";
        String word2 = "black_marlin";
        System.out.printf("Distance between %s and %s: %d\n", word1, word2, wordNet.distance(word1, word2));
        System.out.printf("Common ancestor between %s and %s: %s", word1, word2, wordNet.sap(word1, word2));
    }
}