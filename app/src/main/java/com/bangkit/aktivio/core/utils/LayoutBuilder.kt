package com.bangkit.aktivio.core.utils

import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bangkit.aktivio.R
import com.bangkit.aktivio.config.QuestionType
import com.bangkit.aktivio.core.domain.model.SurveyQuestion
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.card.MaterialCardView
import org.w3c.dom.Text

object LayoutBuilder {
    fun build(parent: LinearLayout, data: SurveyQuestion, layoutInflater: LayoutInflater, onInit: (Any?, MaterialCardView, Map<String, TextView>?, Map<String,EditText>?) -> Unit, onSelected: (Any?) -> Unit){
        data.apply{
            when(type){
                QuestionType.DOUBLE_BOX -> {
                    parent.removeAllViews()
                    options?.forEach { item ->
                        val itemView = layoutInflater.inflate(R.layout.item_double_box, parent, false)
                        parent.addView(itemView)
                        val imageView : ImageView = itemView.findViewById(R.id.ivGender)
                        val textView : TextView = itemView.findViewById(R.id.tvGender)
                        imageView.setImageResource(item.icon!!)
                        textView.text = item.title
                        val cardView : MaterialCardView = itemView.findViewById(R.id.cvDoubleBox)
                        onInit(item.value,cardView, null, null)
                        itemView.setOnClickListener {
                            onSelected(item.value)
                        }
                    }
                }
                QuestionType.SINGLE_BOX -> {
                    parent.removeAllViews()
                    val itemView = layoutInflater.inflate(R.layout.item_single_box, parent, false)
                    parent.addView(itemView)
                    val mapView: MapView = itemView.findViewById(R.id.mapView)
                    val tvLocation: TextView = itemView.findViewById(R.id.tvLocation)
                    val btnSave: Button = itemView.findViewById(R.id.btnSave)
                    mapView.onCreate(null)
                    mapView.getMapAsync { googleMap ->
                        val cardView: MaterialCardView = itemView.findViewById(R.id.cvSingleBox)
                        onInit(null,cardView, mapOf(
                            "location" to tvLocation
                        ), null)
                        googleMap.setOnMapClickListener { latLng ->
                            googleMap.clear()
                            googleMap.addMarker(MarkerOptions().position(latLng).title("Selected Location"))
                            tvLocation.text = GeoHelper.getAddressFromLocation(latLng.latitude, latLng.longitude, itemView.context)
                            btnSave.setOnClickListener {
                                onSelected(tvLocation.text)
                            }
                        }
                    }
                    mapView.onResume()
                }
                QuestionType.TRIPLE_BOX -> {
                    parent.removeAllViews()
                    options?.forEach { item ->
                        val itemView = layoutInflater.inflate(R.layout.item_triple_box, parent, false)
                        parent.addView(itemView)
                        val imageView : ImageView = itemView.findViewById(R.id.ivEquipment)
                        val tvTitle : TextView = itemView.findViewById(R.id.tvEquipment)
                        val tvSubtitle : TextView = itemView.findViewById(R.id.tvEquipmentSubtitle)
                        imageView.setImageResource(item.icon!!)
                        tvTitle.text = item.title
                        tvSubtitle.text = item.description
                        val cardView : MaterialCardView = itemView.findViewById(R.id.cvTripleBox)
                        onInit(item.value,cardView, null, null)
                        itemView.setOnClickListener {
                            onSelected(item.value)
                        }
                    }
                }
                QuestionType.MULTI_RADIO -> {
                    parent.removeAllViews()
                }
                QuestionType.INPUT_BOX -> {
                    parent.removeAllViews()
                }
            }
        }
    }
}