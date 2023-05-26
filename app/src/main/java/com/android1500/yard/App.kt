package com.android1500.yard

import android.app.Application
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.util.Log
import com.google.android.material.color.DynamicColors
import java.security.MessageDigest

class App : Application() {

    init {
        // Load native library
        System.loadLibrary("app")
    }

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        Log.d("sigMD5",getSingInfo(packageName).toString().toUpperCase())
    }



         fun getSingInfo(pkg: String?): String? {
         try {
             val packageInfo = packageManager.getPackageInfo(pkg!!, PackageManager.GET_SIGNATURES)
             val signs: Array<Signature> = packageInfo.signatures
             val sign: Signature = signs[0]
             val s = getMd5(sign)
             return "md5:$s"
         } catch (e: Exception) {
             e.printStackTrace()
         }
         return ""
     }


     private fun getMd5(signature: Signature): String {
         return encryptionMD5(signature.toByteArray())
     }

     fun encryptionMD5(byteStr: ByteArray?): String {
         var messageDigest: MessageDigest? = null
         val md5StrBuff = StringBuffer()
         try {
             messageDigest = MessageDigest.getInstance("MD5")
             messageDigest.reset()
             messageDigest.update(byteStr)
             val byteArray = messageDigest.digest()
             for (i in byteArray.indices) {
                 if (Integer.toHexString(0xFF and byteArray[i].toInt()).length == 1) {
                     md5StrBuff.append("0")
                         .append(Integer.toHexString(0xFF and byteArray[i].toInt()))
                 } else {
                     md5StrBuff.append(Integer.toHexString(0xFF and byteArray[i].toInt()))
                 }
             }
         } catch (e: NoSuchAlgorithmException) {
             e.printStackTrace()
         }
         return md5StrBuff.toString()
     }


}