package com.example.kb_shirlman.adscenddemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by KB-Shirlman on 7/19/2016.
 */
public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder>{
    private LayoutInflater mInflater;
    private List<OfferAPIResult.OffersBean> mOffers;

    public OfferAdapter(Context context, List<OfferAPIResult.OffersBean> offers) {
        this.mInflater = LayoutInflater.from(context);
        this.mOffers = offers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mInflater.inflate(R.layout.offer_wall, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mOffers == null || mOffers.size() == 0) {
            return;
        }

        OfferAPIResult.OffersBean offer = mOffers.get(position);
        final String clickUrl = offer.getClick_url();

        if(offer.getImage_url() != null && !offer.getImage_url().isEmpty()) {
            ImageLoader.getInstance().displayImage(offer.getCreative_url(), holder.offerIcon);
        }

        holder.offerTitle.setText(offer.getName());

        holder.offerDescription.setText(offer.getDescription());
        holder.offerCredits.setText(offer.getCurrency_count() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(clickUrl));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);

                EventBus.getDefault().post(new JobEvent.OnOfferClicked(clickUrl));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOffers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView offerIcon;
        public TextView offerTitle;
        public TextView offerDescription;
        public TextView offerCredits;

        public ViewHolder(View itemView) {
            super(itemView);

            offerIcon = (ImageView)itemView.findViewById(R.id.mo_offer_icon);
            offerTitle = (TextView)itemView.findViewById(R.id.owi_title);
            offerDescription = (TextView)itemView.findViewById(R.id.owi_description);
            offerCredits = (TextView)itemView.findViewById(R.id.owi_offer_credits);
        }
    }
}
