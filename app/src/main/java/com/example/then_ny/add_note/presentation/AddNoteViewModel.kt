package com.example.then_ny.add_note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.then_ny.add_note.domain.usecase.SaveNoteUseCase
import com.example.then_ny.add_note.domain.usecase.SearchImagesUseCase
import com.example.then_ny.core.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val searchImagesUseCase: SearchImagesUseCase
) : ViewModel() {

    private val _addNoteState = MutableStateFlow(AddNoteState())
    val addNoteState = _addNoteState.asStateFlow()

    private val _noteSaveChannel = Channel<Boolean>()
    val noteSaveFlow = _noteSaveChannel.receiveAsFlow()

    fun onAction(action: AddNoteEvent) {
        when (action) {
            is AddNoteEvent.UpdateDescription -> {
                _addNoteState.update {
                    it.copy(description = action.description)
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

            AddNoteEvent.UpdateImageDialogVisibility -> {
                _addNoteState.update {
                    it.copy(isImageDialogShowing = !it.isImageDialogShowing)
                }
            }

            is AddNoteEvent.UpdateSearchImageQuery -> {
                _addNoteState.update {
                    it.copy(searchQuery = action.searchQuery)
                }
                searchImage(action.searchQuery)
            }

            is AddNoteEvent.PickImage -> {
                _addNoteState.update {
                    it.copy(imageUrl = action.imageUrl)
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

    private var searchJob: Job? = null
    fun searchImage(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            searchImagesUseCase.invoke(query).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _addNoteState.update {
                            it.copy(imageList = emptyList())
                        }
                    }

                    is Resource.Loading -> {
                        _addNoteState.update {
                            it.copy(isLoadingImages = result.isLoading)
                        }
                    }

                    is Resource.Success -> {
                        _addNoteState.update {
                            it.copy(imageList = result.data?.images ?: emptyList())
                        }
                    }
                }
            }
        }
    }
}