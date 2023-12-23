package com.example.mobilesoftware_project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mobilesoftware_project.databinding.FragmentScheduleBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/*
    선택한 장소들을 스케줄로 보여주는 Fragment
 */

//GooglePlacesService 인터페이스 - Google Places API를 통해 장소를 검색
interface GooglePlacesService {
    @GET("place/findplacefromtext/json")
    suspend fun findPlaceFromText(
        @Query("input") input: String,
        @Query("inputtype") inputType: String = "textquery",
        @Query("fields") fields: String = "place_id,formatted_address,name,geometry",
        @Query("key") apiKey: String
    ): Response<FragmentSchedule.PlacesResponse>
}
class FragmentSchedule : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private var locations = mutableListOf<LatLng>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        val filename = arguments?.getString("filename")
        val pref = requireContext().getSharedPreferences("$filename", AppCompatActivity.MODE_PRIVATE)
        val route = pref.getString("route", "").toString()


        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //장소검색 (retrofit객체생성)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/maps/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GooglePlacesService::class.java)

        // 검색버튼을 눌렀을 때 검색어를 가져와서 API를 호출
        val searchButton = view.findViewById<Button>(R.id.search_button)
        searchButton.setOnClickListener {
            val searchText = view.findViewById<EditText>(R.id.search_edit_text).text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val response = service.findPlaceFromText(searchText, apiKey = "AIzaSyAg26zMzdpMr3jitBBxFXYfVp9_I7xB92o")
                if (response.isSuccessful && response.body() != null) {
                    val places = response.body()!!.candidates
                    for (place in places) {
                        val location = LatLng(place.geometry.location.lat, place.geometry.location.lng)
                        locations.add(location)
                    }
                    updateMarkers()
                }
            }
        }

        return view
    }

    private fun updateMarkers() {
        activity?.runOnUiThread {
            mMap.clear()
            val polylineOptions = PolylineOptions().width(5f).color(ContextCompat.getColor(requireContext(), R.color.mainBlue))
            for ((index, location) in locations.withIndex()) {
                mMap.addMarker(MarkerOptions().position(location).title("Marker ${index + 1}"))
                polylineOptions.add(location)
            }
            mMap.addPolyline(polylineOptions)
            if (locations.isNotEmpty()) {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(locations[0]))
            }
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        updateMarkers()

        mMap.setOnMarkerClickListener { marker ->
            val builder = AlertDialog.Builder(requireContext())
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.schedule_dialog, null)
            val editText  = dialogLayout.findViewById<EditText>(R.id.editText)

            // SharedPreferences에서 마커의 타이틀에 해당하는 일정을 가져옵니다.
            val schedulePref = requireContext().getSharedPreferences("Schedule", AppCompatActivity.MODE_PRIVATE)
            val schedule = schedulePref.getString(marker.title, "")
            editText.setText(schedule)

            with(builder) {
                setTitle("일정")
                setPositiveButton("확인") { dialog, which ->
                    val newSchedule = editText.text.toString()
                    // 마커의 타이틀을 키로 사용하여 새로운 일정을 SharedPreferences에 저장
                    val editor = schedulePref.edit()
                    editor.putString(marker.title, newSchedule)
                    editor.apply()

                    Toast.makeText(requireContext(), "저장된 메모: $newSchedule", Toast.LENGTH_LONG).show()
                }
                setNegativeButton("취소") { dialog, which ->
                    // 취소 버튼
                }
                setView(dialogLayout)
                show()
            }
            true
        }
    }

//PlacesResponse: Google Places API에서 장소 검색 요청의 응답을 나타내는 클래스 (JSON->Kotlin 객체)
data class PlacesResponse(
    val status: String,
    val candidates: List<Place>
)

data class Place(
    val place_id: String,
    val formatted_address: String,
    val name: String,
    val geometry: Geometry
)

data class Geometry(
    val location: Location
)

data class Location(
    val lat: Double,
    val lng: Double
)
}