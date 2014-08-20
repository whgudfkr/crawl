import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;

public class Sort  {
	public static Map<String, Integer> sort(Map<String, Integer> hm){
		Map<String, Integer> lhm = new LinkedHashMap<String, Integer>();
		int num = hm.keySet().size()*3/100;
		int c =0;
		while(hm.size()>0){
			Iterator<String> it = hm.keySet().iterator();
			String max = "";
			int cnt = 0 ;
			while(it.hasNext()){
				String key = it.next();
				if(cnt==0){
					max = key;
					cnt=1;	
				}
				if(hm.get(key)>hm.get(max)){
					max=key;
				}
			}
			if(max.length()==1){
				hm.remove(max);
			}else{
				lhm.put(max, hm.get(max));
				c++;
				hm.remove(max);
				if(c==num) break;
			}
		}
		return lhm;
	}


}

