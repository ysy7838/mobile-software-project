package com.example.mobilesoftware_project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesoftware_project.databinding.TripRecyclerviewBinding
import com.example.mobilesoftware_project.databinding.TripRecyclerviewEmptyBinding
import org.json.JSONObject

/*
참고 velog - [Android/Kotlin] Recyclerview Header 달기 + 데이터가 없을 때 보여줄 화면 다르게 하기
로그는 나중에 지울 것
 */

private const val EMPTY = 1         // 데이터가 없을 떄 viewType 상수로 선언
private const val ITEM = 2          // 데이터가 있을 때 viewType 상수로 선언

class PageOneEmptyHolder(val binding: TripRecyclerviewEmptyBinding) :   //리사이클러 뷰에 아이템이 없을 경우
    RecyclerView.ViewHolder(binding.root)
class PageOneViewHolder(val binding: TripRecyclerviewBinding) :         // 리사이클러 뷰에 아이템이 있을 경우
    RecyclerView.ViewHolder(binding.root)

// 시작 페이지 어뎁터
class PageOneAdapter(val fileNameArray: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {                              // 아이템이 몇 개인지 반환하는 함수
        return if (fileNameArray.size == 0) EMPTY else fileNameArray.size     // 0이면 종료되기 때문에 +1해서 반환
    }
    override fun getItemViewType(position: Int): Int {              // 아이템 개수에 따라서 viewType 반환
        return if (fileNameArray.size != 0) ITEM
        else EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)       // 뷰 홀더 생성 함수
            : RecyclerView.ViewHolder {

        return when (viewType) {                        // viewType에 따라서 달라짐
            EMPTY ->                                    // 리사이클러뷰에 아이템이 없다면
            {
                Log.d("emptyCheck", "oncreateviewholder에서 empty = true")
                PageOneEmptyHolder(
                    TripRecyclerviewEmptyBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            ITEM ->                                     // 리사이클러뷰에 아이템이 있다면
            {
                Log.d("emptyCheck", "oncreateviewholder에서 empty = false")
                PageOneViewHolder(
                    TripRecyclerviewBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }

            else -> {                                   // 예외 처리
                throw ClassCastException("Unknown viewType $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {                                     // 뷰 홀더 바인딩
            is PageOneEmptyHolder -> {}                      // 아이템 없으면 연결할 필요 X
            is PageOneViewHolder -> {                       // 아이템 있으면 각각 연결하기
                val binding = holder.binding
                val pref : SharedPreferences = holder.itemView.context.getSharedPreferences(fileNameArray[position], Context.MODE_PRIVATE)

                binding.tripListCountry.text = pref.getString("destination", "null")
                binding.tripStart.text = pref.getString("tripStart", "null")
                binding.tripEnd.text = pref.getString("tripEnd", "null")

                val intent = Intent(holder.itemView.context, PageThreetoSixActivity::class.java)       // 목록이랑 연결
                val hash : MutableMap<String, Boolean> = mutableMapOf()
                hash["movie"] = true
                hash["music"] = true


                var sharedStr = pref.getString("KEY", "")
                var result = ""
                if (sharedStr != "") {
                    var obj = JSONObject(sharedStr)
                }

                //https://kong-droid.com/38
                holder.itemView.setOnClickListener {
                    intent.putExtra("filename", fileNameArray[position])
                    ContextCompat.startActivity(holder.itemView.context, intent, null)             // 참고:https://kumgo1d.tistory.com/44
                }
            }
        }
    }
}
