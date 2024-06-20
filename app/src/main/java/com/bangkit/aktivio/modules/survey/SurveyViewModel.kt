package com.bangkit.aktivio.modules.survey

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.aktivio.core.types.QuestionType
import com.bangkit.aktivio.config.SurveyData
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.LocationItem
import com.bangkit.aktivio.core.data.remote.model.RecommendationItem
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import com.bangkit.aktivio.core.domain.model.SurveyQuestion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _data = MutableLiveData<List<SurveyQuestion>>()
    val data: LiveData<List<SurveyQuestion>> = _data
    private val _idx = MutableLiveData<Int>()
    val idx: LiveData<Int> = _idx
    private val _question = MutableLiveData<SurveyQuestion>()
    val question: LiveData<SurveyQuestion> = _question
    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> = _progress
    private val _user = MutableLiveData<Map<String, Any?>>()
    val user: LiveData<Map<String, Any?>> = _user
    private val _lastQuestion = MutableLiveData<Boolean>()
    val lastQuestion: LiveData<Boolean> = _lastQuestion

    init {
        _data.value = SurveyData.getSurveyData("female")
        _idx.value = 0
        _progress.value = (_idx.value!! + 1) * 100 / data.value!!.size
        _question.value = data.value?.get(_idx.value!!)
        _user.value = mapOf()
    }

    private fun update() {
        if(_idx.value == 4) {
            _data.value = SurveyData.getSurveyData(_user.value!!["gender"].toString())
        }
        _question.value = data.value!![_idx.value!!]
        _progress.value = (_idx.value!! + 1) * 100 / data.value!!.size
        _lastQuestion.value = _idx.value == data.value!!.size - 1
    }

    fun addData(key: String, value: Any?) {
        val map = _user.value?.toMutableMap() ?: mutableMapOf()
        if(_question.value!!.type == QuestionType.MULTI_CHECKBOX) {
            val list = map[key] as? MutableList<String> ?: mutableListOf()
            if(value != null) {
                if (list.contains(value as String)) {
                    list.remove(value)
                } else {
                    list.add(value)
                }
            }
            map[key] = list
        } else {
            map[key] = value
        }
        _user.value = map
    }

    fun updateProfile(userItem: UserItem) : LiveData<Resource<String>> {
        return runBlocking {
            userRepository.updateProfile(userItem).asLiveData()
        }
    }

    fun setUserPref(surveyItem: SurveyItem) : LiveData<Resource<RecommendationItem>> {
        return runBlocking {
            userRepository.setUserPreferences(surveyItem).asLiveData()
        }
    }

    fun getData() {
        Log.d("SurveyViewModel", _user.value.toString())
    }

    fun nextQuestion(onProfileUpdate: (UserItem) -> Unit, onSurveySubmit: (SurveyItem) -> Unit) {
        if(_idx.value == 0) {
            val loc = _user.value?.get("location") as Map<*,*>
            val userItem = UserItem(
                location = LocationItem(
                    loc["lat"].toString().toDouble(),
                    loc["lng"].toString().toDouble()
                )
            )
            onProfileUpdate(userItem)
            _user.value = mapOf()
        }
        if (_idx.value!! < data.value!!.size - 1) {
            _idx.value = _idx.value!! + 1
            update()
        } else {
            try {
                val data = _user.value!!
                onSurveySubmit(SurveyItem(
                    gender = data["gender"].toString(),
                    age = data["age"].toString().toInt(),
                    equipment = data["equipment"].toString(),
                    motivation = data["motivation"] as List<String>,
                    availableTime = data["availableTime"].toString(),
                    fitnessLevel = data["fitnessLevel"].toString(),
                    placePreference = data["placePreference"].toString(),
                    socialPreference = data["socialPreference"].toString(),
                    diseaseHistory = data["diseaseHistory"] as List<String>,
                    weight = data["weight"].toString().toInt(),
                    height = data["height"].toString().toInt()
                ))
            } catch (e: Exception) {
                Log.e("SurveyViewModel", "Error creating sport plan", e)
            }
        }
    }

    fun prevQuestion(onForcePrev: () -> Unit) {
        if (_idx.value!! > 0) {
            _idx.value = _idx.value!! - 1
            update()
        } else {
            onForcePrev()
        }
    }
}