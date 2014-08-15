package com.appvn.cocacola;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.appvn.cocacola.draw.DrawBitmap;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class ShareActivity extends ActionBarActivity {
    private ActionBar actionBar;
    private ImageView imgPreview;
    private Button btnShare;
    private AdView adView;
    private InterstitialAd interstitialAd;
    private String UNIT_ID="ca-app-pub-1857950562418699/3555284760";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_share);

        adView=(AdView)findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId(UNIT_ID);
        interstitialAd.loadAd(new AdRequest.Builder().build());
        btnShare=(Button)findViewById(R.id.btnShare);
        imgPreview=(ImageView)findViewById(R.id.imgpreview);
        imgPreview.setImageBitmap(DrawBitmap.buildBitmap(this,0));
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/mycocacola.png";
                share(path);
            }
        });
    }


   private void share( String imagePath) {
//       File filePath =new File(imagePath);
//       Intent shareIntent = new Intent();
//       shareIntent.setAction(Intent.ACTION_SEND);
//       shareIntent.putExtra(Intent.EXTRA_TEXT, "Nice");
//       shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(filePath));
//       shareIntent.setType("image/jpeg");
//       shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//       startActivity(Intent.createChooser(shareIntent, "send"));
       Intent intent = new Intent();
       intent.setAction(Intent.ACTION_SEND);
       intent.setType("image/*");

       intent.putExtra(Intent.EXTRA_TEXT, "eample");
//       intent.putExtra(Intent.EXTRA_TITLE, "example");
//       intent.putExtra(Intent.EXTRA_SUBJECT, "example");
       intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///"+imagePath));

       Intent openInChooser = new Intent(intent);
       openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, "");
       startActivity(openInChooser);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        interstitialAd.show();
        super.onBackPressed();
    }
}
