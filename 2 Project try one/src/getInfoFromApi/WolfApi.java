package getInfoFromApi;

import javax.swing.JOptionPane;

import starter.Start;
import apiKey.KeyFile;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class WolfApi {
	WAEngine engine = new WAEngine();
	WAQuery theQuery = engine.createQuery();
	String EnginFormat;

	public WolfApi(String EnginFormat) {
		this.EnginFormat = EnginFormat;
		startEngine();

	}

	public void startEngine() {
		KeyFile key = new KeyFile();
		String appid = "";
		try {
			appid = key.getApiKey();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Could not get api key! Stoping");
			System.exit(0);
		}
		engine.setAppID(appid);
		engine.addFormat(EnginFormat);
	}

	public WAQueryResult quary(String query) {
		theQuery.setInput(query);
		WAQueryResult queryResult = null;
		try {
			queryResult = engine.performQuery(theQuery);
			if (queryResult.isError()) {
				System.out.println("Query error");
				System.out.println("  error code: "
						+ queryResult.getErrorCode());
				System.out.println("  error message: "
						+ queryResult.getErrorMessage());
			} else if (!queryResult.isSuccess()) {
				System.out
						.println("Query was not understood; no results available.");
			} else {
				if (Start.LogResult) {
					System.out.println("Successful query. Pods follow:\n");
					for (WAPod pod : queryResult.getPods()) {
						if (!pod.isError()) {
							System.out.println(pod.getTitle());
							System.out.println("------------");
							for (WASubpod subpod : pod.getSubpods()) {
								for (Object element : subpod.getContents()) {
									if (element instanceof WAPlainText) {
										System.out
												.println(((WAPlainText) element)
														.getText());
										System.out.println("");
									}
								}
							}
							System.out.println("");
						}
					}
				}
			}
		} catch (WAException e) {
			e.printStackTrace();
		}

		return queryResult;

	}
}
