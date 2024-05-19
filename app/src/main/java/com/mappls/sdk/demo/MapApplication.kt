package com.mappls.sdk.demo

import android.app.Application
import com.mappls.sdk.maps.Mappls
import com.mappls.sdk.services.account.MapplsAccountManager
import com.mappls.sdk.services.api.autosuggest.MapplsAutosuggestManager

class MapApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        MapplsAccountManager.getInstance().mapSDKKey = "305d26c09300c38a0118c2d58400feef"
        MapplsAccountManager.getInstance().restAPIKey = "305d26c09300c38a0118c2d58400feef"
        MapplsAccountManager.getInstance().atlasClientId = "96dHZVzsAuuCYjdQ9m1G3NWGEpp8m8FkaElk0DcB4qf6CisUuJS-r5d4jLYC_sW6k4_gFTns19pHsIVgyPhkXw=="
        MapplsAccountManager.getInstance().atlasClientSecret = "lrFxI-iSEg9AzWQns7gqmrPmCa7zYXAmHmpdgiHHdV60qr0BFitlAABM1GzFawaeSF682YSWs2APf1klyIS_bANfXk0whgVl"
        Mappls.getInstance(this)
    }
}