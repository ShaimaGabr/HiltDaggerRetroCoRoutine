package com.example.hiltdaggerretrocoroutine.utiles

data class Resource<out T>(val status:Statues,val data: T?,val message:String?){
companion object{
    fun <T> success(data:T?):Resource<T>{
        return Resource(Statues.SUCCESS,data,null)
    }
    fun <T> error(msg:String,data:T?):Resource<T>{
        return Resource(Statues.ERROR,data,msg)
    }
    fun <T> loading(data:T?):Resource<T>{
        return Resource(Statues.LOADING,data,null)
    }
}
}
