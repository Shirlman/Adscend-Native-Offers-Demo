package com.example.kb_shirlman.adscenddemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;
import com.birbit.android.jobqueue.log.CustomLogger;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private JobManager mJobManager;
    private RecyclerView mRecyclerView;
    private View mGetMobileApps;
    private View mGetAllApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.offer_wall);
        mGetMobileApps = findViewById(R.id.get_mobile_apps);
        mGetAllApps = findViewById(R.id.get_all_apps);

        mGetMobileApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Getting mobile apps", Toast.LENGTH_SHORT).show();
                mRecyclerView.setAdapter(null);
                getJobManager().addJobInBackground(new GetOfferWallJob(false));
            }
        });

        mGetAllApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Getting all apps", Toast.LENGTH_SHORT).show();
                mRecyclerView.setAdapter(null);
                getJobManager().addJobInBackground(new GetOfferWallJob(true));
            }
        });

        EventBus.getDefault().register(this);

        initImageLoader();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(JobEvent.OnOfferWallGot event){
        if(event.offers != null && event.offers.size() > 0) {
            final OfferAdapter offerAdapter = new OfferAdapter(this.getApplicationContext(), event.offers);

            mRecyclerView.setAdapter(offerAdapter);
        } else {
            Toast.makeText(MainActivity.this, "No offers got", Toast.LENGTH_SHORT).show();
        }
    }

    public JobManager getJobManager() {
        if(mJobManager == null) {
            initJobManager();
        }

        return mJobManager;
    }

    private void initJobManager() {
        Configuration configuration = new Configuration.Builder(this)
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";
                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
//                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }

                    @Override
                    public void v(String text, Object... args) {
                        Log.v(TAG, String.format(text, args));
                    }
                })
                .minConsumerCount(5)//always keep at least one consumer alive
                .maxConsumerCount(10)//up to 5 consumers at a time
                .loadFactor(5)//5 jobs per consumer
                //.consumerKeepAlive(120)//wait 2 minute
                .build();

        mJobManager = new JobManager(configuration);
    }

    private void initImageLoader() {
        //init the universal image loader
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(1000, true, false, false))
                .build();

        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .diskCacheExtraOptions(480, 320, null)
                .threadPoolSize(1)
                .build();

        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }
}
