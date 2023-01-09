package com.aistudio.kaidzentracker.android.ui.viewmodelsimport android.util.Logimport androidx.lifecycle.LiveDataimport androidx.lifecycle.MutableLiveDataimport androidx.lifecycle.ViewModelimport androidx.lifecycle.viewModelScopeimport com.aistudio.kaidzentracker.android.domain.kdi.KDIimport com.aistudio.kaidzentracker.android.domain.kdi.KDIRepositoryimport com.aistudio.kaidzentracker.android.ui.viewmodels.models.ApplicationIntentimport com.aistudio.kaidzentracker.android.ui.viewmodels.models.IntentHandlerimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.launchclass KDIScreenViewModel(private val kdiRepository: KDIRepository) : ViewModel(),    IntentHandler<ApplicationIntent> {    private val _kdiScreenState: MutableLiveData<KDI> =        MutableLiveData(KDI())    val kdiScreenState: LiveData<KDI> = _kdiScreenState    override fun getIntent(intent: ApplicationIntent) {        when (val currentViewState = _kdiScreenState.value) {            is KDI -> intent(intent)        }    }    private fun intent(intent: ApplicationIntent) {        when (intent) {            is ApplicationIntent.InitialScreen -> loadDataKDIScreen()            else -> {}        }    }    private fun loadDataKDIScreen() {        viewModelScope.launch(Dispatchers.Main) {            _kdiScreenState.postValue(KDI(isLoading = true))            with(kdiRepository.getAllResult()) {                _kdiScreenState.postValue(_kdiScreenState.value?.copy(                    isLoading = false,                    listCombine = listCombine,                    listQuestions = listQuestions                ))                Log.i("TAG", listCombine.toString())            }        }    }}