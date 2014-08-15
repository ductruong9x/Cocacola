package com.appvn.cocacola;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.appvn.cocacola.adapter.FontAdapter;
import com.appvn.cocacola.draw.DrawBitmap;
import com.appvn.cocacola.model.Fonts;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.startapp.android.publish.StartAppAd;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class MyActivity extends ActionBarActivity {

    private ImageView imgpreview;
    private ActionBar actionBar;
    private EditText edContent;
    private SeekBar seeFont,seeRotate;
    private Spinner chooseFont;
    private FontAdapter adapter;
    private ArrayList<Fonts> list = new ArrayList<Fonts>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private String name = "";
    private String font = "";
    private int size = 0;
    private Button btnUpdate, btnSaveImage;
    private String dev_id = "108403113";
    private String app_id = "207676674";
    public static final String ACTION_UPDATE_WIDET = "com.appvn.cocacola.UPDATE_BIG_WIDGET";
    private StartAppAd startAppAd;
    private AdView adView;
    private String[] arrNameTemplate;
    private int template;
    private InterstitialAd interstitialAd;
    private String UNIT_ID="ca-app-pub-1857950562418699/3555284760";
    private ImageButton btnPreviour,btnNext;
    private int x;
    private int y;
    private int rotate;
    private Typeface font_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arrNameTemplate = new String[]{
                "Choose Template"
                , "Cocacola"
                , "Cocacola 3D"

        };
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId(UNIT_ID);
        interstitialAd.loadAd(new AdRequest.Builder().build());

        adView = (AdView) findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
        danhGia();
        font_button=Typeface.createFromAsset(getAssets(),"fonts/Lokicola.ttf");

        imgpreview = (ImageView) findViewById(R.id.imgpreview);
        edContent = (EditText) findViewById(R.id.edContent);
        seeFont = (SeekBar) findViewById(R.id.seeFont);
        seeRotate=(SeekBar)findViewById(R.id.seeRotate);
        chooseFont = (Spinner) findViewById(R.id.chooseFont);
        btnUpdate = (Button) findViewById(R.id.btnupdateWidget);
        btnSaveImage = (Button) findViewById(R.id.btnSave);
        btnNext=(ImageButton)findViewById(R.id.btnNext);
        btnPreviour=(ImageButton)findViewById(R.id.btnPreviour);

        btnUpdate.setTypeface(font_button);
        btnSaveImage.setTypeface(font_button);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        list.add(new Fonts("Choose Font", "Coke.ttf"));
        list.add(new Fonts("Cartoon", "Cartoon.ttf"));
        list.add(new Fonts("Chococooky", "Chococooky.ttf"));
        list.add(new Fonts("China", "China.ttf"));
        list.add(new Fonts("Coke", "Coke.ttf"));
        list.add(new Fonts("Dupree", "Dupree.ttf"));
        list.add(new Fonts("Forte", "Forte.ttf"));
        list.add(new Fonts("FiolexGirl","FiolexGirl.ttf"));
        list.add(new Fonts("Graffiti1","Graffiti1.ttf"));
        list.add(new Fonts("Graffiti2","Graffiti2.ttf"));
        list.add(new Fonts("Graffiti3","Graffiti3.ttf"));
        list.add(new Fonts("Graffiti3","Graffiti4.ttf"));
        list.add(new Fonts("Graffiti3","Graffiti5.ttf"));
        list.add(new Fonts("Light", "Light.ttf"));
        list.add(new Fonts("LovePassion","LovePassion.ttf"));
        list.add(new Fonts("Habano", "Habano.ttf"));
        list.add(new Fonts("Lokicola", "Lokicola.ttf"));
        list.add(new Fonts("Thin", "Thin.ttf"));
        list.add(new Fonts("Valentine","Valentine.ttf"));
        adapter = new FontAdapter(this, R.layout.item_layout, list);
        chooseFont.setAdapter(adapter);


        sharedPreferences = getSharedPreferences("content", MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        template = sharedPreferences.getInt("template", 1);
        rotate=sharedPreferences.getInt("rotate",0);
        edit = sharedPreferences.edit();
        name = sharedPreferences.getString("content", getString(R.string.myname));
        size = sharedPreferences.getInt("size", 60);
        edContent.setText(name);
        seeFont.setProgress(size);
        seeRotate.setProgress(rotate);
        imgpreview.setImageBitmap(DrawBitmap.buildBitmap(this, 0));
        imgpreview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int x = (int) event.getX();
                int y = (int) event.getY();
                Intent intent;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:

                        break;
                    case MotionEvent.ACTION_DOWN:

                        break;

                    case MotionEvent.ACTION_MOVE:
                        editor.putInt("x",x);
                        editor.putInt("y",y);
                        editor.commit();

                        imgpreview.setImageBitmap(DrawBitmap.buildBitmap(MyActivity.this, 0));
                        intent = new Intent(ACTION_UPDATE_WIDET);
                        sendBroadcast(intent);
                        break;


                }
                return false;
            }
        });
        edContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edit.putString("content", String.valueOf(s));
                edit.commit();
                imgpreview.setImageBitmap(DrawBitmap.buildBitmap(MyActivity.this,0));
                Intent intent = new Intent(ACTION_UPDATE_WIDET);
                sendBroadcast(intent);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        seeFont.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edit.putInt("size", progress);
                edit.commit();
                imgpreview.setImageBitmap(DrawBitmap.buildBitmap(MyActivity.this,0));
                Intent intent = new Intent(ACTION_UPDATE_WIDET);
                sendBroadcast(intent);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seeRotate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edit.putInt("rotate", progress);
                edit.commit();
                imgpreview.setImageBitmap(DrawBitmap.buildBitmap(MyActivity.this,0));
                Intent intent = new Intent(ACTION_UPDATE_WIDET);
                sendBroadcast(intent);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        chooseFont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Fonts fonts = (Fonts) parent.getItemAtPosition(position);
                if (position == 0) {

                } else {
                    edit.putString("fonts", fonts.getFont());
                    edit.commit();
                    imgpreview.setImageBitmap(DrawBitmap.buildBitmap(MyActivity.this,0));
                    Intent intent = new Intent(ACTION_UPDATE_WIDET);
                    sendBroadcast(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (template==12){
                    editor.putInt("template",template);
                    editor.commit();
                    imgpreview.setImageBitmap(DrawBitmap.buildBitmap(MyActivity.this,0));
                    Intent intent = new Intent(ACTION_UPDATE_WIDET);
                    sendBroadcast(intent);
                }else {
                    template++;
                    editor.putInt("template",template);
                    editor.commit();
                    imgpreview.setImageBitmap(DrawBitmap.buildBitmap(MyActivity.this,0));
                    Intent intent = new Intent(ACTION_UPDATE_WIDET);
                    sendBroadcast(intent);
                }
            }
        });
        btnPreviour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (template==1){
                    editor.putInt("template",template);
                    editor.commit();
                    imgpreview.setImageBitmap(DrawBitmap.buildBitmap(MyActivity.this,0));
                    Intent intent = new Intent(ACTION_UPDATE_WIDET);
                    sendBroadcast(intent);
                }else {
                    template--;
                    editor.putInt("template",template);
                    editor.commit();
                    imgpreview.setImageBitmap(DrawBitmap.buildBitmap(MyActivity.this,0));
                    Intent intent = new Intent(ACTION_UPDATE_WIDET);
                    sendBroadcast(intent);
                }
            }
        });


        btnSaveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = DrawBitmap.buildBitmap(MyActivity.this,1);
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mycocacola.png";
                try {
                    OutputStream stream = new FileOutputStream(path);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
                    stream.close();
                    Intent intent = new Intent(MyActivity.this, ShareActivity.class);
                    startActivity(intent);
                    String imageMimetype = setMimeType("image/png");
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.MediaColumns.DATA, path);
                    values.put(MediaStore.MediaColumns.TITLE, "mycocacola.png");
                    values.put(MediaStore.MediaColumns.DATE_ADDED,
                            System.currentTimeMillis());
                    values.put(MediaStore.MediaColumns.MIME_TYPE, imageMimetype);
                    Uri uri = getContentResolver()
                            .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    values);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(ACTION_UPDATE_WIDET);
                sendBroadcast(intent);
            }
        });


    }

    protected String setMimeType(String type) {
        if (type.equals("jpg") || type.equals("jpeg"))
            return "image/jpeg";
        else if (type.equals("png"))
            return "image/png";
        return "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.more_app:

                Intent goMoreApp = new Intent(Intent.ACTION_VIEW)
                        .setData(Uri
                                .parse("https://play.google.com/store/apps/developer?id=.FreeVN"));
                startActivity(goMoreApp);

                break;
            case R.id.rate:

                Intent goToMarket = new Intent(Intent.ACTION_VIEW).setData(Uri
                        .parse("market://details?id=" + getPackageName()));
                startActivity(goToMarket);
                break;
            case R.id.share:
                shareIt();

                break;
        }

        return true;
    }

    private void shareIt() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                "https://play.google.com/store/apps/details?id=" + getPackageName());
        startActivity(Intent.createChooser(sharingIntent, getResources()
                .getString(R.string.share_via)));
    }

    public void danhGia() {
        SharedPreferences getPre = getSharedPreferences("setting", MODE_PRIVATE);
        int i = getPre.getInt("VOTE", 0);
        SharedPreferences pre;
        SharedPreferences.Editor edit;
        switch (i) {
            case 0:
                pre = getSharedPreferences("setting", MODE_PRIVATE);
                edit = pre.edit();
                edit.putInt("VOTE", 1);
                edit.commit();
                break;
            case 1:
                pre = getSharedPreferences("setting", MODE_PRIVATE);
                edit = pre.edit();
                edit.putInt("VOTE", i + 1);
                edit.commit();
                break;
            case 2:
                pre = getSharedPreferences("setting", MODE_PRIVATE);
                edit = pre.edit();
                edit.putInt("VOTE", i + 1);
                edit.commit();
                break;
            case 3:
                pre = getSharedPreferences("setting", MODE_PRIVATE);
                edit = pre.edit();
                edit.putInt("VOTE", i + 1);
                edit.commit();
                break;
            case 4:
                pre = getSharedPreferences("setting", MODE_PRIVATE);
                edit = pre.edit();
                edit.putInt("VOTE", i + 1);
                edit.commit();
                break;
            case 5:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Vote Application");
                dialog.setMessage("You can vote for "+getString(R.string.app_name));
                dialog.setIcon(R.drawable.ic_launcher);
                dialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW)
                                .setData(Uri.parse("market://details?id="
                                        + getPackageName()));
                        startActivity(goToMarket);
                        SharedPreferences pre = getSharedPreferences("setting", MODE_PRIVATE);
                        SharedPreferences.Editor edit = pre.edit();
                        edit.putInt("VOTE", 6);
                        edit.commit();
                    }
                });
                dialog.setNeutralButton("Do not show", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences pre = getSharedPreferences("setting",
                                MODE_PRIVATE);
                        SharedPreferences.Editor edit = pre.edit();
                        edit.putInt("VOTE", 6);
                        edit.commit();
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("Later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.create().show();
                break;
        }
    }

    @Override
    public void onBackPressed() {


        AlertDialog.Builder dialog = new AlertDialog.Builder(MyActivity.this);
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setTitle(R.string.title);
        dialog.setMessage(R.string.content);
        dialog.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
               interstitialAd.show();
            }
        });
        dialog.setNeutralButton(R.string.more, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent goToMarket = new Intent(Intent.ACTION_VIEW).setData(Uri
                        .parse("market://details?id=com.gamefree.choosecolor"));
                startActivity(goToMarket);

            }
        });
        dialog.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.create().show();


        //super.onBackPressed();
    }
}
