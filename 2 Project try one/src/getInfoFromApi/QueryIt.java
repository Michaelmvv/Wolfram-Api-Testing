package getInfoFromApi;

import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class QueryIt {
	public static WAQueryResult query(String quary, String type) {
		WolfApi api = new WolfApi(type);
		return api.quary(quary);

	}

	public static String qs(String query, String type) {
		WolfApi api = new WolfApi(type);
		StringBuilder builder = new StringBuilder();

		WAQueryResult result = api.quary(query);
		if (result.isError()) {
			return "error: " + result.getErrorMessage();
		}else{
		for (WAPod pod : result.getPods()) {
			if (!pod.isError()) {
				builder.append(pod.getTitle()+"\n");
				builder.append("------------"+"\n");
				for (WASubpod subpod : pod.getSubpods()) {
					for (Object element : subpod.getContents()) {
						if (element instanceof WAPlainText) {
							builder.append(((WAPlainText) element).getText()+"\n");
						}
					}
				}
				
			}
		}
		String s = builder.toString();
		return s;

	}}
}
