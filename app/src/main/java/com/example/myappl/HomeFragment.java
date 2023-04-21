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
import com.example.myappl.domain.LocalService;
import com.example.myappl.domain.LocalServicesAdapter;
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

        services.add(new PopularService("Cleaning","Provider", "500₹","3 hours", R.drawable.cleaningfeature));
        services.add(new PopularService("Gardening","Provider", "250₹","2 hours", R.drawable.gardeningfeature));
        services.add(new PopularService("Salon","Provider", "200₹","1 hours", R.drawable.salonfeature));

// Add services to the list

        RecyclerView recyclerView = view.findViewById(R.id.popularServicesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        PopularServicesAdapter adapter = new PopularServicesAdapter(services);
        recyclerView.setAdapter(adapter);

        List<LocalService> localServices = new ArrayList<>();
        localServices.add(new LocalService("Plumbing", " Fix leaks, unclog drains, and repair pipes.", "$50", "1 hour", R.drawable.ic_plumbing));
        localServices.add(new LocalService("Electrician", "Fix faulty wiring, replace outlets and switches, and install lighting.", "$70", "2 hour", R.drawable.ic_electrician));
        localServices.add(new LocalService("Cleaning", "Clean homes and offices, including dusting, vacuuming, and mopping", "$30", "1.5 hour", R.drawable.ic_electrician));
        localServices.add(new LocalService("Painting", " Paint interior and exterior walls, trim, and doors", "$100", "4 hour", R.drawable.ic_electrician));
        localServices.add(new LocalService("Gardening", "Plant and maintain gardens, mow lawns, and trim trees and shrubs", "$60", "3 hour", R.drawable.ic_electrician));
        localServices.add(new LocalService("Carpentry", "Build and install furniture, repair cabinets and shelves, and frame walls", "$80", "2.5 hour", R.drawable.ic_electrician));
        localServices.add(new LocalService("HVAC Repair", "Repair and install heating, ventilation, and air conditioning systems", "$90", "2 hour", R.drawable.ic_electrician));
        localServices.add(new LocalService("Pest Control", "Remove pests such as insects and rodents from homes and businesses", "$40", "1 hour", R.drawable.ic_electrician));
        localServices.add(new LocalService("Flooring", "Install and repair various types of flooring, such as hardwood, tile, and carpet", "$120", "5 hour", R.drawable.ic_electrician));
        localServices.add(new LocalService("Roofing", "Repair or replace damaged roofs, gutters, and downspouts", "$150", "6 hour", R.drawable.ic_electrician));
        // add more services here...
        // Local services
        RecyclerView localServicesList = view.findViewById(R.id.local_services_list);
        localServicesList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        LocalServicesAdapter adapter1 = new LocalServicesAdapter(localServices);
        localServicesList.setAdapter(adapter1);







        return view;

        //return inflater.inflate(R.layout.fragment_home, container, false);

    }
}