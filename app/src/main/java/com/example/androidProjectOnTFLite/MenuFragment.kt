package com.example.androidProjectOnTFLite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_menu.*

/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toCamera.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_capturePhotoFragment))
        toList.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_menuFragment_to_listOfClassifiedImagesFragment))
    }

    //val navController =

}
