package com.bangkit.aktivio.modules.survey

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bangkit.aktivio.config.SurveyData
import com.bangkit.aktivio.config.SurveyType
import com.bangkit.aktivio.core.data.remote.source.UserRepository
import com.bangkit.aktivio.core.domain.model.SurveyQuestion
import com.bangkit.aktivio.core.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(private val userRepository: UserRepository, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val user : UserModel? = savedStateHandle.get<UserModel>("user")

    private val _data = MutableLiveData<List<SurveyQuestion>>()
    val data: LiveData<List<SurveyQuestion>> = _data
    private val _idx = MutableLiveData<Int>()
    val idx: LiveData<Int> = _idx
    private val _question = MutableLiveData<SurveyQuestion>()
    val question: LiveData<SurveyQuestion> = _question
    private val _surveyType = MutableLiveData<SurveyType>()
    val surveyType: LiveData<SurveyType> = _surveyType
    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> = _progress

    init {
        _data.value = SurveyData.getPostRegisQuestion()
        _surveyType.value = SurveyType.POST_REGIS
        _idx.value = 0
        _progress.value = (_idx.value!! + 1) * 100 / data.value!!.size
        _question.value = data.value?.get(_idx.value!!)
    }

    private fun update() {
        _question.value = data.value!![_idx.value!!]
        _progress.value = (_idx.value!! + 1) * 100 / data.value!!.size
    }

    fun nextQuestion() {
        if (_idx.value!! < data.value!!.size - 1) {
            _idx.value = _idx.value!! + 1
            update()
        }
    }

    fun prevQuestion() {
        if (_idx.value!! > 0) {
            _idx.value = _idx.value!! - 1
            update()
        }
    }
}