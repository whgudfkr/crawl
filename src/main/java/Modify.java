

public class Modify {

	public static String sub(String str) {
		int i = str.indexOf("]");
		if (i >= 0 && i < 15) {
			str = str.substring(i + 1);
		}
		i = str.indexOf("[");
		if (i >= 0) {
			str = str.substring(0, i);
		}
		String[] str1 = str.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < str1.length - 1; j++) {
			if (j == str1.length - 2) {
				if (str1[j].startsWith("네이버")) {

				} else {
					sb = sb.append(str1[j]).append(" ");
				}
			} else {
				if (str1[j].startsWith("디스이즈게임")||str1[j].startsWith("네이버한국경제")||str1[j].startsWith("네이버SBS")||str1[j].startsWith("네이버스포츠월드")) {
					break;
				} else {
					sb = sb.append(str1[j]).append(" ");
				}
			}
		}
		str = sb.toString();
		return str;
	}
	

}

