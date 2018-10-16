package fediverse.pixelfed.memwa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.mindorks.placeholderview.InfinitePlaceHolderView;
import fediverse.pixelfed.memwa.util.InfiniteFeedInfo;
import fediverse.pixelfed.memwa.util.Utils;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private TextView                         mTextMessage;
	private InfinitePlaceHolderView          mLoadMoreView;
	private OnNavigationItemSelectedListener myONISL      = new OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				switch(item.getItemId()) {
					case R.id.navigation_home:
						mTextMessage.setText(R.string.title_home);
						mTextMessage.setVisibility(View.VISIBLE);
						mLoadMoreView.setVisibility(View.INVISIBLE);

						return true;
					case R.id.navigation_dashboard:
						mTextMessage.setVisibility(View.INVISIBLE);
						mLoadMoreView.setVisibility(View.VISIBLE);

						return true;
					case R.id.navigation_notifications:
						mTextMessage.setText(R.string.title_notifications);
						mTextMessage.setVisibility(View.VISIBLE);
						mLoadMoreView.setVisibility(View.INVISIBLE);

						return true;
				}

				return false;
			}
		};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTextMessage                    = (TextView)                findViewById(R.id.message);
		mLoadMoreView                   = (InfinitePlaceHolderView) findViewById(R.id.loadMoreView);
		BottomNavigationView navigation = (BottomNavigationView)    findViewById(R.id.navigation);

		mLoadMoreView.setVisibility(View.INVISIBLE);

		this.setupView();

		navigation.setOnNavigationItemSelectedListener(myONISL);
	}

	private void setupView() {
		List<InfiniteFeedInfo> feedList = Utils.loadInfiniteFeeds(this.getApplicationContext());

		Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " +
		               LoadMoreView.LOAD_VIEW_SET_COUNT);

		for(int i = 0; i < LoadMoreView.LOAD_VIEW_SET_COUNT; i++) {
			mLoadMoreView.addView(new ItemView(this.getApplicationContext(),
			                                   feedList.get(i)));
		}

		mLoadMoreView.setLoadMoreResolver(new LoadMoreView(mLoadMoreView,
		                                                   feedList));
	}
}
