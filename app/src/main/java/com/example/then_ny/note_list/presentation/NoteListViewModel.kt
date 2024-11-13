package com.example.then_ny.note_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.then_ny.note_list.domain.usecase.DeleteNoteUseCase
import com.example.then_ny.note_list.domain.usecase.DeleteNoteWithQueryUseCase
import com.example.then_ny.note_list.domain.usecase.GetNoteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNoteListUseCase: GetNoteListUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val deleteNoteWithQueryUseCase: DeleteNoteWithQueryUseCase
) : ViewModel() {

    private val _noteListState = MutableStateFlow(NoteListState())
    val noteListState = _noteListState.asStateFlow()

    fun onAction(action: NoteListEvent) {
        when (action) {
            NoteListEvent.ChangeNoteListOrder -> {
                _noteListState.value.orderByTitle = !_noteListState.value.orderByTitle
                loadNotes()
            }

            is NoteListEvent.DeleteNote -> {
                viewModelScope.launch {
                    deleteNoteUseCase(action.note)
                    loadNotes()
                }
            }

            is NoteListEvent.DeleteNoteWithQuery -> {
                viewModelScope.launch {
                    deleteNoteWithQueryUseCase(action.id)
                    loadNotes()
                }
            }
        }
    }

    fun loadNotes() {
        viewModelScope.launch {
            _noteListState.value = _noteListState.value.copy(noteList = getNoteListUseCase(orderByTitle = _noteListState.value.orderByTitle))
        }
    }
}