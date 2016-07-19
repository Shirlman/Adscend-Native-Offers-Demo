package com.example.kb_shirlman.adscenddemo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by KB-Shirlman on 7/19/2016.
 */
public class GetOfferWallJob extends Job {
    private final String TAG = GetOfferWallJob.class.getName();
    private boolean mIsGetAllOffers;

    protected GetOfferWallJob(boolean isGetAllOffers) {
        super(new Params(1000).requireNetwork());

        mIsGetAllOffers = isGetAllOffers;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        List<OfferAPIResult.OffersBean> offers = getNativeOffers();

        EventBus.getDefault().post(new JobEvent.OnOfferWallGot(offers));
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }

    @Override
    protected int getRetryLimit() {
        return 3;
    }

    private List<OfferAPIResult.OffersBean> getNativeOffers() throws Throwable {
        List<OfferAPIResult.OffersBean> offers = new ArrayList<>();

        Map<String, String> searchFilters = new LinkedHashMap<>();
        searchFilters.put("subid1", "new_test");

        if(!mIsGetAllOffers) {
            searchFilters.put("category_id[]", "18");  // Mobile Apps
        }

        Call<OfferAPIResult> call = RetrofitHelper.getAdscendApiRetrofit().adscendMediaOfferAPI(searchFilters);

        Response<OfferAPIResult> response = call.execute();

        if(response.isSuccess()){
            Log.i(TAG, "get adscend native offers success");

            if(response.body() != null && response.body().getOffers().size() > 0) {
                List<OfferAPIResult.OffersBean> offerList = response.body().getOffers();

                Log.i(TAG, "getNativeOffers: get offer wall list success, offer count: " + offerList.size());

                offers = response.body().getOffers();
            }
        } else {
            if(response.errorBody() != null) {
                Log.e(TAG, "get native offers failed, error: " + response.errorBody().string());
            } else {
                Log.e(TAG, "get native offers failed");
            }
        }

        return offers;
    }
}
