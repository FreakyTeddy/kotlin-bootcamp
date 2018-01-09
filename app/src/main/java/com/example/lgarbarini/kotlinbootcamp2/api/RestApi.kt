package com.example.lgarbarini.kotlinbootcamp2.api

import com.example.lgarbarini.kotlinbootcamp2.api.categories.CategoriesApi
import com.example.lgarbarini.kotlinbootcamp2.api.categories.CategoriesResponseDto
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by lgarbarini on 1/9/18.
 */
class RestApi {

    fun loadNotes(): Observable<CategoriesResponseDto> {
        return createCategoriesApi().getChildrenCategories()
    }


    private fun createCategoriesApi(): CategoriesApi {
        val apiBuilder = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://api.mercadolibre.com")
                .build()

        return apiBuilder.create(CategoriesApi::class.java)
    }
}