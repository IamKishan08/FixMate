package com.example.myappl;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myappl.domain.Category;
import com.example.myappl.domain.CategoryAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private DatabaseReference databaseReference;
    private SearchView searchView;
    private RecyclerView categoryRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //image slider
        ImageSlider imageSlider = view.findViewById(R.id.image_slider);


        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.img1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img2,ScaleTypes.FIT) );

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        //Category images
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Plumbing", R.drawable.ic_plumbing));
        categoryList.add(new Category("Electrician", R.drawable.ic_electrician));
        categoryList.add(new Category("Carpenter", R.drawable.ic_carpenter));
        categoryList.add(new Category("Cleaning", R.drawable.ic_cleaning));
        categoryList.add(new Category("Painting", R.drawable.ic_painting));
        categoryList.add(new Category("Gardening", R.drawable.ic_gardening));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        List<PopularService> services = new ArrayList<>();

        services.add(new PopularService("Namw","Provider", "80","1 hours", R.drawable.about_us_icon));
// Add services to the list

        RecyclerView recyclerView = view.findViewById(R.id.popularServicesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        PopularServicesAdapter adapter = new PopularServicesAdapter(services);
        recyclerView.setAdapter(adapter);




        return view;

        //return inflater.inflate(R.layout.fragment_home, container, false);

    }
}