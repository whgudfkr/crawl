
import java.util.HashMap;

import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;

public class mama {
	public static HashMap<String, Integer> startmama(String content) {
		HashMap<String, Integer> hashm = new HashMap<String, Integer>();
		mama momo = new mama();
		return momo.keTest(content, hashm);
	}



	

	public HashMap<String, Integer> keTest(String string,
			HashMap<String, Integer> hashm) {
		KeywordExtractor ke = new KeywordExtractor();
		KeywordList kl = ke.extractKeyword(string, true);
		int numbers=0;
		for (int i = 0; i < kl.size(); i++) {
                        Keyword kwrd = kl.get(i);
                        if (kwrd.getTag().compareTo("NNG") == 0
                                        || kwrd.getTag().compareTo("NNP") == 0)
				numbers += kwrd.getCnt(); 

                }
		for (int i = 0; i < kl.size(); i++) {
			Keyword kwrd = kl.get(i);
			if (kwrd.getTag().compareTo("NNG") == 0
					|| kwrd.getTag().compareTo("NNP") == 0)
				hashm.put(kwrd.getString(), kwrd.getCnt()*100000/numbers);

		}
		return hashm;
	}

}

