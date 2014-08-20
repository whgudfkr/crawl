import java.text.SimpleDateFormat;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Crawl {
	
	static int Pages =200;

	public static void main(String[] args) {
		Map<String, Integer> hm = new HashMap<String, Integer>();
		try {	
			System.out.println("단어를 입력하세요");
			Scanner sc = new Scanner(System.in);
			String search = sc.next();
			String starturl = "http://news.naver.com/main/search/search.nhn?query="+URLEncoder.encode(search, "EUC-KR")+"&st=news.all&q_enc=EUC-KR&r_enc=UTF-8&r_format=xml&rp=none&sm=all.basic&ic=all&so=rel.dsc&detail=0&pd=1&start=1&display=20&page=";
			if(!(new File("/home/whgudfkr/WebCrawler/java/data/"+search+".txt").isFile())){
				String path ="/home/whgudfkr/WebCrawler/java/data/"+search+".txt";
				BufferedWriter writer = new BufferedWriter(new FileWriter(path));
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i <= Pages; i++) {
					String startURL = starturl+String.valueOf(i);
					Document doc = Jsoup.connect(startURL).ignoreHttpErrors(true).get();
					Elements links = doc.select("li");
					links = links.select("dt");
					for (Element e : links) {
						String link = e.text();
						link = Modify.sub(link);
						if (link.length() > 0) {
								sb = sb.append(link).append(" ");
						}
					}
				}
				String content=sb.toString();
				hm = mama.startmama(content);
				Map<String, Integer> hml = Sort.sort(hm);
				Date date = new Date();
				String dt = (new SimpleDateFormat("yyMMddHH")).format(date);
				
				for(String key : hml.keySet())
				{
					writer.write(key+"\t"+hml.get(key)+"\t"+getURL(search,key)+"\t"+dt);
//					crawl(key); 
					writer.newLine();
				}
	
				writer.close();
				
				System.out.println("완료");
			}else System.out.println("이미 존재하는 파일입니다.");
	
		} catch (IOException e1) {
				e1.printStackTrace();
			}
	
		}


	public static void crawl(String search){
		Map<String, Integer> hm = new HashMap<String, Integer>();
		try{
                        if(!(new File("/home/whgudfkr/WebCrawler/java/data/"+search+".txt").isFile())){
                        	int k=1;
				String starturl = "http://news.naver.com/main/search/search.nhn?query="+URLEncoder.encode(search, "EUC-KR")+"&st=news.all&q_enc=EUC-KR&r_enc=UTF-8&r_format=xml&rp=none&sm=all.basic&ic=all&so=rel.dsc&detail=0&pd=1&start=1&display=20&page=";
                        	String path ="/home/whgudfkr/WebCrawler/java/data/"+search+".txt";
                  		BufferedWriter writer = new BufferedWriter(new FileWriter(path));
	                        StringBuilder sb = new StringBuilder();
        	                for (int i = 1; i <= Pages; i++) {
                	                String startURL = starturl+String.valueOf(i);
                        	        Document doc = Jsoup.connect(startURL).ignoreHttpErrors(true).get();
                                	Elements links = doc.select("li");
	                                links = links.select("dt");
        	                        for (Element e : links) {
                	                        String link = e.text();
                        	                link = Modify.sub(link);
                                	        if (link.length() > 0) {
                                        	                sb = sb.append(link).append(" ");
	                                        }
        	                        }
                	        }
                     		String content=sb.toString();
	                        hm = mama.startmama(content);
	                        Map<String, Integer> hml = Sort.sort(hm);
                   		Date date = new Date();
                                String dt = (new SimpleDateFormat("yyMMddHH")).format(date);
				for(String key : hml.keySet())
	                        {
	                                writer.write(key+"\t"+hml.get(key)+"\t"+getURL(search, key)+"\t"+dt);
	                                writer.newLine();
	                        }
	
	                        writer.close();
			} 
		}catch (IOException e1) {
                        e1.printStackTrace();
                }
        }
	public static String getURL(String search1, String search){
		String URL = "";
		search = search1+ " "+search;
		try{
			String startURL ="http://news.naver.com/main/search/search.nhn?query="+URLEncoder.encode(search, "EUC-KR")+"&st=news.all&q_enc=EUC-KR&r_enc=UTF-8&r_format=xml&rp=none&sm=all.basic&ic=all&so=rel.dsc&detail=0&pd=1&start=1&display=20&page=1";
			Document doc1 = Jsoup.connect(startURL).ignoreHttpErrors(true).get();
		        String pr = doc1.html();                                              
			pr = pr.substring(pr.indexOf("<!-- 검색 -->")+1);
	                doc1 = Jsoup.parse(pr);
	                Elements links = doc1.select("a");
			for(Element e : links){
		                URL = links.attr("href");
				if(URL.startsWith("http")) break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return URL;
	}
}
