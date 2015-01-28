package com.example.hukuwarai;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Hukuwarai extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hukuwarai);
		Button b2 = (Button) findViewById(R.id.button2);
		b2.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View view) {
				// TODO 自動生成されたメソッド・スタブ
				Intent intent = new Intent(Hukuwarai.this,Ranking.class);
	               startActivity(intent);
			}
		});
	    Button cameraBtn = (Button) findViewById(R.id.button1);
	    cameraBtn.setOnClickListener(this);
	};
	    private static final int IMAGE_CAPTURE = 10001;
	    private static final String KEY_IMAGE_URI = "KEY_IMAGE_URI";

	    private Uri mImageUri;


	    /**
	     * カメラ起動ボンタン押下時の処理
	     */
	    @Override
	    public void onClick(View v) {
	        if (v.getId() == R.id.button1) {
	            mImageUri = getPhotoUri();
	            Intent intent = new Intent();
	            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
	            intent.addCategory(Intent.CATEGORY_DEFAULT);
	            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
	            startActivityForResult(intent, IMAGE_CAPTURE);
	        }
	    }

	    /**
	     * カメラから戻ってきた時の処理
	     */
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == IMAGE_CAPTURE) {
	            if (resultCode == RESULT_OK) {
	    			Intent intent = new Intent(Hukuwarai.this,Purebyu.class);
	                startActivity(intent);
	                //setImageView();
	            }
	        }
	    }


	    /**
	     * 状態を保持する
	     */
	    @Override
	    protected void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        outState.putParcelable(KEY_IMAGE_URI, mImageUri);
	    }

	    /**
	     * 保持した状態を元に戻す
	     */
	    @Override
	    protected void onRestoreInstanceState(Bundle savedInstanceState) {
	        super.onRestoreInstanceState(savedInstanceState);
	        mImageUri = (Uri) savedInstanceState.get(KEY_IMAGE_URI);
	        //setImageView();
	    }
	    /**
	     * ImageViewに画像をセットする

	    private void setImageView() {
	        ImageView imageView = (ImageView) findViewById(R.id.photo_image);
	        imageView.setImageURI(mImageUri);

	    }
**/
	    /**
	     * 画像のディレクトリパスを取得する
	     *
	     * @return
	     */
	    private String getDirPath() {
	        String dirPath = "";
	        File photoDir = null;
	        File extStorageDir = Environment.getExternalStorageDirectory();
	        if (extStorageDir.canWrite()) {
	            photoDir = new File(extStorageDir.getPath() + "/" + getPackageName());
	        }
	        if (photoDir != null) {
	            if (!photoDir.exists()) {
	                photoDir.mkdirs();
	            }
	            if (photoDir.canWrite()) {
	                dirPath = photoDir.getPath();
	            }
	        }
	        return dirPath;
	    }

	    /**
	     * 画像のUriを取得する
	     *
	     * @return
	     */
	    private Uri getPhotoUri() {
	        long currentTimeMillis = System.currentTimeMillis();
	        Date today = new Date(currentTimeMillis);
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	        String title = dateFormat.format(today);
	        String dirPath = getDirPath();
	        String fileName = "samplecameraintent_" + title + ".jpg";
	        String path = dirPath + "/" + fileName;
	        File file = new File(path);
	        ContentValues values = new ContentValues();
	        values.put(Images.Media.TITLE, title);
	        values.put(Images.Media.DISPLAY_NAME, fileName);
	        values.put(Images.Media.MIME_TYPE, "image/jpeg");
	        values.put(Images.Media.DATA, path);
	        values.put(Images.Media.DATE_TAKEN, currentTimeMillis);
	        if (file.exists()) {
	            values.put(Images.Media.SIZE, file.length());
	        }
	        Uri uri = getContentResolver().insert(Images.Media.EXTERNAL_CONTENT_URI, values);
	        return uri;
	    }
	}
