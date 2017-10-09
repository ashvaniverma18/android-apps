package com.senganipatel.gracia;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutUsFragment extends Fragment {
    String content = "Gracia  is a leading supplier of fine wood veneer offering both domestic and exotic international wood veneers  from around the world.  Gracia Veneers was founded on basic principles, established over many years of observing and interacting with customers and vendors in the wood veneer industry. We also understand the need for competitively priced wood veneer products and strive to achieve the best possible balance of competitive pricing and the highest standard of quality and customer service.\n\nGenuine woods veneers have a warmth and beauty that only nature can create, Gracia Veneers  strategically selects their wood veneers to be able to fulfill the greatest majority of needs of a broad customer base. Wood furnishings and interiors are a hallmark of prestige and  Gracia  delivers both quality and Value, to everyone, from the home hobbyist or to skilled craftsman & architects,  from the wide variety of quality wood veneers. \n\nEach tree is a \"Finger Print of Nature.\"  Our website was created with these factors in mind as a showcase for all of our wood veneer products and to provide a place where customers can compare wood veneer prices, species, quality and availability. It is also a valuable resource as a stunning visual reference for wood veneer identification and characteristics.\n";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.aboutus_fragment, container, false);
        ((TextView) rootView.findViewById(R.id.about_us_header)).setText("Gracia Veneers - A Finger Print Of Nature\n");
        ((TextView) rootView.findViewById(R.id.about_us_content)).setText(this.content);
        return rootView;
    }
}
