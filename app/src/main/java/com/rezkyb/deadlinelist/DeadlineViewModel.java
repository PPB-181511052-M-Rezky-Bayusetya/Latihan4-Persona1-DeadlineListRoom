package com.rezkyb.deadlinelist;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DeadlineViewModel extends AndroidViewModel {
    private DeadlineRepository mRepository;
    private LiveData<List<Deadline>> mAllDeadline;

    public DeadlineViewModel(Application application){
        super(application);
        mRepository = new DeadlineRepository(application);
        mAllDeadline = mRepository.getAllDeadline();
    }

    LiveData<List<Deadline>> getAllDeadline() {
        return mAllDeadline;
    }
    void insert(Deadline deadline){
        mRepository.insert(deadline);
    }
    void delete(Deadline deadline) {mRepository.delete(deadline);}
}
