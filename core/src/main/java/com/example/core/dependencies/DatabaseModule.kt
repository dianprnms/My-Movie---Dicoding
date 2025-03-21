package com.example.core.dependencies

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.core.data.local.room.MovieDAO
import com.example.core.data.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("my_secure_password".toCharArray()) // Ganti dengan password aman
        val factory = SupportFactory(passphrase)
        Log.d("DatabaseModule", "Membuat database terenkripsi...")

        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "databasemovie.db"
        )
            .openHelperFactory(factory)
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigration()
            .build()
            .also { Log.d("DatabaseModule", "Database berhasil dibuat!") }

    }

    @Provides
    @Singleton
    fun provideMovieDao(database: MovieDatabase): MovieDAO {
        return database.movieDAO()
    }
}
