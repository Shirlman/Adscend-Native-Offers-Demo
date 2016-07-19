package com.example.kb_shirlman.adscenddemo;

import java.util.List;

/**
 * Created by KB-Shirlman on 7/19/2016.
 */
public class OfferAPIResult {

    /**
     * offer_id : 146931
     * name : EngageMe.TV - Movie Reviews (Earn Unlimited Points!)
     * description : Watch awesome movie reviews and earn for every video you watch!
     * requirements : null
     * credit_delay : 0
     * featured_global : 1
     * epc : null
     * conversion_rate : null
     * testing_status : 0
     * testing_time : 0000-00-00 00:00:00
     * creative_id : 217817
     * creative_filename : 133243-205649.jpg
     * creative_url : https://asmclk.com/creat/133243-205649.jpg
     * payout : 0.01
     * payout_custom : 0
     * stats_pending_ce : 1
     * currency_count : 6
     * target_system : 31
     * featured_profile : 0
     * click_url : https://asmclk.com/click.php?aff=104363&camp=146931&from=5723&prod=4&sub1=1234test
     * image_url : //adscendmedia.com/creat/133243-205649.jpg
     * category_id : [19]
     * mobile_app : {"store_id":"com.facebook.katana","platform":1}
     * matches_target_system_detected : true
     */

    private List<OffersBean> offers;

    public List<OffersBean> getOffers() {
        return offers;
    }

    public void setOffers(List<OffersBean> offers) {
        this.offers = offers;
    }

    public class OffersBean {
        private String offer_id;
        private String name;
        private String description;
        private Object requirements;
        private String credit_delay;
        private int featured_global;
        private Object epc;
        private Object conversion_rate;
        private int testing_status;
        private String testing_time;
        private int creative_id;
        private String creative_filename;
        private String creative_url;
        private double payout;
        private int payout_custom;
        private int stats_pending_ce;
        private int currency_count;
        private int target_system;
        private int featured_profile;
        private String click_url;
        private String image_url;
        /**
         * store_id : com.facebook.katana
         * platform : 1
         */

        private MobileAppBean mobile_app;
        private boolean matches_target_system_detected;
        private List<Integer> category_id;

        public String getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(String offer_id) {
            this.offer_id = offer_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getRequirements() {
            return requirements;
        }

        public void setRequirements(Object requirements) {
            this.requirements = requirements;
        }

        public String getCredit_delay() {
            return credit_delay;
        }

        public void setCredit_delay(String credit_delay) {
            this.credit_delay = credit_delay;
        }

        public int getFeatured_global() {
            return featured_global;
        }

        public void setFeatured_global(int featured_global) {
            this.featured_global = featured_global;
        }

        public Object getEpc() {
            return epc;
        }

        public void setEpc(Object epc) {
            this.epc = epc;
        }

        public Object getConversion_rate() {
            return conversion_rate;
        }

        public void setConversion_rate(Object conversion_rate) {
            this.conversion_rate = conversion_rate;
        }

        public int getTesting_status() {
            return testing_status;
        }

        public void setTesting_status(int testing_status) {
            this.testing_status = testing_status;
        }

        public String getTesting_time() {
            return testing_time;
        }

        public void setTesting_time(String testing_time) {
            this.testing_time = testing_time;
        }

        public int getCreative_id() {
            return creative_id;
        }

        public void setCreative_id(int creative_id) {
            this.creative_id = creative_id;
        }

        public String getCreative_filename() {
            return creative_filename;
        }

        public void setCreative_filename(String creative_filename) {
            this.creative_filename = creative_filename;
        }

        public String getCreative_url() {
            return creative_url;
        }

        public void setCreative_url(String creative_url) {
            this.creative_url = creative_url;
        }

        public double getPayout() {
            return payout;
        }

        public void setPayout(double payout) {
            this.payout = payout;
        }

        public int getPayout_custom() {
            return payout_custom;
        }

        public void setPayout_custom(int payout_custom) {
            this.payout_custom = payout_custom;
        }

        public int getStats_pending_ce() {
            return stats_pending_ce;
        }

        public void setStats_pending_ce(int stats_pending_ce) {
            this.stats_pending_ce = stats_pending_ce;
        }

        public int getCurrency_count() {
            return currency_count;
        }

        public void setCurrency_count(int currency_count) {
            this.currency_count = currency_count;
        }

        public int getTarget_system() {
            return target_system;
        }

        public void setTarget_system(int target_system) {
            this.target_system = target_system;
        }

        public int getFeatured_profile() {
            return featured_profile;
        }

        public void setFeatured_profile(int featured_profile) {
            this.featured_profile = featured_profile;
        }

        public String getClick_url() {
            return click_url;
        }

        public void setClick_url(String click_url) {
            this.click_url = click_url;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public MobileAppBean getMobile_app() {
            return mobile_app;
        }

        public void setMobile_app(MobileAppBean mobile_app) {
            this.mobile_app = mobile_app;
        }

        public boolean isMatches_target_system_detected() {
            return matches_target_system_detected;
        }

        public void setMatches_target_system_detected(boolean matches_target_system_detected) {
            this.matches_target_system_detected = matches_target_system_detected;
        }

        public List<Integer> getCategory_id() {
            return category_id;
        }

        public void setCategory_id(List<Integer> category_id) {
            this.category_id = category_id;
        }

        public class MobileAppBean {
            private String store_id;
            private int platform;

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public int getPlatform() {
                return platform;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }
        }
    }
}
