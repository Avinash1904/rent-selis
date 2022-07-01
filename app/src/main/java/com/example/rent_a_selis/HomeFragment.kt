package com.example.rent_a_selis

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var googleMap: GoogleMap
    lateinit var sharedPref: SharedPreferences
    var frag_view = R.layout.fragment_home
    var layoutView = "home_package"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var flag = false
        // Inflate the layout for this fragment
        sharedPref =  context!!.getSharedPreferences("Home Frag", Context.MODE_PRIVATE)
        layoutView = sharedPref.getString("layoutView", layoutView).toString()
        Log.e("layout view ", layoutView)
        if (layoutView == "home_active_pack"){
            frag_view = R.layout.fragment_home_2
        }
        else if (layoutView == "home_map"){
            frag_view = R.layout.fragment_home_map
        }
        var rootView: View = inflater!!.inflate(R.layout.fragment_home, container, false)

        var packList:LinearLayout = rootView.findViewById(R.id.packList)
        var activePack:LinearLayout = rootView.findViewById(R.id.activePack)
        var mapView:LinearLayout = rootView.findViewById(R.id.mapView)
//        if (layoutView == "home_package"){
//            packList.visibility = View.GONE
//            packList.visibility = View.VISIBLE
//        }

        val packageCard1:CardView = rootView.findViewById(R.id.packge1)
            val packageCard2:CardView = rootView.findViewById(R.id.packge2)
            val packageCard3:CardView = rootView.findViewById(R.id.packge3)
            val packageCard4:CardView = rootView.findViewById(R.id.packge4)

        packageCard1.setOnClickListener { view ->
                Log.e("button ", "clicked")
            packList.visibility = View.GONE
            activePack.visibility = View.VISIBLE
            }

        packageCard2.setOnClickListener { view ->
            Log.e("button ", "clicked")
            packList.visibility = View.GONE
            activePack.visibility = View.VISIBLE
        }

        packageCard3.setOnClickListener { view ->
            Log.e("button ", "clicked")
            packList.visibility = View.GONE
            activePack.visibility = View.VISIBLE
        }

        packageCard4.setOnClickListener { view ->
            Log.e("button ", "clicked")
            packList.visibility = View.GONE
            activePack.visibility = View.VISIBLE
        }

        var scan: Button = rootView.findViewById(R.id.scan)

        scan.setOnClickListener { view ->
            activePack.visibility = View.GONE
            mapView.visibility = View.VISIBLE
            flag = true
        }

        if (flag){
            val mapFragment = childFragmentManager.findFragmentById(R.id.liveMap) as SupportMapFragment
            if (mapFragment != null) {
                Log.d("mapFragment ", "Not Null")
            }
            else{
                Log.d("mapFragment ", "Null")
            }
            mapFragment.getMapAsync(OnMapReadyCallback {
                googleMap = it
                Log.d("inside", "async")
                val location1 = LatLng(13.03, 77.60)
                googleMap.addMarker(MarkerOptions().position(location1).title("My Location"))
            })
        }





//        if (layoutView == "home_package"){
//            Log.e("view ", "home pck")
//            val packageCard1:CardView = rootView.findViewById(R.id.packge1)
//            val packageCard2:CardView = rootView.findViewById(R.id.packge2)
//            val packageCard3:CardView = rootView.findViewById(R.id.packge3)
//            val packageCard4:CardView = rootView.findViewById(R.id.packge4)
//            packageCard1.setOnClickListener { view ->
//                Log.e("button ", "clicked")
//                sharedPref.edit().putString("layoutView", "home_active_pack")
//                sharedPref.edit().putString("packName", "Package 2")
//                sharedPref.edit().putString("packPrice", "2000 IDR for 1 hour")
//                sharedPref.edit().commit()
//                (context as MainActivity).changeFragment(ActivePackageFragment.newInstance("a", "b"))
//            }
//        }
//
//
//        if(layoutView == "home_map"){
//            val mapFragment = childFragmentManager.findFragmentById(R.id.liveMap) as SupportMapFragment
//            if (mapFragment != null) {
//                Log.d("mapFragment ", "Not Null")
//            }
//            else{
//                Log.d("mapFragment ", "Null")
//            }
//            mapFragment.getMapAsync(OnMapReadyCallback {
//                googleMap = it
//                Log.d("inside", "async")
//                val location1 = LatLng(13.03, 77.60)
//                googleMap.addMarker(MarkerOptions().position(location1).title("My Location"))
//            })
//        }


        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}