package com.example.hukuwarai;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ranking extends Activity{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.ranking_hukuwarai);

	    // TODO Auto-generated method stub

	    Button b2 = (Button)findViewById(R.id.button2);
	    b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO 自動生成されたメソッド・スタブ
				finish();
			}
		});
	}

}

