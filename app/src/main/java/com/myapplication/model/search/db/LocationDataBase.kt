package com.myapplication.model.search.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.myapplication.model.search.LocationItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [LocationItem::class], version = 1, exportSchema = false)
abstract class LocationDataBase : RoomDatabase() {

    abstract fun getLocationDao(): LocationDao

    companion object {

        @Volatile
        private var instance: LocationDataBase? = null

        fun getInstance(context: Context): LocationDataBase =
            instance ?: synchronized(this) {
                instance ?: createdDatabase(context).also { instance = it }
            }

        private fun createdDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LocationDataBase::class.java,
                "location_db.db")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch(Dispatchers.Main) {
                            getInstance(context).getLocationDao().updateInsert(PREPOPULATE_LISBON)
                            getInstance(context).getLocationDao().updateInsert(PREPOPULATE_LONDON)
                            getInstance(context).getLocationDao().updateInsert(PREPOPULATE_KINSHASA)
                        }
                    }
                })
                .build()

        val PREPOPULATE_LISBON = LocationItem(742676, "Lisbon", false)
        val PREPOPULATE_LONDON = LocationItem( 44418,"London", false)
        val PREPOPULATE_KINSHASA = LocationItem(1290062, "Kinshasa", true)
    }
}