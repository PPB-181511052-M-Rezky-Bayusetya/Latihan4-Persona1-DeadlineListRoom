package com.rezkyb.deadlinelist;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Deadline.class}, version = 1)
public abstract class DeadlineDatabase extends RoomDatabase {
    private static final String DB_NAME = "deadlineDB";
    private static volatile DeadlineDatabase instance;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized DeadlineDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),DeadlineDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract DeadlineDao DeadlineDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                DeadlineDao dao = instance.DeadlineDao();
                dao.deleteAllDeadline();
            });
        }
    };

}
