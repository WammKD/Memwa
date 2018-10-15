package fediverse.pixelfed.memwa.util;

import java.util.List;
import android.content.Context;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import org.json.JSONArray;
import java.util.ArrayList;
import android.util.Log;
import java.io.InputStream;
import android.content.res.AssetManager;
import java.io.IOException;

public class Utils {
	private static final String TAG = "Utils";

	public static List<InfiniteFeedInfo> loadInfiniteFeeds(Context context) {
		try {
			GsonBuilder            builder  = new GsonBuilder();
			Gson                   gson     = builder.create();
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
