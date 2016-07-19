package com.example.kb_shirlman.adscenddemo;

import java.util.List;

/**
 * Created by KB-Shirlman on 7/19/2016.
 */
public class JobEvent {
    public static class OnOfferWallGot{
        public List<OfferAPIResult.OffersBean> offers;

        public OnOfferWallGot(List<OfferAPIResult.OffersBean> offers) {
            this.offers = offers;
        }
    }
}
