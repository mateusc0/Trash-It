package br.com.fiap.trashit.service.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fiap.trashit.model.Coleta
import br.com.fiap.trashit.model.Endereco
import br.com.fiap.trashit.service.database.dao.ColetaDao
import br.com.fiap.trashit.service.database.dao.EnderecoDao

@Database(entities = [Endereco::class, Coleta::class], version = 3)
@TypeConverters(Converter::class)
abstract class TrashItDb: RoomDatabase() {

    abstract fun enderecoDao(): EnderecoDao
    abstract fun coletaDao(): ColetaDao

    companion object{
        private lateinit var instance: TrashItDb

        fun getDatabase(context: Context): TrashItDb {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        TrashItDb::class.java,
                        "trashIt_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}