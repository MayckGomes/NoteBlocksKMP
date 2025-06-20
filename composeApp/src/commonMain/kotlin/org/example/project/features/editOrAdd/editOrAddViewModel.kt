package org.example.project.features.editOrAdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class editOrAddViewModel: ViewModel() {

    private val _titleScreen = MutableStateFlow("")
    val titleScreen = _titleScreen.asStateFlow()

    fun changeTitleScreen(title: String){
        _titleScreen.value = title
    }


    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    fun changeTitle(title: String){
        _title.value = title
    }


    private val _text = MutableStateFlow("")
    val text = _text.asStateFlow()

    fun changeText(text: String){
        _text.value = text
    }


    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun setIsLoadingTrue(){
        _isLoading.value = true
    }

    fun setIsLoadingFalse(){
        _isLoading.value = false
    }

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    fun setShowDialogTrue(){
        _showDialog.value = true
    }

    fun setShowDialogFalse(){
        _showDialog.value = false
    }
}