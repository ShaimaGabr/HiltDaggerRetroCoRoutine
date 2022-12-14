package com.example.hiltdaggerretrocoroutine.ui.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiltdaggerretrocoroutine.data.model.User
import com.example.hiltdaggerretrocoroutine.data.repositery.MainRepository
import com.example.hiltdaggerretrocoroutine.utiles.NetworkHelper
import com.example.hiltdaggerretrocoroutine.utiles.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
private val mainRepository: MainRepository,
private val networkHelper: NetworkHelper
):ViewModel() {
    private val _user=MutableLiveData<Resource<List<User>>>()
    val users:LiveData<Resource<List<User>>>
    get() = _user
    init {
        fetchUser()
    }
    private fun fetchUser(){

        viewModelScope.launch{
            _user.postValue(Resource.loading(null))
            if(networkHelper.isNetworkConnected()){
                mainRepository.getUsers().let {
                    if(it.isSuccessful){
                        _user.postValue(Resource.success(it.body()))
                    }else{
                       _user.postValue(Resource.error(it.errorBody().toString(),null))
                    }
                }
            }else _user.postValue(Resource.error("No internet",null))

        }

    }
}