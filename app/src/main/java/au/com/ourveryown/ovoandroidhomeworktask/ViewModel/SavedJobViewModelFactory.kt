package au.com.ourveryown.ovoandroidhomeworktask.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class SavedJobViewModelFactory(application: Context) : ViewModelProvider.Factory{

    private lateinit var mApplication: Context

    init {
        mApplication = application
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SavedJobVM::class.java!!)) {
            SavedJobVM(this.mApplication) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}

