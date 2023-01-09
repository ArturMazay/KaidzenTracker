package com.aistudio.kaidzentracker.android.ui.viewmodelsimport androidx.lifecycle.LiveDataimport androidx.lifecycle.MutableLiveDataimport androidx.lifecycle.ViewModelimport androidx.lifecycle.viewModelScopeimport com.aistudio.kaidzentracker.android.domain.mapping.Mappingimport com.aistudio.kaidzentracker.android.domain.mapping.MappingRepositoryimport com.aistudio.kaidzentracker.android.ui.viewmodels.models.ApplicationIntentimport com.aistudio.kaidzentracker.android.ui.viewmodels.models.IntentHandlerimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.launchclass MappingScreenViewModel(private val mappingRepository: MappingRepository) : ViewModel(),    IntentHandler<ApplicationIntent> {    private val _mappingScreenState: MutableLiveData<Mapping> =        MutableLiveData(Mapping())    val mappingScreenState: LiveData<Mapping> = _mappingScreenState    override fun getIntent(intent: ApplicationIntent) {        when (val curentViewState = _mappingScreenState.value) {            is Mapping -> intent(intent, curentViewState)        }    }    private fun intent(intent: ApplicationIntent, curentViewState: Mapping) {        when (intent) {            is ApplicationIntent.InitialScreen -> loadDataScreen()            is ApplicationIntent.ChangedFirstQuestions -> _mappingScreenState.postValue(                _mappingScreenState.value?.copy(firstQuestions = intent.newResult.toDouble()))            is ApplicationIntent.ChangedSecondQuestions -> _mappingScreenState.postValue(                _mappingScreenState.value?.copy(secondQuestions = intent.newResult.toDouble()))            is ApplicationIntent.ChangedThreeQuestions -> _mappingScreenState.postValue(                _mappingScreenState.value?.copy(threeQuestions = intent.newResult.toDouble()))            is ApplicationIntent.ChangedFourQuestions -> _mappingScreenState.postValue(                _mappingScreenState.value?.copy(fourQuestions = intent.newResult.toDouble()))            is ApplicationIntent.ChangedFiveQuestions -> _mappingScreenState.postValue(                _mappingScreenState.value?.copy(fiveQuestions = intent.newResult.toDouble()))            is ApplicationIntent.ChangedSixQuestions -> _mappingScreenState.postValue(                _mappingScreenState.value?.copy(sixQuestions = intent.newResult.toDouble()))            is ApplicationIntent.SaveClicked -> saveData(state = curentViewState)            is ApplicationIntent.DeletedClicked -> deleteAll()            else -> {}        }    }    private fun saveData(state: Mapping) {        viewModelScope.launch(Dispatchers.IO) {            val calc = (state.firstQuestions+                    state.secondQuestions +                    state.threeQuestions +                    state.fourQuestions +                    state.fiveQuestions +                    state.sixQuestions                    ) / 6.toDouble()            mappingRepository.save(mapping = Mapping(                firstQuestions = state.firstQuestions,                secondQuestions = state.secondQuestions,                threeQuestions = state.threeQuestions,                fourQuestions = state.fourQuestions,                fiveQuestions = state.fiveQuestions,                sixQuestions = state.sixQuestions,                result = calc ,                newResult = if(state.result == calc) calc else state.result))            loadDataScreen()        }    }    private fun loadDataScreen() {        viewModelScope.launch(Dispatchers.IO) {            _mappingScreenState.postValue(Mapping(isLoading = true))            with(mappingRepository.get()) {                _mappingScreenState.postValue(_mappingScreenState.value?.copy(                    isLoading = false,                    firstQuestions = this?.firstQuestions ?: 0.0,                    secondQuestions = this?.secondQuestions ?: 0.0,                    threeQuestions = this?.threeQuestions ?: 0.0,                    fourQuestions = this?.fourQuestions ?: 0.0,                    fiveQuestions = this?.fiveQuestions ?: 0.0,                    sixQuestions = this?.sixQuestions ?: 0.0,                    result = this?.result ?: 0.0,                    newResult = this?.newResult ?: 0.0))            }        }    }    private fun deleteAll() {        viewModelScope.launch(Dispatchers.IO) {            mappingRepository.delete()            loadDataScreen()        }    }}