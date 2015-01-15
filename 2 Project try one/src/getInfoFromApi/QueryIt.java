package getInfoFromApi;

import com.wolfram.alpha.WAQueryResult;

public class QueryIt {
	public static WAQueryResult query(String quary, String type) {
		WolfApi api = new WolfApi(type);
		return api.quary(quary);

	}

}
