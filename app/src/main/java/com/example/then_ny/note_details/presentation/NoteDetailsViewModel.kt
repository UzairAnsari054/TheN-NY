package com.example.then_ny.note_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.then_ny.note_details.domain.usecase.GetNoteByIdUseCase
import com.example.then_ny.note_details.domain.usecase.SaveUpdatedNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailsViewModel @Inject constructor(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveUpdatedNoteUseCase: SaveUpdatedNoteUseCase
) : ViewModel() {

    private val _noteDetailsState = MutableStateFlow(NoteDetailsState())
    val noteDetailsState = _noteDetailsState.asStateFlow()

    private val _noteSavedChannel = Channel<Boolean>()
    val noteSavedFlow = _noteSavedChannel.receiveAsFlow()

    fun onAction(action: NoteDetailsEvent) {
        when (action) {
            NoteDetailsEvent.SaveNote -> {
                viewModelScope.launch {
                    val isSaved = saveUpdatedNote(
                        id = noteDetailsState.value.id,
                        title = noteDetailsState.value.title,
                        description = noteDetailsState.value.description,
                        imageUrl = noteDetailsState.value.imageUrl,
                    )
                    _noteSavedChannel.send(isSaved)
                }
            }

            is NoteDetailsEvent.UpdateDate -> {
                _noteDetailsState.update {
                    it.copy(date = action.date)
                }
            }

            is NoteDetailsEvent.UpdateDescription -> {
                _noteDetailsState.update {
                    it.copy(description = action.description)
                }
            }

            is NoteDetailsEvent.UpdateImageUrl -> {

                _noteDetailsState.update {
                    it.copy(imageUrl = action.imageUrl)
                }
            }

            is NoteDetailsEvent.UpdateTitle -> {
                _noteDetailsState.update {
                    it.copy(title = action.title)
                }
            }
        }
    }

    fun loadNoteDetails(id: Int) {
        viewModelScope.launch {
            val note = getNoteByIdUseCase(id)
            _noteDetailsState.update {
                it.copy(
                    id = note.id!!,
                    title = note.title,
                    description = note.description,
                    imageUrl = note.imageUrl
                )
            }
        }
    }

    private suspend fun saveUpdatedNote(
        id: Int,
        title: String,
        description: String,
        imageUrl: String
    ): Boolean {
        return saveUpdatedNoteUseCase(id, title, description, imageUrl)
    }
}