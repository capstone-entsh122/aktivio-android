package com.bangkit.aktivio.core.utils

import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bangkit.aktivio.R
import com.bangkit.aktivio.config.QuestionType
import com.bangkit.aktivio.core.domain.model.SurveyQuestion
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.radiobutton.MaterialRadioButton

object LayoutBuilder {
    fun build(parent: LinearLayout, data: SurveyQuestion, layoutInflater: LayoutInflater, onInit: (String, Any?, MaterialCardView, Map<String, TextView>?, CompoundButton?) -> Unit, onSelected: (String, Any?) -> Unit){
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
                        onInit(data.field,item.value,cardView, null, null)
                        itemView.setOnClickListener {
                            onSelected(data.field, item.value)
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
                    val currentLatLong = GeoHelper.getCurrentLatLong(itemView.context)
                    mapView.onCreate(null)
                    mapView.getMapAsync { googleMap ->
                        googleMap.uiSettings.isZoomControlsEnabled = true
                        googleMap.uiSettings.isIndoorLevelPickerEnabled = true
                        googleMap.uiSettings.isCompassEnabled = true
                        googleMap.uiSettings.isMapToolbarEnabled = true
                        googleMap.uiSettings.isMyLocationButtonEnabled = true
                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLong))
                        var currentMarker: Marker? = null
                        val cardView: MaterialCardView = itemView.findViewById(R.id.cvSingleBox)
                        onInit(data.field,null,cardView, mapOf(
                            "location" to tvLocation
                        ), null)
                        googleMap.setOnMapClickListener { latLng ->
                            googleMap.clear()
                            currentMarker?.remove()
                            currentMarker = googleMap.addMarker(MarkerOptions().position(latLng).title("Selected Location"))
                            tvLocation.text = GeoHelper.getAddressFromLocation(latLng.latitude, latLng.longitude, itemView.context)
                            btnSave.setOnClickListener {
                                onSelected(data.field, mapOf(
                                    "location" to tvLocation.text.toString(),
                                    "lat" to latLng.latitude,
                                    "lng" to latLng.longitude
                                ))
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
                        onInit(data.field,item.value,cardView, null, null)
                        itemView.setOnClickListener {
                            onSelected(data.field, item.value)
                        }
                    }
                }
                QuestionType.MULTI_CHECKBOX -> {
                    parent.removeAllViews()
                    options?.forEach { item ->
                        val itemView = layoutInflater.inflate(R.layout.item_multi_checkbox, parent, false)
                        parent.addView(itemView)
                        val checkBox : MaterialCheckBox = itemView.findViewById(R.id.cbSurvey)
                        val cardView : MaterialCardView = itemView.findViewById(R.id.cvCheckBox)
                        checkBox.text = item.title
                        onInit(data.field,item.value,cardView, null, checkBox)
                        checkBox.setOnClickListener {
                            onSelected(data.field, item.value)
                        }
                    }
                }
                QuestionType.MULTI_RADIO -> {
                    parent.removeAllViews()
                    options?.forEach { item ->
                        val itemView = layoutInflater.inflate(R.layout.item_multi_radio, parent, false)
                        parent.addView(itemView)
                        val radioButton : MaterialRadioButton = itemView.findViewById(R.id.rbSurvey)
                        val cardView : MaterialCardView = itemView.findViewById(R.id.cvRadioBox)
                        radioButton.text = item.title
                        onInit(data.field,item.value,cardView, null, radioButton)
                        radioButton.setOnClickListener {
                            onSelected(data.field, item.value)
                        }
                    }
                }
                QuestionType.INPUT_BOX -> {
                    parent.removeAllViews()
                    options?.forEach { item ->
                        val itemView = layoutInflater.inflate(R.layout.item_input_box, parent, false)
                        parent.addView(itemView)
                        val editText : EditText = itemView.findViewById(R.id.et_input)
                        val imageView : ImageView = itemView.findViewById(R.id.ivInput)
                        val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
                        val cardView : MaterialCardView = itemView.findViewById(R.id.cvInputBox)
                        editText.hint = item.hint
                        imageView.setImageResource(item.icon!!)
                        tvTitle.text = item.title
                        onInit(item.field!!,item.value,cardView, mapOf(
                            item.field to editText
                        ), null)
                        editText.setOnEditorActionListener { v, _, _ ->
                            onSelected(item.field, v.text.toString())
                            // hide keyboard
                            false
                        }
                }
            }
        }
    }
}}