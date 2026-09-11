package com.example.tarea

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class notasDatabaseHelper (context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null , DATABASE_VERCIN
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME ($COLUM_ID INTEGER PRIMARY KEY, $COLUM_TITLE TEXT, $COLUM_DESCRIPTION TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }


    companion object{
        private const val DATABASE_NAME = "notas.db"
        private const val DATABASE_VERCIN = 1
        private const val TABLE_NAME = "notas"
        private const val  COLUM_ID = "id"
        private const val  COLUM_TITLE = "titulo"
        private const val  COLUM_DESCRIPTION = "descripion"
    }
}