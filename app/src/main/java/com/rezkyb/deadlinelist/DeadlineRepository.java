package com.rezkyb.deadlinelist;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class DeadlineRepository {
    private DeadlineDao mDeadlineDao;
    private LiveData<List<Deadline>> mAllDeadline;

    DeadlineRepository(Application application){
        DeadlineDatabase db = DeadlineDatabase.getInstance(application);
        mDeadlineDao = db.DeadlineDao();
        mAllDeadline = mDeadlineDao.getDeadlineList();
    }

    LiveData<List<Deadline>> getAllDeadline() {
        return mAllDeadline;
    }

    void insert(final Deadline deadlines) {
        DeadlineDatabase.databaseWriteExecutor.execute(() -> {
            mDeadlineDao.insertDeadline(deadlines);
        });
    }

    public void delete(Deadline deadline)  {
        new deleteWordAsyncTask(mDeadlineDao).execute(deadline);
    }

    private DeadlineDao mAsyncTaskDao;


    private static class deleteWordAsyncTask extends AsyncTask<Deadline, Void, Void> {
        private DeadlineDao mAsyncTaskDao;

        deleteWordAsyncTask(DeadlineDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Deadline... params) {
            mAsyncTaskDao.deleteDeadline(params[0]);
            return null;
        }
    }

}
