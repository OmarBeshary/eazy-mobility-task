package com.example.eazy_mobility_task.common.component.states

/**
 * This an interface to make view model not depend on the concrete implementation
 * */

interface IViewState<T> {

    fun whichState(): CommonStates

    fun fetchData(): T?

    fun fetchError(): String?

}


/**
 * This class will represent common states
 * if the app will need more states you have to create another enum/sealed class
 * that implement @see IViewState interface
 */

interface CommonStates

enum class CommonStatus : CommonStates {
    SUCCESS, LOADING, ERROR
}


/**
 * a data class that hold status and data
 * */

data class Result<T>(val status: CommonStates, val data: T?, val errorMessage: String?) :
    IViewState<T> {

    companion object {
        fun <T> success(data: T) = Result<T>(CommonStatus.SUCCESS, data, null)

        fun <T> success() = Result<T>(CommonStatus.SUCCESS, null, null)

        fun <T> error(message: String?) = Result<T>(CommonStatus.ERROR, null, message)

        fun <T> loading() = Result<T>(CommonStatus.LOADING, null, null)
    }

    override fun whichState(): CommonStates = status

    override fun fetchData(): T? = data

    override fun fetchError(): String? = errorMessage
}

