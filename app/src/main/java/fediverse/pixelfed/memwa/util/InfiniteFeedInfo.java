package fediverse.pixelfed.memwa.util;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class InfiniteFeedInfo {
	@SerializedName("title")
	@Expose
	private String title;

	@SerializedName("image_url")
	@Expose
	private String imageURL;

	@SerializedName("caption")
	@Expose
	private String caption;

	@SerializedName("time")
	@Expose
	private String time;

	public String getTitle() {
		return title;
	}

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String newImageURL) {
		this.imageURL = newImageURL;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String newCaption) {
		this.caption = newCaption;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String newTime) {
		this.time = newTime;
	}
}
