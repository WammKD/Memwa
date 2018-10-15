package fediverse.pixelfed.memwa.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class Utils {
	private static final String TAG = "Utils";

	public static List<InfiniteFeedInfo> loadInfiniteFeeds(Context context) {
		try {
			Gson                   gson     = new GsonBuilder().create();
			JSONArray              array    = new JSONArray(loadJSONfromAsset(context,
			                                                                  "infinite_news.json"));
			List<InfiniteFeedInfo> feedList = new ArrayList<InfiniteFeedInfo>();

			for(int i = 0; i < array.length(); i++) {
				feedList.add(gson.fromJson(array.getString(i),
				                           InfiniteFeedInfo.class));
			}

			return feedList;
		} catch(Exception e) {
			Log.d(TAG, "seedGames parseException " + e);
			e.printStackTrace();

			return null;
		}
	}

	private static String loadJSONfromAsset(Context context, String jsonFileName) {
		String      json = null;
		InputStream is   = null;

		try {
			AssetManager manager = context.getAssets();

			Log.d(TAG, "path " + jsonFileName);

			is            = manager.open(jsonFileName);
			byte[] buffer = new byte[is.available()];

			is.read(buffer);
			is.close();

			json = new String(buffer, "UTF-8");
		} catch(IOException ex) {
			ex.printStackTrace();

			return null;
		}

		return json;
	}
}
