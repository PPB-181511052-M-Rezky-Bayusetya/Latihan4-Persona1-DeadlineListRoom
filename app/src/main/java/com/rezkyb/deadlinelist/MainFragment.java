package com.rezkyb.deadlinelist;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.rezkyb.deadlinelist.MainActivity.*;

public class MainFragment extends Fragment {
    public ArrayList<Deadline> deadlines;
    public DeadlineAdapter adapter;
    private Context context;

    DeadlineViewModel mDeadlineViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ArrayList<Deadline> createDeadlineList() {
        context = getActivity();

        String Nama;
        String Details;
        String Date;

        return deadlineArrayList;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView rvDeadline = view.findViewById(R.id.rvDeadline);

        deadlines = createDeadlineList();

        adapter = new DeadlineAdapter(deadlines);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this.getActivity().getBaseContext());
        rvDeadline.setAdapter(adapter);
        rvDeadline.setLayoutManager(layoutmanager);
        rvDeadline.setHasFixedSize(true);

        mDeadlineViewModel = new ViewModelProvider(requireActivity()).get(DeadlineViewModel.class);
        mDeadlineViewModel.getAllDeadline().observe(requireActivity(), new Observer<List<Deadline>>() {
            @Override
            public void onChanged(List<Deadline> deadlines) {
                adapter.setDeadline(deadlines);
            }
        });

        // Inflate the layout for this fragment
        return view;

    }
}
