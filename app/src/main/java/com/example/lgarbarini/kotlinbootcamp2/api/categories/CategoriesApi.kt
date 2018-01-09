package com.example.lgarbarini.kotlinbootcamp2.api.categories

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by lgarbarini on 1/9/18.
 */
interface CategoriesApi {

    @GET("/categories/MLA3025")
    fun getChildrenCategories(): Observable<CategoriesResponseDto>
}