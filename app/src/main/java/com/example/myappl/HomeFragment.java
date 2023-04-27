package com.example.myappl;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

        slideModels.add(new SlideModel(R.drawable.img_slide32, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_slide22,ScaleTypes.FIT) );
        slideModels.add(new SlideModel(R.drawable.img_slide12,ScaleTypes.FIT) );

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);



      //  handler.postDelayed(runnable, delay);


        //Category images
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Home Services", R.drawable.homeservice_ic));
        categoryList.add(new Category("Computer & Hardwares", R.drawable.computer_ic));
        categoryList.add(new Category("Personal Care", R.drawable.ic_carpenter));
        categoryList.add(new Category("Automotive Services", R.drawable.ic_cleaning));
        categoryList.add(new Category("Beauty & Wellness", R.drawable.ic_painting));
        categoryList.add(new Category("Education & Tutoring", R.drawable.ic_gardening));

        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        List<PopularService> services = new ArrayList<>();

        services.add(new PopularService("Cleaning","Clean, Refresh, Shine", R.drawable.cleaningfeature,"Our cleaning service provides a deep cleaning of your home, including dusting, vacuuming, mopping, and more. We use eco-friendly products and ensure a clean and fresh living space for you.","Our cleaning service provides a deep cleaning of your home, including dusting, vacuuming, mopping, and more. We use eco-friendly products and ensure a clean and fresh living space for you."));
        services.add(new PopularService("Gardening","Green, Prune, Bloom", R.drawable.gardeningfeature,"We offer professional gardening services for both residential and commercial properties, including lawn maintenance, planting, pruning, and more","Our team of skilled gardeners are passionate about creating beautiful outdoor spaces, and will work closely with you to bring your vision to life."));
        services.add(new PopularService("Salon","Style, Pamper, Relax", R.drawable.salonfeature," We provide a wide range of hair and beauty services, including haircuts, styling, coloring, facials, manicures, and pedicures.","Our team of talented stylists and beauty therapists are dedicated to helping you look and feel your best, using only the highest quality products and equipment."));




        // Add services to the list

        RecyclerView recyclerView = view.findViewById(R.id.popularServicesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        PopularServicesAdapter adapter = new PopularServicesAdapter(services);
        recyclerView.setAdapter(adapter);

        List<LocalService> localServices = new ArrayList<>();
        localServices.add(new LocalService("Plumbing", " Fix leaks, unclog drains, and repair pipes.", " Plumbing services include the installation, repair, and maintenance of pipes, fixtures, and other plumbing systems in residential and commercial buildings. This may include repairing leaks, unclogging drains, and installing new plumbing fixtures like toilets and sinks.", "A qualified plumber will have a license and/or certification in plumbing, as well as experience in the field. They should be able to identify and troubleshoot plumbing problems, and provide reliable and timely service", R.drawable.home_plumbing));
        localServices.add(new LocalService("Electrician", "Fix faulty wiring, replace outlets and switches, and install lighting.", "Electrical services include the installation, repair, and maintenance of electrical systems in residential and commercial buildings. This may include installing new wiring, repairing faulty electrical outlets, and upgrading electrical panels.", "An electrician should be licensed and insured, with expertise in electrical systems and safety standards. They should be able to troubleshoot electrical problems and provide safe and efficient electrical solutions.", R.drawable.home_electrican));
        localServices.add(new LocalService("Cleaning", "Clean homes and offices, including dusting, vacuuming, and mopping", " Cleaning services include regular or deep cleaning of residential or commercial spaces. This may include cleaning bathrooms, kitchens, bedrooms, living rooms, and other common areas, as well as cleaning floors, windows, and surfaces", "A cleaning service provider should be able to provide reliable and consistent cleaning services, using safe and effective cleaning products and techniques. They may have experience in residential or commercial cleaning, or both.", R.drawable.home_cleaning));
        localServices.add(new LocalService("Painting", " Paint interior and exterior walls, trim, and doors", "Painting services include the preparation and painting of interior and exterior surfaces in residential and commercial buildings. This may include priming, sanding, and repairing surfaces before painting, as well as selecting and applying paint colors and finishes.", " A painting service provider should have experience in painting and knowledge of different types of paint and finishes. They should be able to provide accurate estimates and timelines for painting projects, and be able to work efficiently and safely.", R.drawable.home_painting));
        localServices.add(new LocalService("Gardening", "Plant and maintain gardens, mow lawns, and trim trees and shrubs", "Gardening services include the design, installation, and maintenance of outdoor spaces, including gardens, lawns, and landscaping. This may include planting and pruning trees and shrubs, mowing lawns, and installing outdoor features like patios and retaining walls.", "A gardening service provider should have knowledge of local plants and soil types, as well as experience in gardening and landscaping. They should be able to provide guidance and recommendations for creating and maintaining a beautiful and functional outdoor space.", R.drawable.home_gardening));
        localServices.add(new LocalService("Carpentry", "Build and install furniture, repair cabinets and shelves, and frame walls", "Carpentry services include the design, installation, and repair of wooden structures in residential and commercial buildings. This may include installing cabinets, building decks or patios, and repairing or replacing doors and windows.", "A carpentry service provider should have experience in working with wood and building structures, as well as knowledge of building codes and safety standards. They should be able to provide accurate estimates and timelines for carpentry projects, and be able to work efficiently and safely.", R.drawable.home_carpentry));
        localServices.add(new LocalService("HVAC Repair", "Repair and install heating, ventilation, and air conditioning systems", " HVAC (heating, ventilation, and air conditioning) repair services include the installation, repair, and maintenance of HVAC systems in residential and commercial buildings. This may include repairing or replacing HVAC units, fixing ductwork, and installing new thermostats.", " An HVAC repair service provider should have knowledge of HVAC systems and their components, as well as experience in troubleshooting and repairing HVAC issues. They should be able to provide reliable and timely service, and ensure that HVAC systems are working safely and efficiently.", R.drawable.home_hvac));
        localServices.add(new LocalService("Pest Control", "Remove pests such as insects and rodents from homes and businesses", " Pest control services aim to protect homes and businesses from infestations of insects, rodents, and other pests. These services may include inspecting properties for signs of infestation, treating infestations using appropriate methods, and implementing preventative measures to discourage future infestations. Common pests targeted by pest control services include ants, cockroaches, bed bugs, termites, rodents, and mosquitoes.", "Pest control services may be provided by specialized pest control companies or by general home services companies that offer pest control as one of their services. Service providers should be licensed and certified in accordance with local regulations, and should use safe and effective methods for pest control. It is also important for service providers to communicate clearly with customers about the nature of the infestation, the methods used for treatment, and any precautions that need to be taken by customers.", R.drawable.home_pest));
        localServices.add(new LocalService("Flooring", "Install and repair various types of flooring, such as hardwood, tile, and carpet", "Flooring services involve the installation, repair, and maintenance of various types of flooring, including hardwood, laminate, vinyl, tile, and carpet. Services may include removing old flooring, preparing the subfloor, installing new flooring, and finishing or refinishing the surface. Flooring services may also include the installation of underlayment, moisture barriers, and soundproofing materials.", "Flooring services may be provided by specialized flooring companies or by general home services companies that offer flooring as one of their services. Service providers should have the necessary expertise and experience to handle different types of flooring, and should use high-quality materials and equipment for installation and repair. It is also important for service providers to communicate clearly with customers about the type of flooring that will best suit their needs and budget, and to provide accurate estimates of costs and timelines for the work to be done.", R.drawable.home_flooring));
        localServices.add(new LocalService("Roofing", "Repair or replace damaged roofs, gutters, and downspouts", "Roofing services involve the installation, repair, and maintenance of roofs on residential and commercial properties. Services may include installing new roofs, repairing damaged roofs, and providing regular maintenance to prevent damage and extend the life of the roof. Roofing services may also include inspecting roofs for damage or wear and tear, and providing advice to customers on the best course of action.", "Roofing services may be provided by specialized roofing companies or by general home services companies that offer roofing as one of their services. Service providers should have the necessary expertise and experience to handle different types of roofs, including shingle, tile, and metal roofs. They should also have the appropriate licensing and insurance in accordance with local regulations, and should use high-quality materials and equipment for installation and repair. It is important for service providers to communicate clearly with customers about the condition of their roof, the necessary repairs or maintenance, and the costs and timelines involved.", R.drawable.home_roofing));
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