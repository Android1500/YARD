package com.android1500.yard

import android.content.Context
import com.scottyab.rootbeer.RootBeer

class CheckForRoot(context : Context) {
    private val rootBeer = RootBeer(context)
       operator fun invoke(): List<RootItemResult> = getRootResults()
       private external fun getXposedStat() :Int

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
        RootItemResult("Xposed Detect", isXposed()),
        RootItemResult("Magisk specific checks", rootBeer.checkForMagiskBinary())
    )
    private fun isXposed(): Boolean {
        return when (getXposedStat()) {
            0 -> return false
            1 -> return true
            2 -> return true
            else -> {
                return false
            }
        }
    }


}