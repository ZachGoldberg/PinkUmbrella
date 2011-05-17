package com.pinkumbrella.waterhole;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.pinkumbrella.service.PredictionService;

public class BarActivity extends ListActivity implements Runnable {

	private ProgressDialog pd;
	List<String> items = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	
		
	}

	public void run() {
		try {
			items = PredictionService.getRecommendedPlaces(BarActivity.this,
					40.756620, -73.997855);
			
			handler.sendEmptyMessage(0);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			handler.sendEmptyMessage(-1);

		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			
			if(msg.what == 0) {
			pd.dismiss();
			BarActivity.this.setListAdapter(new ArrayAdapter<String>(
					BarActivity.this, android.R.layout.simple_list_item_1,
					items));
			} else {
				pd.dismiss();
				Toast.makeText(BarActivity.this, "I had results. But I eated them.", Toast.LENGTH_SHORT);
			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.barmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.connect:

			pd = ProgressDialog.show(this, "Plx wait", "kthx", true,
					false);

			Thread thread = new Thread(this);
			thread.start();
			return true;

		case R.id.debug:

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}