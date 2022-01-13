package com.ewadus.mvvmnews.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ewadus.mvvmnews.data.model.news.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article): Long

    @Query("Select * From articles")
    fun getAllArticle(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}
