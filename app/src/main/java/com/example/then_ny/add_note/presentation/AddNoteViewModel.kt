package com.example.then_ny.add_note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.then_ny.add_note.domain.usecase.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val saveNoteUseCase: SaveNoteUseCase
) : ViewModel() {

    private val _addNoteState = MutableStateFlow(AddNoteState())
    val addNoteState = _addNoteState.asStateFlow()

    private val _noteSaveChannel = Channel<Boolean>()
    val noteSaveFlow = _noteSaveChannel.receiveAsFlow()

    fun onAction(action: AddNoteEvent) {
        when (action) {
            is AddNoteEvent.UpdateDate -> {
                _addNoteState.update {
                    it.copy(date = action.date)
                }
            }

            is AddNoteEvent.UpdateDescription -> {
                _addNoteState.update {
                    it.copy(description = action.description)
                }
            }

            is AddNoteEvent.UpdateImageUrl -> {
                _addNoteState.update {
                    it.copy(imageUrl = action.imageUrl)
                }
            }

            is AddNoteEvent.UpdateTitle -> {
                _addNoteState.update {
                    it.copy(title = action.title)
                }
            }

            is AddNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    val isSaved = saveNote(
                        title = addNoteState.value.title,
                        description = addNoteState.value.description,
                        imageUrl = addNoteState.value.imageUrl
                    )
                    _noteSaveChannel.send(isSaved)
                }
            }
        }
    }

    private suspend fun saveNote(
        title: String,
        description: String,
        imageUrl: String,
    ): Boolean {
        return saveNoteUseCase(title, description, imageUrl)
    }

}