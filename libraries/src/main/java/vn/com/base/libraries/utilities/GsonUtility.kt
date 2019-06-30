package vn.com.base.libraries.utilities

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtility {
    fun getGsonBuilder(): Gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setPrettyPrinting()
            .create()
}