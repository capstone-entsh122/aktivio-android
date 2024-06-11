package com.bangkit.aktivio.modules.survey

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.aktivio.config.QuestionType
import com.bangkit.aktivio.config.SurveyData
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.data.remote.model.SurveyItem
import com.bangkit.aktivio.core.data.remote.model.UserItem
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import com.bangkit.aktivio.core.domain.model.SurveyQuestion
import com.bangkit.aktivio.core.utils.mapTo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
        _data.value = SurveyData.getSurveyData()
        _idx.value = 0
        _progress.value = (_idx.value!! + 1) * 100 / data.value!!.size
        _question.value = data.value?.get(_idx.value!!)
        _user.value = mapOf()
    }

    private fun update() {
        _question.value = data.value!![_idx.value!!]
        _progress.value = (_idx.value!! + 1) * 100 / data.value!!.size
        _lastQuestion.value = _idx.value == data.value!!.size - 1
    }

    fun addData(key: String, value: Any?) {
        val map = _user.value?.toMutableMap() ?: mutableMapOf()
        if(_question.value!!.type == QuestionType.MULTI_CHECKBOX) {
            val list = map[key] as? MutableList<String> ?: mutableListOf()
            list.add(value as String)
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

    fun updateUserPref(surveyItem: SurveyItem) : LiveData<Resource<Map<String, Any>>> {
        return runBlocking {
            userRepository.updateUserPreferences(surveyItem).asLiveData()
        }
    }

    fun getData() {
        Log.d("SurveyViewModel", _user.value.toString())
    }

    fun nextQuestion(onProfileUpdate: (UserItem) -> Unit, onSurveySubmit: (SurveyItem) -> Unit) {
        if(_idx.value == 0) {
            onProfileUpdate(_user.value!!.mapTo())
            _user.value = mapOf()
        }
        if (_idx.value!! < data.value!!.size - 1) {
            _idx.value = _idx.value!! + 1
            update()
        } else {
            try {
                val surveyItem: SurveyItem = _user.value!!.mapTo()
                onSurveySubmit(surveyItem)
            } catch (e: Exception) {
                Log.e("SurveyViewModel", "Error creating sport plan", e)
            }
        }
    }

    fun prevQuestion() {
        if (_idx.value!! > 0) {
            _idx.value = _idx.value!! - 1
            update()
        } else {
            Toast.makeText(null, "This is the first question", Toast.LENGTH_SHORT).show()
        }
    }
}