package fediverse.pixelfed.memwa;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Context;
import fediverse.pixelfed.memwa.util.InfiniteFeedInfo;
import com.mindorks.placeholderview.annotations.Resolve;
import com.bumptech.glide.Glide;

@Layout(R.layout.load_more_item_view)
public class ItemView {
	@View(R.id.titleTxt)
	private TextView titleTxt;

	@View(R.id.captionTxt)
	private TextView captionTxt;

	@View(R.id.timeTxt)
	private TextView timeTxt;

	@View(R.id.imageView)
	private ImageView imageView;

	private InfiniteFeedInfo mInfo;
	private Context          mContext;

	public ItemView(Context context, InfiniteFeedInfo info) {
		this.mContext = context;
		this.mInfo    = info;
	}

	@Resolve
	private void onResolved() {
		titleTxt.setText(mInfo.getTitle());
		captionTxt.setText(mInfo.getCaption());
		timeTxt.setText(mInfo.getTime());
		Glide.with(mContext).load(mInfo.getImageURL()).into(imageView);
	}
}
