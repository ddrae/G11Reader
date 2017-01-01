package com.g11.g11reader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Environment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.g11.g11reader.backend.Backend;
import com.g11.g11reader.backend.Book;
import com.g11.g11reader.fileinput.BookLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = false;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private ImageView mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    //private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
    //    @Override
    //    public boolean onTouch(View view, MotionEvent motionEvent) {
    //        if (AUTO_HIDE) {
    //            delayedHide(AUTO_HIDE_DELAY_MILLIS);
    //        }
    //        return false;
    //    }
    //};

    private GestureDetectorCompat mDetector;
    private Bitmap currentFrame;
    private Backend backend;

    private final Runnable mainRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = (ImageView) findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        //mContentView.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        toggle();
        //    }
        //});

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        //TODO Check permission on 23+

        updateFileList();

        Thread mainLoop = new MainLoop(this);
        mainLoop.start();

        mDetector = new GestureDetectorCompat(this,
                new MyGestureListener(this, getWindowManager().getDefaultDisplay()));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public void restart() {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void updateView() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ImageView imageView = (ImageView) findViewById(R.id.fullscreen_content);
        ListView listView = (ListView) findViewById(R.id.fileList);

        listView.setEnabled(false);
        listView.setVisibility(View.INVISIBLE);
        if(currentFrame == null) {
            progressBar.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            imageView.setImageBitmap(currentFrame);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    public void updateFileList() {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        ImageView imageView = (ImageView) findViewById(R.id.fullscreen_content);
        imageView.setVisibility(View.INVISIBLE);

        ListView listview = (ListView) findViewById(R.id.fileList);

        ArrayList<String> listElements = new ArrayList<>();
        //listElements.add("DEFAULT_TEST");

        FilenameFilter g11Filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".g11");
            }
        };

        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if(folder.listFiles()!=null) {
            File[] files = folder.listFiles(g11Filter);
            for (File f : files) {
                listElements.add(f.getName().substring(0, f.getName().length()-4));
            }
        }

        listview.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listElements));

        listview.setOnItemClickListener(new FileItemClickListener(this));
    }

    public synchronized void setFrame(Bitmap frame) {
        currentFrame = frame;
    }

    public synchronized Bitmap getFrame() {
        return currentFrame;
    }

    public synchronized void setBackend(Backend backend) {
        this.backend = backend;
    }

    public synchronized Backend getBackend() {
        return backend;
    }

    private class MainLoop extends Thread {
        private MainActivity ma;

        private long previousMillis;

        public MainLoop(MainActivity ma) {
            super();
            this.ma = ma;
        }

        @Override
        public void run() {
            while((backend == null) || (backend.getState() != Backend.BackendState.QUITTING)) {
                previousMillis = System.currentTimeMillis();

                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    Log.e("MainLoop", "Main loop interrupted.");
                }

                if(ma.getBackend()!=null) {
                    Backend be = ma.getBackend();
                    be.update(System.currentTimeMillis()-previousMillis);
                    if(be.getState() == Backend.BackendState.QUITTING) {
                        ma.finish();
                        System.exit(0);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ma.setFrame(ma.getBackend().getFrame());
                            ma.updateView();
                        }
                    });
                }
            }
        }
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private MainActivity ma;
        private Display display;

        private static final float minVel = 0;

        public MyGestureListener(MainActivity ma, Display display) {
            super();
            this.ma = ma;
            this.display = display;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            Backend be = ma.getBackend();
            if(be != null) {
                if(velocityX > minVel) {
                    be.swipedRight();
                } else if(velocityY < (-minVel)) {
                    be.swipedLeft();
                }
            }
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            Backend be = ma.getBackend();
            if(be != null) {
                Point size = new Point();
                display.getSize(size);
                be.pressed((event.getX()/(float)size.x), (event.getY()/(float)size.y));
            }
            return true;
        }
    }

    private class FileItemClickListener implements AdapterView.OnItemClickListener {
        private MainActivity ma;
        private boolean used = false;

        public FileItemClickListener(MainActivity ma) {
            super();
            this.ma = ma;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(!used) {
                used = true;
                final String item = (String) parent.getItemAtPosition(position);
                ma.updateView();

                if(item.equals("DEFAULT_TEST")) {
                    ma.setBackend(new Backend(new Book()));
                } else {
                    String path = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS).toString() + "/" + item + ".g11";
                    File file = new File(path);
                    Book book = BookLoader.loadZipBook(file);
                    //Book book = BookLoader.loadBook(file);
                    ma.setBackend(new Backend(book));
                }
            }
        }
    }
}
