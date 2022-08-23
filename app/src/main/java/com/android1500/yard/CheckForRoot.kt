package com.android1500.yard

import android.content.Context
import android.util.Log
import com.scottyab.rootbeer.RootBeer

class CheckForRoot(context : Context) {
       private val rootBeer = RootBeer(context)
       private external fun getXposedStat(): Boolean
       operator fun invoke(): List<RootItemResult> = getRootResults()


    private fun getRootResults() = listOf(
        RootItemResult("Root Management Apps", rootBeer.detectRootManagementApps()),
        RootItemResult("Potentially Dangerous Apps", rootBeer.detectPotentiallyDangerousApps()),
        RootItemResult("Root Cloaking Apps", rootBeer.detectRootCloakingApps()),
        RootItemResult("TestKeys", rootBeer.detectTestKeys()),
        RootItemResult("BusyBoxBinary", rootBeer.checkForBusyBoxBinary()),
        RootItemResult("SU Binary", rootBeer.checkForSuBinary()),
        RootItemResult("2nd SU Binary check", rootBeer.checkSuExists()),
        RootItemResult("For RW Paths", rootBeer.checkForRWPaths()),
        RootItemResult("Dangerous Props", rootBeer.checkForDangerousProps()),
        RootItemResult("Root via native check", rootBeer.checkForRootNative()),
        RootItemResult("Magisk specific checks", rootBeer.checkForMagiskBinary()),
        RootItemResult("Xposed Detect", getXposedStat())

    )








}