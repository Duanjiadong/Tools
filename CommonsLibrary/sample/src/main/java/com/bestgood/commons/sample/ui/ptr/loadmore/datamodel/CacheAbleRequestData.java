package com.bestgood.commons.sample.ui.ptr.loadmore.datamodel;

import com.bestgood.commons.sample.ui.ptr.loadmore.event.EventCenter;
import com.bestgood.commons.sample.ui.ptr.loadmore.event.MsgDataEvent;
import com.bestgood.commons.sample.ui.ptr.loadmore.request.API;
import com.bestgood.commons.sample.ui.ptr.loadmore.request.DemoCacheRequest;

import in.srain.cube.request.CacheAbleRequest;
import in.srain.cube.request.CacheAbleRequestDefaultHandler;
import in.srain.cube.request.CacheAbleRequestHandler;
import in.srain.cube.request.FailData;
import in.srain.cube.request.JsonData;

public class CacheAbleRequestData {

    public static void getImage(boolean useCacheAnyway, boolean disableCache) {

        DemoCacheRequest<JsonData> request = new DemoCacheRequest<JsonData>();
        CacheAbleRequestHandler<JsonData> handler = new CacheAbleRequestDefaultHandler<JsonData>() {

            @Override
            public JsonData processOriginData(JsonData jsonData) {
                EventCenter.getInstance().post(new MsgDataEvent("processOriginData"));
                return jsonData;
            }

            @Override
            public void onCacheAbleRequestFinish(JsonData data, CacheAbleRequest.ResultType type, boolean outOfDate) {
                EventCenter.getInstance().post(new MsgDataEvent(
                        "onCacheAbleRequestFinish, result type: %s, out of date: %s", type, outOfDate));
                EventCenter.getInstance().post(new MsgDataEvent(
                        "time: %s", data.optJson("data").optString("time")));
            }

            @Override
            public void onCacheData(JsonData data, boolean outOfDate) {
                super.onCacheData(data, outOfDate);
                EventCenter.getInstance().post(new MsgDataEvent(
                        "onCacheData, out of date: %s", outOfDate));
            }

            @Override
            public void onRequestFail(FailData failData) {
            }
        };

        request.setCacheAbleRequestHandler(handler);
        request.setUseCacheAnyway(useCacheAnyway);
        request.setTimeout(1000);

        String cacheKey = "api/get-server-time";
        request.setCacheTime(30).setCacheKey(cacheKey);
        request.setDisableCache(disableCache);

        request.getRequestData().setRequestUrl(API.API_GET_IMAGE);
        request.send();
    }

}
